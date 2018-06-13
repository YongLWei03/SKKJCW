package com.example.skkj.entity;

import java.io.Serializable;

public class CommandInformation implements Serializable {
    private String Id;

    private String deviceNumber;

    private String name;//是什么命令

    private String type;

    private String commandId;

    private String method;

    private String userName;

    private String time;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "CommandInformation{" +
                "Id='" + Id + '\'' +
                ", deviceNumber='" + deviceNumber + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", commandId='" + commandId + '\'' +
                ", method='" + method + '\'' +
                ", userName='" + userName + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
