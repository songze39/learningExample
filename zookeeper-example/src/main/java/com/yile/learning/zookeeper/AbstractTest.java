package com.yile.learning.zookeeper;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;

public abstract class AbstractTest {
	private Logger logger = LogManager.getLogger(AbstractTest.class);
	protected ZooKeeper zookeeper;
	protected String connectString = "192.168.134.136:2181";
	protected int sessionTimeout = 50000;

	@Before
	public void before() {
		try {
			// 创建一个Zookeeper实例，第一个参数为目标服务器地址和端口，第二个参数为Session超时时间，第三个为节点变化时的回调方法
			zookeeper = new ZooKeeper(connectString, sessionTimeout, new TestWatcher());
		} catch (IOException e) {
			logger.error("before fail:", e);
			throw new RuntimeException(e);
		}
	}

	@After
	public void after() {
		try {
			zookeeper.close();
		} catch (InterruptedException e) {
			logger.error("before fail:", e);
			throw new RuntimeException(e);
		}
	}
}
