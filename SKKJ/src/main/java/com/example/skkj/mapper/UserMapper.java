package com.example.skkj.mapper;


import com.example.skkj.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 用户操作
 * */
@Mapper
@Component(value = "userMapper")
public interface UserMapper {
	//登录判断
	User findByUserName(String userName)throws Exception;
	
	//删除对象
	int  deletByuserId(String userId)throws Exception; 
	
	//添加
	int  insert(User user)throws Exception;
	
	//查询出用户列表的总数
	int selectUserCount(Map<String, Object> map)throws Exception;
	
	//查询出用户列表的
	List<User> selectUser(Map<String, Object> map)throws Exception;
	
	//修改用户
	int updateByUserId(User user)throws Exception;
	
	//通过Id查询出对象
	User selectByUserId(String userId)throws Exception;
	
	//通过管理员等级来查询出能授权的哦用户
	List<User> selectByType(Map<String, Object> map)throws Exception;
	
	int selectByTypeCount(Map<String, Object> map)throws Exception;
	
	//通过管理员或者超级管理员授权等级来查询APP用户授权
	List<User> selectByTypeApp(Map<String, Object> map)throws Exception;
	
	int selectByTypeCountApp(Map<String, Object> map)throws Exception;
	
	//通过管理员等级来查询出能授权的哦用户的Id
	List<String> findeUserIdByType(Map<String, Object> map)throws Exception;
	
	//通过用户查询所属部门
	List<String> selectDepartmentByUser()throws Exception;
	
	//超级管理员查看授权人
	List<User> selectByTypeG(Map<String, Object> map)throws Exception;
	int selectByTypeGCount(Map<String, Object> map)throws Exception;
}
