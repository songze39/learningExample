package com.yile.learning.zookeeper.test.curator.leader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.CloseableUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.yile.learning.zookeeper.test.curator.CuratorClientFactory;

/**
 * leader选举
 * 
 */
public class LeaderSelectorTest {
	private static final Logger logger = LogManager.getLogger(LeaderSelectorTest.class);

	@Test
	public void leaderSelector() {

		List<CuratorFramework> clients = Lists.newArrayList();
		List<LeaderSelectorClient> examples = Lists.newArrayList();
		try {
			for (int i = 0; i < 10; i++) {
				CuratorFramework client = CuratorClientFactory.newClient();
				LeaderSelectorClient example = new LeaderSelectorClient(client, "Client #" + i);
				clients.add(client);
				examples.add(example);

				client.start();
				example.start();
			}

			Thread.sleep(10000);

			logger.info("----------关闭前5个客户端，再观察选举leader的结果-----------");
			for (int i = 0; i < 5; i++) {
				clients.get(i).close();
			}

			new BufferedReader(new InputStreamReader(System.in)).readLine();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			for (LeaderSelectorClient exampleClient : examples) {
				CloseableUtils.closeQuietly(exampleClient);
			}
			for (CuratorFramework client : clients) {
				CloseableUtils.closeQuietly(client);
			}
		}
	}
}
