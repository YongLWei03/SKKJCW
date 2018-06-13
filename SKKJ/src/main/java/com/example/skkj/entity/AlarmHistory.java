package com.example.skkj.entity;

import java.io.Serializable;

/**
      * @Author ZhouNan
      * @Description 报警信息之前的数据
      * @params
      * @Date 2018/1/31 0031  09:18
      */
public class AlarmHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    private String alarId;

    private String inA;//A相进入温度

    private String inB;//B相进入温度

    private String inC;//C相进入温度

    private String outA;//A相进入温度

    private String outB;//B相进入温度

    private String outC;//C相进入温度

    private String contactA;//A触头

    private String contactB;//B触头

    private String contactC;//C触头

    private String contactD;//D触头

    private String contactE;//E触头

    private String contactF;//F触头

    private String deviceNumber;//设备号编号

    private String type; //状态

    private String time; //状态

    public String getAlarId() {
        return alarId;
    }

    public void setAlarId(String alarId) {
        this.alarId = alarId;
    }

    public String getInA() {
        return inA;
    }

    public void setInA(String inA) {
        this.inA = inA;
    }

    public String getInB() {
        return inB;
    }

    public void setInB(String inB) {
        this.inB = inB;
    }

    public String getInC() {
        return inC;
    }

    public void setInC(String inC) {
        this.inC = inC;
    }

    public String getOutA() {
        return outA;
    }

    public void setOutA(String outA) {
        this.outA = outA;
    }

    public String getOutB() {
        return outB;
    }

    public void setOutB(String outB) {
        this.outB = outB;
    }

    public String getOutC() {
        return outC;
    }

    public void setOutC(String outC) {
        this.outC = outC;
    }

    public String getContactA() {
        return contactA;
    }

    public void setContactA(String contactA) {
        this.contactA = contactA;
    }

    public String getContactB() {
        return contactB;
    }

    public void setContactB(String contactB) {
        this.contactB = contactB;
    }

    public String getContactC() {
        return contactC;
    }

    public void setContactC(String contactC) {
        this.contactC = contactC;
    }

    public String getContactD() {
        return contactD;
    }

    public void setContactD(String contactD) {
        this.contactD = contactD;
    }

    public String getContactE() {
        return contactE;
    }

    public void setContactE(String contactE) {
        this.contactE = contactE;
    }

    public String getContactF() {
        return contactF;
    }

    public void setContactF(String contactF) {
        this.contactF = contactF;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "AlarmHistory{" +
                "alarId='" + alarId + '\'' +
                ", inA='" + inA + '\'' +
                ", inB='" + inB + '\'' +
                ", inC='" + inC + '\'' +
                ", outA='" + outA + '\'' +
                ", outB='" + outB + '\'' +
                ", outC='" + outC + '\'' +
                ", contactA='" + contactA + '\'' +
                ", contactB='" + contactB + '\'' +
                ", contactC='" + contactC + '\'' +
                ", contactD='" + contactD + '\'' +
                ", contactE='" + contactE + '\'' +
                ", contactF='" + contactF + '\'' +
                ", deviceNumber='" + deviceNumber + '\'' +
                ", type='" + type + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
