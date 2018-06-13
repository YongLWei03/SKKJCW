package com.example.skkj.mapper;


import com.example.skkj.entity.Region;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component(value = "regionMapper")
public interface RegionMapper {
	//查询出省
    List<Region> findeByRegionIdSS()throws Exception;
    
	//通过省查询出市
    List<Region> findeByRegionIdS(String regionId)throws Exception;
    
	//通过市查询出县
    List<Region> findeByRegionIdX(String regionId)throws Exception;
    
    //通过名字Id查询地区名字的信息
    String findeByRegionId(String regionId)throws Exception;
}
