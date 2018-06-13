package com.example.skkj.entity;

import java.io.Serializable;

public class Region implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String regionId;  
	
	private String regionName;
	
	private String parentId;

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "Region [regionId=" + regionId + ", regionName=" + regionName
				+ ", parentId=" + parentId + "]";
	}

}
