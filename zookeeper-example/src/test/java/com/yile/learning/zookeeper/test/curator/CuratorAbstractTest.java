package com.yile.learning.zookeeper.test.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.CloseableUtils;
import org.junit.After;
import org.junit.Before;

public abstract class CuratorAbstractTest {
	protected CuratorFramework curatorFramework;

	@Before
	public void before() {
		curatorFramework = CuratorClientFactory.newClient();
		curatorFramework.start();
	}

	@After
	public void after() {
		CloseableUtils.closeQuietly(curatorFramework);
	}
}
