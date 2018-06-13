package com.example.skkj.comment;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.locks.ReentrantLock;

/** 
 * Redis 工具类 
 * 连接池
 */  
public class JedisRedisUtils {  
  
    protected static ReentrantLock lockPool = new ReentrantLock();  
    protected static ReentrantLock lockJedis = new ReentrantLock();  
  
    protected static Logger logger = Logger.getLogger(JedisRedisUtils.class);
  
    //Redis服务器IP  
    private static String ADDR_ARRAY = "183.61.5.4";
  
    //Redis的端口号  
    private static int PORT = 6379;  
    //Redis服务器IP
    private static String ADDR_ARRAY_TWO = "183.61.5.4";

    //Redis的端口号
    private static int PORT_TWO = 6379;
  
    //Redis的端口号  
    private static String CHARSET = "UTF-8"; 
    
    //访问密码  
//    private static String AUTH = "http://blog.csdn.net/unix21";  
    //可用连接实例的最大数目，默认值为8；  
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。  
    private static int MAX_ACTIVE = 1000;
  
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。  
    private static int MAX_IDLE = 100;
  
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；  
    private static int MAX_WAIT = 10000;

    //超时时间
    private static int TIMEOUT = 200000;
  
    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；  
    private static boolean TEST_ON_BORROW = true;
    //在return一个jedis实例时，是否提前进行validate操作.
    private static boolean TEST_ON_RETURN = true;
  
    private static JedisPool jedisPool = null;  
  
    /** 
     * redis过期时间,以秒为单位 
     */  
//    public final static int EXRP_HOUR = 60 * 60;            //一小时  
//    public final static int EXRP_DAY = 60 * 60 * 24;        //一天  
//    public final static int EXRP_MONTH = 60 * 60 * 24 * 30; //一个月  

    /**
     * 初始化Redis连接池
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, ADDR_ARRAY, PORT,TIMEOUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Jedis实例
     * @return
     */
    public  synchronized Jedis getJedis() {
        Jedis resource = null;
        try {
            if (jedisPool != null) {
                resource = jedisPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            returnBrokenResource(resource);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 释放jedis资源
     * @param jedis
     */
    public  synchronized void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }

    // 出现异常释放资源
    public synchronized  void returnBrokenResource(Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnBrokenResource(jedis);
        }
    }

    /** 
     * 设置 String 
     * 
     * @param key 
     * @param value 
     */  
    public synchronized  void setString(String key, String value) {
        Jedis redis = getJedis();
        try {  
            value = StringUtils.isEmpty(value) ? "" : value;
              redis.set(key, value);
        } catch (Exception e) {  
            logger.error("Set key error : " + e);  
        }finally {
            returnResource(redis);
        }
    }  
  
    /** 
     * 设置 过期时间 
     * 
     * @param key 
     * @param seconds 以秒为单位 
     * @param value 
     */  
    public synchronized  void setString(String key, int seconds, String value) {
        Jedis redis = getJedis();
        try {
            value = StringUtils.isEmpty(value) ? "" : value;
            redis.setex(key, seconds, value);
        } catch (Exception e) {  
            logger.error("Set keyex error : " + e);  
        } finally {
            returnResource(redis);
        }
    }  
  
    /** 
     * 获取String值 
     * 
     * @param key 
     * @return value 
     */  
    public synchronized  String getString(String key) {
        Jedis jedis = getJedis();
    	  String jdesis = jedis.get(key);
        returnResource(jedis);
        return jdesis;  
    }  
    
    /** 
     * 删除String值
     * @param key
     * @return value
     */
    public synchronized  Long deletString(String key) {
        Jedis jedis = getJedis();
        Long a = jedis.del(key);
        returnResource(jedis);
		return a;
    }

    public static void main(String[] args) {
//        System.out.println(RedisUtils.getList("tempListUDP6", Temperature.class));
    }
    
}  