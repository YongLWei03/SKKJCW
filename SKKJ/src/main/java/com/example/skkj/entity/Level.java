package com.example.skkj.entity;

import java.io.Serializable;
import java.util.List;

public class Level implements Serializable {
    private String deviceType;

    private String deviceId;

    private String isRoot;

    private String reportPeriod;

    private String captureMode;

    private String sensorAddr;
    private String underTemp;
    private String overTemp;
    private String underSignal;
    private String overSignal;
    private String sensorNum;
    private String sequence;
    private String isAutoMode;
    private String zoneInNum;
    private String zoneInTatol;
    private String capturePeriod;

    private String southIP;

    private String  southPort;

    private List<String> sensorName;

    private String nccId;


    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(String isRoot) {
        this.isRoot = isRoot;
    }

    public String getReportPeriod() {
        return reportPeriod;
    }

    public void setReportPeriod(String reportPeriod) {
        this.reportPeriod = reportPeriod;
    }

    public String getCaptureMode() {
        return captureMode;
    }

    public void setCaptureMode(String captureMode) {
        this.captureMode = captureMode;
    }

    public String getCapturePeriod() {
        return capturePeriod;
    }

    public void setCapturePeriod(String capturePeriod) {
        this.capturePeriod = capturePeriod;
    }

    public String getSensorAddr() {
        return sensorAddr;
    }

    public void setSensorAddr(String sensorAddr) {
        this.sensorAddr = sensorAddr;
    }

    public String getUnderTemp() {
        return underTemp;
    }

    public void setUnderTemp(String underTemp) {
        this.underTemp = underTemp;
    }

    public String getOverTemp() {
        return overTemp;
    }

    public void setOverTemp(String overTemp) {
        this.overTemp = overTemp;
    }

    public String getUnderSignal() {
        return underSignal;
    }

    public void setUnderSignal(String underSignal) {
        this.underSignal = underSignal;
    }

    public String getOverSignal() {
        return overSignal;
    }

    public void setOverSignal(String overSignal) {
        this.overSignal = overSignal;
    }

    public String getSensorNum() {
        return sensorNum;
    }

    public void setSensorNum(String sensorNum) {
        this.sensorNum = sensorNum;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public List<String> getSensorName() {
        return sensorName;
    }

    public void setSensorName(List<String> sensorName) {
        this.sensorName = sensorName;
    }

    public String getZoneInNum() {
        return zoneInNum;
    }

    public void setZoneInNum(String zoneInNum) {
        this.zoneInNum = zoneInNum;
    }

    public String getZoneInTatol() {
        return zoneInTatol;
    }

    public void setZoneInTatol(String zoneInTatol) {
        this.zoneInTatol = zoneInTatol;
    }

    public String getSouthIP() {
        return southIP;
    }

    public void setSouthIP(String southIP) {
        this.southIP = southIP;
    }

    public String getSouthPort() {
        return southPort;
    }

    public void setSouthPort(String southPort) {
        this.southPort = southPort;
    }

    public String getNccId() {
        return nccId;
    }

    public void setNccId(String nccId) {
        this.nccId = nccId;
    }

    @Override
    public String toString() {
        return "Level{" +
                "deviceType='" + deviceType + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", isRoot='" + isRoot + '\'' +
                ", reportPeriod='" + reportPeriod + '\'' +
                ", captureMode='" + captureMode + '\'' +
                ", sensorAddr='" + sensorAddr + '\'' +
                ", underTemp='" + underTemp + '\'' +
                ", overTemp='" + overTemp + '\'' +
                ", underSignal='" + underSignal + '\'' +
                ", overSignal='" + overSignal + '\'' +
                ", sensorNum='" + sensorNum + '\'' +
                ", sequence='" + sequence + '\'' +
                ", isAutoMode='" + isAutoMode + '\'' +
                ", zoneInNum='" + zoneInNum + '\'' +
                ", zoneInTatol='" + zoneInTatol + '\'' +
                ", capturePeriod='" + capturePeriod + '\'' +
                ", southIP='" + southIP + '\'' +
                ", southPort='" + southPort + '\'' +
                ", sensorName=" + sensorName +
                ", nccId='" + nccId + '\'' +
                '}';
    }

    public String getIsAutoMode() {
        return isAutoMode;
    }

    public void setIsAutoMode(String isAutoMode) {
        this.isAutoMode = isAutoMode;
    }
}
