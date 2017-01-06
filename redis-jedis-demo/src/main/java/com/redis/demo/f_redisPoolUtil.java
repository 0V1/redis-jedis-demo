package com.redis.demo;

import redis.clients.jedis.Jedis;

/**
 * @ClassName: redisPing
 * @Description: redis 连接池工具 工厂模式（双端检索模式）
 * @author qinf QQ:908247035
 * @date 2017年1月6日
 * @version V1.0
 */
public class f_redisPoolUtil {
	static Jedis jedis = new Jedis("127.0.0.1",6379);

	public static void main(String[] args) {
		System.out.println(jedis.ping());
	}
	
}
