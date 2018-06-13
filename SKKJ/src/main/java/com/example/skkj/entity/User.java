package com.example.skkj.entity;

import java.io.Serializable;

/**
 *用户信息
 * */
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//用户Id
	private Integer userId;
	//用户名
	private String userName;
	//用户密码
	private String password;
	//用户权限 1超级管理，2.管理员，3.负责人，4.普通用户
	private String type;
	//姓名
	private String  name;
	//联系电话
	private String telephone;
	//员工编号
	private String userNumber;
	//职称
	private String title;
	//职务
	private String post;
	//座机号
	private String planeNumber;
	//手机号码
	private String phoneNumber;
	//所属部门
	private String department;
	//备注
	private String remarks;
	
	private String region;//管理区域
	
	private String station;//站点	
	
	private String provinceId;//省
	
	private String cityId;//市
	
	private String areaId;//区/县
	
	private String state;//1.省管理 2.市管理3.区管理
	
	private String flog;//服务端的用户，APP端用户
	
	private String Keyword;//关键字
	
	private String  salt;//生成盐值

	private String jurisdictionl;//1.测温 2.电缆头

	public Integer getUserId() {
		return userId;
	}

	public String getKeyword() {
		return Keyword;
	}

	public void setKeyword(String keyword) {
		Keyword = keyword;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPlaneNumber() {
		return planeNumber;
	}

	public void setPlaneNumber(String planeNumber) {
		this.planeNumber = planeNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getJurisdictionl() {
		return jurisdictionl;
	}

	public void setJurisdictionl(String jurisdictionl) {
		this.jurisdictionl = jurisdictionl;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", type='" + type + '\'' +
				", name='" + name + '\'' +
				", telephone='" + telephone + '\'' +
				", userNumber='" + userNumber + '\'' +
				", title='" + title + '\'' +
				", post='" + post + '\'' +
				", planeNumber='" + planeNumber + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", department='" + department + '\'' +
				", remarks='" + remarks + '\'' +
				", region='" + region + '\'' +
				", station='" + station + '\'' +
				", provinceId='" + provinceId + '\'' +
				", cityId='" + cityId + '\'' +
				", areaId='" + areaId + '\'' +
				", state='" + state + '\'' +
				", flog='" + flog + '\'' +
				", Keyword='" + Keyword + '\'' +
				", salt='" + salt + '\'' +
				", jurisdictionl='" + jurisdictionl + '\'' +
				'}';
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
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

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFlog() {
		return flog;
	}

	public void setFlog(String flog) {
		this.flog = flog;
	}


	
}
