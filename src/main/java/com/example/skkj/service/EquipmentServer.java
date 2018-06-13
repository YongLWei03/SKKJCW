package com.example.skkj.service;

import com.example.skkj.entity.Equipment;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface EquipmentServer {
	/**
	 * 添加变电设备
	 * */
	@Transactional
	String insetEquipment(Equipment equipment, EquipmentServer equipmentServer)throws Exception;

	/**
	 * 添加变电设备
	 * */
	@Transactional
	String insetEqui(Equipment equipment)throws Exception;

	//查看变电站设备
	@Transactional
	List<Equipment> selectEquipment(HttpServletRequest request)throws Exception;

	//删除变电站设备
	@Transactional
	String deletByeqId(String eqId, String deviceId)throws Exception;

	//修改变电站设备
	@Transactional
	String updateByeqId(Equipment equipment, HttpServletRequest request)throws Exception;

	//通过ID查看单个变电站
	@Transactional
	Equipment findByeqId(String eqId)throws Exception;

	//通过变电站查看当前变电站正常个数 异常个数  报警个数
	@Transactional
	List<Equipment> seleTypeCountBysubsId(String substationId)throws Exception;

	//通过云平台添加设置
	@Transactional
	String insetEquipmentYPT(Equipment equipment)throws Exception;
	@Transactional
    void deletByDeviceNumber(String deviceNumber)throws Exception;

    //修改设备型号ID
	@Transactional
    String updateByDeviceNumber(Equipment equipment)throws Exception;

	//通过设备号进行查询
	@Transactional
	int selectByDeviceNumber(String DeviceNumber)throws  Exception;
	@Transactional
	String updateByMust(HttpServletRequest request)throws Exception;

	//通过subid查询出对象
	@Transactional
	List<String> selectBySubstationId(String substationId)throws  Exception;
	@Transactional
	List<Equipment> selectDeviceNumberByPhone(String Phone)throws Exception;

	//查看所有的设备
	@Transactional
	List<Equipment> selectAll()throws Exception;
	@Transactional
	Equipment findByDeviceNumberEqui(String deviceNumber)throws Exception;

	//导入excel
	@Transactional
	Map<String,Object> Import(InputStream is, EquipmentServer equipmentServer, String subId)throws Exception;
	@Transactional
	List<Equipment> selectByEqui(String substationId)throws Exception;
	@Transactional
	Map<String,Object> appSelectEquipment(HttpServletRequest request)throws Exception;
	@Transactional
	String appUpdateByeqId(Equipment equipment, String deviceNames, String deviceNumber, String eptIds)throws Exception;
	@Transactional
	//总管理查询出设备
	void selectByUserType(HttpServletRequest request)throws Exception;
	@Transactional
	//总页面批量添加数据
	Map<String,Object> skImport(InputStream is)throws Exception;
	@Transactional
	//修改设备的开关柜名称
	String updateBysubIdAndkgg(Equipment equipment)throws Exception;
	@Transactional
	//导入excel
	Map<String,Object> Importsq(InputStream is, String subId)throws Exception;
	@Transactional
	String EquiQy(Equipment equipment)throws Exception;
}
