package com.example.skkj.test;

import com.example.skkj.comment.RedisUtils;
import com.example.skkj.entity.AddTable;
import com.example.skkj.entity.Temperature;
import com.example.skkj.mapper.AddTableMapper;
import com.example.skkj.mapper.TemperaturesMapper;
import org.apache.log4j.Logger;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ckanTempLs implements Runnable{
    protected static Logger logger = Logger.getLogger(ckanTempLs.class);
    private AddTableMapper addTableMapper;

    private TemperaturesMapper temperaturesMapper;

    @Override
    public void run() {
        ss();
    }

    public void ss(){
        String startTime = "2018-04-27 00:00:00";
        String endTime = "2018-04-28 00:00:00";
        String deviceNumber = "3629c0a0-8f1a-442a-8237-a802ef6b2467";
        String sort = "0";
        String startTimes = "";
        String endTimes = "";

        List<Temperature> tempList = new ArrayList<Temperature>();
//
        Map<String, Object> map = new HashMap<String, Object>();
        Date date = new Date();
        map.put("startTime","2018-04-27 00:00:00");
        map.put("endTime","2018-04-28 00:00:00");
        map.put("number","6");

        if(sort != null && !"".equals(sort) && sort == "0" || sort != null && !"".equals(sort) && "0".equals(sort)) {
            List<AddTable> addTableList = null;
            try {
                addTableList = addTableMapper.selectByse(startTime, endTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
            RedisUtils redis = new RedisUtils();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            long startTimesa = 0;
            try {
                startTimesa = df.parse(startTime).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long endTimesa = 0;
            try {
                endTimesa = df.parse(endTime).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            List<Temperature> temperatureList = new ArrayList<Temperature>();
            if(addTableList != null && addTableList.size() > 0){
                for (int i = 0; i < addTableList.size(); i++) {
                    AddTable addtable = addTableList.get(i);
                    String tableName = addtable.getTableName();
                    temperatureList = redis.getList(tableName, Temperature.class);
                    if(temperatureList == null || temperatureList.size() <= 0){
                        map.put("object",tableName);
                        try {
                            temperatureList = temperaturesMapper.selectBySE(map);
                        } catch (Exception e) {
                            logger.error("定时查询当前设备历史数据:"+e.getMessage(),e);
                        }
                        redis.setList(tableName,temperatureList);
                    }
                    if(temperatureList != null && temperatureList.size() > 0){
                        for (int j = 0; j < temperatureList.size(); j++) {
                            Temperature tempera = temperatureList.get(j);
                            String deviceNumbers = tempera.getDeviceNumber();
                            String time = tempera.getTime();
                            if(deviceNumber == deviceNumbers || deviceNumber.equals(deviceNumbers)){
                                Date timesa = null;
                                try {
                                    timesa = df.parse(time);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                long tiesa = timesa.getTime();
                                if(tiesa>= startTimesa && tiesa<=endTimesa){
                                    tempList.add(tempera);
                                }
                            }
                        }
                    }
                }
            }else {
                AddTable addtable = null;
                try {
                    addtable = addTableMapper.selectByTime();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                map.put("object",addtable.getTableName());
                try {
                    temperatureList = temperaturesMapper.selectBySE(map);
                } catch (Exception e) {
                    logger.error("定时查询当前设备历史数据:"+e.getMessage(),e);
                }
                if(temperatureList != null && temperatureList.size() > 0){
                    for (int j = 0; j < temperatureList.size(); j++) {
                        Temperature tempera = temperatureList.get(j);
                        String deviceNumbers = tempera.getDeviceNumber();
                        String time = tempera.getTime();
                        if(deviceNumber == deviceNumbers || deviceNumber.equals(deviceNumbers)){
                            Date timesa = null;
                            try {
                                timesa = df.parse(time);
                            } catch (ParseException e) {
                                logger.error("定时任务时间转换错误:"+e.getMessage(),e);
                            }
                            long tiesa = timesa.getTime();
                            if(tiesa>= startTimesa && tiesa<=endTimesa){
                                tempList.add(tempera);
                            }
                        }
                    }
                }
            }
        }
        map.clear();
        if(tempList != null && tempList.size()>0){
            map.put("tempList", tempList);
        }else {
            map.put("tempList", "false");
        }
        Date dates = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("定时查看历史数据:开始时间:"+sdf.format(date)+" 结束时间:"+sdf.format(dates));
    }

    public ckanTempLs(AddTableMapper addTableMapper, TemperaturesMapper temperaturesMapper) {
        this.addTableMapper = addTableMapper;
        this.temperaturesMapper = temperaturesMapper;
    }
}
