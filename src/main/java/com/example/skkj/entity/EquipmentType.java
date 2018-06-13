package com.example.skkj.entity;

public class EquipmentType {
    private String  eptId;

    //设备类型
    private String  deviceType;

    //型号
    private String model;

    //设备协议
    private String protocolType;

    //厂商名称
    private String manufacturerName;

    //厂商ID
    private String manufacturerId;

    public String getEptId() {
        return eptId;
    }

    public void setEptId(String eptId) {
        this.eptId = eptId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    @Override
    public String toString() {
        return "EquipmentType{" +
                "eptId='" + eptId + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", model='" + model + '\'' +
                ", protocolType='" + protocolType + '\'' +
                ", manufacturerName='" + manufacturerName + '\'' +
                ", manufacturerId='" + manufacturerId + '\'' +
                '}';
    }
}
