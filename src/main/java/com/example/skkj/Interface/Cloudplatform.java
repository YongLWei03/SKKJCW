package com.example.skkj.Interface;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.skkj.comment.*;
import com.example.skkj.dingxin.*;
import com.example.skkj.entity.*;
import com.example.skkj.service.*;
import com.example.skkj.temperatureCalculation.Calculation;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

/**
 * 云平台数据解析
 * @Author ZhouNan
 * @Description
 * @params
 * @Date 11:31 2017/12/20 0020
 */
public class Cloudplatform{

  private  AlarmInformationServer alarmInformationServer;

  private JSONObject jsonObject;

  private String deviceId;

  private EquipmentServer equipmentServer;

  private EquipmentTypeServer equipmentTypeServer;

  private SubstationServer substationServer;

  private AcpEquipmentServer acpEquipmentServer;

  private AlarmHistoryServer alarmHistoryServer;

  private PowerOnServer powerOnServer;

  private TemperatureServer temperatureServer;

  private CalculationWdServer calculationWdServer;

  private TemperaturesServer temperaturesServer;

  private AddTableServer addTableServer;

       /**
          * @Author ZhouNan
          * @Description  解析json数据  批量数据变化
          * @params
          * @Date 2017/12/27 0027  09:20
          */
       public  void analysis(){
               Temperature temper = new Temperature();
               AlarmHistory alarh = null;
               Object deviceIds = jsonObject.get("deviceId");
               if(deviceIds != null && !"".equals(deviceIds)){
                   RedisUtils redisUtils = new RedisUtils();
                   String deviceId = (String)deviceIds;
                   temper.setDeviceNumber(deviceId);
                   String jsa = JSONArray.toJSONString(jsonObject.get("services"));
                   List<DeviceServiceData>  list2 = JSONArray.parseArray(jsa,DeviceServiceData.class);
                   String underTemp ="";//下线温度
                   String overTemp ="";//上线温度
                   String time = "";
                   String Ztype = "";
                   String deviceTypes = "";
                   String BoardStatus = ""; //设备状态
                   String AntSignal = ""; //信号
                   String HarvesterStatus = "";//采集器状态
                   String command = "";//采集器状态
                   int deviceNum = 0;
                   String temperatureAlarm = "";
                   String eventTimes = "";
                   String hasMore = "";
                   if(list2.size()>0){
                       for (int i = 0; i < list2.size() ; i++) {
                           DeviceServiceData dto = list2.get(i);
                           //里面的数据
                           JSONObject data = dto.getData();
                           //判断是那种类型的解析方式
                           Integer deviceType = (Integer) data.get("deviceType");
                           Integer hasMores = (Integer)data.get("hasMore");
                           if(deviceType != null && !"".equals(deviceType)){
                               deviceTypes = String.valueOf(deviceType);
                           }
                           if(hasMores != null && !"".equals(hasMores)){
                               hasMore = String.valueOf(hasMores);
                           }
                           if(BoardStatus == null || "".equals(BoardStatus)){

                               //判断是否是报错后的缓存数据
                               String commands = (String) data.get("command");
                               if(commands != null && !"".equals(commands)){
                                   command = commands;
                               }
                               //获取温度
                               String datas = (String) data.get("data");
                               if(command != null && !"".equals(command) && command != "COMMAND_DEVICE_BUFFER_DATA" && !"COMMAND_DEVICE_BUFFER_DATA".equals(command)){
                                   if(datas != null && !"".equals(datas)){
                                       //g传感器个数
                                       StringBuilder dnum = new StringBuilder();
                                       String dnum1 = String.valueOf(datas.charAt(0));
                                       String dnum2 = String.valueOf(datas.charAt(1));
                                       dnum.append(dnum1).append(dnum2);
                                       deviceNum = Encoding.gs(dnum.toString());

                                       //温度越线下线温度
                                       StringBuilder underTempStr = new StringBuilder();
                                       String underTempStr1 = String.valueOf(datas.charAt(2));
                                       String underTempStr2 = String.valueOf(datas.charAt(3));
                                       String underTempStr4 = String.valueOf(datas.charAt(4));
                                       String underTempStr5 = String.valueOf(datas.charAt(5));
                                       underTempStr.append(underTempStr1).append(underTempStr2).append(underTempStr4).append(underTempStr5);
                                       underTemp = Encoding.intpa(underTempStr.toString());

                                       //温度上线tr
                                       StringBuilder overTempStr = new StringBuilder();
                                       String overTemp1 = String.valueOf(datas.charAt(6));
                                       String overTemp2 = String.valueOf(datas.charAt(7));
                                       String overTemp4 = String.valueOf(datas.charAt(8));
                                       String overTemp5 = String.valueOf(datas.charAt(9));
                                       overTempStr.append(overTemp1).append(overTemp2).append(overTemp4).append(overTemp5);
                                       overTemp = Encoding.intpa(overTempStr.toString());

                                       //拼接字符
                                       for (int j = 0; j < deviceNum; j++) {
                                           StringBuilder wd = new StringBuilder();
                                           StringBuilder wdxh = new StringBuilder();
                                           if(deviceTypes == CfFinal.Temp_Type_Uint8 || CfFinal.Temp_Type_Uint8.equals(deviceTypes)) {
                                               int shu = j * 7;
                                               //温度值
                                               String wendu1 = String.valueOf(datas.charAt(j + 18 + shu));
                                               String wendu2 = String.valueOf(datas.charAt(j + 19 + shu));
                                               String wendu3 = String.valueOf(datas.charAt(j + 20 + shu));
                                               String wendu4 = String.valueOf(datas.charAt(j + 21 + shu));
                                               String wendu5 = String.valueOf(datas.charAt(j + 22 + shu));
                                               String wendu6 = String.valueOf(datas.charAt(j + 23 + shu));
                                               String wendu7 = String.valueOf(datas.charAt(j + 24 + shu));
                                               String wendu8 = String.valueOf(datas.charAt(j + 25 + shu));
                                                if(deviceNum == 12){
                                                    //温度信息号强度
                                                    String wenduxh1 = String.valueOf(datas.charAt(j + 114 + shu));
                                                    String wenduxh2 = String.valueOf(datas.charAt(j + 115 + shu));
                                                    String wenduxh3 = String.valueOf(datas.charAt(j + 116 + shu));
                                                    String wenduxh4 = String.valueOf(datas.charAt(j + 117 + shu));
                                                    String wenduxh5 = String.valueOf(datas.charAt(j + 118 + shu));
                                                    String wenduxh6 = String.valueOf(datas.charAt(j + 119 + shu));
                                                    String wenduxh7 = String.valueOf(datas.charAt(j + 120 + shu));
                                                    String wenduxh8 = String.valueOf(datas.charAt(j + 121 + shu));
                                                    wdxh.append(wenduxh1).append(wenduxh2).append(wenduxh3).append(wenduxh4).append(wenduxh5).append(wenduxh6).append(wenduxh7).append(wenduxh8);
                                                }else {
                                                    //温度信息号强度
                                                    String wenduxh1 = String.valueOf(datas.charAt(j + 66 + shu));
                                                    String wenduxh2 = String.valueOf(datas.charAt(j + 67 + shu));
                                                    String wenduxh3 = String.valueOf(datas.charAt(j + 68 + shu));
                                                    String wenduxh4 = String.valueOf(datas.charAt(j + 69 + shu));
                                                    String wenduxh5 = String.valueOf(datas.charAt(j + 70 + shu));
                                                    String wenduxh6 = String.valueOf(datas.charAt(j + 71 + shu));
                                                    String wenduxh7 = String.valueOf(datas.charAt(j + 72 + shu));
                                                    String wenduxh8 = String.valueOf(datas.charAt(j + 73 + shu));
                                                    wdxh.append(wenduxh1).append(wenduxh2).append(wenduxh3).append(wenduxh4).append(wenduxh5).append(wenduxh6).append(wenduxh7).append(wenduxh8);
                                                }
                                               wd.append(wendu1).append(wendu2).append(wendu3).append(wendu4).append(wendu5).append(wendu6).append(wendu7).append(wendu8);

                                               String wdstr = wd.toString();
                                               String wdstrxh = wdxh.toString();
//                                               System.out.println("获取的型号："+wdstrxh);
                                               temper = temperaturewds(temper,j,wdstr,wdstrxh);
                                           }
                                           if(deviceTypes == CfFinal.Temp_Type_Float || CfFinal.Temp_Type_Float.equals(deviceTypes)){
                                               int shu = j * 3;
                                               String wendu1 = String.valueOf(datas.charAt(j + 18 + shu));
                                               String wendu2 = String.valueOf(datas.charAt(j + 19 + shu));
                                               String wendu3 = String.valueOf(datas.charAt(j + 20 + shu));
                                               String wendu4 = String.valueOf(datas.charAt(j + 21 + shu));

                                               //信号强度
                                               String wenduxh1 = String.valueOf(datas.charAt(j + 42 + shu));
                                               String wenduxh2 = String.valueOf(datas.charAt(j + 43+ shu));
                                               String wenduxh3 = String.valueOf(datas.charAt(j + 44 + shu));
                                               String wenduxh4 = String.valueOf(datas.charAt(j + 45 + shu));
                                               wd.append(wendu1).append(wendu2).append(wendu3).append(wendu4);
                                               wdxh.append(wenduxh1).append(wenduxh2).append(wenduxh3).append(wenduxh4);
                                               String wdstr = wd.toString();
                                               String wdxhstr=wdxh.toString();
                                               temper = temperaturewdwx(temper,j,wdstr,wdxhstr);
                                           }
                                       }
                                       Calculation calcu = new Calculation();
                                       temper = calcu.phase(calculationWdServer,temper,deviceTypes,deviceNum);

                                   }
                               }else if (command == "COMMAND_DEVICE_BUFFER_DATA" || "COMMAND_DEVICE_BUFFER_DATA".equals(command)){
                                   //判断当前接收到的长度
                                   int dateLes = datas.length();

                                   alarh = new AlarmHistory();
                                   alarh.setDeviceNumber(deviceId);
                                   String times = redisUtils.getString("time" + deviceId);
                                   alarh.setTime(times);
                                   //g传感器个数
                                   StringBuilder dnum = new StringBuilder();
                                   String dnum1 = String.valueOf(datas.charAt(0));
                                   String dnum2 = String.valueOf(datas.charAt(1));
                                   dnum.append(dnum1).append(dnum2);
                                   deviceNum = Encoding.gs(dnum.toString());
                                   //总共有组设备温度
                                   int shebei = 0;
                                   if(deviceTypes == CfFinal.Temp_Type_Uint8 || CfFinal.Temp_Type_Uint8.equals(deviceTypes)){
                                       shebei = ((dateLes - 4) / 8)/deviceNum;
                                   }else {
                                       shebei = ((dateLes - 4) / 4)/deviceNum;
                                   }
                                   int  intcc= 0;
                                   if(deviceNum == 12){
                                       intcc = 96;
                                   }else if(deviceNum == 3){
                                       intcc =  24;
                                   }else {
                                       intcc =  48;
                                   }
                                   for (int j = 0; j < shebei; j++) {

                                       if(deviceTypes == CfFinal.Temp_Type_Uint8 || CfFinal.Temp_Type_Uint8.equals(deviceTypes)){

                                           int intc = j * intcc;
                                           for (int k = 0; k < deviceNum; k++) {
                                               StringBuilder wd = new StringBuilder();
                                               int intk = k * 8;
                                               String wendu1 = String.valueOf(datas.charAt(intk+4 +intc));
                                               String wendu2 = String.valueOf(datas.charAt(intk+5 + intc));
                                               String wendu3 = String.valueOf(datas.charAt(intk+6 +intc));
                                               String wendu4 = String.valueOf(datas.charAt(intk+7 + intc));
                                               String wendu5 = String.valueOf(datas.charAt(intk+8 +intc));
                                               String wendu6 = String.valueOf(datas.charAt(intk+9 +intc));
                                               String wendu7 = String.valueOf(datas.charAt(intk+10 +intc));
                                               String wendu8 = String.valueOf(datas.charAt(intk+11 +intc));
                                               wd.append(wendu1).append(wendu2).append(wendu3).append(wendu4).append(wendu5).append(wendu6).append(wendu7).append(wendu8);
                                               String wdstr = wd.toString();
                                               alarh = alarmewds(alarh,k,wdstr);
                                           }
                                       }else {
                                           int intc = j * 24;
                                           for (int k = 0; k < deviceNum; k++) {
                                               StringBuilder wd = new StringBuilder();
                                               int intk = k * 4;
                                               String wendu1 = String.valueOf(datas.charAt(intk+4 +intc));
                                               String wendu2 = String.valueOf(datas.charAt(intk+5 + intc));
                                               String wendu3 = String.valueOf(datas.charAt(intk+6 +intc));
                                               String wendu4 = String.valueOf(datas.charAt(intk+7 + intc));
                                               wd.append(wendu1).append(wendu2).append(wendu3).append(wendu4);
                                               String wdstr = wd.toString();
                                               alarh = alarmewdwxs(alarh,k,wdstr);
                                           }
                                       }
                                       try {
                                           alarmHistoryServer.insert(alarh);
                                       } catch (Exception e) {
                                           e.printStackTrace();
                                       }
                                   }
                               }
                           }
                           if(time == null || "".equals(time)){
                               Object timeStamp = data.get("timeStamp");
                               if(timeStamp != null && !"".equals(timeStamp)){
                                   time = TimeUtile.timeStamp2Date(String.valueOf(timeStamp));
                                   temper.setTime(time);
                               }
                           }
                           if(BoardStatus == null || "".equals(BoardStatus)){
                               Object boardStatuss = data.get("BoardStatus");

                               if(boardStatuss != null && !"".equals(boardStatuss)){
                                   BoardStatus= String.valueOf(boardStatuss);
                                   String event = dto.getEventTime();
                                   eventTimes = TimeUtile.dateTimeStringDx(event);
                               }
                           }
                           if(AntSignal == null || "".equals(AntSignal)){
                               Object antSignal = data.get("AntSignal");
                               if(antSignal != null && !"".equals(antSignal)){
                                   AntSignal= String.valueOf(antSignal);
                                   temper.setAntSignal(AntSignal);
                               }
                           }
                           if(HarvesterStatus == null || "".equals(HarvesterStatus)){
                               Object harvesterStatuss = data.get("HarvesterStatus");
                               if(harvesterStatuss != null && !"".equals(harvesterStatuss)){
                                   HarvesterStatus= String.valueOf(harvesterStatuss);
                               }
                           }
                           if(temperatureAlarm == null || "".equals(temperatureAlarm)){
                               temperatureAlarm = (String)data.get("temperatureAlarm");
                           }else if(temperatureAlarm != null && !"".equals(temperatureAlarm)){
                               if (temperatureAlarm == "Normal" || "Normal".equals(temperatureAlarm) ){
                                   if(Ztype == "" || "".equals(Ztype)){
                                       Ztype="1";
                                   }
                                   ConcurrentFenbu.getConcurrent().shortMessage(deviceId,"0","3","2");
                               }else if (temperatureAlarm == "ALARM_ERROR"  || "ALARM_ERROR".equals(temperatureAlarm)|| temperatureAlarm == "ALARM_CLOSE"  || "ALARM_CLOSE".equals(temperatureAlarm) || temperatureAlarm == "ALARM_UN_ADJUST"  || "ALARM_UN_ADJUST".equals(temperatureAlarm) || temperatureAlarm == "ALARM_INTERFERENCE"  || "ALARM_INTERFERENCE".equals(temperatureAlarm) || temperatureAlarm == "ALARM_Nan"  || "ALARM_Nan".equals(temperatureAlarm)){
                                   if(Ztype == "" || "".equals(Ztype) || Ztype == "1" || "1".equals(Ztype)){
                                       Ztype="3";
                                   }
                                   ConcurrentFenbu.getConcurrent().shortMessage(deviceId,"0","3","2");
                               }else if(temperatureAlarm == "Temperature_Alarm"|| "Temperature_Alarm".equals(temperatureAlarm)){
                                   Ztype = "2";
                                   redisUtils.setString("time"+deviceId,time);
                                   AlartmInfoWend alartwd = new AlartmInfoWend();
                                   alartwd.temperatureAlarm(temper, underTemp, overTemp, alarmInformationServer,deviceId,time);
                               }else if(temperatureAlarm == "Temperature_Warring" || "Temperature_Warring".equals(temperatureAlarm)){
                                   Ztype = "2";
                                   AlartmInfoWend alartwd = new AlartmInfoWend();
                                   redisUtils.setString("time"+deviceId,time);
                                   alartwd.temperatureAlarm(temper, underTemp, overTemp, alarmInformationServer,deviceId,time);
                               }
//                        else if(Ztype == "2" || "2".equals(Ztype)){
//                            AlartmInfoWend alartwd = new AlartmInfoWend();
//                            alartwd.temperatureAlarm(temper, underTemp, overTemp, alarmInformationServer,deviceId,time);
//                        }
                           }
                           if(command != null && !"".equals(command)){
                               if(command == "CMD_INIT" || "CMD_INIT".equals(command)){
                                   PowerOn pon = new PowerOn();
                                   String eventTime = (String) dto.getEventTime();
                                   pon.setTime(TimeUtile.dateTimeStringDx(eventTime));
                                   pon.setName("上电统计");
                                   pon.setDeviceId(deviceId);
                                   try {
                                       powerOnServer.insert(pon);
                                   } catch (Exception e) {
                                       System.out.println("添加异常错误");
                                   }
                               }
                           }
                       }
                   }
                   if(BoardStatus == null || "".equals(BoardStatus)){
                       if(command != "COMMAND_DEVICE_BUFFER_DATA" && !"COMMAND_DEVICE_BUFFER_DATA".equals(command) && command != "CMD_INIT" && !"CMD_INIT".equals(command)){
                           if(Ztype != null && !"".equals(Ztype)){
                               temper.setType(Integer.parseInt(Ztype));
                           }else {
                               temper.setType(1);
                           }
                           temper.setDeviceNumber(deviceId);
                           String message = insetUpdaTem(temperatureServer, temper,String.valueOf(deviceNum));
                           if(message !="false" &&  !"false".equals(message)){
                               ConcurrentFenbu.getConcurrent().setSSwd(deviceId, temper);
                               temper.setNumber(String.valueOf(deviceNum));
                               ConcurrentFenbu.getConcurrent().concurrent(temper,temperaturesServer,time,addTableServer);
//                               ConcurrentFenbu.getConcurrent().concurrent(temper,String.valueOf(deviceNum),temperaturesServer,null);
                           }
                       }
                   }else {
                       AcpEquipment acpe = new AcpEquipment();
                       acpe.setBoardStatus(BoardStatus);
                       acpe.setHarvesterStatus(HarvesterStatus);
                       acpe.setDeviceNumber(deviceId);
                       acpe.setTime(eventTimes);
                       acpe.setInformationNumber(AntSignal);
                       try {
                           acpEquipmentServer.insertUpda(acpe);
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                   }
               }
       }

        /**
             * @Author ZhouNan
             * @Description  添加设备
             * @params
             * @Date 2017/12/27 0027  14:11
             */
        public void deviceAdded(){
            deviceId = (String) jsonObject.get("deviceId");
            try {
                String a = ConcurrentFenbu.getConcurrent().selectByDeviceId(deviceId, equipmentServer,null);

                if(a == "ypt" || "ypt".equals(a)){
                    JSONObject deviceInfo = jsonObject.getJSONObject("deviceInfo");
                    if(deviceInfo != null && !"".equals(deviceInfo)){
                        Equipment equi = new Equipment();
                        equi.setDeviceNumber(deviceId);
                        String deviceType = (String) deviceInfo.get("deviceType");
                        String manufacturerName = (String) deviceInfo.get("manufacturerName");
                        String manufacturerId = (String) deviceInfo.get("manufacturerId");
                        String name = (String) deviceInfo.get("name");
                        String location = (String) deviceInfo.get("location");
                        String model = (String) deviceInfo.get("model");
                        String protocolType = (String) deviceInfo.get("protocolType");
                        if(deviceType != null && !"".equals(deviceType) || name != null && !"".equals(name) || location != null && !"".equals(location)){
                            if (deviceType != null && !"".equals(deviceType)){
                                EquipmentType eqty = new EquipmentType();
                                eqty.setDeviceType(deviceType);
                                if(model != null && !"".equals(model)){
                                    eqty.setModel(model);
                                }
                                if(manufacturerName != null && !"".equals(manufacturerName)){
                                    eqty.setManufacturerName(manufacturerName);
                                }
                                if(manufacturerId != null && !"".equals(manufacturerId)){
                                    eqty.setManufacturerId(manufacturerId);
                                }
                                if(protocolType != null && !"".equals(protocolType)){
                                    eqty.setProtocolType(protocolType);
                                }
                                String message = ConcurrentFenbu.getConcurrent().panduanType(eqty, equipmentTypeServer);
                                if(message != "false" && !"false".equals(message)){
                                    String eqtID = null;
                                    try {
                                        eqtID = equipmentTypeServer.findBydeviceType(deviceType);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    equi.setEptId(eqtID);
                                }
                            }
                            if(location != null && !"".equals(location)){
                                String subId = "";
                                try {
                                    subId  = substationServer.selectBySubstationPY(location);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                if(subId != null && !"".equals(subId)){
                                    equi.setSubstationId(subId);
                                }
                                equi.setAddRess(location);
                                equi.setMute("1");
                            }
                            if(name != null && !"".equals(name)){
                                equi.setDeviceName(name);
                            }
                        }
                        ConcurrentFenbu.getConcurrent().selectByDeviceId(deviceId, equipmentServer,equi);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

         /**
              * @Author ZhouNan
              * @Description  修改设备信息
              * @params
              * @Date 2017/12/27 0027  17:27
              */
         public void deviceInfoChanged(){
            boolean f = true;
            int a = 0;
             deviceId = (String) jsonObject.get("deviceId");
             JSONObject deviceInfo = jsonObject.getJSONObject("deviceInfo");
             if(deviceInfo != null && !"".equals(deviceInfo)){
                 String name = (String) deviceInfo.get("name");
                 String location = (String) deviceInfo.get("location");
                 String deviceType = (String) deviceInfo.get("deviceType");
                 String manufacturerName = (String) deviceInfo.get("manufacturerName");
                 String manufacturerId = (String) deviceInfo.get("manufacturerId");
                 String model = (String) deviceInfo.get("model");
                 String protocolType = (String) deviceInfo.get("protocolType");
                 String mute = (String) deviceInfo.get("mute");

                 if(name != null && !"".equals(name) || location != null && !"".equals(location) || deviceType != null && !"".equals(deviceType)){
                     Equipment equi = new Equipment();
                     equi.setDeviceNumber(deviceId);
                     if(name != null && !"".equals(name)){
                         equi.setDeviceName(name);
                     }
                     if(location != null && !"".equals(location)){
                         String subId = "";
                         try {
                             subId  = substationServer.selectBySubstationPY(location);
                         } catch (Exception e) {
                             e.printStackTrace();
                         }
                         if(subId != null && !"".equals(subId)){
                             equi.setSubstationId(subId);
                         }
                         equi.setAddRess(location);
                     }
                     if (mute != null && !"".equals(mute)){
                         if(mute != "FALSE" && !"FALSE".equals(mute)){
                             equi.setMute("2");
                         }else {
                             equi.setMute("1");
                         }
                     }
                     if(deviceType != null && !"".equals(deviceType)){
                         EquipmentType eqty = new EquipmentType();
                         eqty.setDeviceType(deviceType);
                         if(model != null && !"".equals(model)){
                             eqty.setModel(model);
                         }
                         if(manufacturerName != null && !"".equals(manufacturerName)){
                             eqty.setManufacturerName(manufacturerName);
                         }
                         if(manufacturerId != null && !"".equals(manufacturerId)){
                             eqty.setManufacturerId(manufacturerId);
                         }
                         if(protocolType != null && !"".equals(protocolType)){
                             eqty.setProtocolType(protocolType);
                         }
                         String message = ConcurrentFenbu.getConcurrent().panduanType(eqty, equipmentTypeServer);
                         if(message != "false" && !"false".equals(message)){
                             String eqtID = null;
                             try {
                                 eqtID = equipmentTypeServer.findBydeviceType(deviceType);
                             } catch (Exception e) {
                                 e.printStackTrace();
                             }
                             equi.setEptId(eqtID);
                         }
                     }
                     try {
                         String mm = ConcurrentFenbu.getConcurrent().selectByDeviceId(deviceId, equipmentServer, equi);
//                         equipmentServer.updateByDeviceNumber(equi);
                     } catch (Exception e) {
                         e.printStackTrace();
                     }
                 }
             }
         }


    public Cloudplatform(AlarmInformationServer alarmInformationServer, JSONObject jsonObject, AcpEquipmentServer acpEquipmentServer, AlarmHistoryServer alarmHistoryServer, PowerOnServer powerOnServer, TemperatureServer temperatureServer, CalculationWdServer calculationWdServer, TemperaturesServer temperaturesServer, AddTableServer addTableServer) {
        this.alarmInformationServer = alarmInformationServer;
        this.jsonObject = jsonObject;
        this.acpEquipmentServer=acpEquipmentServer;
        this.alarmHistoryServer=alarmHistoryServer;
        this.powerOnServer=powerOnServer;
        this.temperatureServer=temperatureServer;
        this.calculationWdServer=calculationWdServer;
        this.temperaturesServer=temperaturesServer;
        this.addTableServer=addTableServer;
    }

    public Cloudplatform(JSONObject jsonObject, EquipmentServer equipmentServer, EquipmentTypeServer equipmentTypeServer) {
        this.jsonObject = jsonObject;
        this.equipmentServer = equipmentServer;
        this.equipmentTypeServer = equipmentTypeServer;
    }

    public Cloudplatform(String deviceId) {
        this.deviceId = deviceId;
    }

    public Cloudplatform(String deviceId, EquipmentServer equipmentServer) {
        this.deviceId = deviceId;
        this.equipmentServer = equipmentServer;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public Cloudplatform(JSONObject jsonObject, EquipmentTypeServer equipmentTypeServer) {
        this.jsonObject = jsonObject;
        this.equipmentTypeServer = equipmentTypeServer;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public AlarmInformationServer getAlarmInformationServer() {
        return alarmInformationServer;
    }

    public void setAlarmInformationServer(AlarmInformationServer alarmInformationServer) {
        this.alarmInformationServer = alarmInformationServer;
    }

    public Cloudplatform(String deviceId, EquipmentServer equipmentServer, EquipmentTypeServer equipmentTypeServer) {
        this.deviceId = deviceId;
        this.equipmentServer = equipmentServer;
        this.equipmentTypeServer = equipmentTypeServer;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Cloudplatform(JSONObject jsonObject, EquipmentServer equipmentServer, EquipmentTypeServer equipmentTypeServer, SubstationServer substationServer) {
        this.jsonObject = jsonObject;
        this.equipmentServer = equipmentServer;
        this.equipmentTypeServer = equipmentTypeServer;
        this.substationServer = substationServer;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public EquipmentServer getEquipmentServer() {
        return equipmentServer;
    }

    public void setEquipmentServer(EquipmentServer equipmentServer) {
        this.equipmentServer = equipmentServer;
    }

    public EquipmentTypeServer getEquipmentTypeServer() {
        return equipmentTypeServer;
    }


    public void setEquipmentTypeServer(EquipmentTypeServer equipmentTypeServer) {
        this.equipmentTypeServer = equipmentTypeServer;
    }

    public Cloudplatform(JSONObject jsonObject, EquipmentServer equipmentServer) {
        this.jsonObject = jsonObject;
        this.equipmentServer = equipmentServer;
    }

    public Cloudplatform(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public static void main(String[] args) throws ParseException {
//        String str =  "ot":1,"hasMore":0,"deviceID":446002085},"eventTime":"20180201T035846Z","serviceId":"ServieBasic"},{"serviceType":"ServieCommand","data":{"data":"06004296D96B429523DC42948E2E4290CC06428F4E9B4294FE1642968E524294D48442944D3F429090CB428F062C4294A9F8429643384294ADDC","command":"COMMAND_DEVICE_BUFFER_DATA","dataLen":58},"eventTime":"20180201T035846Z","serviceId":"ServieCommand"}],"deviceId":"e31a7dc4-903e-4fde-9060-72a0602e5d17","gatewayId":"e31a7dc4-903e-4fde-9060-72a0602e5d17"}";
//             String str = "{\"request\": [AA,72,00,00,32,08,8D,03,20,62,33,99],\"services\":[{\"serviceType\":\"ServieBasic\",\"data\":{\"deviceType\":1,\"timeStamp\":305419896,\"isRoot\":1,\"hasMore\":1,\"deviceID\":446002085},\"eventTime\":\"20180208T081437Z\",\"serviceId\":\"ServieBasic\"},{\"serviceType\":\"ServieCommand\",\"data\":{\"data\":\"0607FF419942D142531685FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF4252FE1BFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF4252F5F8FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF4252FE1BFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF42532ADDFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF42534347FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF42532EEFFFFFFFFF42A5624BFFFFFFFFFFFFFFFFFFFFFFFF424BF7E9FFFFFFFFFFFFFFFF4197591C425363D5FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF4198190942534B6BFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF419A130842533F36FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF419A00974253D875FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF4199C8274254817BFFFFFFFF424BE919FFFFFFFFFFFFFFFF41995CD1424CA280FFFFFFFF424CD8E3FFFFFFFFFFFFFFFF41986674424D4BFCFFFFFFFF424C414FFFFFFFFFFFFFFFFF419A9699424DE0D5FFFFFFFF424CEF0BFFFFFFFFFFFFFFFF419AA3F3424CA280FFFFFFFF424D0101FFFFFFFFFFFFFFFF4198225E424C8E28FFFFFFFF424D11D3FFFFFFFFFFFFFFFF41994F41424D0026FFFFFFFF424C5892FFFFFFFFFFFFFFFF41986274424C923AFFFFFFFF424D053BFFFFFFFFFFFFFFFFFFFFFFFF424C86\",\"command\":\"COMMAND_DEVICE_BUFFER_DATA\",\"dataLen\":482},\"eventTime\":\"20180208T081437Z\",\"serviceId\":\"ServieCommand\"}],\"deviceId\":\"cfc5ca5f-c806-45ba-a90f-36f59d01bd9b\",\"gatewayId\":\"cfc5ca5f-c806-45ba-a90f-36f59d01bd9b\"}";
//             String str = "{\"notifyType\":\"deviceDatasChanged\",\"services\":[{\"serviceType\":\"ServieBasic\",\"data\":{\"deviceType\":1,\"timeStamp\":1519577091,\"isRoot\":1,\"hasMore\":0,\"deviceID\":446002085},\"eventTime\":\"20180225T164759Z\",\"serviceId\":\"ServieBasic\"},{\"serviceType\":\"ServieAlarm\",\"data\":{\"temperatureAlarm\":\"Temperature_Alarm\"},\"eventTime\":\"20180225T164759Z\",\"serviceId\":\"ServieAlarm\"},{\"serviceType\":\"ServieCommand\",\"data\":{\"data\":\"0603E81F4003E81F40417F56584181976B4184584D4185CBB7417EF2F447685BC003A90C4F03D46B4F0387034F03A5324F04FED94E02B6864E\",\"command\":\"COMMAND_DEVICE_REPORT\",\"dataLen\":57},\"eventTime\":\"20180225T164759Z\",\"serviceId\":\"ServieCommand\"}],\"deviceId\":\"cfc5ca5f-c806-45ba-a90f-36f59d01bd9b\",\"gatewayId\":\"cfc5ca5f-c806-45ba-a90f-36f59d01bd9b\"}";
//             String str = "{\"notifyType\":\"deviceDatasChanged\",\"services\":[{\"serviceType\":\"ServieBasic\",\"data\":{\"deviceType\":1,\"timeStamp\":1520505831,\"isRoot\":1,\"hasMore\":0,\"deviceID\":446002085},\"eventTime\":\"20180308T104410Z\",\"serviceId\":\"ServieBasic\"},{\"serviceType\":\"ServieAlarm\",\"data\":{\"temperatureAlarm\":\"Normal\"},\"eventTime\":\"20180308T104410Z\",\"serviceId\":\"ServieAlarm\"},{\"serviceType\":\"ServieCommand\",\"data\":{\"data\":\"0603E81F4003E81F4042047D0F41FB234B41F6CF1141EDC68D41E95A11FFFFFFFF0000B04D0000C84D0000C04D0000C04D0000C04D0000804F\",\"command\":\"COMMAND_DEVICE_REPORT\",\"dataLen\":57},\"eventTime\":\"20180308T104410Z\",\"serviceId\":\"ServieCommand\"}],\"deviceId\":\"78c0d660-7c8c-4107-8e66-500b32aa6241\",\"gatewayId\":\"78c0d660-7c8c-4107-8e66-500b32aa6241\"}";
//             String str = "{\"notifyType\":\"deviceDatasChanged\",\"services\":[{\"serviceType\":\"ServieBasic\",\"data\":{\"deviceType\":1,\"timeStamp\":305419896,\"isRoot\":1,\"hasMore\":0,\"deviceID\":446002085},\"eventTime\":\"20180201T035846Z\",\"serviceId\":\"ServieBasic\"},{\"serviceType\":\"ServieCommand\",\"data\":{\"data\":\"06004296D96B429523DC42948E2E4290CC06428F4E9B4294FE1642968E524294D48442944D3F429090CB428F062C4294A9F8\",\"command\":\"COMMAND_DEVICE_BUFFER_DATA\",\"dataLen\":58},\"eventTime\":\"20180201T035846Z\",\"serviceId\":\"ServieCommand\"}],\"deviceId\":\"e31a7dc4-903e-4fde-9060-72a0602e5d17\",\"gatewayId\":\"e31a7dc4-903e-4fde-9060-72a0602e5d17\"}";
//             String str = "{\"notifyType\":\"deviceDatasChanged\",\"services\":[{\"serviceType\":\"ServieBasic\",\"data\":{\"deviceType\":1,\"timeStamp\":1521010598,\"isRoot\":1,\"hasMore\":0,\"deviceID\":446002085},\"eventTime\":\"20180314T065643Z\",\"serviceId\":\"ServieBasic\"},{\"serviceType\":\"ServieAlarm\",\"data\":{\"temperatureAlarm\":\"Temperature_Alarm\"},\"eventTime\":\"20180314T065643Z\",\"serviceId\":\"ServieAlarm\"},{\"serviceType\":\"ServieCommand\",\"data\":{\"AntSignal\":23,\"data\":\"0603E81F4003E81F4041A969D9FFFFFFFFFFFFFFFF430048D541A29B7A41A0C76000000019FFFFFFFFFFFFFFFF000000110000000E0000000F\",\"command\":\"COMMAND_DEVICE_REPORT\",\"dataLen\":57},\"eventTime\":\"20180314T065643Z\",\"serviceId\":\"ServieCommand\"}],\"deviceId\":\"2c1ba0d9-3409-459c-808c-3b744f3dd12c\",\"gatewayId\":\"2c1ba0d9-3409-459c-808c-3b744f3dd12c\"}";
             String str = "{\"notifyType\":\"deviceDatasChanged\",\"services\":[{\"serviceId\":\"ServieBasic\",\"serviceType\":\"ServieBasic\",\"data\":{\"deviceID\":446002085,\"deviceType\":1,\"isRoot\":1,\"hasMore\":0,\"timeStamp\":1528781471},\"eventTime\":\"20180612T063944Z\"},{\"serviceId\":\"ServieAlarm\",\"serviceType\":\"ServieAlarm\",\"data\":{\"temperatureAlarm\":\"Normal\"},\"eventTime\":\"20180612T063944Z\"},{\"serviceId\":\"ServieCommand\",\"serviceType\":\"ServieCommand\",\"data\":{\"command\":\"COMMAND_DEVICE_REPORT\",\"AntSignal\":1,\"cirTemp\":68.05,\"dataLen\":57,\"data\":\"0603E81F4003E81F404235DABE422D0FDA422DF1D8421DBE2C421EE003FFFFFFFF0000A04D0000C84D0000C84D0000C04D0000C04D0000804F\"},\"eventTime\":\"20180612T063944Z\"}],\"deviceId\":\"78c0d660-7c8c-4107-8e66-500b32aa6241\",\"gatewayId\":\"78c0d660-7c8c-4107-8e66-500b32aa6241\"}";

//             new Cloudplatform(JSONObject.parseObject(str)).analysis();
        String jsa = JSONObject.parseObject(str).getString("services");
        List<DeviceServiceData>  list2 = JSONArray.parseArray(jsa,DeviceServiceData.class);
        System.out.println("获取的jsa:"+list2);
//        String str = "0C00FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF42AD5DE742AE249242AEED4C42AD4A4542B23490FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF42AD5DE742AE249242AEED4C42AD237742B17A4EFFFFFFFF";
//        int shebei = ((str.length() - 4) / 8) /3;
//        for (int j = 0; j < shebei; j++) {
//
//                int intc = j * 24;
//                for (int k = 0; k < 3; k++) {
//                    StringBuilder wd = new StringBuilder();
//                    int intk = k * 8;
//                    String wendu1 = String.valueOf(str.charAt(intk+4 +intc));
//                    String wendu2 = String.valueOf(str.charAt(intk+5 + intc));
//                    String wendu3 = String.valueOf(str.charAt(intk+6 +intc));
//                    String wendu4 = String.valueOf(str.charAt(intk+7 + intc));
//                    String wendu5 = String.valueOf(str.charAt(intk+8 +intc));
//                    String wendu6 = String.valueOf(str.charAt(intk+9 +intc));
//                    String wendu7 = String.valueOf(str.charAt(intk+10 +intc));
//                    String wendu8 = String.valueOf(str.charAt(intk+11 +intc));
//                    wd.append(wendu1).append(wendu2).append(wendu3).append(wendu4).append(wendu5).append(wendu6).append(wendu7).append(wendu8);
//                    String wdstr = wd.toString();
//                    System.out.println("获取的数据:"+wdstr+"---"+j);
//            }
//            }


//        System.out.println("获取的参数:"+((str.length() - 4) / 8)/12);
//        RedisUtils.delObject("2c1ba0d9-3409-459c-808c-3b744f3dd12c");
//        deviceAdded();
    }

    public SubstationServer getSubstationServer() {
        return substationServer;
    }

    public AcpEquipmentServer getAcpEquipmentServer() {
        return acpEquipmentServer;
    }

    public void setAcpEquipmentServer(AcpEquipmentServer acpEquipmentServer) {
        this.acpEquipmentServer = acpEquipmentServer;
    }

    public void setSubstationServer(SubstationServer substationServer) {
        this.substationServer = substationServer;
    }

    //26所温度解析的获取
    public Temperature temperaturewds(Temperature temper,int j,String wdstr,String wdstrxh){
        switch (j){
            case 0:
                temper.setInA(wdstr);
                temper.setInAxh(wdssXh(wdstrxh));
                break;
            case 1:
                temper.setInB(wdstr);
                temper.setInBxh(wdssXh(wdstrxh));
                break;
            case 2:
                temper.setInC(wdstr);
                temper.setInCxh(wdssXh(wdstrxh));
                break;
            case 3:
                temper.setOutA(wdstr);
                temper.setOutAxh(wdssXh(wdstrxh));
                break;
            case 4:
                temper.setOutB(wdstr);
                temper.setOutBxh(wdssXh(wdstrxh));
                break;
            case 5:
                temper.setOutC(wdstr);
                temper.setOutCxh(wdssXh(wdstrxh));
                break;
            case 6:
                temper.setContactA(wdstr);
                temper.setContactAxh(wdssXh(wdstrxh));
                break;
            case 7:
                temper.setContactB(wdstr);
                temper.setContactBxh(wdssXh(wdstrxh));
                break;
            case 8:
                temper.setContactC(wdstr);
                temper.setContactCxh(wdssXh(wdstrxh));
                break;
            case 9:
                temper.setContactD(wdstr);
                temper.setContactDxh(wdssXh(wdstrxh));
                break;
            case 10:
                temper.setContactE(wdstr);
                temper.setContactExh(wdssXh(wdstrxh));
                break;
            case 11:
                temper.setContactF(wdstr);
                temper.setContactFxh(wdssXh(wdstrxh));
                break;
            default:break;
        }
        return temper;
//       ;switch (j){
//            case 0:
//                temper.setInA(wdss(wdstr,null));
//                break;
//            case 1:
//                temper.setInB(wdss(wdstr,null));
//                break;
//            case 2:
//                temper.setInC(wdss(wdstr,null));
//                break;
//            case 3:
//                temper.setOutA(wdss(wdstr,null));
//                break;
//            case 4:
//                temper.setOutB(wdss(wdstr,null));
//                break;
//            case 5:
//                temper.setOutC(wdss(wdstr,temper));
//                break;
//            case 6:
//                temper.setContactA(wdss(wdstr,null));
//                break;
//            case 7:
//                temper.setContactB(wdss(wdstr,null));
//                break;
//            case 8:
//                temper.setContactC(wdss(wdstr,null));
//                break;
//            case 9:
//                temper.setContactD(wdss(wdstr,null));
//                break;
//            case 10:
//                temper.setContactE(wdss(wdstr,null));
//                break;
//            case 11:
//                temper.setContactF(wdss(wdstr,null));
//                break;
//            default:break;
//        }
//        return temper;
    }

    //26所温度解析的获取
    public AlarmHistory alarmewds(AlarmHistory alarm,int j,String wdstr){
        switch (j){
            case 0:
                alarm.setInA(wdss(wdstr));
                break;
            case 1:
                alarm.setInB(wdss(wdstr));
                break;
            case 2:
                alarm.setInC(wdss(wdstr));
                break;
            case 3:
                alarm.setOutA(wdss(wdstr));
                break;
            case 4:
                alarm.setOutB(wdss(wdstr));
                break;
            case 5:
                alarm.setOutC(wdss(wdstr));
                break;
            case 6:
                alarm.setContactA(wdss(wdstr));
                break;
            case 7:
                alarm.setContactB(wdss(wdstr));
                break;
            case 8:
                alarm.setContactC(wdss(wdstr));
                break;
            case 9:
                alarm.setContactD(wdss(wdstr));
                break;
            case 10:
                alarm.setContactE(wdss(wdstr));
                break;
            case 11:
                alarm.setContactF(wdss(wdstr));
                break;
            default:break;
        }
        return alarm;
    }

    //微星温度解析的获取
    public Temperature temperaturewdwx(Temperature temper,int j,String wdstr,String wdxhstr){
        switch (j){
            case 0:
                temper.setInA(wdstr);
                temper.setInAxh(wdwxXh(wdxhstr));
                break;
            case 1:
                temper.setInB(wdstr);
                temper.setInBxh(wdwxXh(wdxhstr));
                break;
            case 2:
                temper.setInC(wdstr);
                temper.setInCxh(wdwxXh(wdxhstr));
                break;
            case 3:
                temper.setOutA(wdstr);
                temper.setOutAxh(wdwxXh(wdxhstr));
                break;
            case 4:
                temper.setOutB(wdstr);
                temper.setOutBxh(wdwxXh(wdxhstr));
                break;
            case 5:
                temper.setOutC(wdstr);
                temper.setOutCxh(wdwxXh(wdxhstr));
                break;
            case 6:
                temper.setContactA(wdstr);
                temper.setContactAxh(wdwxXh(wdxhstr));
                break;
            case 7:
                temper.setContactB(wdstr);
                temper.setContactBxh(wdwxXh(wdxhstr));
                break;
            case 8:
                temper.setContactC(wdstr);
                temper.setContactCxh(wdwxXh(wdxhstr));
                break;
            case 9:
                temper.setContactD(wdstr);
                temper.setContactDxh(wdwxXh(wdxhstr));
                break;
            case 10:
                temper.setContactE(wdstr);
                temper.setContactExh(wdwxXh(wdxhstr));
                break;
            case 11:
                temper.setContactF(wdstr);
                temper.setContactFxh(wdwxXh(wdxhstr));
                break;
            default:break;
        }
        return temper;

    }
    //26s温度解析
   public String wdss(String wind){
             String windS = "";
               windS = Encoding.intpaFour(wind);

       return windS;
   }
   //26s温度解析信号
   public String wdssXh(String wind){
             String windS = "";
               windS = Encoding.intpaFourXh(wind);

       return windS;
   }

   //微星温度解析
   public String wdwx(String wind){
             String windS = "";
     try {
               windS = Encoding.intpa(wind);
       } catch (Exception e) {
           e.printStackTrace();
       }
       System.out.println("温度："+windS);
    return windS;
   }

    //26所温度解析的获取
    public AlarmHistory alarmewdwxs(AlarmHistory alarm,int j,String wdstr){
        switch (j){
            case 0:
                alarm.setInA(wdwx(wdstr));
                break;
            case 1:
                alarm.setInB(wdwx(wdstr));
                break;
            case 2:
                alarm.setInC(wdwx(wdstr));
                break;
            case 3:
                alarm.setOutA(wdwx(wdstr));
                break;
            case 4:
                alarm.setOutB(wdwx(wdstr));
                break;
            case 5:
                alarm.setOutC(wdwx(wdstr));
                break;
            case 6:
                alarm.setContactA(wdwx(wdstr));
                break;
            case 7:
                alarm.setContactB(wdwx(wdstr));
                break;
            case 8:
                alarm.setContactC(wdwx(wdstr));
                break;
            case 9:
                alarm.setContactD(wdwx(wdstr));
                break;
            case 10:
                alarm.setContactE(wdwx(wdstr));
                break;
            case 11:
                alarm.setContactF(wdwx(wdstr));
                break;
            default:break;
        }
        return alarm;
    }
 /**
      * @Author ZhouNan
      * @Description 微星信号解析
      * @params
      * @Date 2018/3/27 0027  09:34
      */
   public String wdwxXh(String num){
       String number = Encoding.wexin(num);
       return number;
   }

    //添加/修改温度信息
    public String insetUpdaTem(TemperatureServer temperatureServer,Temperature temperature,String dernum){
        String message = "";
        try {
//           ConcurrentFenbu.getConcurrent().concurrent(temper,String.valueOf(deviceNum),temperaturesServer,null);
            if(dernum != "12" && !"12".equals(dernum)){
                message = temperatureServer.selectByTemu(temperature.getDeviceNumber(),temperature);
            }else {
                return "true";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
            return message;
    }

    //12个修改温度
    public Temperature insetUpdaTemsC(Temperature temperature,String number){
        String message = "";
        try {
                DecimalFormat decimalFormat=new DecimalFormat("0.00");
                Calculation calcu = new Calculation();
                Float sjs = calcu.sjzhq();
                temperature.setInA(decimalFormat.format(Float.parseFloat(temperature.getInA())+sjs));
                temperature.setInB(decimalFormat.format(Float.parseFloat(temperature.getInB())+sjs));
                temperature.setInC(decimalFormat.format(Float.parseFloat(temperature.getInC())+sjs));
                temperature.setOutA(decimalFormat.format(Float.parseFloat(temperature.getOutA())+sjs));
                temperature.setOutB(decimalFormat.format(Float.parseFloat(temperature.getOutB())+sjs));
                temperature.setOutC(decimalFormat.format(Float.parseFloat(temperature.getOutC())+sjs));
            if(number != "6" && !"6".equals(number)){
                temperature.setContactA(decimalFormat.format(Float.parseFloat(temperature.getContactA())+sjs));
                temperature.setContactB(decimalFormat.format(Float.parseFloat(temperature.getContactB())+sjs));
                temperature.setContactC(decimalFormat.format(Float.parseFloat(temperature.getContactC())+sjs));
                temperature.setContactD(decimalFormat.format(Float.parseFloat(temperature.getContactD())+sjs));
                temperature.setContactE(decimalFormat.format(Float.parseFloat(temperature.getContactE())+sjs));
                temperature.setContactF(decimalFormat.format(Float.parseFloat(temperature.getContactF())+sjs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return temperature;
    }


}
