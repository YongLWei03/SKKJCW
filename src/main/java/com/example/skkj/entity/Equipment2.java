package com.example.skkj.entity;

import java.io.Serializable;

/**
 * 变电站设备管理
 * */
public class Equipment2 implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer eqId;
	
	private String deviceNumber;//设备号
	
	private String headOfEquipment;//设备负责人
	
	private String phone;//设备负责人手机号码
	
	private String equipmentxi;//对应开关柜
	
	private String substationId;//对应的变电站Id
	
	private String type;//状态 1.正常 2.警报 3.异常
	
	private String count;//总数
	
	private String serialNumber;//序号
	
	private String substationName;//开关柜名称

	private String connect;//连接类型 1.UDP，2.TCP
	

	public Integer getEqId() {
		return eqId;
	}

	public void setEqId(Integer eqId) {
		this.eqId = eqId;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public String getHeadOfEquipment() {
		return headOfEquipment;
	}

	public void setHeadOfEquipment(String headOfEquipment) {
		this.headOfEquipment = headOfEquipment;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEquipmentxi() {
		return equipmentxi;
	}

	public void setEquipmentxi(String equipmentxi) {
		this.equipmentxi = equipmentxi;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSubstationId() {
		return substationId;
	}

	public void setSubstationId(String substationId) {
		this.substationId = substationId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Equipment{" +
				"eqId=" + eqId +
				", deviceNumber='" + deviceNumber + '\'' +
				", headOfEquipment='" + headOfEquipment + '\'' +
				", phone='" + phone + '\'' +
				", equipmentxi='" + equipmentxi + '\'' +
				", substationId='" + substationId + '\'' +
				", type='" + type + '\'' +
				", count='" + count + '\'' +
				", serialNumber='" + serialNumber + '\'' +
				", substationName='" + substationName + '\'' +
				", connect='" + connect + '\'' +
				'}';
	}

	public String getConnect() {
		return connect;
	}

	public void setConnect(String connect) {
		this.connect = connect;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSubstationName() {
		return substationName;
	}

	public void setSubstationName(String substationName) {
		this.substationName = substationName;
	}

}
