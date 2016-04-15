package com.yile.learning.redis.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class ShardedJedisPoolTest {
	private static final Logger logger = LogManager.getLogger(ShardedJedisPoolTest.class);
	private ShardedJedisPool shardedJedisPool;

	@Before
	public void before() {
		List<JedisShardInfo> shards = new ArrayList<>();
		JedisShardInfo jedisShardInfo = new JedisShardInfo("192.168.2.111", 6379);
		shards.add(jedisShardInfo);
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setMaxTotal(1);
		config.setBlockWhenExhausted(false);
		config.setTestOnBorrow(true);
		config.setTestWhileIdle(true);
		shardedJedisPool = new ShardedJedisPool(config, shards);
	}

	@After
	public void after() {
		shardedJedisPool.close();
	}

	@Test
	public void setValue() {
		ShardedJedis shardedJedis = getShardedJedis();
		String result = shardedJedis.set("redisexample", "shardedjedispool");
		logger.debug("result:" + result);
	}

	/**
	 * 获取分片{@link ShardedJedis}
	 * 
	 * @return
	 */
	public ShardedJedis getShardedJedis() {
		ShardedJedis shardedJedis = shardedJedisPool.getResource();
		return shardedJedis;
	}
}
