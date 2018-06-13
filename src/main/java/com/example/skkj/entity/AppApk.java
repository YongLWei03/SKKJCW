package com.example.skkj.entity;

import java.io.Serializable;

/**
      * @Author ZhouNan
      * @Description apk下载
      * @params
      * @Date 2018/6/4 0004  09:26
      */
public class AppApk implements Serializable{
    private String appId;

    //更新URL
    private String appApkUrl;

    //更新哪些东西
    private String appName;

    //版本号
    private String vesion;

    private String time;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppApkUrl() {
        return appApkUrl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setAppApkUrl(String appApkUrl) {
        this.appApkUrl = appApkUrl;
    }

    @Override
    public String toString() {
        return "AppApk{" +
                "appId='" + appId + '\'' +
                ", appApkUrl='" + appApkUrl + '\'' +
                ", appName='" + appName + '\'' +
                ", vesion='" + vesion + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getVesion() {
        return vesion;
    }

    public void setVesion(String vesion) {
        this.vesion = vesion;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

}
