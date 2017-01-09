package com.redis.demo;

import redis.clients.jedis.Jedis;

/**
 * @ClassName: redisPing
 * @Description: redis 链接测试
 * @author qinf QQ:908247035
 * @date 2017年1月6日
 * @version V1.0
 */
public class a_redisPing {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1",6379);
		System.out.println(jedis.ping());
		
		jedis.set("a", "30");
	}
	
}
