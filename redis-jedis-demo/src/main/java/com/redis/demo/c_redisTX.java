package com.redis.demo;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
/**
 * @ClassName: redisPing
 * @Description: redis 事务演示
 * @author qinf QQ:908247035
 * @date 2017年1月6日
 * @version V1.0
 */
public class c_redisTX {
	static Jedis jedis = new Jedis("127.0.0.1",6379);

	public static void main(String[] args) {
		System.out.println(jedis.ping());
		jedis.set("a", "100");
		jedis.set("b", "0");
		jedis.set("d", "test");
		try {
			transMethod();
		} catch (InterruptedException e) {
			System.out.println("事务失败！");
			e.printStackTrace();
		}
	}
	
	/**
	 * 通俗点讲，watch命令就是标记一个键，如果标记了一个键， 
	 * 在提交事务前如果该键被别人修改过，那事务就会失败，这种情况通常可以在程序中
	 * 重新再尝试一次。
	 * 
	 * 首先标记了键balance，然后检查余额是否足够，不足就取消标记，并不做扣减； 
	 * 足够的话，就启动事务进行更新操作，
	 * 如果在此期间键balance被其它人修改， 那在提交事务（执行exec）时就会报错， 
	 * 程序中通常可以捕获这类错误再重新执行一次，直到成功。
	 */
	public static void transMethod()throws InterruptedException{
		int a = Integer.parseInt(jedis.get("a"));
		int b = Integer.parseInt(jedis.get("b"));
		//用于测试事务失败 是否对其他有影响
		String d = jedis.get("d");
		int c = 30;
		//开启监控
		jedis.watch("a");
		
//		jedis.set("a", "90");
		if(a>c){
			//开启事务
			Transaction tx = jedis.multi();
			
			//命令入队
			a = a-c;
			tx.decrBy("a", c);
			b = b+c;
			tx.incrBy("b", c);
			//这里是测试一个事务中如果有失败的事务会怎么样  字符串+1会报错
			tx.incr("d");
			/**
			 * 执行事务 
			 * 执行后查看re的内容
			 * 事务执行完成后会返回每条命令的执行反馈 
			 * 1.[OK,... OK]都是ok或者正常的返回值[70,30]则表示事务都成功
			 * 2.[70, 30, redis.clients.jedis.exceptions.JedisDataException: ERR value is not an integer or out of range]
			 * 这样的结果表示事务队列中有3个命令，前两个成功，最后一个失败，但是事务整体还是算成功的
			 * 所以redis的事务跟关系型事务还是有区别的。
			 */
			
			List<Object> re = tx.exec();
			
			System.out.println(re);
			System.out.println("a---->"+jedis.get("a"));
			System.out.println("b---->"+jedis.get("b"));

			//如果re=null，则表示事务失败
			if(null == re){
				//再次回调  直到成功
				transMethod();
			}
		}else{
			//取消监控
			jedis.unwatch();
			System.out.println("事务失败，a<c");
		}
	}
}
