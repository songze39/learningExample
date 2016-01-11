package com.yile.learning.redis.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class JedisTest {
	private Jedis jedis = null;

	@Before
	public void before() {
		jedis = new Jedis("192.168.134.134", 6379);
	}

	@After
	public void after() {
		jedis.flushAll();
		jedis.flushDB();
		jedis.close();
	}

	// @Test
	public void testSet() {
		jedis.set("user:1", "liangjy");
		System.out.println("getValue:" + jedis.get("user:1"));
		long value = jedis.del("user:1");
		System.out.println("value:" + value);
	}

	@Test
	public void testMset() {
		String[] keyValue = new String[] { "test1", "value1", "test2", "value2" };
		jedis.mset(keyValue);
		System.out.println("getValue:" + jedis.get("test2"));
	}
}
