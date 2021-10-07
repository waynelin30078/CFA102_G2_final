package com.chat.jedis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
	
	
	private static JedisPool pool = null;
	private JedisPoolUtil() {
	}		//private的建構子, 其他類別不能直接new, 一定要用下面的getJedisPool方法

	public static JedisPool getJedisPool() {
		if (pool == null) {
			synchronized (JedisPoolUtil.class) {
				if (pool == null) {    //第一個人設定就好了
					JedisPoolConfig config = new JedisPoolConfig();
					config.setMaxTotal(150);  //設定連線數量
					config.setMaxIdle(8);
					config.setMaxWaitMillis(10000);
					pool = new JedisPool(config, "localhost", 6379);
				}
			}
		}
		return pool;
	}

	// 可在ServletContextListener的ContextDestroyed呼叫方法註銷redis連線池
	public static void shutdownJedisPool() {
		if (pool != null)
			pool.destroy();
	}
}
