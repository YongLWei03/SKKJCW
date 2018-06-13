package com.example.skkj.entity;
/**
 * 历史数据对应的文件存储
 * */
public class FileLocation {
	
	private String flId;
	
	private String fileName;//文件所以对应的位置
	
	private String year;//存入文件年月日

	private String numBer;//存的是多少个传感器文件

	public String getFlId() {
		return flId;
	}

	public void setFlId(String flId) {
		this.flId = flId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getNumBer() {
		return numBer;
	}

	public void setNumBer(String numBer) {
		this.numBer = numBer;
	}

	@Override
	public String toString() {
		return "FileLocation{" +
				"flId='" + flId + '\'' +
				", fileName='" + fileName + '\'' +
				", year='" + year + '\'' +
				", numBer='" + numBer + '\'' +
				'}';
	}
}
