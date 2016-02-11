package com.yile.learning.cassandra.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.yile.learning.cassandra.AbstractCassandraCase;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.ddl.KeyspaceDefinition;
import me.prettyprint.hector.api.factory.HFactory;

public class HectorTest extends AbstractCassandraCase {
	private static final Logger logger = LogManager.getLogger(HectorTest.class);
	private static StringSerializer stringSerializer = StringSerializer.get();
	private Cluster cluster;

	@Override
	public void before() throws RuntimeException {
		cluster = HFactory.getOrCreateCluster("Test Cluster", "192.168.2.113:9160");
	}

	@Override
	public void after() throws RuntimeException {

	}

	@Test
	public void getKeyspaces() {
		String clusterName = cluster.describeClusterName();
		logger.info("clusterName:" + clusterName);
		List<KeyspaceDefinition> keyspaces = cluster.describeKeyspaces();
		for (KeyspaceDefinition keyspaceDefinition : keyspaces) {
			String name = keyspaceDefinition.getName();
			logger.info("name:" + name);
		}
	}
	
	

}
