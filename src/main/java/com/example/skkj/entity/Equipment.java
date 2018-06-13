package com.example.skkj.entity;

import java.io.Serializable;

/**
 * 变电站设备管理
 * */
public class Equipment implements Serializable{
	private static final long serialVersionUID = 1L;

	private String deviceSbId;//设备ID

	private Integer eqId;
	
	private String deviceNumber;//设备号
	
	private String headOfEquipment;//设备负责人
	
	private String phone;//设备负责人手机号码
	
	private String equipmentxi;//对应开关柜
	
	private String substationId;//对应的变电站Id
	
	private String type;//状态 1.正常 2.警报 3.异常
	
	private String count;//总数
	
	private String deviceName;//设备名称
	
	private String substationName;//开关柜名称

	private String code;//标识码

	private String eptId; //设备类型Id

	private String numberDevices;//传感器数量

	private String addRess; //对应变电站地址

	private String mute; //1.FALSE :允许上报,2.TRUE 不允许上报

	private String deviceType; //0.微星 1.26

	private String isRoot; //1.跟节点 0.子节点

	public String getMute() {
		return mute;
	}

	public void setMute(String mute) {
		this.mute = mute;
	}

    public String getDeviceSbId() {
		return deviceSbId;
	}

	public void setDeviceSbId(String deviceSbId) {
		this.deviceSbId = deviceSbId;
	}

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getSubstationName() {
		return substationName;
	}

	public void setSubstationName(String substationName) {
		this.substationName = substationName;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public String getNumberDevices() {
		return numberDevices;
	}

	public void setNumberDevices(String numberDevices) {
		this.numberDevices = numberDevices;
	}

	public String getAddRess() {
		return addRess;
	}

	public void setAddRess(String addRess) {
		this.addRess = addRess;
	}

	public String getEptId() {
		return eptId;
	}

	public void setEptId(String eptId) {
		this.eptId = eptId;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

    public String getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(String isRoot) {
        this.isRoot = isRoot;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "deviceSbId='" + deviceSbId + '\'' +
                ", eqId=" + eqId +
                ", deviceNumber='" + deviceNumber + '\'' +
                ", headOfEquipment='" + headOfEquipment + '\'' +
                ", phone='" + phone + '\'' +
                ", equipmentxi='" + equipmentxi + '\'' +
                ", substationId='" + substationId + '\'' +
                ", type='" + type + '\'' +
                ", count='" + count + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", substationName='" + substationName + '\'' +
                ", code='" + code + '\'' +
                ", eptId='" + eptId + '\'' +
                ", numberDevices='" + numberDevices + '\'' +
                ", addRess='" + addRess + '\'' +
                ", mute='" + mute + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", isRoot='" + isRoot + '\'' +
                '}';
    }
}
