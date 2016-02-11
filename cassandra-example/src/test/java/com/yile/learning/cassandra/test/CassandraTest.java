package com.yile.learning.cassandra.test;

import java.nio.ByteBuffer;
import java.util.List;

import org.apache.cassandra.thrift.Cassandra;
import org.apache.cassandra.thrift.CfDef;
import org.apache.cassandra.thrift.Compression;
import org.apache.cassandra.thrift.ConsistencyLevel;
import org.apache.cassandra.thrift.CqlResult;
import org.apache.cassandra.thrift.InvalidRequestException;
import org.apache.cassandra.thrift.KsDef;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.Test;

import com.yile.learning.cassandra.AbstractCassandraCase;

public class CassandraTest extends AbstractCassandraCase {
	private static final Logger logger = LogManager.getLogger(CassandraTest.class);
	private Cassandra.Client client;
	private TTransport transport;

	// @Test
	public void getKeyspaces() throws Exception {
		List<KsDef> ksDefs = client.describe_keyspaces();
		for (KsDef ksDef : ksDefs) {
			logger.info("keyspaces:" + ksDef.getName());
		}
	}

	@Override
	public void before() {
		transport = new TFramedTransport(new TSocket("192.168.2.113", 9160));
		TProtocol proto = new TBinaryProtocol(transport);
		client = new Cassandra.Client(proto);
		try {
			transport.open();
			if (!transport.isOpen()) {
				throw new RuntimeException("connect failed");
			}
		} catch (TTransportException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void after() {
		transport.close();
	}
}
