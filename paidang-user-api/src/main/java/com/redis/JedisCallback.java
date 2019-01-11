package com.redis;

import redis.clients.jedis.Jedis;

/**
 * 
 * 定义redis回调接口
 * 
 */
public interface JedisCallback {
    /**
     * 回调函数，做实际处理
     * 
     * @param jedis redis连接
     * @return 返回处理结果
     */
    Object doInRedis(Jedis jedis);
}
