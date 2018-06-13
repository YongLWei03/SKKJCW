package com.example.skkj.mapper;


import com.example.skkj.entity.UserRegion;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 管理区域添加
 * */
@Mapper
@Component(value = "userRegionMapper")
public interface UserRegionMapper {
	
	//查询变电站所属站点
	List<String> selectByUserId(String userId)throws Exception;
	
	//添加变电站的信息
	int insert(List<UserRegion> userRegions)throws Exception;
	
	//删除所属站点
	int deletByUserId(String userId)throws Exception;
	
	//用户权限查询拥有哪些权限
	List<UserRegion> findUserRegion()throws Exception;
	
	//通过变电站删除
	int deletBysubsId(String substationId)throws Exception;
	
	//通过变电站I的查询出用户ID
	List<String> selectBysubsId(String substationId)throws Exception;
}
