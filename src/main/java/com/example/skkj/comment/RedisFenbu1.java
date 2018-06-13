package com.example.skkj.comment;


import com.alibaba.fastjson.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;


/**
 * redis公共类
 * */
public class RedisFenbu1 {
	
		//通过传入的key值查出对象
		public static String getString(String key){
			if(key != null && !"".equals(key)){
				return JedisRedisUtilsFenbu.getInstance().get(key);
			}else {
				return null;
			}
		}
		//查询出对象
				public  static <T> T getObejct(String key,Class<T> clazz){
					   String objString = JedisRedisUtilsFenbu.getInstance().get(key);
					   if(objString != null && !"".equals(objString)){
						   net.sf.json.JSONObject jsonObject= net.sf.json.JSONObject.fromObject(objString);
						   T t = (T) net.sf.json.JSONObject.toBean(jsonObject, clazz);
						   return t;
					   }else {
						   return null;
					}
				}
	
		//存储当字符
		public static void setString(String key,String value){
			if(value != null && !"".equals(value)){
				JedisRedisUtilsFenbu.getInstance().set(key, value);
			}
		}
		
		//存储对象
		public static void setObject(String key,Object obj){
			   JSONObject objcta = JSONObject.fromObject(obj);
			   JedisRedisUtilsFenbu.getInstance().set(key, objcta.toString());
		}
		
		//通过key值删除缓存
		public static void delObject(String key){
			JedisRedisUtilsFenbu.getInstance().del(key);
		}
		
		//通过传入的key值查出对象集合
		public  static <T> List<T> getList(String key,Class<T> clazz){
			String arrList = JedisRedisUtilsFenbu.getInstance().get(key);
			if(arrList != null && !"".equals(arrList) && arrList != "PONG" && !"PONG".equals(arrList)){
				//json
				 List<T> ts = (List<T>) JSONArray.parseArray(arrList, clazz);
				return ts;
			}else {
				return null;
			}
		}
		//存数组
		public static void setList(String key,List value){
			String json = net.sf.json.JSONArray.fromObject(value).toString();
			JedisRedisUtilsFenbu.getInstance().set(key, json);
		}
		
}
