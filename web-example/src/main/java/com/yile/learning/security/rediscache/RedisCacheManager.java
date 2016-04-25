package com.yile.learning.security.rediscache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 缓存管理实现{@link com.rabbitframework.security.cache.CacheManager}
 */
public class RedisCacheManager implements CacheManager {
	private static final Logger logger = LoggerFactory
			.getLogger(RedisCacheManager.class);
	// fast lookup by name map
	private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();
	private RedisManager redisManager;
	private String keyPrefix = "security_redis_cache:";

	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		logger.debug("获取名称为: " + name + " 的RedisCache实例");
		Cache c = caches.get(name);
		if (c == null) {
			redisManager.init();
			// create a new rediscache instance
			c = new RedisCache<K, V>(redisManager, keyPrefix);
			// add it to the rediscache collection
			caches.put(name, c);
		}
		return c;
	}

	public RedisManager getRedisManager() {
		return redisManager;
	}

	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}

	public String getKeyPrefix() {
		return keyPrefix;
	}

	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}
}
