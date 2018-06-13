package com.example.skkj.comment;

import com.example.skkj.entity.Temperature;
import com.example.skkj.service.AlarmInformationServer;

public class AlartmInfoWend {

    public void temperatureAlarm(Temperature temperature, String underTemp, String overTemp, AlarmInformationServer alarmInformationServer, String deviceId, String time){
        String order = "0";// 1.表示温度超过105,NaN 0.表示单前温度为80到105之间
        order =  wdorder(temperature.getInA(),order);
        order =  wdorder(temperature.getInB(),order);
        order =  wdorder(temperature.getInC(),order);
        order =  wdorder(temperature.getOutA(),order);
        order =  wdorder(temperature.getOutB(),order);
        order =  wdorder(temperature.getOutC(),order);
        order =  wdorder(temperature.getContactA(),order);
        order =  wdorder(temperature.getContactB(),order);
        order =  wdorder(temperature.getContactC(),order);
        order =  wdorder(temperature.getContactD(),order);
        order =  wdorder(temperature.getContactE(),order);
        order =  wdorder(temperature.getContactF(),order);

        if(order != "1" && !"1".equals(order) && order != "6" && !"6".equals(order)){
            ConcurrentFenbu.getConcurrent().shortMessage(deviceId,"2","2","0");
        }else if(order == "6"|| !"6".equals(order)){
            ConcurrentFenbu.getConcurrent().shortMessage(deviceId,"2","6","0");
        }else {
            ConcurrentFenbu.getConcurrent().shortMessage(deviceId,"2","5","0");
        }
        alarmInforma(alarmInformationServer,deviceId,time,temperature,"1","2",order);

    }

    public void alarmInforma(AlarmInformationServer alarmInformationServer,String deviceId,String time,Temperature temperature,String information,String type,String order){
        AlarmInformationUtile alutil = new AlarmInformationUtile();
        alutil.alarmInformationInsert(alarmInformationServer,deviceId,time,temperature,information,type,order);
    }

     /**
          * @Author ZhouNan
          * @Description 报警信息温度判断
          * @params
          * @Date 2018/2/26 0026  14:50
          */
     public String wdorder(String wd,String order){
         if(wd != null && !"".equals(wd)&& wd != "NAN" && !"NAN".equals(wd)){
             Float contactDf = Float.parseFloat(wd);
             if (contactDf >=  100){
                     order = "1";
             }else if(contactDf >=  90 && contactDf<100){
                     order="6";
             }
         }else if (wd != null && !"".equals(wd) && wd == "NAN" || wd != null && !"".equals(wd) && "NAN".equals(wd)){
             order = "1";
         }
         return order;
     }
}
