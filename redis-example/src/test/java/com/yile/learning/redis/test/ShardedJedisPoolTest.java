package com.yile.learning.redis.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.After;
import org.junit.Before;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class ShardedJedisPoolTest {
	private ShardedJedisPool shardedJedisPool;

	@Before
	public void before() {
		List<JedisShardInfo> shards = new ArrayList<>();
		JedisShardInfo jedisShardInfo = new JedisShardInfo("192.168.134.135", 7002);
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
