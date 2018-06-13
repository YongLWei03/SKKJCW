package com.example.skkj.entity;
/***
 * 报警信息
 * */
public class AlarmInformation {
		private String afId;

		private String deviceNumber;//设备ID
		
		private String equipmentxi;//开关柜编号
		
		private String state;//报警状态 1.已处理 2.未处理
		
		private String day;//年月日时分秒
		
		private String eqId;//开关柜Id
		
		private String substationName;//变电站名称
		
		private String substationId;//变电站Id

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
		
		private String type;//1.异常，2警告

		
		private String informationType;//1.温度越限告警，2.主控单元与传感器通信异常,3.传感器自检异常,4.传感器信号异常,5.传感器供电异常,8.温升速率告警,9.三相不平衡告警
		
		//扩展
		private String information;
		
		public String getAfId() {
			return afId;
		}

		public void setAfId(String afId) {
			this.afId = afId;
		}


		public String getEquipmentxi() {
			return equipmentxi;
		}

		public void setEquipmentxi(String equipmentxi) {
			this.equipmentxi = equipmentxi;
		}

		public String getInformation() {
			return information;
		}

		public void setInformation(String information) {
			this.information = information;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getDay() {
			return day;
		}

		public void setDay(String day) {
			this.day = day;
		}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	@Override
	public String toString() {
		return "AlarmInformation{" +
				"afId='" + afId + '\'' +
				", deviceNumber='" + deviceNumber + '\'' +
				", equipmentxi='" + equipmentxi + '\'' +
				", state='" + state + '\'' +
				", day='" + day + '\'' +
				", eqId='" + eqId + '\'' +
				", substationName='" + substationName + '\'' +
				", substationId='" + substationId + '\'' +
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
				", type='" + type + '\'' +
				", informationType='" + informationType + '\'' +
				", information='" + information + '\'' +
				'}';
	}

	public String getEqId() {
			return eqId;
		}

		public void setEqId(String eqId) {
			this.eqId = eqId;
		}

		public String getSubstationName() {
			return substationName;
		}

		public void setSubstationName(String substationName) {
			this.substationName = substationName;
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

	public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getSubstationId() {
			return substationId;
		}

		public void setSubstationId(String substationId) {
			this.substationId = substationId;
		}

		public String getInformationType() {
			return informationType;
		}

		public void setInformationType(String informationType) {
			this.informationType = informationType;
		}
}
