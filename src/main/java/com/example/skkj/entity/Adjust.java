package com.example.skkj.entity;

import java.io.Serializable;

public class Adjust implements Serializable {

    private String adId;

    private int ID;

    private float maxFre;

    private float minFre;

    private float meanFre;

    private int maxPower;

    private int minPower;

    private int meanPower;

    private int validCount;

    private int InvalidCount;

    private boolean IsFlag;

    private boolean IsAdjust;

    private String commandId;

    private String sensorName;



    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getMaxFre() {
        return maxFre;
    }

    public void setMaxFre(float maxFre) {
        this.maxFre = maxFre;
    }

    public float getMinFre() {
        return minFre;
    }

    public void setMinFre(float minFre) {
        this.minFre = minFre;
    }

    public float getMeanFre() {
        return meanFre;
    }

    public void setMeanFre(float meanFre) {
        this.meanFre = meanFre;
    }

    public int getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(int maxPower) {
        this.maxPower = maxPower;
    }

    public int getMinPower() {
        return minPower;
    }

    public void setMinPower(int minPower) {
        this.minPower = minPower;
    }

    public int getMeanPower() {
        return meanPower;
    }

    public void setMeanPower(int meanPower) {
        this.meanPower = meanPower;
    }

    public int getValidCount() {
        return validCount;
    }

    public void setValidCount(int validCount) {
        this.validCount = validCount;
    }

    public int getInvalidCount() {
        return InvalidCount;
    }

    public void setInvalidCount(int invalidCount) {
        InvalidCount = invalidCount;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public boolean isFlag() {
        return IsFlag;
    }

    public void setIsFlag(boolean isFlag) {
        IsFlag = isFlag;
    }

    public boolean isAdjust() {
        return IsAdjust;
    }

    public void setIsAdjust(boolean isAdjust) {
        IsAdjust = isAdjust;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    @Override
    public String toString() {
        return "Adjust{" +
                "adId='" + adId + '\'' +
                ", ID=" + ID +
                ", maxFre=" + maxFre +
                ", minFre=" + minFre +
                ", meanFre=" + meanFre +
                ", maxPower=" + maxPower +
                ", minPower=" + minPower +
                ", meanPower=" + meanPower +
                ", validCount=" + validCount +
                ", InvalidCount=" + InvalidCount +
                ", IsFlag=" + IsFlag +
                ", IsAdjust=" + IsAdjust +
                ", commandId='" + commandId + '\'' +
                ", sensorName='" + sensorName + '\'' +
                '}';
    }
}
