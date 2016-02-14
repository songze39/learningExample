package com.yile.learning.cassandra.test;

import static com.yile.learning.cassandra.test.Constants.*;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import com.netflix.astyanax.AstyanaxContext;
import com.netflix.astyanax.Keyspace;
import com.netflix.astyanax.MutationBatch;
import com.netflix.astyanax.connectionpool.NodeDiscoveryType;
import com.netflix.astyanax.connectionpool.OperationResult;
import com.netflix.astyanax.connectionpool.exceptions.ConnectionException;
import com.netflix.astyanax.connectionpool.impl.ConnectionPoolConfigurationImpl;
import com.netflix.astyanax.connectionpool.impl.CountingConnectionPoolMonitor;
import com.netflix.astyanax.impl.AstyanaxConfigurationImpl;
import com.netflix.astyanax.model.Column;
import com.netflix.astyanax.model.ColumnFamily;
import com.netflix.astyanax.model.ColumnList;
import com.netflix.astyanax.serializers.IntegerSerializer;
import com.netflix.astyanax.serializers.StringSerializer;
import com.netflix.astyanax.thrift.ThriftFamilyFactory;
import com.yile.learning.cassandra.AbstractCassandraCase;

public class AstyanaxTest extends AbstractCassandraCase {
	private static final Logger logger = LogManager.getLogger(AstyanaxTest.class);
	private AstyanaxContext<Keyspace> context;
	private Keyspace keyspace;
	private ColumnFamily<Integer, String> EMP_CF;

	@Override
	public void before() throws RuntimeException {
		context = new AstyanaxContext.Builder().forCluster("Test Cluster").forKeyspace(DYN_KEYSPACE)
				.withAstyanaxConfiguration(
						new AstyanaxConfigurationImpl().setDiscoveryType(NodeDiscoveryType.RING_DESCRIBE))
				.withConnectionPoolConfiguration(new ConnectionPoolConfigurationImpl("MyConnectionPool").setPort(9160)
						.setMaxConnsPerHost(1).setSeeds("192.168.2.113:9160"))
				.withAstyanaxConfiguration(
						new AstyanaxConfigurationImpl().setCqlVersion("3.0.0").setTargetCassandraVersion("3.3"))
				.withConnectionPoolMonitor(new CountingConnectionPoolMonitor())
				.buildKeyspace(ThriftFamilyFactory.getInstance());
		context.start();
		keyspace = context.getEntity();
		EMP_CF = ColumnFamily.newColumnFamily(DYN_CF, IntegerSerializer.get(), StringSerializer.get());
	}

	@Override
	public void after() throws RuntimeException {
		context.shutdown();
	}

	public void insert(int empId, int deptId, String firstName, String lastName) {
		MutationBatch m = keyspace.prepareMutationBatch();

		m.withRow(EMP_CF, empId).putColumn(COL_NAME_EMPID, empId, null).putColumn(COL_NAME_DEPTID, deptId, null)
				.putColumn(COL_NAME_FIRST_NAME, firstName, null).putColumn(COL_NAME_LAST_NAME, lastName, null);
		try {
			@SuppressWarnings("unused")
			OperationResult<Void> result = m.execute();
		} catch (ConnectionException e) {
			logger.error("failed to write data to C*", e);
			throw new RuntimeException("failed to write data to C*", e);
		}
		logger.debug("insert ok");
	}

	public void read(int empId) {
		OperationResult<ColumnList<String>> result;
		try {
			result = keyspace.prepareQuery(EMP_CF).getKey(empId).execute();

			ColumnList<String> cols = result.getResult();
			logger.debug("read: isEmpty: " + cols.isEmpty());

			// process data

			// a) iterate over columsn
			logger.debug("emp");
			for (Iterator<Column<String>> i = cols.iterator(); i.hasNext();) {
				Column<String> c = i.next();
				Object v = null;
				if (c.getName().endsWith("id")) // type induction hack
					v = c.getIntegerValue();
				else
					v = c.getStringValue();
				logger.debug("- col: '" + c.getName() + "': " + v);
			}
			// b) get columns by name
			logger.debug("emp");
			logger.debug("- emp id: " + cols.getIntegerValue(COL_NAME_EMPID, null));
			logger.debug("- dept: " + cols.getIntegerValue(COL_NAME_DEPTID, null));
			logger.debug("- firstName: " + cols.getStringValue(COL_NAME_FIRST_NAME, null));
			logger.debug("- lastName: " + cols.getStringValue(COL_NAME_LAST_NAME, null));

		} catch (ConnectionException e) {
			logger.error("failed to read from C*", e);
			throw new RuntimeException("failed to read from C*", e);
		}
	}

	@Test
	public void testMain() {
//		insert(222, 333, "Eric", "Cartman");
		read(222);
	}

}
