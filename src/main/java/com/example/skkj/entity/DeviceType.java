package com.example.skkj.entity;

import java.io.Serializable;

public class DeviceType implements Serializable {
    private String devId;

    private String rootDeviceType;//设备类型名称

    private  String sencondDeviceType;//设备类型的名称

    private String deviceNum;//设备类型对应的编号

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getRootDeviceType() {
        return rootDeviceType;
    }

    public void setRootDeviceType(String rootDeviceType) {
        this.rootDeviceType = rootDeviceType;
    }

    public String getSencondDeviceType() {
        return sencondDeviceType;
    }

    public void setSencondDeviceType(String sencondDeviceType) {
        this.sencondDeviceType = sencondDeviceType;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    @Override
    public String toString() {
        return "DeviceType{" +
                "devId='" + devId + '\'' +
                ", rootDeviceType='" + rootDeviceType + '\'' +
                ", sencondDeviceType='" + sencondDeviceType + '\'' +
                ", deviceNum='" + deviceNum + '\'' +
                '}';
    }
}
