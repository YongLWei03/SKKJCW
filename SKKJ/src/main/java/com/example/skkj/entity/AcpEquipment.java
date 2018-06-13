package com.example.skkj.entity;

public class AcpEquipment {
    private String acpeId;

    private String boardStatus;//设备状态

    private String harvesterStatus;//采集器状态

    private String deviceNumber;//设备号

    private String informationNumber;

    private String time;

    public String getBoardStatus() {
        return boardStatus;
    }

    public void setBoardStatus(String boardStatus) {
        this.boardStatus = boardStatus;
    }

    public String getHarvesterStatus() {
        return harvesterStatus;
    }

    public void setHarvesterStatus(String harvesterStatus) {
        this.harvesterStatus = harvesterStatus;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getAcpeId() {
        return acpeId;
    }

    public void setAcpeId(String acpeId) {
        this.acpeId = acpeId;
    }

    public String getInformationNumber() {
        return informationNumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setInformationNumber(String informationNumber) {
        this.informationNumber = informationNumber;
    }

    @Override
    public String toString() {
        return "AcpEquipment{" +
                "acpeId='" + acpeId + '\'' +
                ", boardStatus='" + boardStatus + '\'' +
                ", harvesterStatus='" + harvesterStatus + '\'' +
                ", deviceNumber='" + deviceNumber + '\'' +
                ", informationNumber='" + informationNumber + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
