package com.redis.demo;

import redis.clients.jedis.Jedis;

/**
 * @ClassName: redisPing
 * @Description: redis 主从配置演示
 * @author qinf QQ:908247035
 * @date 2017年1月6日
 * @version V1.0
 */
public class d_redisMS {
	static Jedis jedis_M = new Jedis("127.0.0.1",6379);
	static Jedis jedis_S = new Jedis("127.0.0.1",6380);

	public static void main(String[] args) {
//		System.out.println(jedis.ping());
		
		//主从配置，配从不配主
		jedis_S.slaveof("127.0.0.1",6379);
		
		jedis_M.set("master","master");
		
		String result = jedis_S.get("master");
		System.out.println(result);
	}
	
}
