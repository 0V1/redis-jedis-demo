# redis-jedis-demo
此项目演示redis->jedis的各种使用
分为6个类，依次深入演示。
a_redisPing.java：测试ping 确保jedis联通，可以使用
b_redisAPI.java：演示5+1（五种数据类型+key）的常用api操作 
c_redisTX.java：演示redis的事务特性
d_redisMS.java：演示主从配置下的redis使用，可以延伸下为集群
e_redisPool.java：演示redis使用连接池，获取链接
f_redisPoolUtil.java：工厂模式（双端检索），获取线程池。
