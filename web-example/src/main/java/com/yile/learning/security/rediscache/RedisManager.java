package com.yile.learning.security.rediscache;

import java.util.Set;

public interface RedisManager {

    /**
     * 初始化方法
     */
    public void init();

    /**
     * get value from redis
     *
     * @param key
     * @return
     */
    public byte[] get(byte[] key);

    /**
     * set
     *
     * @param key
     * @param value
     * @return
     */
    public byte[] set(byte[] key, byte[] value);

    /**
     * set
     *
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public byte[] set(byte[] key, byte[] value, int expire);

    /**
     * del
     *
     * @param key
     */
    public void del(byte[] key);

    /**
     * flush
     */
    public void flushDB();

    /**
     * size
     */
    public Long dbSize();
    /**
     * keys
     *
     * @param pattern
     * @return
     */
    public Set<byte[]> keys(String pattern);

    public void setExpire(int expire);

    public int getExpire();

}
