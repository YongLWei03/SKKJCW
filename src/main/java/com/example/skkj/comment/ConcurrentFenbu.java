package com.example.skkj.comment;

import org.apache.log4j.Logger;
import com.example.skkj.dingxin.HttpsUtil;
import com.example.skkj.dingxin.LoginUtile;
import com.example.skkj.entity.*;
import com.example.skkj.mapper.CommandInformationMapper;
import com.example.skkj.mapper.EquipmentMapper;
import com.example.skkj.service.*;
import com.example.skkj.timing.JdbcTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 简单的TCP UDP 并发处理
 * */
public class ConcurrentFenbu {
    protected static Logger logger = Logger.getLogger(ConcurrentFenbu.class);
	private static ConcurrentFenbu concurrent = null;
	private static int i;
	
	private static AlarmInformationServer alarmInformationServer;

	private static EquipmentMapper equipmentMapper;

	private  static EquipmentTypeServer equipmentTypeServer;

	private static RedisUtils redisUtils;
	static{
		 concurrent = new ConcurrentFenbu();
        redisUtils = new RedisUtils();
		 i=30;
	}
	/**
	 *
	 *历史数据存储
	 * @Author ZhouNan
	 * @Description
	 * @params deviceNumber 设备号
	 * @params temper 数据类
	 * @Date 15:47 2017/12/12 0012
	 */
	public  String concurrent(Temperature temper, TemperaturesServer temperaturesServer,String time,AddTableServer addTableServer) {
        RedisUtils redis = new RedisUtils();
        String tablenames = redis.getString("tablenamesc");
//        String tablenames = redis.getString("tablename");
        AddTable addtables = redis.getObejct("addtablesc", AddTable.class);
//        AddTable addtables = redis.getObejct("addtable", AddTable.class);
        String d2 = null;

        if(tablenames == null || "".equals(tablenames)){
            try {
                AddTable addTable = addTableServer.selectByTime();
                tablenames = addTable.getTableName();
            } catch (Exception e) {
                logger.error("查看最近创建的表addTable:"+e.getMessage(),e);
            }
        }
        if(addtables != null && !"".equals(addtables)){
             d2 = addtables.getTime();
        }else {
            try {
                addtables = addTableServer.selectByEndTime();
                d2 = addtables.getTime();
            } catch (Exception e) {
                logger.error("查看最近结束表的时间:"+e.getMessage(),e);
            }
        }
        tablenames = tableNumber(tablenames,temperaturesServer,addTableServer);
        int a = TimeUtile.compareToTime(time, d2);
        if(a<0 || a == 0){
            tablenames = addtables.getTableName();
            temper.setObject(tablenames);
        }else {
            temper.setObject(tablenames);
        }
        try {
            temperaturesServer.insert(temper);
        } catch (Exception e) {
            e.printStackTrace();
        }
            return null;
    }
//    public  String concurrent(Temperature temper, String numberDevices, TemperaturesServer temperaturesServer,FileLocationServer fileLocationServer) {
//        //获取出来数据是否存在
//        List<Temperature> tempersList = new LinkedList<Temperature>();
//        if (temper == null || "".equals(temper)) {
//            StringBuilder fileName = new StringBuilder();
//            try {
//                String time = TimeUtile.dateTimess();
//                String time2 = TimeUtile.TimeJ(time);
//                List<Temperature> tempersLists = temperaturesServer.selectByDinshisTime(time2, numberDevices);
//                if(tempersLists != null && !"".equals(tempersLists) && tempersLists.size()>0){
//                    String fileNames = fileLocationServer.selectByTime(time2);
//                    if(fileNames != null && !"".equals(fileNames)){
//                        FileStoreUtile.fileStorex(tempersLists,numberDevices,fileNames);
//                        temperaturesServer.deletByTime(time2,numberDevices);
//                    }
//                }
//                tempersList  = temperaturesServer.selectByDinshisTime(time,numberDevices);
//                if(tempersList != null && !"".equals(tempersList) && tempersList.size()>0){
//                    StringBuilder fileNames = FileStoreUtile.fileStore(tempersList, numberDevices);
//                    fileName.append(fileNames);
//                    temperaturesServer.deletByTime(time,numberDevices);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return fileName.toString();
//        } else {
//            try {
//                temperaturesServer.insert(temper);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
     /**
          * @Author ZhouNan
          * @Description 计算出还剩下多少数据添加
          * @params tablenames 表名
          * @Date 2018/4/26 0026  09:27
          */
     public synchronized String tableNumber(String tablenames,TemperaturesServer temperaturesServer,AddTableServer addTableServer){
         RedisUtils redis = new RedisUtils();
         String tableNumbers = redis.getString("tableNumbersc");
//         String tableNumbers = redis.getString("tableNumber");
         int tableNumber = 0;
         if(tableNumbers != null && !"".equals(tableNumbers)){
             tableNumber = Integer.parseInt(tableNumbers);
         }else {
             try {
                 int tableNumbersss = temperaturesServer.selectCount(tablenames);
                 if(tableNumbersss <= 10000000){
                     tableNumber = 10000000-tableNumbersss;
                 }
             } catch (Exception e) {
                 logger.error("判断是否需要重新创建表tableNumbersc："+e.getMessage(),e);
             }
         }
         if(tableNumber > 0){
             String tableNumberss = String.valueOf(tableNumber - 1);
//
             //当前数据大于0
             if(tableNumberss == "0" || "0".equals(tableNumberss)){
                 JdbcTime jdbtime = new JdbcTime();
                 String temble = jdbtime.jdbcUp(temperaturesServer,addTableServer,tablenames);
                 if(temble != "false" && !"false".equals(temble)){
//                     redis.setString("tableNumber","10000000");
                     redis.setString("tableNumbersc","10000000");
                     tablenames = temble;
                 }
             }else {
//                   redis.setString("tableNumber",tableNumberss);
                   redis.setString("tableNumbersc",tableNumberss);
             }
         }else {
             JdbcTime jdbtime = new JdbcTime();
             String temble = jdbtime.jdbcUp(temperaturesServer,addTableServer,tablenames);
             if(temble != "false" && !"false".equals(temble)){
//                 redis.setString("tableNumber","10000000");
                 redis.setString("tableNumbersc","10000000");
                 tablenames = temble;
             }
         }
            return tablenames;
     }


	/**
	 *
	 *实时数据采集
	 * @Author ZhouNan
	 * @Description
	 * @params deviceNumber 设备号
	 * @params temper 数据类
	 * @Date 15:47 2017/12/12 0012
	 */
	public synchronized Map<String,Object> realTime(String deviceNumber, String time, String ljcj, TemperatureServer temperatureServer){
        Map<String, Object> mapte = new HashMap<String, Object>();
            try {
                if(deviceNumber != null && !"".equals(deviceNumber)){
                    Temperature temp = redisUtils.getObejct(deviceNumber, Temperature.class);

                    if(temp != null && !"".equals(temp) && time != null && !"".equals(time)){
                        if(ljcj != "0" && !"0".equals(ljcj) ){
                            String temTime = temp.getTime();
                            if(temTime != time && !time.equals(temTime)){
                                mapte.put("temp",temp);
                            }else {
                                mapte.put("temp","false");
                            }
                        }else {
                            mapte.put("temp",temp);
                        }
                    }else {
                        Temperature temuipe = temperatureServer.selectByTemuSb(deviceNumber);
                        if(temuipe != null && !"".equals(temuipe)){
                            if(time != null && !"".equals(time)){
                                String temTime = temuipe.getTime();
                                if(temTime != time && !time.equals(temTime)){
                                    mapte.put("temp",temuipe);
                                }else {
                                    mapte.put("temp","false");
                                }
                            }else {
                                mapte.put("temp",temuipe);
                            }

                            redisUtils.setObjectDS(deviceNumber,600,temuipe);
                        }else {
                            mapte.put("temp","false");
                        }
                    }
                }else {
                    mapte.put("temp","false");
                }
                return mapte;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
	}

	//存实时数据
    public  void setSSwd(String deviceNumber, Temperature temper){
        redisUtils.setObjectDS(deviceNumber,600,temper);
        redisUtils.delObject("userIdList"+deviceNumber);
    }


	/**
	 * 添加报警信息是否存在当前设备
	 * @Author ZhouNan
	 * @Description
	 * @params deviceNumber 设备号
	 * @params serialNumber 设备序号
	 * @Date 09:35 2017/12/20 0020
	 */
	public synchronized Equipment redsiEqui(String deviceNumber,String serialNumber,EquipmentMapper equipmentMapper){

			StringBuilder builder = new StringBuilder(deviceNumber);
			if(serialNumber != null && !"".equals(serialNumber)){
				builder.append(serialNumber);
			}
			Equipment equip = redisUtils.getObejct(builder.toString(), Equipment.class);
			if(equip != null){
				return equip;
			}else {
				Equipment equipment = new Equipment();
				equipment.setDeviceNumber(deviceNumber);
				try {
					equipment = equipmentMapper.selectByEquipmentxi(equipment);
					if(equipment != null){
                        redisUtils.setObject(builder.toString(),equipment);
                        return equipment;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
				return null;
			}
	}

	 /**
	      * @Author ZhouNan
	      * @Description 判断是否添加d当前设备型号
	      * @params
	      * @Date 2017/12/27 0027  16:01
	      */
    public synchronized String panduanType(EquipmentType equipmentType,EquipmentTypeServer equipmentTypeServer){
            String deviceType = equipmentType.getDeviceType();
            String equipmentTypes = redisUtils.getString(deviceType);
            if(equipmentTypes == null || "".equals(equipmentTypes)){
                try {
                    String equiId = equipmentTypeServer.findBydeviceType(deviceType);
                    if(equiId != null && !"".equals(equiId)){
                        redisUtils.setString(deviceType,deviceType);
                        return "true";
                    }else {
                        String message = equipmentTypeServer.insert(equipmentType);
                        if(message != "false" && !"false".equals(message)){
                            redisUtils.setString(deviceType,deviceType);
                            return "true";
                        }else {
                            return "false";
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                return "cz";
            }
            return "false";

        }

     /**
          * @Author ZhouNan
          * @Description 云平台accessToken
          * @params 当前传入的token state 1.定时获取
          * @Date 2017/12/28 0028  11:16
          */
     public synchronized String accessTokenPt(HttpsUtil httpsUtil,String state){
//                 if(state != "1" && !"1".equals(state)){
//                     String token = redisUtils.getString("Token");
//                     if(token != null && !"".equals(token)){
//                         return token;
//                     }else {
//                         try {
//                             token = LoginUtile.login(httpsUtil);
//                             redisUtils.setStringDS("Token",300,token);
//                             return token;
//                         } catch (Exception e) {
//                             e.printStackTrace();
//                         }
//                     }
//                 }else {
//                     String token = null;
//                     try {
//                         token = LoginUtile.login(httpsUtil);
//                     } catch (Exception e) {
//                         e.printStackTrace();
//                     }
//                     redisUtils.setStringDS("Token",300,token);
//                     return token;
//                 }

//         生产平台
         if(state != "1" && !"1".equals(state)){
             String token = redisUtils.getString("Tokensc");
             if(token != null && !"".equals(token)){
                 return token;
             }else {
                 try {
                     token = LoginUtile.login(httpsUtil);
                     redisUtils.setStringDS("Tokensc",300,token);
                     return token;
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }
         }else {
             String token = null;
             try {
                 token = LoginUtile.login(httpsUtil);
             } catch (Exception e) {
                 e.printStackTrace();
             }
             redisUtils.setStringDS("Tokensc",300,token);
             return token;
         }
             return null;
    }
    //判读是否存在当前设备
    public synchronized String selectByDeviceId(String deviceId,EquipmentServer equipmentServer,Equipment equipment){
            String message = "";
            try {
                int a = equipmentServer.selectByDeviceNumber(deviceId);

                if(a > 0 && a != -1 && equipment != null && !"".equals(equipment)){
                    equipment.setDeviceNumber(deviceId);
                    message  = equipmentServer.updateByDeviceNumber(equipment);
                    return message;
                }else if (a < 1 && a != -1 && equipment != null && !"".equals(equipment)){
                    equipment.setDeviceNumber(deviceId);
                    message = equipmentServer.insetEqui(equipment);
                    return message;
                }else if (a < 1 && a != -1 && equipment == null || a < 1 && a != -1 && "".equals(equipment)){
                    return "ypt";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
    }

    /**
     * @Author ZhouNan
     * @Description 设置板级参数
     * @params 1成功 2.送达 3.正在发送命令,4.超时 5.失败
     * @Date 2017/12/27 0027  16:01
     */
    public synchronized String boardLevel(String deviceId,CommandInformationMapper commandInformationMapper){
            String boardLevelcz = redisUtils.getString("boardLevel" + deviceId);
                if(boardLevelcz != null && !"".equals(boardLevelcz)){
                    //可以进行命令成功或者在执行命令不能重复
                    if( boardLevelcz == "3" || "3".equals(boardLevelcz)){
                            return "false";
                    }else {
                        redisUtils.setString("boardLevel"+deviceId,"3");
                    }
                }else {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("deviceNumber",deviceId);
                    map.put("name","设置参数命令");
                    String type ="";
                    try {
                        type = commandInformationMapper.findByDeviceNumber(map);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(type != null &&  !"".equals(type)){
                        if(type == "3" || "3".equals(type)){
                            redisUtils.setString("boardLevel"+deviceId,type);
                            return "false";
                        }else {
                            redisUtils.setString("boardLevel"+deviceId,"3");
                            return "true";
                        }
                    }else {
                        redisUtils.setString("boardLevel"+deviceId,"3");
                        return "true";
                    }
                }
              return "true";
    }

    /**
     * @Author ZhouNan
     * @Description 板级参数查询
     * @params
     * @Date 2017/12/27 0027  16:01
     */
    public synchronized String selectBoar(String deviceId,CommandInformationMapper commandInformationMapper){

            String selectBoarcz = redisUtils.getString("selectBoar" + deviceId);
                if(selectBoarcz != null && !"".equals(selectBoarcz)){
                    if(selectBoarcz == "3" || "3".equals(selectBoarcz)){
                            return "false";
                    }else {
                        redisUtils.setString("selectBoar"+deviceId,"3");
                    }
                }else {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("deviceNumber",deviceId);
                    map.put("name","查询板级参数命令");
                    String type ="";
                    try {
                        type = commandInformationMapper.findByDeviceNumber(map);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(type != null &&  !"".equals(type)){
                        if(type == "3" || "3".equals(type)){
                            redisUtils.setString("selectBoar"+deviceId,type);
                            return "false";
                        }else {
                            redisUtils.setString("selectBoar"+deviceId,"3");
                            return "true";
                        }
                    }else {
                        redisUtils.setString("selectBoar"+deviceId,"3");
                        return "true";
                    }
                }
              return "true";
    }
    /**
     * @Author ZhouNan
     * @Description 采样使能
     * @params
     * @Date 2017/12/27 0027  16:01
     */
    public synchronized String samplingEnable(String deviceId,CommandInformationMapper commandInformationMapper){
            String samplingEnablecz = redisUtils.getString("samplingEnable" + deviceId);
                if(samplingEnablecz != null && !"".equals(samplingEnablecz)){
                    if(samplingEnablecz == "3" || "3".equals(samplingEnablecz)|| samplingEnablecz == "2" || "2".equals(samplingEnablecz)){
                            return "false";
                    }else {
                        redisUtils.setString("samplingEnable"+deviceId,"3");
                    }
                }else {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("deviceNumber",deviceId);
                    map.put("name","设置定时采集使能");
                    String type ="";
                    try {
                        type = commandInformationMapper.findByDeviceNumber(map);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(type != null &&  !"".equals(type)){
                        if(type == "3" || "3".equals(type)){
                            redisUtils.setString("samplingEnable"+deviceId,type);
                            return "false";
                        }else {
                            redisUtils.setString("samplingEnable"+deviceId,"3");
                            return "true";
                        }
                    }else {
                        redisUtils.setString("samplingEnable"+deviceId,"3");
                        return "true";
                    }

                }
              return "true";
    }

    /**
     * @Author ZhouNan
     * @Description 采立即采样
     * @params
     * @Date 2017/12/27 0027  16:01
     */
    public synchronized String immediateSampling(String deviceId,CommandInformationMapper commandInformationMapper){

            String immediateSamplingcz = redisUtils.getString("immediateSampling" + deviceId);
            System.out.println("immediateSamplingcz："+immediateSamplingcz);
                if(immediateSamplingcz != null && immediateSamplingcz != ""){
                    if(immediateSamplingcz == "3" || "3".equals(immediateSamplingcz)){
                            return "false";
                    }else {
                        redisUtils.setString("immediateSampling"+deviceId,"3");
                    }
                }else {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("deviceNumber",deviceId);
                    map.put("name","立即采集命令");
                    String type ="";
                    try {
                        type = commandInformationMapper.findByDeviceNumber(map);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(type != null &&  !"".equals(type)){
                        if(type == "3" || "3".equals(type)){
                            redisUtils.setString("immediateSampling"+deviceId,type);
                            return "false";
                        }else {
                            redisUtils.setString("immediateSampling"+deviceId,"3");
                            return "true";
                        }
                    }else {
                        redisUtils.setString("immediateSampling"+deviceId,"3");
                        return "true";
                    }

                }
              return "true";
    }
  /**
     * @Author ZhouNan
     * @Description 效验设置
     * @params
     * @Date 2017/12/27 0027  16:01
     */
    public synchronized String calibration(String deviceId,CommandInformationMapper commandInformationMapper){

            String calibrationz = redisUtils.getString("calibration" + deviceId);
                if(calibrationz != null && calibrationz != ""){
                    if(calibrationz == "3" || "3".equals(calibrationz)){
                            return "false";
                    }else {
                        redisUtils.setString("calibration"+deviceId,"3");
                    }
                }else {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("deviceNumber",deviceId);
                    map.put("name","效验设置");
                    String type ="";
                    try {
                        type = commandInformationMapper.findByDeviceNumber(map);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(type != null &&  !"".equals(type)){
                        if(type == "3" || "3".equals(type)){
                            redisUtils.setString("calibration"+deviceId,type);
                            return "false";
                        }else {
                            redisUtils.setString("calibration"+deviceId,"3");
                            return "true";
                        }
                    }else {
                        redisUtils.setString("calibration"+deviceId,"3");
                        return "true";
                    }

                }
              return "true";
    }
    /**
     * @Author ZhouNan
     * @Description 相位设置
     * @params
     * @Date 2017/12/27 0027  16:01
     */
    public synchronized String algorithm(String deviceId,CommandInformationMapper commandInformationMapper){

            String calibrationz = redisUtils.getString("algorithm" + deviceId);
                if(calibrationz != null && calibrationz != ""){
                    if(calibrationz == "3" || "3".equals(calibrationz)){
                            return "false";
                    }else {
                        redisUtils.setString("algorithm"+deviceId,"3");
                    }
                }else {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("deviceNumber",deviceId);
                    map.put("name","相位设置");
                    String type ="";
                    try {
                        type = commandInformationMapper.findByDeviceNumber(map);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(type != null &&  !"".equals(type)){
                        if(type == "3" || "3".equals(type)){
                            redisUtils.setString("algorithm"+deviceId,type);
                            return "false";
                        }else {
                            redisUtils.setString("algorithm"+deviceId,"3");
                            return "true";
                        }
                    }else {
                        redisUtils.setString("algorithm"+deviceId,"3");
                        return "true";
                    }

                }
              return "true";
    }

      public synchronized void alarh(String deviceId,AlarmHistory alarh) {

              List<AlarmHistory> alarmLists = redisUtils.getList("alarmList" + deviceId, AlarmHistory.class);
              if (alarmLists != null && alarmLists.size() > 0) {
                  alarmLists.add(alarh);
                  redisUtils.setList("alarmList" + deviceId, alarmLists);
              } else {
                  List<AlarmHistory> alarmListss = new ArrayList<AlarmHistory>();
                  alarmListss.add(alarh);
                  redisUtils.setList("alarmList" + deviceId, alarmLists);
              }
      }
       /**
            * @Author ZhouNan
            * @Description  判断80-105度之间的数据是否已经处理
            * @params  oedr 1.查当前状态 ， 2.是添加当前状态
            * @params  type 设置当前状态 2.是没有发送 4.已经发送，3.正常状态,5.如过温度在105发送了信息后降温处理
            * @params  state 0.警报信息，1.确认收到信息发送状态，2.是收到正常信息的状态
            * @Date 2018/2/24 0024  14:20
            */
       public synchronized String shortMessage(String deviceId,String oedr,String type,String state){
               String dxfs = redisUtils.getString("dxfs" + deviceId);
               //判断当前是否是查看状态
               if(oedr != "1" && !"1".equals(oedr)){
                   if(type != "5" && !"5".equals(type)){
                       //判断是否为空
                       if(dxfs != null && !"".equals(dxfs)){
                           //判断是否是报警信息
                           if(state == "0" || "0".equals(state)){
                               if(dxfs == "3" || "3".equals(dxfs)){
                                   redisUtils.setString("dxfs"+deviceId,type);
                                   dxfs = type;
                               }
                           }else if (state == "1" || "1".equals(state)){
                               //是否是确认收到信息
                               if(dxfs != "5" && !"5".equals(dxfs)){
                                   redisUtils.setString("dxfs"+deviceId,type);
                                   dxfs = type;
                               }
                           }else {
                                   //收到正常信息判断
                                   redisUtils.setString("dxfs"+deviceId,type);
                                   dxfs = type;
                           }
                       }else {
                           if(type != "3" && !"3".equals(type)){
                               redisUtils.setString("dxfs"+deviceId,type);
                               dxfs = type;
                           }
                       }
                   }else {
                       redisUtils.setString("dxfs"+deviceId,type);
                       dxfs = type;
                   }
               }
               return dxfs;
       }

	public static ConcurrentFenbu getConcurrent() {
		return concurrent;
	}

	public static void setConcurrent(ConcurrentFenbu concurrent) {
		ConcurrentFenbu.concurrent = concurrent;
	}

    public static AlarmInformationServer getAlarmInformationServer() {
        return alarmInformationServer;
    }

    public static void setAlarmInformationServer(AlarmInformationServer alarmInformationServer) {
        ConcurrentFenbu.alarmInformationServer = alarmInformationServer;
    }

    public static EquipmentMapper getEquipmentMapper() {
        return equipmentMapper;
    }

    public static void setEquipmentMapper(EquipmentMapper equipmentMapper) {
        ConcurrentFenbu.equipmentMapper = equipmentMapper;
    }

    public static EquipmentTypeServer getEquipmentTypeServer() {
        return equipmentTypeServer;
    }

    public static void setEquipmentTypeServer(EquipmentTypeServer equipmentTypeServer) {
        ConcurrentFenbu.equipmentTypeServer = equipmentTypeServer;
    }

    public static void main(String[] args) {
        redisUtils.delObject("userIdList"+"50128242-3288-4eb9-842d-5d2c6589db6c");
    }

    public static int getI() {
        return i;
    }

    public static void setI(int i) {
        ConcurrentFenbu.i = i;
    }
}
