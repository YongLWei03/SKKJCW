package com.example.skkj.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.example.skkj.entity.Temperature;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface TemperatureServer {
	//根据设备号查询当前是否存在温度
	@Transactional
	 int selectBydeviceNumber(String deviceNumberXm)throws Exception;
	 
	  //根据设备号名称删除设备
	  @Transactional
	 int deleteBydeviceNumber(String deviceNumber)throws Exception;
	 
	 //批量添加数据
	 @Transactional
	 int insert(List<Temperature> temperature, String deviceNumber)throws Exception;
	 //根据时间上传
	 @Transactional
	 Map<String, Object> selectByTime(HttpServletRequest request)throws Exception;
	@Transactional
	HSSFWorkbook selectByTimeDaochu(HttpServletRequest request, String[] titles)throws Exception;

	 //添加单个设备的温度
	 @Transactional
	 int insertOne(Temperature temperature)throws Exception;


	//添加设备获取的温度信息
	@Transactional
	String insertTemu(Temperature temperature)throws Exception;

	//修改记录的数据
	@Transactional
	String updateTemu(Temperature temperature)throws Exception;

	//根据设备号进行查询
	@Transactional
    String selectByTemu(String deviceNumber, Temperature temper)throws Exception;
	@Transactional
    Temperature selectByTemuSb(String deviceNumber)throws Exception;
	@Transactional
	Map<String,Object> ImportByTempe(InputStream is, HttpServletRequest request)throws Exception;
	@Transactional
	Map<String,Object> ImportByTempeSJ(InputStream is, HttpServletRequest request)throws Exception;
	@Transactional
	Temperature selectByTemuS(String deviceNumber)throws Exception;
}
