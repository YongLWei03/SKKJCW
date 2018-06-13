package com.example.skkj.entity;

import java.io.Serializable;

public class Temperature implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String temId;
	
	private String inA;//A相进入温度
	
	private String inB;//B相进入温度
	
	private String inC;//C相进入温度
	
	private String outA;//A相进入温度
	
	private String outB;//B相进入温度
	
	private String outC;//C相进入温度

	private String contactA;//A触头

	private String contactB;//B触头

	private String contactC;//C触头

	private String contactD;//D触头

	private String contactE;//E触头

	private String contactF;//F触头

	private String BoardStatus;//设备状态

	private String HarvesterStatus;//采集器状态

	private String deviceNumber;//设备号编号
	
	private String time;//获取温度的时间

	private String antSignal;//信号强度

	private String deviceNumberXm;//设备号
	
	private Integer type; //状态

	private String inAxh;//A相进入温度

	private String inBxh;//B相进入温度

	private String inCxh;//C相进入温度

	private String outAxh;//A相进入温度

	private String outBxh;//B相进入温度

	private String outCxh;//C相进入温度

	private String contactAxh;//A触头

	private String contactBxh;//B触头

	private String contactCxh;//C触头

	private String contactDxh;//D触头

	private String contactExh;//E触头

	private String contactFxh;//F触头

	private String number;

	//数据库表名
	private Object object;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public static long getSerialVersionUID() {

		return serialVersionUID;
	}

	public String getAntSignal() {
		return antSignal;
	}

	public void setAntSignal(String antSignal) {
		this.antSignal = antSignal;
	}

	public String getTemId() {
		return temId;
	}

	public void setTemId(String temId) {
		this.temId = temId;
	}

	public String getInA() {
		return inA;
	}

	public void setInA(String inA) {
		this.inA = inA;
	}

	public String getInB() {
		return inB;
	}

	public void setInB(String inB) {
		this.inB = inB;
	}

	public String getInC() {
		return inC;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	@Override
	public String toString() {
		return "Temperature{" +
				"temId='" + temId + '\'' +
				", inA='" + inA + '\'' +
				", inB='" + inB + '\'' +
				", inC='" + inC + '\'' +
				", outA='" + outA + '\'' +
				", outB='" + outB + '\'' +
				", outC='" + outC + '\'' +
				", contactA='" + contactA + '\'' +
				", contactB='" + contactB + '\'' +
				", contactC='" + contactC + '\'' +
				", contactD='" + contactD + '\'' +
				", contactE='" + contactE + '\'' +
				", contactF='" + contactF + '\'' +
				", BoardStatus='" + BoardStatus + '\'' +
				", HarvesterStatus='" + HarvesterStatus + '\'' +
				", deviceNumber='" + deviceNumber + '\'' +
				", time='" + time + '\'' +
				", antSignal='" + antSignal + '\'' +
				", deviceNumberXm='" + deviceNumberXm + '\'' +
				", type=" + type +
				", inAxh='" + inAxh + '\'' +
				", inBxh='" + inBxh + '\'' +
				", inCxh='" + inCxh + '\'' +
				", outAxh='" + outAxh + '\'' +
				", outBxh='" + outBxh + '\'' +
				", outCxh='" + outCxh + '\'' +
				", contactAxh='" + contactAxh + '\'' +
				", contactBxh='" + contactBxh + '\'' +
				", contactCxh='" + contactCxh + '\'' +
				", contactDxh='" + contactDxh + '\'' +
				", contactExh='" + contactExh + '\'' +
				", contactFxh='" + contactFxh + '\'' +
				", number='" + number + '\'' +
				'}';
	}

	public void setInC(String inC) {
		this.inC = inC;
	}

	public String getOutA() {
		return outA;
	}

	public void setOutA(String outA) {
		this.outA = outA;
	}

	public String getOutB() {
		return outB;
	}

	public void setOutB(String outB) {
		this.outB = outB;
	}

	public String getOutC() {
		return outC;
	}

	public void setOutC(String outC) {
		this.outC = outC;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDeviceNumberXm() {
		return deviceNumberXm;
	}

	public void setDeviceNumberXm(String deviceNumberXm) {
		this.deviceNumberXm = deviceNumberXm;
	}


	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}


	public String getContactA() {
		return contactA;
	}

	public void setContactA(String contactA) {
		this.contactA = contactA;
	}

	public String getContactB() {
		return contactB;
	}

	public void setContactB(String contactB) {
		this.contactB = contactB;
	}

	public String getContactC() {
		return contactC;
	}

	public void setContactC(String contactC) {
		this.contactC = contactC;
	}

	public String getContactD() {
		return contactD;
	}

	public void setContactD(String contactD) {
		this.contactD = contactD;
	}

	public String getContactE() {
		return contactE;
	}

	public void setContactE(String contactE) {
		this.contactE = contactE;
	}

	public String getContactF() {
		return contactF;
	}

	public void setContactF(String contactF) {
		this.contactF = contactF;
	}

	public String getBoardStatus() {
		return BoardStatus;
	}

	public void setBoardStatus(String boardStatus) {
		BoardStatus = boardStatus;
	}

	public String getHarvesterStatus() {
		return HarvesterStatus;
	}

	public void setHarvesterStatus(String harvesterStatus) {
		HarvesterStatus = harvesterStatus;
	}

	public String getInAxh() {
		return inAxh;
	}

	public void setInAxh(String inAxh) {
		this.inAxh = inAxh;
	}

	public String getInBxh() {
		return inBxh;
	}

	public void setInBxh(String inBxh) {
		this.inBxh = inBxh;
	}

	public String getInCxh() {
		return inCxh;
	}

	public void setInCxh(String inCxh) {
		this.inCxh = inCxh;
	}

	public String getOutAxh() {
		return outAxh;
	}

	public void setOutAxh(String outAxh) {
		this.outAxh = outAxh;
	}

	public String getOutBxh() {
		return outBxh;
	}

	public void setOutBxh(String outBxh) {
		this.outBxh = outBxh;
	}

	public String getOutCxh() {
		return outCxh;
	}

	public void setOutCxh(String outCxh) {
		this.outCxh = outCxh;
	}

	public String getContactAxh() {
		return contactAxh;
	}

	public void setContactAxh(String contactAxh) {
		this.contactAxh = contactAxh;
	}

	public String getContactBxh() {
		return contactBxh;
	}

	public void setContactBxh(String contactBxh) {
		this.contactBxh = contactBxh;
	}

	public String getContactCxh() {
		return contactCxh;
	}

	public void setContactCxh(String contactCxh) {
		this.contactCxh = contactCxh;
	}

	public String getContactDxh() {
		return contactDxh;
	}

	public void setContactDxh(String contactDxh) {
		this.contactDxh = contactDxh;
	}

	public String getContactExh() {
		return contactExh;
	}

	public void setContactExh(String contactExh) {
		this.contactExh = contactExh;
	}

	public String getContactFxh() {
		return contactFxh;
	}

	public void setContactFxh(String contactFxh) {
		this.contactFxh = contactFxh;
	}

	public Temperature(String inA, String inB, String inC,
					   String outA, String outB, String outC, String deviceNumber,
					   String time, Integer type,String inAxh,String inBxh,String inCxh,String outAxh,String outBxh,String outCxh) {
		super();
		this.inA = inA;
		this.inB = inB;
		this.inC = inC;
		this.outA = outA;
		this.outB = outB;
		this.outC = outC;
		this.deviceNumber = deviceNumber;
		this.time = time;
		this.type = type;
		this.inAxh = inAxh;
		this.inBxh = inBxh;
		this.inCxh = inCxh;
		this.outAxh = outAxh;
		this.outBxh = outBxh;
		this.outCxh = outCxh;
	}
	public Temperature(String inA, String inB, String inC,
					   String outA, String outB, String outC, String deviceNumber,
					   String time, Integer type) {
		super();
		this.inA = inA;
		this.inB = inB;
		this.inC = inC;
		this.outA = outA;
		this.outB = outB;
		this.outC = outC;
		this.deviceNumber = deviceNumber;
		this.time = time;
		this.type = type;
	}
	public Temperature() {
		super();
	}

}
