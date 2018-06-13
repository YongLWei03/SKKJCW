package com.example.skkj.dingxin;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class DeviceServiceData implements Serializable {

    private String serviceId;

    private String serviceType;

    private JSONObject data;

    private String eventTime;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "DeviceServiceData{" +
                "serviceId='" + serviceId + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", data=" + data +
                ", eventTime='" + eventTime + '\'' +
                '}';
    }
}
