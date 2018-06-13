package com.example.skkj.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.skkj.comment.RedisUtils;
import com.example.skkj.entity.Region;
import com.example.skkj.mapper.RegionMapper;
import com.example.skkj.service.RegionServer;

import java.util.List;

@Service("regionServer")
public class RegionServerImpl implements RegionServer{
	@Autowired
	private RegionMapper regionMapper;
	/**
	 * 省查询
	 * */
	@Override
	public List<Region> findeByRegionIdSS(String regionId) throws Exception {
		RedisUtils redisUtils = new RedisUtils();
		String regis = "regionss";
		@SuppressWarnings("unchecked")
		List<Region> region = redisUtils.getList(regis,Region.class);
		if(region != null && region.size()>0){
			return region;
		}else {
			region = regionMapper.findeByRegionIdSS();
			redisUtils.setList(regis, region);
			return region;
		}
		
	}
	/**
	 * 市查询 通过省的ID
	 * */
	@Override
	public List<Region> findeByRegionIdS(String regionId) throws Exception {
		RedisUtils redisUtils = new RedisUtils();
		String regis = "regions"+regionId;
		List<Region> region = redisUtils.getList(regis,Region.class);
		if(region != null && region.size()>0 ){
			return region;
		}else {
			region = regionMapper.findeByRegionIdS(regionId);
//			RedisUtils.setList(regis, region);
			redisUtils.setList(regis, region);
			return region;
		}
	}

	/**
	 * 县查询 通过市的Id
	 * */
	@Override
	public List<Region> findeByRegionIdX(String regionId) throws Exception {
		String regis = "regions"+regionId;
		RedisUtils redisUtils = new RedisUtils();
		List<Region> region = redisUtils.getList(regis,Region.class);
		if(region != null && region.size()>0){
			return region;
		}else {
			region = regionMapper.findeByRegionIdX(regionId);
			redisUtils.setList(regis, region);
			return region;
		}
	}
	@Override
	public String findeByRegionId(String regionId) throws Exception {
		return regionMapper.findeByRegionId(regionId);
	}

}
