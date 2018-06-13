package com.example.skkj.comment;


import com.alibaba.fastjson.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;


/**
 * redis公共类
 * */
public class RedisUtils {
	
		//通过传入的key值查出对象
		public  String getString(String key){
			JedisRedisUtils jedisuti = new JedisRedisUtils();
				return jedisuti.getString(key);
		}
		//查询出对象
		public   <T> T getObejct(String key,Class<T> clazz){
			JedisRedisUtils jedisuti = new JedisRedisUtils();
			String objString = jedisuti.getString(key);
			if(objString != null && !"".equals(objString)){
				JSONObject jsonObject=JSONObject.fromObject(objString);
				T t = (T) JSONObject.toBean(jsonObject, clazz);
				return t;
			}else {
				return null;
			}
		}


	
		//存储当字符
		public  void setString(String key,String value){
			JedisRedisUtils jedisuti = new JedisRedisUtils();
				jedisuti.setString(key, value);
		}
		
		//存储对象
		public  void setObject(String key,Object obj){
			JedisRedisUtils jedisuti = new JedisRedisUtils();
			   JSONObject jsono = new JSONObject();
			   JSONObject objcta = jsono.fromObject(obj);
			jedisuti.setString(key, objcta.toString());
		}
		
		//通过key值删除缓存
		public  void delObject(String key){
			JedisRedisUtils jedisuti = new JedisRedisUtils();
			jedisuti.deletString(key);
		}
		
		//通过传入的key值查出对象集合
		public   <T> List<T> getList(String key,Class<T> clazz){
			JedisRedisUtils jedisuti = new JedisRedisUtils();
			String arrList = jedisuti.getString(key);
			if(arrList != null && !"".equals(arrList) && arrList != "PONG" && !"PONG".equals(arrList)){
				//json
				 List<T> ts = (List<T>) JSONArray.parseArray(arrList, clazz);
				return ts;
			}else {
				return null;
			}
		}
		//存数组
		public  void setList(String key,List value){
			JedisRedisUtils jedisuti = new JedisRedisUtils();
			String json = net.sf.json.JSONArray.fromObject(value).toString();
			jedisuti.setString(key, json);
		}

	public  void setStringDS(String key, int seconds, String value){
		JedisRedisUtils jedisuti = new JedisRedisUtils();
		jedisuti.setString(key,seconds,value);
	}


	//存定时数据
		public  void setObjectDS(String key, int seconds, Object object){
			JedisRedisUtils jedisuti = new JedisRedisUtils();
			JSONObject jsono = new JSONObject();
			JSONObject objcta = jsono.fromObject(object);
			jedisuti.setString(key,seconds,objcta.toString());
		}

		
}
