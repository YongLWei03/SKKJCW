package com.example.skkj.comment;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import com.example.skkj.entity.User;

import java.util.UUID;

/*
 * shiro的加密
*/
public class ShiroMD5 {
	/**
	 * shiro的加密
	 * */
    public static User md5jm(User user){
    	String saltStr = UUID.randomUUID().toString();   //生成盐值
		ByteSource salt = ByteSource.Util.bytes(saltStr);
		int hashIterations = 1024;
		String pwd = new SimpleHash("MD5",user.getPassword(),salt,hashIterations).toString();
		user.setPassword(pwd);
		user.setSalt(saltStr);
    	return user;
    }
    
    /**
     * 获取验证加密
     * **/
    public static String md5yzjm(String pass,String salta){
		ByteSource salt = ByteSource.Util.bytes(salta);
		int hashIterations = 1024;
		String pwd = new SimpleHash("MD5",pass,salt,hashIterations).toString();
    	return pwd;
    }

	public static void main(String[] args) {
		User user = new User();
		user.setPassword("456");
		User as = ShiroMD5.md5jm(user);
		System.out.println("a:"+as);
	}

}
