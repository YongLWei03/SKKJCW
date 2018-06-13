package com.example.skkj.entity;

import java.io.Serializable;

/**
 * 用户对应的变电站
 * */
public class UserRegion implements Serializable {
	
	private String UserRegionId;
	
	private String userId;//用户Id
	
	private String substationId;//变电站Id
	
	private String substationName;//变电站名称
	
	public String getUserRegionId() {
		return UserRegionId;
	}

	public void setUserRegionId(String userRegionId) {
		UserRegionId = userRegionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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
	
	
	
}
