package com.example.skkj.service;

import com.alibaba.fastjson.JSONObject;
import com.example.skkj.entity.AlarmInformation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface AlarmInformationServer {
	//添加报警信息
	@Transactional
	String insert(String deviceNumber, String serialNumber, String order, AlarmInformation alarmInformation)throws Exception;
	
	//修改报警状态
	@Transactional
	String updateByafId(HttpServletRequest request)throws Exception;
	
	//查看警报信息
	@Transactional
	List<AlarmInformation> selectAlarmInformation(HttpServletRequest request)throws Exception;
	
	//根据报警ID删除信息
	@Transactional
	String deleteByAfIdList(List<String> afId)throws Exception;
	
	//根据报警ID删除信息
	@Transactional
	String deleteByAfId(HttpServletRequest request)throws Exception;
	
	/**
	 * 查看当前登录用户是否存在报警信息
	 * @params userId 用户Id
	 * @params type 报警状态     2 报警
	 * @params state 是否处理  2 为处理
	 * */
	@Transactional
	String selectByType(HttpServletRequest request)throws Exception;
	
	/**
	 * 修改报警状态温度
	 * */
	@Transactional
	void UpdateByType(AlarmInformation alarmInformation)throws Exception;

	//APP查看警报信息
	@Transactional
	Map<String,Object> appSelectAlarmInformation(JSONObject jsonObject)throws Exception;

}
