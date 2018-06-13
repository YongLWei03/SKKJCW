package com.example.skkj.mapper;

import com.example.skkj.entity.Temperature;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 实时温度管理
 * */
@Mapper
@Component(value = "temperaturesMapper")
public interface TemperaturesMapper {

	 //批量添加数据
	 int insertTemu(Temperature temperatures)throws Exception;

	 //定时查看当前时间的历史数据
	 List<Temperature> selectByDinshisTime(@Param("time") String time, @Param("number") String number)throws Exception;

	 //删除选定的设备
	int deletByTime(@Param("time") String time, @Param("number") String number)throws Exception;

	List<Temperature> selectByNumber(String number)throws Exception;

	//更据时间断来查询数据开始时间结束时间
	List<Temperature> selectBySE(Map<String, Object> map)throws Exception;

	//查看当前数据总数
    int selectCount(Map<String, Object> map)throws Exception;

    //批量添加数据
    int insertList(List<Temperature> temperature)throws Exception;

    //批量查询出所有数据
	List<Temperature> selectCountSj(Map<String, Object> map)throws Exception;

	int ImportByTempe(List<Temperature> temperatures)throws Exception;



}
