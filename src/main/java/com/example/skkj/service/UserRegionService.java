package com.example.skkj.service;

import com.example.skkj.entity.UserRegion;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserRegionService {
	//查询变电站所属站点
	@Transactional
		List<String> selectByUserId(String userId)throws Exception;
		
		//用户权限查询拥有哪些权限
		@Transactional
		List<UserRegion> findUserRegion()throws Exception;
}
