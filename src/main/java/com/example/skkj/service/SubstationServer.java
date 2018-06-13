package com.example.skkj.service;

import com.alibaba.fastjson.JSONObject;
import com.example.skkj.entity.Substation;
import com.example.skkj.entity.User;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface SubstationServer {
	//根据列表的所属地区查询
	@Transactional
		void selectSubstationByDq(HttpServletRequest request)throws Exception;
	@Transactional
	   Map<String, Object> selectSubstationByDqMap(HttpServletRequest request)throws Exception;
	@Transactional
	 //根据权限查看自己所拥有的权限
		void selectSubstationByType(HttpServletRequest request)throws Exception;
	@Transactional
		//添加变电站
		String insert(Substation substation, HttpServletRequest request)throws Exception;
	@Transactional
		//通过ID查询出变电站信息
		Substation selectBySubstationId(String substationId)throws Exception;
	@Transactional
		//修改变电站
		String updateBySubstationId(Substation substation, HttpServletRequest request)throws Exception;
	@Transactional
		//用过权限查询出所有的对象
		Map<String, Object> findByType(HttpServletRequest request)throws Exception;

	@Transactional
		//删除变电站
		String deleteSubstation(String substationId, HttpServletRequest request)throws Exception;
	@Transactional
		//根据变电站拼音查出是否存在单前变电站
		String selectBySubstationPY(String substationPY)throws Exception;
	@Transactional
	//超级管理员查询
	List<Substation> findByTypeCj()throws Exception;
	@Transactional
	//根据所在的市对坐标进行查看
	Substation findByArer(HttpServletRequest request)throws Exception;
	@Transactional
	//app根据用户查看信息
	 Map<String,Object> APPselectSubstationByType(HttpServletRequest request)throws Exception;

	 //app修改变电站信息
	 @Transactional
	 String appUpdateBySubstationId(Substation substation, String userId)throws Exception;
	@Transactional
	//APP添加变电站
	String appInsert(Substation substation, String userId)throws Exception;
	@Transactional
	//APP根据列表的所属地区查询
    List<Substation> appSelectSubstationByDq(User userId)throws Exception;
	@Transactional
	Map<String, Object> appSelectSubstationByDqMap(JSONObject jsonObject)throws Exception;
	@Transactional
	String appDeleteSubstation(String substationId)throws Exception;
	@Transactional
	//查询出所有变电站
	List<Substation> selectByAll()throws Exception;

}
