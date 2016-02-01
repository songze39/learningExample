package com.yile.learning.zookeeper.test.curator.framework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import com.yile.learning.zookeeper.test.curator.CuratorAbstractTest;

/**
 * 增删改查
 *
 */
public class CrudTest extends CuratorAbstractTest {
	private Logger logger = LogManager.getLogger(CrudTest.class);
	private static final String PATH = "/crud";

	@Test
	public void crud() {
		try {
			curatorFramework.create().forPath(PATH, "create root node".getBytes());
			byte[] bs = curatorFramework.getData().forPath(PATH);
			logger.info("get root node data:" + new String(bs));
			curatorFramework.setData().forPath(PATH, "update root node".getBytes());
			// 由于是在background模式下获取的data，此时的bs可能为null
			byte[] bs2 = curatorFramework.getData().watched().inBackground().forPath(PATH);
			logger.info("修改后的data为" + new String(bs2 != null ? bs2 : new byte[0]));
			curatorFramework.delete().forPath(PATH);
			Stat stat = curatorFramework.checkExists().forPath(PATH);
			// Stat就是对zonde所有属性的一个映射， stat=null表示节点不存在！
			logger.info("stat info:" + stat);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}