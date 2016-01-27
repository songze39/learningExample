package com.yile.learning.redis.test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.SystemPropertyUtils;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisClusterKey {
	private JedisCluster jedisCluster;

	@Before
	public void before() {
		Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
		jedisClusterNode.add(new HostAndPort("192.168.134.135", 7002));
		jedisClusterNode.add(new HostAndPort("192.168.134.135", 7003));
		jedisClusterNode.add(new HostAndPort("192.168.134.135", 7007));
		jedisCluster = new JedisCluster(jedisClusterNode, 60 * 1000, 10000);
	}

	@After
	public void after() {
		try {
			jedisCluster.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void del() {
		jedisCluster.set("name", "liangjy");
		String getName = jedisCluster.get("name");
		System.out.println("getName:" + getName);
		Long l = jedisCluster.del("phone");
		System.out.println("del return:" + l);
		l = jedisCluster.del("name");
		System.out.println("del return:" + l);
	}

	/**
	 * 集群中不支持keys命令
	 */
	private void keys() {
		jedisCluster.set("name", "liangjy");
	}

	private void type() {
		String type = jedisCluster.type("name");
		System.out.println("type:" + type);
	}

	private void expire() {
		jedisCluster.set("cache_page", "www.google.com");
		Long expreReturn = jedisCluster.expire("cache_page", 30);
		System.out.println("expreReturn:" + expreReturn);
	}

	@Test
	public void testMain() {
		// del();
		// type();
		// expire();
	}

}
