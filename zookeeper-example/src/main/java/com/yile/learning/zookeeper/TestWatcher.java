package com.yile.learning.zookeeper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class TestWatcher implements Watcher {
	private Logger logger = LogManager.getLogger(TestWatcher.class);

	@Override
	public void process(WatchedEvent event) {
		logger.info("event.getPath():" + event.getPath());
		logger.info("event.getState():" + event.getState());
		logger.info("event.getType():" + event.getType());

		logger.info("event.getWrapper().getPath():" + event.getWrapper().getPath());
		logger.info("event.getWrapper().getState():" + event.getWrapper().getState());
		logger.info("event.getWrapper().getType():" + event.getWrapper().getType());
	}

}
