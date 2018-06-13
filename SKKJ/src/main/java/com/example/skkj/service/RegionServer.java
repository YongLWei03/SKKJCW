package com.example.skkj.service;

import com.example.skkj.entity.Region;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RegionServer {
	//查询出省
    @Transactional
    List<Region> findeByRegionIdSS(String regionId)throws Exception;
    
	//通过省查询出市
    @Transactional
    List<Region> findeByRegionIdS(String regionId)throws Exception;
    
	//通过市查询出县
    @Transactional
    List<Region> findeByRegionIdX(String regionId)throws Exception;
    //通过名字Id查询地区名字的信息\
    @Transactional
    String findeByRegionId(String regionId)throws Exception;
}
