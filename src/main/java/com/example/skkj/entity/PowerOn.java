package com.example.skkj.entity;

public class PowerOn {
    private String powId;

    private String time;

    private String name;

    private String deviceId;

    public String getPowId() {
        return powId;
    }

    public void setPowId(String powId) {
        this.powId = powId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "PowerOn{" +
                "powId='" + powId + '\'' +
                ", time='" + time + '\'' +
                ", name='" + name + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
