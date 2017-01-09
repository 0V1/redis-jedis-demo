package com.redis.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName: redisPing
 * @Description: redis 连接池工具 工厂模式（双端检索模式）
 * @author qinf QQ:908247035
 * @date 2017年1月6日
 * @version V1.0
 */
public class f_redisPoolUtil {
	
	private static volatile JedisPool jedisPool = null;

	
	public f_redisPoolUtil(){
		
	}


	/**
	 * 获取一个连接池链接
	 * @return
	 */
	public static JedisPool getJedisPool() {
		if(null == jedisPool){
			synchronized (f_redisPoolUtil.class) {
				if(null == jedisPool){
					JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
					jedisPoolConfig.setMaxWaitMillis(100*1000);
					jedisPoolConfig.setMaxIdle(32);
					jedisPoolConfig.setTestOnBorrow(true);
					
					jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);
					
				}
			}
		}
		return jedisPool;
	}
	
	/**
	 * jedis使用完后放回连接池
	 * @param jedisPool
	 * @param jedis
	 */
	public static void release(JedisPool jedisPool,Jedis jedis){
		if(null != jedis){
			jedisPool.returnResourceObject(jedis);
		}
	}
}
