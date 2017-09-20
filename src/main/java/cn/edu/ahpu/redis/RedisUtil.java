package cn.edu.ahpu.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: jhs
 * @Desc:
 * @Date: Create in 2017/9/15  9:14
 */
public class RedisUtil {
	// Redis服务器IP
	private static String ADDR = "127.0.0.1";
	
	// Redis的端口号
	private static int PORT = 6379;
	
	// 访问密码
	private static String AUTH = "123456";
	
	// 可用连接实例的最大数目，默认值为8；
	// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	private static int MAX_ACTIVE = 1024;
	
	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 200;
	
	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	private static int MAX_WAIT = 10000;
	
	private static int TIMEOUT = 10000;
	
	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = true;
	
	private static JedisPool jedisPool = null;
	
	/**
	 * 初始化Redis连接池
	 */
	static {
		try {
			// 在高版本的jedis
			// jar包，比如2.8.2，我们在使用中发现使用JedisPoolConfig时，没有setMaxActive和setMaxWait属性了，这是因为高版本中官方废弃了此方法，用以下两个属性替换。
			// maxActive ==> maxTotal
			// maxWait ==> maxWaitMillis
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(MAX_ACTIVE);
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWaitMillis(MAX_WAIT);
			config.setTestOnBorrow(TEST_ON_BORROW);
			jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取Jedis实例
	 *
	 * @return
	 */
	public synchronized static Jedis getJedis() {
		try {
			if (jedisPool != null) {
				Jedis resource = jedisPool.getResource();
				resource.select(7);
				return resource;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 释放jedis资源
	 *
	 * @param jedis
	 */
	public static void returnResource(final Jedis jedis) {
		if (jedis != null) {
			jedisPool.returnResource(jedis);
		}
	}
	
	public static void set(String key, String value) {
		set(key, value, -1);
	}
	
	/**
	 * @param key
	 * @param value
	 * @param timeout -1:表示一直有效，-2:表示不存在key,非负表示有效秒数;
	 */
	public static void set(String key, String value, int timeout) {
		Jedis jedis = RedisUtil.getJedis();
		jedis.set(key, value);
		jedis.expire(key, timeout);
		
		returnResource(jedis);
	}
	
	public static String get(String key) {
		Jedis jedis = RedisUtil.getJedis();
		String value = jedis.get(key);
		returnResource(jedis);
		return value;
	}
	
	
	public static void main(String[] args) {
		Jedis jedis = RedisUtil.getJedis();
		System.out.println(jedis.get("aa"));
	}
}