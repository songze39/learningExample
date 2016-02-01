package com.yile.learning.zookeeper.test.original;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.junit.Test;
import com.yile.learning.zookeeper.AbstractTest;

public class ZookeeperTest extends AbstractTest {
	private static final Logger logger = LogManager.getLogger(ZookeeperTest.class);

	// @Test
	public void createRootNode() {
		try {
			zookeeper.create("/root", "rootData".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			List<String> childrenName = zookeeper.getChildren("/root", true);
			if (childrenName != null) {
				logger.info("childrenName.size():" + childrenName.size());
			}
			byte[] b = zookeeper.getData("/root", true, null);
			logger.info("getData:" + new String(b));
		} catch (KeeperException | InterruptedException e) {

		}
	}

	@Test
	public void createRootChildren() {
		try {
			zookeeper.create("/root/childone", "childone".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		} catch (KeeperException | InterruptedException e) {
			logger.error("createRootChildren fail:", e);
		}
	}

}
