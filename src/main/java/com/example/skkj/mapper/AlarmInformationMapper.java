package com.example.skkj.mapper;

import com.example.skkj.entity.AlarmInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component(value = "alarmInformationMapper")
public interface AlarmInformationMapper {
	
	//添加报警信息
	int insert(AlarmInformation alarmInformation)throws Exception;
	
	//修改报警状态
	int updateByafId(AlarmInformation alarmInformation)throws Exception;
	
	
	//查看警报信息 这是负责人能查看到的信息
	List<AlarmInformation> selectAlarmInformation(Map<String, Object> map)throws Exception;
	int selectAlarmInformationCount(Map<String, Object> map)throws Exception;
	
	//根据变电站Id删除
	int deleteBySubstationId(String substationId)throws Exception;

	int deleteByEqId(String substationId)throws Exception;

	//根据报警ID批量删除信息
	int deleteByAfIdList(List<String> afId)throws Exception;
	
	//根据报警ID删除信息
	int deleteByAfId(String afId)throws Exception;
	
	//根据对应的开关柜编号删除
	int deleteByEquipmentxi(String equipmentxi)throws Exception;
	
	//查看所有报警信息
	List<AlarmInformation> selectByStateType(@Param("state") String state, @Param("type") String type)throws Exception;

	//查看是否存在当前问题
	AlarmInformation selectByAlarmInformation(AlarmInformation alarmInformation)throws Exception;

	//查看是否还存在其他异常
	int findType(@Param("type") String type, @Param("eqId") String eqId, @Param("state") String state)throws Exception;

	/**
	 * 查看当前登录用户是否存在报警信息
	 * @param userId 用户Id
	 * @param type 报警状态     2 报警
	 * @param state 是否处理  2 为处理
	 * */
	int selectByType(@Param("userId") String userId, @Param("type") String type, @Param("state") String state)throws Exception;

	/**
	 * 查询当前开光的报警信息
	 * @parameqId 开光柜ID
	 * @paramtype 异常状态
	 * */
		int selectByYC(@Param("eqId") String eqId, @Param("type") String type, @Param("state") String state)throws Exception;

	//通过ID查看当前变电站状态的正常个数
	int selectTypeCountBysubId(@Param("substationId") String substationId, @Param("type") String type)throws Exception;

	//根据ID查询出对应的报错信息的信息
	List<AlarmInformation> selectByAfId(List<String> afId)throws Exception;

}
