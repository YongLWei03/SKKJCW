package com.example.skkj.entity;

public class BaiDuAddr {
    private String address;//精确地址

    private String name;//地址名称

    private String lng;//经度

    private String lat;//纬度

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "BaiDuAddr{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", lng='" + lng + '\'' +
                ", lat='" + lat + '\'' +
                '}';
    }
}
