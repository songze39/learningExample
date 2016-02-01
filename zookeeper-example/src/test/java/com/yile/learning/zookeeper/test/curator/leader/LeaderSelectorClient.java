package com.yile.learning.zookeeper.test.curator.leader;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 本类基于leaderSelector实现,所有存活的client会公平的轮流做leader
 * 如果不想频繁的变化Leader，需要在takeLeadership方法里阻塞leader的变更！ 或者使用
 * {@link} LeaderLatchClient
 */
public class LeaderSelectorClient extends LeaderSelectorListenerAdapter implements Closeable {
	private static final Logger logger = LogManager.getLogger(LeaderSelectorClient.class);
	private final String name;
	private final LeaderSelector leaderSelector;
	private final String PATH = "/leaderselector";

	public LeaderSelectorClient(CuratorFramework client, String name) {
		this.name = name;
		leaderSelector = new LeaderSelector(client, PATH, this);
		leaderSelector.autoRequeue();
	}

	public void start() throws IOException {
		leaderSelector.start();
	}

	@Override
	public void close() throws IOException {
		leaderSelector.close();
	}

	public boolean isLeader() {
		return leaderSelector.hasLeadership();
	}

	public void release() {
		leaderSelector.interruptLeadership();
	}

	public void take() {
		leaderSelector.requeue();
	}

	/**
	 * client成为leader后，会调用此方法
	 */
	@Override
	public void takeLeadership(CuratorFramework client) throws Exception {

		int waitSeconds = (int) (5 * Math.random()) + 1;
		logger.info(name + "是当前的leader");
		try {
			Thread.sleep(TimeUnit.SECONDS.toMillis(waitSeconds));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} finally {
			logger.info(name + " 让出领导权\n");
		}
	}
}