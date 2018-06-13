package com.example.skkj.comment;

import com.example.skkj.entity.AlarmInformation;
import com.example.skkj.entity.Temperature;
import com.example.skkj.service.AlarmInformationServer;

public class AlarmInformationUtile {

    public  void alarmInformationInsert(AlarmInformationServer alarmInformationServer, String deviceId, String time, Temperature temperature, String informationType, String type, String order){
        AlarmInformation alarm = new AlarmInformation();
        alarm.setDay(time);
        alarm.setInA(temperature.getInA());
        alarm.setInB(temperature.getInB());
        alarm.setInC(temperature.getInC());
        alarm.setOutA(temperature.getOutA());
        alarm.setOutB(temperature.getOutB());
        alarm.setOutC(temperature.getOutC());
        alarm.setContactA(temperature.getContactA());
        alarm.setContactB(temperature.getContactB());
        alarm.setContactC(temperature.getContactC());
        alarm.setContactD(temperature.getContactD());
        alarm.setContactE(temperature.getContactE());
        alarm.setContactF(temperature.getContactF());
        alarm.setInformationType(informationType);
        alarm.setType(type);
        try {
            alarmInformationServer.insert(deviceId,null,order,alarm);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
