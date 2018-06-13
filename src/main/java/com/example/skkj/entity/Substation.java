package com.example.skkj.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Substation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String substationId;
	
	//变电站名称
	private String substationName;
	
	//变电站所属区域
	private String substationArea;
	
	//地理位置信息
	private String information;
	
	//地理位置的精度
	private String accuracy;
	
	//地理位置的纬度
	private String latitude;

	//地址拼音
	private String substationPY;
	
	//摆放图纸
	private String substationImage;
	
	private String provinceId;//省
	
	private String cityId;//市
	
	private String areaId;//区/县
	
	private List<String> count = new ArrayList<String>();
	
	
	public String getSubstationId() {
		return substationId;
	}
	public void setSubstationId(String substationId) {
		this.substationId = substationId;
	}
	public String getSubstationName() {
		return substationName;
	}
	public void setSubstationName(String substationName) {
		this.substationName = substationName;
	}
	public String getSubstationArea() {
		return substationArea;
	}
	public void setSubstationArea(String substationArea) {
		this.substationArea = substationArea;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getSubstationImage() {
		return substationImage;
	}
	public void setSubstationImage(String substationImage) {
		this.substationImage = substationImage;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Substation{" +
				"substationId='" + substationId + '\'' +
				", substationName='" + substationName + '\'' +
				", substationArea='" + substationArea + '\'' +
				", information='" + information + '\'' +
				", accuracy='" + accuracy + '\'' +
				", latitude='" + latitude + '\'' +
				", substationPY='" + substationPY + '\'' +
				", substationImage='" + substationImage + '\'' +
				", provinceId='" + provinceId + '\'' +
				", cityId='" + cityId + '\'' +
				", areaId='" + areaId + '\'' +
				", count=" + count +
				'}';
	}

	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public List<String> getCount() {
		return count;
	}
	public void setCount(List<String> count) {
		this.count = count;
	}

	public String getSubstationPY() {
		return substationPY;
	}

	public void setSubstationPY(String substationPY) {
		this.substationPY = substationPY;
	}
}
