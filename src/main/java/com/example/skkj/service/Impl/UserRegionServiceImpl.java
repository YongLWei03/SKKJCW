package com.example.skkj.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.skkj.entity.UserRegion;
import com.example.skkj.mapper.UserRegionMapper;
import com.example.skkj.service.UserRegionService;

import java.util.List;

@Service("userRegionService")
public class UserRegionServiceImpl implements UserRegionService{

	@Autowired
	private UserRegionMapper userRegionMapper;
	
	/**
	 * 查询当前用户所拥有的站点权限
	 * */
	@Override
	public List<String> selectByUserId(String userId) throws Exception {
		return userRegionMapper.selectByUserId(userId);
	}

	//用户权限查询拥有哪些权限
	@Override
	public List<UserRegion> findUserRegion() throws Exception {
		System.out.println("server："+userRegionMapper.findUserRegion());
		return userRegionMapper.findUserRegion();
	}
	
}
