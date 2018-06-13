package com.example.skkj.mapper;


import com.example.skkj.entity.Substation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 变电站管理操作
 * */
@Mapper
@Component(value = "substationMapper")
public interface SubstationMapper {
	//添加变电站
	int insert(Substation substation)throws Exception;
	
	//根据权限查看自己所拥有的权限
	int selectSubstationByTypeCount(Map<String, Object> map)throws Exception;
	
	//根据权限查看自己所拥有的权限
	List<Substation> selectSubstationByType(Map<String, Object> map)throws Exception;
	
	int selectSubstationCount(Map<String, Object> map)throws Exception;
	
	List<Substation> selectSubstation(Map<String, Object> map)throws Exception;
	
	//根据列表的所属地区查询
	List<Substation> selectSubstationByDq(Map<String, Object> map)throws Exception;
	
	//修改变电站
	int updateBySubstationId(Substation substation)throws Exception;
	
	//非超级管理员所拥有权限
	List<Substation> selectByqx(Map<String, Object> map)throws Exception;
	
	//通过ID查询出变电站信息
	Substation selectBySubstationId(String substationId)throws Exception;
	
	//用过权限查询出所有的对象
	List<Substation> findByType(String userId)throws Exception;
	
	//超级管理员查询
	List<Substation> findByTypeCj()throws Exception;
	
	//删除变电站
	int deleteSubstation(String substationId)throws Exception;

	//根据变电站拼音查出是否存在单前变电站
	String selectBySubstationPY(String substationPY)throws Exception;

	//通过变电站的名称查询变电站的ID
	String selectBySubstationName(String substationName)throws Exception;

	//根据所在的市对坐标进行查看
	Substation findByArer(String substationArea)throws Exception;

	//app不是超级管理员
	List<Substation> appSelectSubstationByType(Map<String, Object> map)throws Exception;


	//查询出所有变电站
	List<Substation> selectByAll()throws Exception;
}
