package com.yile.learning.zookeeper.test.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorClientFactory {
	public static CuratorFramework newClient() {
		String connectionString = "192.168.134.136:2181,192.168.134.136:2182,192.168.134.136:2183";
		ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
		return CuratorFrameworkFactory.newClient(connectionString, retryPolicy);
	}
}
