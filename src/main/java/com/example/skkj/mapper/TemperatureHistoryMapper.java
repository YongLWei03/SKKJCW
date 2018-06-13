package com.example.skkj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 变电站管理操作
 * */
@Mapper
@Component(value = "temperatureHistoryMapper")
public interface TemperatureHistoryMapper {
	 //查看历史记录
//	 int insert(List<Temperature> temperature)throws Exception;
	
	
}
