package com.yile.learning.security.rediscache;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.*;

import java.util.Set;

public class ShardedJedisPoolManager implements RedisManager {
    // 0 - never expire
    private int expire = 0;
    @Autowired
    private ShardedJedisPool shardedJedisPool;

    public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
        this.shardedJedisPool = shardedJedisPool;
    }

    @Override
    public void init() {

    }

    /**
     * get value from redis
     *
     * @param key
     * @return
     */
    @Override
    public byte[] get(byte[] key) {
        byte[] value = null;
        ShardedJedis shardedJedis = getShardedJedis();
        try {
            value = shardedJedis.get(key);
        } catch (Throwable e) {
            shardedJedisPool.returnBrokenResource(shardedJedis);
            throw new RuntimeException(e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return value;
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public byte[] set(byte[] key, byte[] value) {
        set(key, value, expire);
        return value;
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @param expire
     * @return
     */
    @Override
    public byte[] set(byte[] key, byte[] value, int expire) {
        ShardedJedis shardedJedis = getShardedJedis();
        try {
            shardedJedis.set(key, value);
            if (expire != 0) {
                shardedJedis.expire(key, expire);
            }
        } catch (Throwable e) {
            shardedJedisPool.returnBrokenResource(shardedJedis);
            throw new RuntimeException(e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
        return value;
    }

    /**
     * del
     *
     * @param key
     */
    @Override
    public void del(byte[] key) {
        ShardedJedis shardedJedis = getShardedJedis();
        try {
            shardedJedis.del(key);
        } catch (Throwable e) {
            shardedJedisPool.returnBrokenResource(shardedJedis);
            throw new RuntimeException(e);
        } finally {
            shardedJedisPool.returnResource(shardedJedis);
        }
    }

    /**
     * flush
     */
    @Override
    public void flushDB() {

    }

    /**
     * size
     */
    @Override
    public Long dbSize() {
        Long dbSize = 0L;
        return dbSize;
    }

    /**
     * keys
     *
     * @param pattern
     * @return
     */
    @Override
    public Set<byte[]> keys(String pattern) {
        Set<byte[]> keys = null;
        return keys;
    }

    public int getExpire() {
        return expire;
    }

    @Override
    public void setExpire(int expire) {
        this.expire = expire;
    }

    /**
     * 获取分片{@link ShardedJedis}
     *
     * @return
     */
    private ShardedJedis getShardedJedis() {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        return shardedJedis;
    }
}
