package com.example.skkj.service;


import com.alibaba.fastjson.JSONObject;
import com.example.skkj.entity.User;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserService {
	//登录判断
	@Transactional
	String findByUserName(User user, HttpServletRequest request)throws Exception;

	@Transactional
	User findByUserNameApp(User user)throws Exception;

	//查询用户信息
	@Transactional
	void selectUser(HttpServletRequest request)throws Exception;
	@Transactional
	String  deletByuserId(String userId)throws Exception;

	//添加
	@Transactional
	String  insert(User user, HttpServletRequest request)throws Exception;
	@Transactional
	String selectByUserName(String userName)throws Exception;

	//修改用户
	@Transactional
	String updateByUserId(User user, HttpServletRequest request)throws Exception;
	@Transactional
	//通过Id查询出对象
	User selectByUserId(String userId)throws Exception;
	@Transactional
	//通过管理员等级来查询出能授权的哦用户
	void selectByType(HttpServletRequest request)throws Exception;
	@Transactional
	//通过管理员等级来查询出能授权的哦用户
	void selectByTypeApp(HttpServletRequest request)throws Exception;
	@Transactional
	//app添加用户
	String  appInsert(User user, String type, String userId)throws Exception;
	@Transactional
	//app通过管理员等级来查询出能授权的哦用户
	Map<String,Object> appSelectByType(HttpServletRequest request)throws Exception;
	@Transactional
	//app查看用户
	Map<String,Object> appSelectUser(JSONObject jsonObject)throws Exception;
	@Transactional
	//app授权
	String appUpdateByUserId(User user, String passwordls)throws Exception;
	@Transactional
	String appUpdateByUserIdRegis(User user, String substationIds)throws Exception;
 }
