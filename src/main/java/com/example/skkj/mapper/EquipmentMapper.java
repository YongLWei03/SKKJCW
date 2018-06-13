package com.example.skkj.mapper;

import com.example.skkj.entity.Equipment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


/**
 * 变电站管理操作
 * */
@Mapper
@Component(value = "equipmentMapper")
public interface EquipmentMapper {
	/**
	 * 添加变电设备
	 * */
	int insetEquipment(Equipment equipment)throws Exception;
	
	//查看变电站设备
	List<Equipment> selectEquipment(Map<String, Object> map)throws Exception;
	
	//变电站设备总数
	int selectEquipmentCount(Map<String, Object> map)throws Exception;
	
	//删除变电站设备
	int deletByeqId(String eqId)throws Exception;
	
	//修改变电站设备
	int updateByeqId(Equipment equipment)throws Exception;
	
	//通过ID查看单个变电站
	Equipment findByeqId(String eqId)throws Exception;
	
	//通过变电站查看当前变电站正常个数 异常个数  报警个数
	List<Equipment> seleTypeCountBysubsId(String substationId)throws Exception;
	
	//通过变电站ID删除对象
	int deleteBysubID(String substationId)throws Exception;
	
	
	//通过设备号查看当前编号
	Equipment selectByEquipmentxi(Equipment equipment)throws Exception;

	
	//根据变电站ID查询出当前变电站有多少个开关柜
	int selectBysubID(String substationId)throws Exception;

	//根据设备对应的型号删除
	int deletByDeviceNumber(String deviceNumber)throws Exception;

	//修改设备型号ID
	int updateByDeviceNumber(Equipment equipment)throws Exception;

	//通过设备号进行查询
	int selectByDeviceNumber(String DeviceNumber)throws  Exception;

	//添加变电站Id
	int updateByAddRess(@Param("addRess") String addRess, @Param("substationId") String substationId)throws Exception;

	//查询是否存在这些设备
	int selectByAddRess(String addRess)throws Exception;

	//通过变电站ID查询出开光柜设备号
	List<String> findByDeviceNumber(String substationId)throws  Exception;

	//通过subid查询出对象的电话号码
	List<String> selectBySubstationId(String substationId)throws  Exception;

	List<Equipment> selectDeviceNumberByPhone(String phone)throws Exception;

	String selectByDeviceNumberEqui(String deviceNumber)throws Exception;

	//查看所有的设备不用任何条件
	List<Equipment> selectAll()throws Exception;

	//听过设备号来查询设备信息
	Equipment findByDeviceNumberEqui(String deviceNumber)throws Exception;

	List<Equipment> selectByEqui(String substationId)throws Exception;

	  List<Equipment> selectEquipmentcc(Map<String, Object> map)throws Exception;

	  //总管理查询出设备 不管状态的操作
	List<Equipment> selectByUserType(Map<String, Object> map)throws Exception;

	//查看总管理总数
	int selectByUserTypeCount()throws Exception;

	 /**
	      * @Author ZhouNan
	      * @Description 批量添加设备
	      * @params
	      * @Date 2018/5/3 0003  16:14
	      */
	 int insertAll(List<Equipment> equipmentList)throws Exception;

	  /**
	       * @Author ZhouNan
	       * @Description 批量修改设备
	       * @params
	       * @Date 2018/5/4 0004  09:59
	       */
	  int updateAll(List<Equipment> equipmentList)throws Exception;

	  int ImportByEqui(List<Equipment> equipmentList)throws Exception;
	  int EquiQy(Equipment equipment)throws Exception;

	   /**
	        * @Author ZhouNan
	        * @Description 根据设备ID进行查询
	        * @params
	        * @Date 2018/5/16 0016  15:44
	        */
	   String selectByDevicesbId(String deviceSbId)throws Exception;
}
