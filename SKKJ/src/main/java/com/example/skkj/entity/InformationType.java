package com.example.skkj.entity;
/**
 * 报警信息
 * */
public class InformationType {
	private String InformationTypeId;// 

	private String information;

	private String type;

	public String getInformationTypeId() {
		return InformationTypeId;
	}

	public void setInformationTypeId(String informationTypeId) {
		InformationTypeId = informationTypeId;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "InformationType [InformationTypeId=" + InformationTypeId
				+ ", information=" + information + ", type=" + type + "]";
	}
	
	
}
