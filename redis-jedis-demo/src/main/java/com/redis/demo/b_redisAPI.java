package com.redis.demo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * @ClassName: redisPing
 * @Description: redis 常用API演示
 * 5+1（String+List+Set+ZSet+Hash）+keys
 * @author qinf QQ:908247035
 * @date 2017年1月6日
 * @version V1.0
 */
public class b_redisAPI {
	
	static Jedis jedis = new Jedis("127.0.0.1",6379);
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		
		System.out.println(jedis.ping());
		
		//keys
		Set<String> keys = jedis.keys("*");
		for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			System.out.println(key);
		}
		
		redis_String();
		redis_List();
		redis_Set();
		redis_ZSet();
		redis_Hash();
	}
	
	/**
	 * String 字符串类型的数据结构
	 */
	public static void redis_String(){
		System.out.println("----------------->");

		jedis.set("s1", "v1");
		System.out.println(jedis.get("s1"));
		
		jedis.append("s1", "v2");
		System.out.println(jedis.get("s1"));
		
		jedis.mset("s2","v2","s3","v3","s4","v4");
		System.out.println(jedis.mget("s1","s2","s3","s4"));
		//数字
		jedis.set("s5", 1+"");
		
		jedis.incr("s5");
		System.out.println(jedis.get("s5"));
		jedis.incrBy("s5", 3);
		System.out.println(jedis.get("s5"));
		jedis.decr("s5");
		System.out.println(jedis.get("s5"));
		jedis.decrBy("s5", 3);
		System.out.println(jedis.get("s5"));
		
		System.out.println(jedis.ttl("s5"));
		System.out.println("<-----------------");
	}
	
	/**
	 * List 链表类型的数据结构
	 */
	public static void redis_List(){
		System.out.println("----------------->");
		jedis.lpush("list_l", "l1","l2","l3","l4","l5","l6","l7","l8");
		System.out.println(jedis.lrange("list_l", 0, -1));
		
		jedis.rpush("list_r", "l1","l2","l3","l4","l5","l6","l7","l8");
		System.out.println(jedis.lrange("list_r", 0, -1));
		
		System.out.println(jedis.lpop("list_1"));
		System.out.println(jedis.rpop("list_1"));
		
		System.out.println("<-----------------");
	}
	
	/**
	 * Set 无序集合类型的数据结构
	 */
	public static void redis_Set(){
		System.out.println("----------------->");
		jedis.sadd("set","s1","s2","s3","s4","s5","s6");
		System.out.println(jedis.smembers("set"));
		
		jedis.srem("set", "s6");
		System.out.println(jedis.smembers("set"));
		System.out.println("<-----------------");
		
	}

	/**
	 * ZSet 有序集合类型的数据结构
	 */
	public static void redis_ZSet(){
		System.out.println("----------------->");
		jedis.zadd("zset",1d,"z1");
		jedis.zadd("zset",2d,"z2");
		jedis.zadd("zset",3d,"z3");
		System.out.println(jedis.zcard("zset"));
		System.out.println(jedis.zrange("zset", 0, -1));
		
		System.out.println("<-----------------");
	}

	/**
	 * Hash 哈希类型的数据结构
	 */
	public static void redis_Hash(){
		System.out.println("----------------->");
		jedis.hset("hset", "user", "zhangsan");
		jedis.hset("hset", "age", "zhangsan");
		jedis.hset("hset", "user", "zhangsan");
		jedis.hset("hset", "user", "zhangsan");
		System.out.println(jedis.hget("hset", "age"));
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("m1", "v1");
		map.put("m2", "v2");
		map.put("m3", "v3");
		map.put("m4", "v4");
		map.put("m5", "v5");
		jedis.hmset("hset2", map);
		System.out.println(jedis.hmget("hset2", "m2"));
		
		jedis.hdel("hset2", "m5");
		System.out.println(jedis.hmget("hset2", "m5"));
		System.out.println("<-----------------");
	}
}
