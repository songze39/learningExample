package com.yile.learning.cassandra.test;
import static com.yile.learning.cassandra.test.Constants.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.yile.learning.cassandra.AbstractCassandraCase;

import me.prettyprint.cassandra.model.BasicColumnDefinition;
import me.prettyprint.cassandra.model.BasicColumnFamilyDefinition;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.cassandra.service.ThriftCfDef;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.beans.ColumnSlice;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.beans.OrderedRows;
import me.prettyprint.hector.api.beans.Row;
import me.prettyprint.hector.api.ddl.ColumnFamilyDefinition;
import me.prettyprint.hector.api.ddl.ColumnIndexType;
import me.prettyprint.hector.api.ddl.ComparatorType;
import me.prettyprint.hector.api.ddl.KeyspaceDefinition;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.Mutator;
import me.prettyprint.hector.api.query.ColumnQuery;
import me.prettyprint.hector.api.query.QueryResult;
import me.prettyprint.hector.api.query.RangeSlicesQuery;

public class HectorTest extends AbstractCassandraCase {
	private static final Logger logger = LogManager.getLogger(HectorTest.class);
	// private static final String CF_SUPER = "SuperCf";
	private static StringSerializer stringSerializer = StringSerializer.get();
	private Cluster cluster;

	@Override
	public void before() throws RuntimeException {
		cluster = HFactory.getOrCreateCluster("Test Cluster", "192.168.2.113:9160");
	}

	@Override
	public void after() throws RuntimeException {
		cluster.getConnectionManager().shutdown();
	}

	public void getKeyspaces() {
		String clusterName = cluster.describeClusterName();
		logger.info("clusterName:" + clusterName);
		List<KeyspaceDefinition> keyspaces = cluster.describeKeyspaces();
		for (KeyspaceDefinition kd : keyspaces) {
			if (kd.getName().equals(DYN_KEYSPACE)) {
				logger.info("Name: " + kd.getName());
				logger.info("RF: " + kd.getReplicationFactor());
				logger.info("strategy class: " + kd.getStrategyClass());
				logger.info("---------cfDefs--------------");
				List<ColumnFamilyDefinition> cfDefs = kd.getCfDefs();
				for (ColumnFamilyDefinition def : cfDefs) {
					logger.info("  CF Type: " + def.getColumnType());
					logger.info("  CF Name: " + def.getName());
					logger.info("  CF Metadata: " + def.getColumnMetadata());
				}
			}
		}
	}

	public void createSchema() {
		if (cluster.describeKeyspace(DYN_KEYSPACE) != null) {
			cluster.dropKeyspace(DYN_KEYSPACE);
		}
		BasicColumnDefinition columnDefinition = new BasicColumnDefinition();
		columnDefinition.setName(stringSerializer.toByteBuffer("username"));
		columnDefinition.setIndexName("username_idx");
		columnDefinition.setIndexType(ColumnIndexType.KEYS);
		columnDefinition.setValidationClass(ComparatorType.UTF8TYPE.getClassName());
		BasicColumnFamilyDefinition familyDefinition = new BasicColumnFamilyDefinition();
		familyDefinition.setKeyspaceName(DYN_KEYSPACE);
		familyDefinition.setName(DYN_CF);
		familyDefinition.addColumnDefinition(columnDefinition);
		ColumnFamilyDefinition cfDefStandard = new ThriftCfDef(familyDefinition);
		KeyspaceDefinition keyspaceDefinition = HFactory.createKeyspaceDefinition(DYN_KEYSPACE,
				"org.apache.cassandra.locator.SimpleStrategy", 1, Arrays.asList(cfDefStandard));
		cluster.addKeyspace(keyspaceDefinition);
	}

	public void insertData() {
		Keyspace keyspaceOperator = HFactory.createKeyspace(DYN_KEYSPACE, cluster);

		Mutator<String> mutator = HFactory.createMutator(keyspaceOperator, stringSerializer);
		mutator.addInsertion("username", DYN_CF, HFactory.createStringColumn("username1", "leungjy"));
		mutator.addInsertion("username", DYN_CF, HFactory.createStringColumn("username2", "leungjy"));
		mutator.execute();
	}

	public void query() {
		Keyspace keyspaceOperator = HFactory.createKeyspace(DYN_KEYSPACE, cluster);
		RangeSlicesQuery<String, String, String> rangeSlicesQuery = HFactory.createRangeSlicesQuery(keyspaceOperator,
				stringSerializer, stringSerializer, stringSerializer);
		rangeSlicesQuery.setColumnFamily(DYN_CF);
		rangeSlicesQuery.setKeys("", "");
		rangeSlicesQuery.setRange("", "", false, 3);

		rangeSlicesQuery.setRowCount(10);
		QueryResult<OrderedRows<String, String, String>> result = rangeSlicesQuery.execute();

		OrderedRows<String, String, String> orderRows = result.get();
		Iterator<Row<String, String, String>> it = orderRows.iterator();
		while (it.hasNext()) {
			Row<String, String, String> row = it.next();
			String key = row.getKey();
			logger.info("key:" + key);
			ColumnSlice<String, String> columnSlice = row.getColumnSlice();
			List<HColumn<String, String>> lists = columnSlice.getColumns();
			for (HColumn<String, String> hColumn : lists) {
				logger.info("columnName:" + hColumn.getName() + ",columnValue:" + hColumn.getValue());
			}
		}

		ColumnQuery<String, String, String> columnQuery = HFactory.createStringColumnQuery(keyspaceOperator);
		columnQuery.setColumnFamily(DYN_CF).setKey("username").setName("username1");
		QueryResult<HColumn<String, String>> colResult = columnQuery.execute();
		logger.info("Execution time: " + colResult.getExecutionTimeMicro());
		logger.info("CassandraHost used: " + colResult.getHostUsed());
		logger.info("Query Execute: " + colResult.get());
	}

	@Test
	public void testMain() {
		// createSchema();
		// getKeyspaces();
//		 insertData();
		query();
	}
}
