package com.example.skkj.comment;

import redis.clients.jedis.*;

import java.util.*;


/** 
 * Redis分布式调用API 
 * @author pengtian 
 * @Date 2016年11月17日18:10:52 
 */  
public class JedisRedisUtilsFenbu {  
      
      
      
    private static String RESULT_OK = "OK";  
    private static Set<HostAndPort> clusterNodes = null;  //对应的IP和端口
    private static JedisPoolConfig config = null;  
    private static JedisCluster jedisCluster=null;  
      
    public JedisRedisUtilsFenbu() {
    	super();
    }    
    private static JedisRedisUtilsFenbu jredisClient=null;    
    //静态工厂方法     
    public static synchronized  JedisRedisUtilsFenbu getInstance() {    
         if (jredisClient == null) {      
             jredisClient = new JedisRedisUtilsFenbu(); 
             jredisClient.clusterInit(); 
             
         } else{  
         }
         return jredisClient;    
    }    
      
   /** 
    * 获取操作redis的客户端 
    * @return 
    */  
    public JedisCluster getJedisCluster(){  
        if(jedisCluster==null){  
            JedisRedisUtilsFenbu.getInstance();  
        }  
        return jedisCluster;  
    }  
  
    /**
     * 配置Redis分布式节点 
     */  
    private void genClusterNode() {  
        clusterNodes = new HashSet<HostAndPort>();  
        clusterNodes.add(new HostAndPort("183.61.5.4", 7001));  
        clusterNodes.add(new HostAndPort("183.61.5.4", 7002));  
        clusterNodes.add(new HostAndPort("183.61.5.4", 7003));  
        clusterNodes.add(new HostAndPort("183.61.5.4", 7004));  
        clusterNodes.add(new HostAndPort("183.61.5.4", 7005));  
        clusterNodes.add(new HostAndPort("183.61.5.4", 7006));  
    }  
      
    /** 
     * 实列化JRedis 线程池相关配置 
     */  
    private void genJedisConfig() {  
         config = new JedisPoolConfig();  
         //pool 最大线程数  
         config.setMaxTotal(100000);  
         // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。  
         config.setMaxIdle(100);  
         //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；  
         config.setMaxWaitMillis(100000);  
         // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；  
         config.setTestOnBorrow(true);
//         config.setTestOnReturn(true);
    }  
      
    /** 
     * JRedis分布式初始化 
     */  
    private void clusterInit() {  
        if(jedisCluster == null){  
            /*  配置Redis分布式节点 **/  
            genClusterNode();  
            /* 实列化JRedis 线程池相关配置 **/  
            genJedisConfig();  
              
            jedisCluster = new JedisCluster(clusterNodes, 5000, config);  
        }  
    }  
      
      
    /** 
     * 字符串类型数据存储 
     * @param key 
     * @param value 
     * @return 
     */  
    public boolean  set(String key, String value)  
    {  
        return this.set(key,0,value);
    }  
      
    /** 
     * 字符串数据类型存储并设置有效期 
     * @param key 
     * @param value 
     * @return 
     */  
    public boolean   set(String key, int seconds,String value)  
    {  
        boolean isOk = false;  
        String returnResult = "";  
        if(seconds>0){  
            returnResult= jedisCluster.setex(key, seconds, value);  
        }else{  
            returnResult = jedisCluster.set(key, value);  
        }  
        if(RESULT_OK.equals(returnResult)){  
            isOk = true;  
        }  
        return isOk;  
    }  
      
    /** 
     * 获取存储的字符串类型数据 
     * @param key 
     * @return 
     */  
    public static String get(String key) {  
        return jedisCluster.get(key);  
    }  
      
    /** 
     * Map<String,String>类型数据存储 
     * @param key 
     * @param value 
     * @return 
     */  
    public boolean hmset(String key, Map<String,String> hashMap){  
        return this.hmset(key, 0, hashMap);
    }  
      
    /** 
     * Map<String,String>类型数据存储，并设置有效期 
     * @param key 
     * @param value 
     * @return 
     */  
    public boolean hmset(String key,int seconds, Map<String,String> hashMap){  
        boolean isOk = false;  
        String returnResult = "";  
        returnResult = jedisCluster.hmset(key, hashMap);  
        if(seconds>0){  
         this.expire(key, seconds);  
        }  
        if(RESULT_OK.equals(returnResult)){  
            isOk = true;  
        }  
        return isOk;  
    }  
    /** 
     * 获取Map中的指定KEY值 
     * @param key 
     * @param field 
     * @return String 
     */  
    public String  hget(String key,String field){  
        return jedisCluster.hget(key, field);  
    }  
      
    /** 
     * 获取指定key中Map 
     * @param key 
     * @return Map 
     */  
    public Map  hget(String key){  
        return jedisCluster.hgetAll(key);  
    }  
      
    /** 
     * 计数递增 
     * @param key 
     * @return 
     */  
    public long incr(String key){  
      
        return jedisCluster.incr(key);  
    }  
      
    /** 
     * 计数递减 
     * @param key 
     * @return 
     */  
    public long decr(String key){  
        return  jedisCluster.decr(key);  
    }  
      
      
      
    /** 
     * 设置KEY的有效期 
     * @param key 
     * @param seconds 
     * @return 
     */  
    public long expire(String key,int seconds){  
        return jedisCluster.expire(key, seconds);  
    }  
      
    /** 
     * 删除指定KEY 
     * @param key 
     * @return 
     */  
    public long del(String key){  
        return jedisCluster.del(key);  
    }  
      
    /** 
     * 存储List<String>数据 
     * @param key 
     * @param seconds 
     * @param list 
     */  
    public long list(String key,List<String> list){  
        return this.list(key, 0, list);  
          
    }  
      
    /** 
     * 存储List<String>数据并设置有效期 
     * @param key 
     * @param seconds 
     * @param list 
     */  
    public long list(String key,int seconds,List<String> list){  
        long returnResult = 0;  
        String[] strArray = null;  
        if(list!=null&&!list.isEmpty()){  
            strArray = new String[list.size()];  
            for(int i=0;i<list.size();i++){  
                strArray[i]=list.get(i).toString();  
            }  
        }  
        returnResult = jedisCluster.lpush(key,strArray);  
        if(seconds>0){  
             this.expire(key, seconds);  
        }  
        return returnResult;  
    }  
      
    /** 
     * 获取List<String> 
     * @param key 
     * @param start 
     * @param end 
     * @return 
     */  
    public List<String> lrange(String key,long start,long end){  
        return jedisCluster.lrange(key, start, end);  
    }  
      
    /** 
     * KEYS 获取String数据类型的值 
     * @param pattern 
     * @return 
     */  
    public TreeSet<String> keys(String pattern){    
        TreeSet<String> keys = new TreeSet<String>();    
        Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();    
        for(String k : clusterNodes.keySet()){    
            JedisPool jp = clusterNodes.get(k);    
            Jedis connection = jp.getResource();    
            try {    
                keys.addAll(connection.keys(pattern));    
            } catch(Exception e){    
                e.printStackTrace();  
            } finally{    
                connection.close();//用完一定要close这个链接！！！    
            }    
        }    
        return keys;    
    }    
  
      
      
   
  
}  