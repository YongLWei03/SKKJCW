package com.example.skkj.mapper;


import com.example.skkj.entity.Temperature;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 实时温度管理
 * */
@Mapper
@Component(value = "temperatureMapper")
public interface TemperatureMapper {
		//根据设备号查询当前是否存在温度
	 int selectBydeviceNumber(String deviceNumberXm)throws Exception;
	 
	  //根据设备号名称根据设备编号删除
	 int deleteBydeviceNumber(String deviceNumber)throws Exception;
	 
	 //删除设备更据设备名称删除
	 int deleteBydeviceNumberXm(String deviceNumberXm)throws Exception;
	 //批量添加数据
	 int insert(List<Temperature> temperature)throws Exception;
	 
	 //修改温度
	 int updateByDeviceNumber(List<Temperature> temperature)throws Exception;
	 
	 //修改柜体编号
	 int updateByDeviceNumberBh(Temperature temperature)throws Exception;
	 
	 //查看历史数据
	 List<Temperature> selectByTime(Map<String, Object> map)throws Exception;
	 
	 //定时查看当前时间的历史数据
	 List<Temperature> selectByDinshiTime(String time)throws Exception;
	 
	 //根据时间删除数据删除
	 int deletByTime(String time)throws Exception;
	 
	 //添加单个设备的温度
	 int insertOne(Temperature temperature)throws Exception;

	 //添加设备获取的温度信息
	int insertTemu(Temperature temperature)throws Exception;

	//修改记录的数据
	int updateTemu(Temperature temperature)throws Exception;

	//根据设备号进行查询
	Temperature selectByTemu(String deviceNumber)throws Exception;

}
