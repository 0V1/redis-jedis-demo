package com.redis.demo;

import redis.clients.jedis.Jedis;

/**
 * @ClassName: redisPing
 * @Description: redis 连接池演示
 * @author qinf QQ:908247035
 * @date 2017年1月6日
 * @version V1.0
 */
public class e_redisPool {
	static Jedis jedis = new Jedis("127.0.0.1",6379);

	public static void main(String[] args) {
		System.out.println(jedis.ping());
	}
	
}
