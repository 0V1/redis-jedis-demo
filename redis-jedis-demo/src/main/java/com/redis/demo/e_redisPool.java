package com.redis.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @ClassName: redisPing
 * @Description: redis 连接池演示
 * @author qinf QQ:908247035
 * @date 2017年1月6日
 * @version V1.0
 */
public class e_redisPool {

	public static void main(String[] args) {
		JedisPool jedisPool = f_redisPoolUtil.getJedisPool();
		JedisPool jedisPool2 = f_redisPoolUtil.getJedisPool();
		System.out.println(jedisPool == jedisPool2);
		
		Jedis jedis = null;
		
		try {
			jedis = jedisPool.getResource();
			jedis.set("pool", "1");
			System.out.println(jedis.get("pool"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			f_redisPoolUtil.release(jedisPool, jedis);
		}
		System.out.println(jedis.ping());
	}
	
}
