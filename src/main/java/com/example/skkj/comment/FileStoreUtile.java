package com.example.skkj.comment;

import com.example.skkj.entity.Equipment;
import com.example.skkj.entity.FileLocation;
import com.example.skkj.entity.Temperature;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 文件存储
 * */
public class FileStoreUtile {


	/**
	 * 存储到文件
	 */
	public static StringBuilder fileStore(List<?> value, String num) throws IOException {
		String time = TimeUtile.dateTime();
		StringBuilder stbder = new StringBuilder();
		if (num == "6" || "6".equals(num)) {
			  stbder.append(CfFinal.FILE_SIX).append(time).append(".txt");
			File file = new File(stbder.toString());  //存放数组数据的文
			// 件

			FileWriter out = new FileWriter(file);  //文件写入流

			//将数组中的数据写入到文件中。每行各数据之间TAB间隔
			//如果文件不存在，则创建新的文件
			file.createNewFile();
			//创建文件成功后，写入内容到文件里
			//以控制方式进行隔离
			for (int i = 0; i < value.size(); i++) {
				Temperature temp = (Temperature) value.get(i);
				String str = temp.getInA() + " " + temp.getInB() + " " + temp.getInC() + " " + temp.getOutA() + " " + temp.getOutB() + " " + temp.getOutC() + " " + temp.getTime() + " " + temp.getType() + " " + temp.getDeviceNumber();
				String inAxh = temp.getInAxh();
				String inBxh = temp.getInBxh();
				String inCxh = temp.getInCxh();
				String outAxh = temp.getOutAxh();
				String outBxh = temp.getOutBxh();
				String outCxh = temp.getOutCxh();
				String adtsing = temp.getAntSignal();
				if (inAxh != null && !"".equals(inAxh)) {
					str += " " + inAxh;
				}
				if (inBxh != null && !"".equals(inBxh)) {
					str += " " + inBxh;
				}
				if (inCxh != null && !"".equals(inCxh)) {
					str += " " + inCxh;
				}
				if (outAxh != null && !"".equals(outAxh)) {
					str += " " + outAxh;
				}
				if (outBxh != null && !"".equals(outBxh)) {
					str += " " + outBxh;
				}
				if (outCxh != null && !"".equals(outCxh)) {
					str += " " + outCxh;
				}
				if (adtsing != null && !"".equals(adtsing)) {
					str += " " + adtsing;
				}
				out.write(str);
				out.write("\r\n");
			}
			out.close();
			return stbder;
		} else if (num == "3" || "3".equals(num)) {
			stbder.append(CfFinal.FILE_THREE).append(time).append(".txt");
			File file = new File(stbder.toString());  //存放数组数据的文件

			FileWriter out = new FileWriter(file);  //文件写入流

			//将数组中的数据写入到文件中。每行各数据之间TAB间隔
			//如果文件不存在，则创建新的文件
			file.createNewFile();
			//创建文件成功后，写入内容到文件里
			//以控制方式进行隔离
			for (int i = 0; i < value.size(); i++) {
				Temperature temp = (Temperature) value.get(i);
				String inAxh = temp.getInAxh();
				String inBxh = temp.getInBxh();
				String inCxh = temp.getInCxh();
				String antsing = temp.getAntSignal();
				String str = temp.getInA() + " " + temp.getInB() + " " + temp.getInC() + " " + temp.getTime() + " " + temp.getType() + " " + temp.getDeviceNumber();
				if (inAxh != null && !"".equals(inAxh)) {
					str += " " + inAxh;
				}
				if (inBxh != null && !"".equals(inBxh)) {
					str += " " + inBxh;
				}
				if (inCxh != null && !"".equals(inCxh)) {
					str += " " + inCxh;
				}
				if (antsing != null && !"".equals(antsing)) {
					str += " " + antsing;
				}
				out.write(str);
				out.write("\r\n");
			}
			out.close();
			return stbder;
		} else if (num == "9" || "9".equals(num)) {
			stbder.append(CfFinal.FILE_NINE).append(time).append(".txt");
			File file = new File(stbder.toString());  //存放数组数据的文件

			FileWriter out = new FileWriter(file);  //文件写入流

			//将数组中的数据写入到文件中。每行各数据之间TAB间隔
			//如果文件不存在，则创建新的文件
			file.createNewFile();
			//创建文件成功后，写入内容到文件里
			//以控制方式进行隔离
			for (int i = 0; i < value.size(); i++) {
				Temperature temp = (Temperature) value.get(i);
				String inAxh = temp.getInAxh();
				String inBxh = temp.getInBxh();
				String inCxh = temp.getInCxh();
				String outAxh = temp.getOutAxh();
				String outBxh = temp.getOutBxh();
				String outCxh = temp.getOutCxh();
				String contactAxh = temp.getContactAxh();
				String contactBxh = temp.getContactBxh();
				String contactCxh = temp.getContactCxh();
				String antsing = temp.getAntSignal();
				String str = temp.getInA() + " " + temp.getInB() + " " + temp.getInC() + " " + temp.getOutA() + " " + temp.getOutB() + " " + temp.getOutC() + " " + temp.getContactA() + " " + temp.getContactB() + " " + temp.getContactC() + " " + temp.getTime() + " " + temp.getType() + " " + temp.getDeviceNumber();
				if (inAxh != null && !"".equals(inAxh)) {
					str += " " + inAxh;
				}
				if (inBxh != null && !"".equals(inBxh)) {
					str += " " + inBxh;
				}
				if (inCxh != null && !"".equals(inCxh)) {
					str += " " + inCxh;
				}
				if (outAxh != null && !"".equals(outAxh)) {
					str += " " + outAxh;
				}
				if (outBxh != null && !"".equals(outBxh)) {
					str += " " + outBxh;
				}
				if (outCxh != null && !"".equals(outCxh)) {
					str += " " + outCxh;
				}
				if (contactAxh != null && !"".equals(contactAxh)) {
					str += " " + contactAxh;
				}
				if (contactBxh != null && !"".equals(contactBxh)) {
					str += " " + contactBxh;
				}
				if (contactCxh != null && !"".equals(contactCxh)) {
					str += " " + contactCxh;
				}
				if (antsing != null && !"".equals(antsing)) {
					str += " " + antsing;
				}
				out.write(str);
				out.write("\r\n");
			}
			out.close();
			return stbder;
		} else {
			stbder.append(CfFinal.FILE_TWELVE).append(time).append(".txt");
			File file = new File(stbder.toString());  //存放数组数据的文件

			FileWriter out = new FileWriter(file);  //文件写入流

			//将数组中的数据写入到文件中。每行各数据之间TAB间隔
			//如果文件不存在，则创建新的文件
			file.createNewFile();
			//创建文件成功后，写入内容到文件里
			//以控制方式进行隔离
			for (int i = 0; i < value.size(); i++) {
				Temperature temp = (Temperature) value.get(i);
				String inAxh = temp.getInAxh();
				String inBxh = temp.getInBxh();
				String inCxh = temp.getInCxh();
				String outAxh = temp.getOutAxh();
				String outBxh = temp.getOutBxh();
				String outCxh = temp.getOutCxh();
				String contactAxh = temp.getContactAxh();
				String contactBxh = temp.getContactBxh();
				String contactCxh = temp.getContactCxh();
				String contactDxh = temp.getContactDxh();
				String contactExh = temp.getContactExh();
				String contactFxh = temp.getContactFxh();
				String adntsi = temp.getAntSignal();
				String str = temp.getInA() + " " + temp.getInB() + " " + temp.getInC() + " " + temp.getOutA() + " " + temp.getOutB() + " " + temp.getOutC() + " " + temp.getContactA() + " " + temp.getContactB() + " " + temp.getContactC() + " " + temp.getContactD() + " " + temp.getContactE() + " " + temp.getContactF() + " " + temp.getTime() + " " + temp.getType() + " " + temp.getDeviceNumber();
				if (inAxh != null && !"".equals(inAxh)) {
					str += " " + inAxh;
				}
				if (inBxh != null && !"".equals(inBxh)) {
					str += " " + inBxh;
				}
				if (inCxh != null && !"".equals(inCxh)) {
					str += " " + inCxh;
				}
				if (outAxh != null && !"".equals(outAxh)) {
					str += " " + outAxh;
				}
				if (outBxh != null && !"".equals(outBxh)) {
					str += " " + outBxh;
				}
				if (outCxh != null && !"".equals(outCxh)) {
					str += " " + outCxh;
				}
				if (contactAxh != null && !"".equals(contactAxh)) {
					str += " " + contactAxh;
				}
				if (contactBxh != null && !"".equals(contactBxh)) {
					str += " " + contactBxh;
				}
				if (contactCxh != null && !"".equals(contactCxh)) {
					str += " " + contactCxh;
				}
				if (contactDxh != null && !"".equals(contactDxh)) {
					str += " " + contactDxh;
				}
				if (contactExh != null && !"".equals(contactExh)) {
					str += " " + contactExh;
				}
				if (contactFxh != null && !"".equals(contactFxh)) {
					str += " " + contactFxh;
				}
				if (adntsi != null && !"".equals(adntsi)) {
					str += " " + adntsi;
				}
				out.write(str);
				out.write("\r\n");
			}
			out.close();
			return stbder;
		}

	}

	/**
	 * 读取文件流文件
	 */
	public static List<Temperature> fileStoreTq(String fileName, String numBer, String deviceNumber, String startTime, String endTime) throws IOException {
		File file = new File(fileName);  //存放数组数据的文件

		BufferedReader in = new BufferedReader(new FileReader(file));  //
		String line;  //一行数据
		List<Temperature> temperatureList = new ArrayList<Temperature>();
		//逐行读取，并将每个数组放入到数组中
		if (numBer == "6" || "6".equals(numBer)) {
			while ((line = in.readLine()) != null) {
				String[] temp = line.trim().split(" ");
				//开关柜对应的名称
				String deviceNumbers = temp[9];
				if (deviceNumber == deviceNumbers || deviceNumbers.equals(deviceNumber)) {
					//A相入
					String inA = temp[0];
					//B相入
					String inB = temp[1];
					//C相入
					String inC = temp[2];
					//A相出
					String outA = temp[3];
					//B相出
					String outB = temp[4];
					//C相出
					String outC = temp[5];
					//时间
					String time = temp[6] + " " + temp[7];
					//状态
					String type = temp[8];
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String inAxh = "";
					String inBxh = "";
					String inCxh = "";
					String outAxh = "";
					String outBxh = "";
					String outCxh = "";
					String anting = "";
					if (temp.length > 10) {
						inAxh = temp[10];
						inBxh = temp[11];
						inCxh = temp[12];
						outAxh = temp[13];
						outBxh = temp[14];
						outCxh = temp[15];
						if (temp.length > 16) {
							anting = temp[16];
						}
					}
					try {
						Date dt1 = df.parse(time);
						Date dt2 = df.parse(startTime);
						Date dt3 = df.parse(endTime);
						if (dt1.getTime() > dt2.getTime() && dt1.getTime() < dt3.getTime()) {
							Temperature temperature = new Temperature(inA, inB, inC, outA, outB, outC, deviceNumbers, time, Integer.parseInt(type), inAxh, inBxh, inCxh, outAxh, outBxh, outCxh);
							temperature.setAntSignal(anting);
							temperatureList.add(temperature);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		} else if (numBer == "3" || "3".equals(numBer)) {
			while ((line = in.readLine()) != null) {
				String[] temp = line.trim().split(" ");
				//开关柜对应的名称
				String deviceNumbers = temp[6];
				if (deviceNumbers == deviceNumber || deviceNumbers.equals(deviceNumber)) {
					//A相入
					String inA = temp[0];
					//B相入
					String inB = temp[1];
					//C相入
					String inC = temp[2];
					//时间
					String time = temp[3] + " " + temp[4];
					//状态
					String type = temp[5];
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String inAxh = "";
					String inBxh = "";
					String inCxh = "";
					String anting = "";
					if (temp.length > 7) {
						inAxh = temp[7];
						inBxh = temp[8];
						inCxh = temp[9];
						if (temp.length > 10) {
							anting = temp[10];
						}
					}

					try {
						Date dt1 = df.parse(time);
						Date dt2 = df.parse(startTime);
						Date dt3 = df.parse(endTime);
						if (dt1.getTime() > dt2.getTime() && dt1.getTime() < dt3.getTime()) {
							Temperature temperature = new Temperature();
							temperature.setInA(inA);
							temperature.setInB(inB);
							temperature.setInC(inC);
							temperature.setTime(time);
							temperature.setType(Integer.parseInt(type));
							temperature.setDeviceNumber(deviceNumber);
							if (inAxh != null && !"".equals(inAxh)) {
								temperature.setInAxh(inAxh);
							}
							if (inBxh != null && !"".equals(inBxh)) {
								temperature.setInBxh(inBxh);
							}
							if (inCxh != null && !"".equals(inCxh)) {
								temperature.setInCxh(inCxh);
							}
							if (anting != null && !"".equals(anting)) {
								temperature.setAntSignal(anting);
							}
							temperatureList.add(temperature);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		} else if (numBer == "9" || "9".equals(numBer)) {
			while ((line = in.readLine()) != null) {
				String[] temp = line.trim().split(" ");
				//开关柜对应的名称
				String deviceNumbers = temp[12];
				if (deviceNumber == deviceNumbers || deviceNumbers.equals(deviceNumber)) {
					//A相入
					String inA = temp[0];
					//B相入
					String inB = temp[1];
					//C相入
					String inC = temp[2];
					//A相出
					String outA = temp[3];
					//B相出
					String outB = temp[4];
					//C相出
					String outC = temp[5];
					//A线盘
					String contactA = temp[6];
					//A线盘
					String contactB = temp[7];
					//A线盘
					String contactC = temp[8];
					//时间
					String time = temp[9] + " " + temp[10];
					//状态
					String type = temp[11];
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String inAxh = "";
					String inBxh = "";
					String inCxh = "";
					String outAxh = "";
					String outBxh = "";
					String outCxh = "";
					String contactAxh = "";
					String contactBxh = "";
					String contactCxh = "";
					String anting = "";
					if (temp.length > 13) {
						inAxh = temp[13];
						inBxh = temp[14];
						inCxh = temp[15];
						outAxh = temp[16];
						outBxh = temp[17];
						outCxh = temp[18];
						contactAxh = temp[19];
						contactBxh = temp[20];
						contactCxh = temp[21];
						if (temp.length > 22) {
							anting = temp[22];
						}
					}

					try {
						Date dt1 = df.parse(time);
						Date dt2 = df.parse(startTime);
						Date dt3 = df.parse(endTime);
						if (dt1.getTime() > dt2.getTime() && dt1.getTime() < dt3.getTime()) {
							Temperature temperature = new Temperature();
							temperature.setInA(inA);
							temperature.setInB(inB);
							temperature.setInC(inC);
							temperature.setOutA(outA);
							temperature.setOutB(outB);
							temperature.setOutC(outC);
							temperature.setContactA(contactA);
							temperature.setContactB(contactB);
							temperature.setContactC(contactC);
							temperature.setTime(time);
							temperature.setType(Integer.parseInt(type));
							temperature.setDeviceNumber(deviceNumber);
							if (inAxh != null && !"".equals(inAxh)) {
								temperature.setInAxh(inAxh);
							}
							if (inBxh != null && !"".equals(inBxh)) {
								temperature.setInBxh(inBxh);
							}
							if (inCxh != null && !"".equals(inCxh)) {
								temperature.setInCxh(inCxh);
							}
							if (outAxh != null && !"".equals(outAxh)) {
								temperature.setOutAxh(outAxh);
							}
							if (outBxh != null && !"".equals(outBxh)) {
								temperature.setOutBxh(outBxh);
							}
							if (outCxh != null && !"".equals(outCxh)) {
								temperature.setOutCxh(outCxh);
							}
							if (contactAxh != null && !"".equals(contactAxh)) {
								temperature.setContactAxh(contactAxh);
							}
							if (contactBxh != null && !"".equals(contactBxh)) {
								temperature.setContactBxh(contactBxh);
							}
							if (contactCxh != null && !"".equals(contactCxh)) {
								temperature.setContactCxh(contactCxh);
							}
							if (anting != null && !"".equals(anting)) {
								temperature.setAntSignal(anting);
							}
							if (anting != null && !"".equals(anting)) {
								temperature.setAntSignal(anting);
							}
							temperatureList.add(temperature);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}

				}
			}
		} else {
			while ((line = in.readLine()) != null) {
				String[] temp = line.trim().split(" ");
				//开关柜对应的名称
				String deviceNumbers = temp[15];
				if (deviceNumber == deviceNumbers || deviceNumbers.equals(deviceNumber)) {
					//A相入
					String inA = temp[0];
					//B相入
					String inB = temp[1];
					//C相入
					String inC = temp[2];
					//A相出
					String outA = temp[3];
					//B相出
					String outB = temp[4];
					//C相出
					String outC = temp[5];
					//A线盘
					String contactA = temp[6];
					//B线盘
					String contactB = temp[7];
					//C线盘
					String contactC = temp[8];
					//D线盘
					String contactD = temp[9];
					//E线盘
					String contactE = temp[10];
					//C线盘
					String contactF = temp[11];
					//时间
					String time = temp[12] + " " + temp[13];
					//状态
					String type = temp[14];
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String inAxh = "";
					String inBxh = "";
					String inCxh = "";
					String outAxh = "";
					String outBxh = "";
					String outCxh = "";
					String contactAxh = "";
					String contactBxh = "";
					String contactCxh = "";
					String contactDxh = "";
					String contactExh = "";
					String contactFxh = "";
					String anting = "";
					if (temp.length > 16) {
						inAxh = temp[16];
						inBxh = temp[17];
						inCxh = temp[18];
						outAxh = temp[19];
						outBxh = temp[20];
						outCxh = temp[21];
						contactAxh = temp[22];
						contactBxh = temp[23];
						contactCxh = temp[24];
						contactDxh = temp[25];
						contactExh = temp[26];
						contactFxh = temp[27];
						if (temp.length > 18) {
							anting = temp[28];
						}
					}
					try {
						Date dt1 = df.parse(time);
						Date dt2 = df.parse(startTime);
						Date dt3 = df.parse(endTime);
						if (dt1.getTime() > dt2.getTime() && dt1.getTime() < dt3.getTime()) {
							Temperature temperature = new Temperature();
							temperature.setInA(inA);
							temperature.setInB(inB);
							temperature.setInC(inC);
							temperature.setOutA(outA);
							temperature.setOutB(outB);
							temperature.setOutC(outC);
							temperature.setContactA(contactA);
							temperature.setContactB(contactB);
							temperature.setContactC(contactC);
							temperature.setContactD(contactD);
							temperature.setContactE(contactE);
							temperature.setContactF(contactF);
							temperature.setTime(time);
							temperature.setType(Integer.parseInt(type));
							temperature.setDeviceNumber(deviceNumber);
							if (inAxh != null && !"".equals(inAxh)) {
								temperature.setInAxh(inAxh);
							}
							if (inBxh != null && !"".equals(inBxh)) {
								temperature.setInBxh(inBxh);
							}
							if (inCxh != null && !"".equals(inCxh)) {
								temperature.setInCxh(inCxh);
							}
							if (outAxh != null && !"".equals(outAxh)) {
								temperature.setOutAxh(outAxh);
							}
							if (outBxh != null && !"".equals(outBxh)) {
								temperature.setOutBxh(outBxh);
							}
							if (outCxh != null && !"".equals(outCxh)) {
								temperature.setOutCxh(outCxh);
							}
							if (contactAxh != null && !"".equals(contactAxh)) {
								temperature.setContactAxh(contactAxh);
							}
							if (contactBxh != null && !"".equals(contactBxh)) {
								temperature.setContactBxh(contactBxh);
							}
							if (contactCxh != null && !"".equals(contactCxh)) {
								temperature.setContactCxh(contactCxh);
							}
							if (contactDxh != null && !"".equals(contactDxh)) {
								temperature.setContactDxh(contactDxh);
							}
							if (contactExh != null && !"".equals(contactExh)) {
								temperature.setContactExh(contactExh);
							}
							if (contactFxh != null && !"".equals(contactFxh)) {
								temperature.setContactFxh(contactFxh);
							}
							if (anting != null && !"".equals(anting)) {
								temperature.setAntSignal(anting);
							}
							temperatureList.add(temperature);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}

				}
			}
		}
		in.close();
		return temperatureList;
	}

	/**
	 * 读取文件流文件
	 */
	public static List<Temperature> fileStoreTqA(String fileName, String numBer, String deviceNumber) throws IOException {
		File file = new File(fileName);  //存放数组数据的文件

		BufferedReader in = new BufferedReader(new FileReader(file));  //
		String line;  //一行数据
		List<Temperature> temperatureList = new ArrayList<Temperature>();
		//逐行读取，并将每个数组放入到数组中
		if (numBer == "6" || "6".equals(numBer)) {
			while ((line = in.readLine()) != null) {
				String[] temp = line.trim().split(" ");
				//开关柜对应的名称
				String deviceNumbers = temp[9];
				if(deviceNumbers != "4a00b8c6-8047-4dfc-b32b-02df2151795d" && !"4a00b8c6-8047-4dfc-b32b-02df2151795d".equals(deviceNumbers)){
					//A相入
					String inA = temp[0];
					//B相入
					String inB = temp[1];
					//C相入
					String inC = temp[2];
					//A相出
					String outA = temp[3];
					//B相出
					String outB = temp[4];
					//C相出
					String outC = temp[5];
					//时间
					String time = temp[6] + " " + temp[7];
					//状态
					String type = temp[8];
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String inAxh = "";
					String inBxh = "";
					String inCxh = "";
					String outAxh = "";
					String outBxh = "";
					String outCxh = "";
					String anting = "";
					if (temp.length > 10) {
						inAxh = temp[10];
						inBxh = temp[11];
						inCxh = temp[12];
						outAxh = temp[13];
						outBxh = temp[14];
						outCxh = temp[15];
						if (temp.length > 16) {
							anting = temp[16];
						}
					}

					Temperature temperature = new Temperature(inA, inB, inC, outA, outB, outC, deviceNumbers, time, Integer.parseInt(type), inAxh, inBxh, inCxh, outAxh, outBxh, outCxh);
					temperature.setAntSignal(anting);
					temperature.setNumber(numBer);
					temperatureList.add(temperature);
				}

			}
		} else if (numBer == "3" || "3".equals(numBer)) {
			while ((line = in.readLine()) != null) {
				String[] temp = line.trim().split(" ");
				//开关柜对应的名称
				String deviceNumbers = temp[6];

					//A相入
					String inA = temp[0];
					//B相入
					String inB = temp[1];
					//C相入
					String inC = temp[2];
					//时间
					String time = temp[3] + " " + temp[4];
					//状态
					String type = temp[5];
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String inAxh = "";
					String inBxh = "";
					String inCxh = "";
					String anting = "";
					if (temp.length > 7) {
						inAxh = temp[7];
						inBxh = temp[8];
						inCxh = temp[9];
						if (temp.length > 10) {
							anting = temp[10];
						}
					}


							Temperature temperature = new Temperature();
							temperature.setInA(inA);
							temperature.setInB(inB);
							temperature.setInC(inC);
							temperature.setTime(time);
							temperature.setType(Integer.parseInt(type));
							temperature.setDeviceNumber(deviceNumber);
							if (inAxh != null && !"".equals(inAxh)) {
								temperature.setInAxh(inAxh);
							}
							if (inBxh != null && !"".equals(inBxh)) {
								temperature.setInBxh(inBxh);
							}
							if (inCxh != null && !"".equals(inCxh)) {
								temperature.setInCxh(inCxh);
							}
							if (anting != null && !"".equals(anting)) {
								temperature.setAntSignal(anting);
							}
							temperatureList.add(temperature);


			}
		} else if (numBer == "9" || "9".equals(numBer)) {
			while ((line = in.readLine()) != null) {
				String[] temp = line.trim().split(" ");
				//开关柜对应的名称
				String deviceNumbers = temp[12];

					//A相入
					String inA = temp[0];
					//B相入
					String inB = temp[1];
					//C相入
					String inC = temp[2];
					//A相出
					String outA = temp[3];
					//B相出
					String outB = temp[4];
					//C相出
					String outC = temp[5];
					//A线盘
					String contactA = temp[6];
					//A线盘
					String contactB = temp[7];
					//A线盘
					String contactC = temp[8];
					//时间
					String time = temp[9] + " " + temp[10];
					//状态
					String type = temp[11];
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String inAxh = "";
					String inBxh = "";
					String inCxh = "";
					String outAxh = "";
					String outBxh = "";
					String outCxh = "";
					String contactAxh = "";
					String contactBxh = "";
					String contactCxh = "";
					String anting = "";
					if (temp.length > 13) {
						inAxh = temp[13];
						inBxh = temp[14];
						inCxh = temp[15];
						outAxh = temp[16];
						outBxh = temp[17];
						outCxh = temp[18];
						contactAxh = temp[19];
						contactBxh = temp[20];
						contactCxh = temp[21];
						if (temp.length > 22) {
							anting = temp[22];
						}
					}


							Temperature temperature = new Temperature();
							temperature.setInA(inA);
							temperature.setInB(inB);
							temperature.setInC(inC);
							temperature.setOutA(outA);
							temperature.setOutB(outB);
							temperature.setOutC(outC);
							temperature.setContactA(contactA);
							temperature.setContactB(contactB);
							temperature.setContactC(contactC);
							temperature.setTime(time);
							temperature.setType(Integer.parseInt(type));
							temperature.setDeviceNumber(deviceNumber);
							if (inAxh != null && !"".equals(inAxh)) {
								temperature.setInAxh(inAxh);
							}
							if (inBxh != null && !"".equals(inBxh)) {
								temperature.setInBxh(inBxh);
							}
							if (inCxh != null && !"".equals(inCxh)) {
								temperature.setInCxh(inCxh);
							}
							if (outAxh != null && !"".equals(outAxh)) {
								temperature.setOutAxh(outAxh);
							}
							if (outBxh != null && !"".equals(outBxh)) {
								temperature.setOutBxh(outBxh);
							}
							if (outCxh != null && !"".equals(outCxh)) {
								temperature.setOutCxh(outCxh);
							}
							if (contactAxh != null && !"".equals(contactAxh)) {
								temperature.setContactAxh(contactAxh);
							}
							if (contactBxh != null && !"".equals(contactBxh)) {
								temperature.setContactBxh(contactBxh);
							}
							if (contactCxh != null && !"".equals(contactCxh)) {
								temperature.setContactCxh(contactCxh);
							}
							if (anting != null && !"".equals(anting)) {
								temperature.setAntSignal(anting);
							}
							if (anting != null && !"".equals(anting)) {
								temperature.setAntSignal(anting);
							}
							temperatureList.add(temperature);

			}
		} else {
			while ((line = in.readLine()) != null) {
				String[] temp = line.trim().split(" ");
				//开关柜对应的名称
				String deviceNumbers = temp[15];
				if (deviceNumber == deviceNumbers || deviceNumbers.equals(deviceNumber)) {
					//A相入
					String inA = temp[0];
					//B相入
					String inB = temp[1];
					//C相入
					String inC = temp[2];
					//A相出
					String outA = temp[3];
					//B相出
					String outB = temp[4];
					//C相出
					String outC = temp[5];
					//A线盘
					String contactA = temp[6];
					//B线盘
					String contactB = temp[7];
					//C线盘
					String contactC = temp[8];
					//D线盘
					String contactD = temp[9];
					//E线盘
					String contactE = temp[10];
					//C线盘
					String contactF = temp[11];
					//时间
					String time = temp[12] + " " + temp[13];
					//状态
					String type = temp[14];
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String inAxh = "";
					String inBxh = "";
					String inCxh = "";
					String outAxh = "";
					String outBxh = "";
					String outCxh = "";
					String contactAxh = "";
					String contactBxh = "";
					String contactCxh = "";
					String contactDxh = "";
					String contactExh = "";
					String contactFxh = "";
					String anting = "";
					if (temp.length > 16) {
						inAxh = temp[16];
						inBxh = temp[17];
						inCxh = temp[18];
						outAxh = temp[19];
						outBxh = temp[20];
						outCxh = temp[21];
						contactAxh = temp[22];
						contactBxh = temp[23];
						contactCxh = temp[24];
						contactDxh = temp[25];
						contactExh = temp[26];
						contactFxh = temp[27];
						if (temp.length > 18) {
							anting = temp[28];
						}
					}

							Temperature temperature = new Temperature();
							temperature.setInA(inA);
							temperature.setInB(inB);
							temperature.setInC(inC);
							temperature.setOutA(outA);
							temperature.setOutB(outB);
							temperature.setOutC(outC);
							temperature.setContactA(contactA);
							temperature.setContactB(contactB);
							temperature.setContactC(contactC);
							temperature.setContactD(contactD);
							temperature.setContactE(contactE);
							temperature.setContactF(contactF);
							temperature.setTime(time);
							temperature.setType(Integer.parseInt(type));
							temperature.setDeviceNumber(deviceNumber);
							if (inAxh != null && !"".equals(inAxh)) {
								temperature.setInAxh(inAxh);
							}
							if (inBxh != null && !"".equals(inBxh)) {
								temperature.setInBxh(inBxh);
							}
							if (inCxh != null && !"".equals(inCxh)) {
								temperature.setInCxh(inCxh);
							}
							if (outAxh != null && !"".equals(outAxh)) {
								temperature.setOutAxh(outAxh);
							}
							if (outBxh != null && !"".equals(outBxh)) {
								temperature.setOutBxh(outBxh);
							}
							if (outCxh != null && !"".equals(outCxh)) {
								temperature.setOutCxh(outCxh);
							}
							if (contactAxh != null && !"".equals(contactAxh)) {
								temperature.setContactAxh(contactAxh);
							}
							if (contactBxh != null && !"".equals(contactBxh)) {
								temperature.setContactBxh(contactBxh);
							}
							if (contactCxh != null && !"".equals(contactCxh)) {
								temperature.setContactCxh(contactCxh);
							}
							if (contactDxh != null && !"".equals(contactDxh)) {
								temperature.setContactDxh(contactDxh);
							}
							if (contactExh != null && !"".equals(contactExh)) {
								temperature.setContactExh(contactExh);
							}
							if (contactFxh != null && !"".equals(contactFxh)) {
								temperature.setContactFxh(contactFxh);
							}
							if (anting != null && !"".equals(anting)) {
								temperature.setAntSignal(anting);
							}
							temperatureList.add(temperature);
				}
			}
		}
		in.close();
		return temperatureList;
	}

	/**
	 * 读取文件流文件
	 */
	public static Map<String, String> fileStoreGs(List<FileLocation> fileNameList, List<Equipment> equi, String substationName) throws IOException {
		Map<String, String> map = new HashMap<String, String>();

		StringBuilder str = new StringBuilder("本周温度报警正常次数统计:" + "\r\n");
		for (int i = 0; i < equi.size(); i++) {
			Equipment eq = equi.get(i);
			String deviceNumber = eq.getDeviceNumber();
			String deviceName = eq.getDeviceName();
			//正常个数
			int a = 0;
			//异常个数
			int b = 0;
			for (int j = 0; j < fileNameList.size(); j++) {
				FileLocation filena = fileNameList.get(j);
				String numBer = filena.getNumBer();
				File file = new File(filena.getFileName());  //存放数组数据的文件

				BufferedReader in = new BufferedReader(new FileReader(file));  //
				String line;  //一行数据

				//逐行读取，并将每个数组放入到数组中
				if (numBer == "6" || "6".equals(numBer)) {
					while ((line = in.readLine()) != null) {
						String[] temp = line.trim().split(" ");
						//开关柜对应的名称
						String deviceNumbers = temp[9];
						if (deviceNumber == deviceNumbers || deviceNumbers.equals(deviceNumber)) {
							//状态
							String type = temp[8];
							if (type == "1" || "1".equals(type)) {
								a += 1;
							} else {
								b += 1;
							}
						}
					}

				} else if (numBer == "3" || "3".equals(numBer)) {
					while ((line = in.readLine()) != null) {
						String[] temp = line.trim().split(" ");
						//开关柜对应的名称
						String deviceNumbers = temp[6];
						if (deviceNumbers == deviceNumber || deviceNumbers.equals(deviceNumber)) {
							//状态
							String type = temp[5];
							if (type == "1" || "1".equals(type)) {
								a += 1;
							} else {
								b += 1;
							}
						}
					}
				} else if (numBer == "9" || "9".equals(numBer)) {
					while ((line = in.readLine()) != null) {
						String[] temp = line.trim().split(" ");
						//开关柜对应的名称
						String deviceNumbers = temp[12];
						if (deviceNumber == deviceNumbers || deviceNumbers.equals(deviceNumber)) {
							//状态
							String type = temp[11];
							if (type == "1" || "1".equals(type)) {
								a += 1;
							} else {
								b += 1;
							}
						}
					}
				} else {
					while ((line = in.readLine()) != null) {
						String[] temp = line.trim().split(" ");
						//开关柜对应的名称
						String deviceNumbers = temp[15];
						if (deviceNumber == deviceNumbers || deviceNumbers.equals(deviceNumber)) {
							//状态
							String type = temp[14];
							if (type == "1" || "1".equals(type)) {
								a += 1;
							} else {
								b += 1;
							}

						}
					}
				}
				in.close();
			}
			if (i == fileNameList.size() - 1) {
				str.append(deviceName).append("正常的温度次数:" + String.valueOf(a)).append(" 异常温度次数:" + String.valueOf(b));
			} else {
				str.append(deviceName).append(" 正常的温度次数：" + String.valueOf(a)).append(" 异常温度次数: " + String.valueOf(b)).append("\r\n");
			}
		}
		map.put(substationName, str.toString());
		return map;
	}

	 /**
	      * @Author ZhouNan
	      * @Description 文件续写
	      * @params
	      * @Date 2018/3/21 0021  10:11
	      */
    /**
     * 存储到文件
     */
    public static String fileStorex(List<?> value, String num,String fileName) throws IOException {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileName, true)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (num == "6" || "6".equals(num)) {
            //创建文件成功后，写入内容到文件里
            //以控制方式进行隔离
            for (int i = 0; i < value.size(); i++) {
                Temperature temp = (Temperature) value.get(i);
                String str = temp.getInA() + " " + temp.getInB() + " " + temp.getInC() + " " + temp.getOutA() + " " + temp.getOutB() + " " + temp.getOutC() + " " + temp.getTime() + " " + temp.getType() + " " + temp.getDeviceNumber();
                String inAxh = temp.getInAxh();
                String inBxh = temp.getInBxh();
                String inCxh = temp.getInCxh();
                String outAxh = temp.getOutAxh();
                String outBxh = temp.getOutBxh();
                String outCxh = temp.getOutCxh();
                String adtsing = temp.getAntSignal();
                if (inAxh != null && !"".equals(inAxh)) {
                    str += " " + inAxh;
                }
                if (inBxh != null && !"".equals(inBxh)) {
                    str += " " + inBxh;
                }
                if (inCxh != null && !"".equals(inCxh)) {
                    str += " " + inCxh;
                }
                if (outAxh != null && !"".equals(outAxh)) {
                    str += " " + outAxh;
                }
                if (outBxh != null && !"".equals(outBxh)) {
                    str += " " + outBxh;
                }
                if (outCxh != null && !"".equals(outCxh)) {
                    str += " " + outCxh;
                }
                if (adtsing != null && !"".equals(adtsing)) {
                    str += " " + adtsing;
                }
                out.write(str);
                out.write("\r\n");
            }
            out.close();
            return "true";
        } else if (num == "3" || "3".equals(num)) {
            for (int i = 0; i < value.size(); i++) {
                Temperature temp = (Temperature) value.get(i);
                String inAxh = temp.getInAxh();
                String inBxh = temp.getInBxh();
                String inCxh = temp.getInCxh();
                String antsing = temp.getAntSignal();
                String str = temp.getInA() + " " + temp.getInB() + " " + temp.getInC() + " " + temp.getTime() + " " + temp.getType() + " " + temp.getDeviceNumber();
                if (inAxh != null && !"".equals(inAxh)) {
                    str += " " + inAxh;
                }
                if (inBxh != null && !"".equals(inBxh)) {
                    str += " " + inBxh;
                }
                if (inCxh != null && !"".equals(inCxh)) {
                    str += " " + inCxh;
                }
                if (antsing != null && !"".equals(antsing)) {
                    str += " " + antsing;
                }
                out.write(str);
                out.write("\r\n");
            }
            out.close();
            return "true";
        } else if (num == "9" || "9".equals(num)) {

            for (int i = 0; i < value.size(); i++) {
                Temperature temp = (Temperature) value.get(i);
                String inAxh = temp.getInAxh();
                String inBxh = temp.getInBxh();
                String inCxh = temp.getInCxh();
                String outAxh = temp.getOutAxh();
                String outBxh = temp.getOutBxh();
                String outCxh = temp.getOutCxh();
                String contactAxh = temp.getContactAxh();
                String contactBxh = temp.getContactBxh();
                String contactCxh = temp.getContactCxh();
                String antsing = temp.getAntSignal();
                String str = temp.getInA() + " " + temp.getInB() + " " + temp.getInC() + " " + temp.getOutA() + " " + temp.getOutB() + " " + temp.getOutC() + " " + temp.getContactA() + " " + temp.getContactB() + " " + temp.getContactC() + " " + temp.getTime() + " " + temp.getType() + " " + temp.getDeviceNumber();
                if (inAxh != null && !"".equals(inAxh)) {
                    str += " " + inAxh;
                }
                if (inBxh != null && !"".equals(inBxh)) {
                    str += " " + inBxh;
                }
                if (inCxh != null && !"".equals(inCxh)) {
                    str += " " + inCxh;
                }
                if (outAxh != null && !"".equals(outAxh)) {
                    str += " " + outAxh;
                }
                if (outBxh != null && !"".equals(outBxh)) {
                    str += " " + outBxh;
                }
                if (outCxh != null && !"".equals(outCxh)) {
                    str += " " + outCxh;
                }
                if (contactAxh != null && !"".equals(contactAxh)) {
                    str += " " + contactAxh;
                }
                if (contactBxh != null && !"".equals(contactBxh)) {
                    str += " " + contactBxh;
                }
                if (contactCxh != null && !"".equals(contactCxh)) {
                    str += " " + contactCxh;
                }
                if (antsing != null && !"".equals(antsing)) {
                    str += " " + antsing;
                }
                out.write(str);
                out.write("\r\n");
            }
            out.close();
            return "true";
        } else {
            for (int i = 0; i < value.size(); i++) {
                Temperature temp = (Temperature) value.get(i);
                String inAxh = temp.getInAxh();
                String inBxh = temp.getInBxh();
                String inCxh = temp.getInCxh();
                String outAxh = temp.getOutAxh();
                String outBxh = temp.getOutBxh();
                String outCxh = temp.getOutCxh();
                String contactAxh = temp.getContactAxh();
                String contactBxh = temp.getContactBxh();
                String contactCxh = temp.getContactCxh();
                String contactDxh = temp.getContactDxh();
                String contactExh = temp.getContactExh();
                String contactFxh = temp.getContactFxh();
                String adntsi = temp.getAntSignal();
                String str = temp.getInA() + " " + temp.getInB() + " " + temp.getInC() + " " + temp.getOutA() + " " + temp.getOutB() + " " + temp.getOutC() + " " + temp.getContactA() + " " + temp.getContactB() + " " + temp.getContactC() + " " + temp.getContactD() + " " + temp.getContactE() + " " + temp.getContactF() + " " + temp.getTime() + " " + temp.getType() + " " + temp.getDeviceNumber();
                if (inAxh != null && !"".equals(inAxh)) {
                    str += " " + inAxh;
                }
                if (inBxh != null && !"".equals(inBxh)) {
                    str += " " + inBxh;
                }
                if (inCxh != null && !"".equals(inCxh)) {
                    str += " " + inCxh;
                }
                if (outAxh != null && !"".equals(outAxh)) {
                    str += " " + outAxh;
                }
                if (outBxh != null && !"".equals(outBxh)) {
                    str += " " + outBxh;
                }
                if (outCxh != null && !"".equals(outCxh)) {
                    str += " " + outCxh;
                }
                if (contactAxh != null && !"".equals(contactAxh)) {
                    str += " " + contactAxh;
                }
                if (contactBxh != null && !"".equals(contactBxh)) {
                    str += " " + contactBxh;
                }
                if (contactCxh != null && !"".equals(contactCxh)) {
                    str += " " + contactCxh;
                }
                if (contactDxh != null && !"".equals(contactDxh)) {
                    str += " " + contactDxh;
                }
                if (contactExh != null && !"".equals(contactExh)) {
                    str += " " + contactExh;
                }
                if (contactFxh != null && !"".equals(contactFxh)) {
                    str += " " + contactFxh;
                }
                if (adntsi != null && !"".equals(adntsi)) {
                    str += " " + adntsi;
                }
                out.write(str);
                out.write("\r\n");
            }
            out.close();
            return "true";
        }

    }

	public static void main(String[] args) {
		List<Temperature> temper = new LinkedList<Temperature>();
		for (int i = 0; i < 2; i++) {
			Temperature temp = new Temperature();
			temp.setAntSignal("1");
			temp.setInA("1");
			temp.setInB("1");
			temp.setInC("1");
			temp.setOutA("1");
			temp.setOutB("1");
			temp.setOutC("1");
			temp.setDeviceNumber("2");
			temper.add(temp);
		}

		BufferedWriter out = null;

		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("F:\\demoSix/421e5e1f7a674fe48747da311916aca42018012711.txt", true)));
			for (int i = 0; i < temper.size(); i++) {
				Temperature temp = temper.get(i);
				String str = temp.getInA() + " " + temp.getInB() + " " + temp.getInC() + " " + temp.getOutA() + " " + temp.getOutB() + " " + temp.getOutC() + " " + temp.getTime() + " " + temp.getType() + " " + temp.getDeviceNumber();
				out.write("\r\n");
				out.write(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}