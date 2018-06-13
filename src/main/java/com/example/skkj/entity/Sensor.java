package com.example.skkj.entity;

import java.io.Serializable;

/**
      * @Author ZhouNan
      * @Description 传感器
      * @params
      * @Date 2018/1/9 0009  17:54
      */
 //
public class Sensor implements Serializable {
    private String sensorId;

    private String sensorName;

    private String sensorNum;

     public String getSensorId() {
         return sensorId;
     }

     public void setSensorId(String sensorId) {
         this.sensorId = sensorId;
     }

     public String getSensorName() {
         return sensorName;
     }

     public void setSensorName(String sensorName) {
         this.sensorName = sensorName;
     }

    public String getSensorNum() {
        return sensorNum;
    }

    public void setSensorNum(String sensorNum) {
        this.sensorNum = sensorNum;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "sensorId='" + sensorId + '\'' +
                ", sensorName='" + sensorName + '\'' +
                ", sensorNum='" + sensorNum + '\'' +
                '}';
    }
}
