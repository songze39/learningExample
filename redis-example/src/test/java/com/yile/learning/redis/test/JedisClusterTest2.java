package com.yile.learning.redis.test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class JedisClusterTest2 {
	@Test
	public void testSingle() throws IOException {
		Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
		jedisClusterNode.add(new HostAndPort("192.168.134.134", 7000));
		final JedisCluster jc = new JedisCluster(jedisClusterNode);
		jc.set("foo", "bar");
		jc.close();
	}
}
