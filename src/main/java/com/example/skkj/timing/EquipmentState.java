package com.example.skkj.timing;

import com.example.skkj.RCDx.TemplateSms;
import com.example.skkj.comment.ConcurrentFenbu;
import com.example.skkj.comment.TimeUtile;
import com.example.skkj.entity.AcpEquipment;
import com.example.skkj.entity.Equipment;
import com.example.skkj.entity.Substation;
import com.example.skkj.entity.Temperature;
import com.example.skkj.service.AcpEquipmentServer;
import com.example.skkj.service.EquipmentServer;
import com.example.skkj.service.SubstationServer;
import com.example.skkj.service.TemperatureServer;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
      * @Author ZhouNan
      * @Description 每天下午4点统计设备及采集状态
      * @params
      * @Date 2018/2/26 0026  15:04
      */
public class EquipmentState implements Job{
    @Autowired
    private EquipmentServer equipmentServer;
    @Autowired
    private AcpEquipmentServer acpEquipmentServer;
    
    @Autowired
    private SubstationServer substationServer;

    @Autowired
    private TemperatureServer temperatureServer;
    public void state(){
        List<Equipment> equiList = new LinkedList<Equipment>();
        try {
          equiList =  equipmentServer.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(equiList != null && equiList.size()>0){
            for (int i = 0; i < equiList.size(); i++) {
                Equipment equi = equiList.get(i);
                String deviceNumber = equi.getDeviceNumber();
                Map<String, Object> mapte = ConcurrentFenbu.getConcurrent().realTime(deviceNumber,"-1".toString(),"0",temperatureServer);
                Temperature temp = new Temperature();
                Object mateper = mapte.get("temp");
                if(mateper != null && !"".equals(mateper) && mateper != "false" && !"false".equals(mateper)){
                    temp = (Temperature)mateper;
                }else {
                    try {
                        temp =  temperatureServer.selectByTemuSb(equi.getDeviceNumber());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if(temp != null && !"".equals(temp)){
                    String time = temp.getTime();
                    //判断是佛时间差是否在5小时以内
                    String pdsj = TimeUtile.sjc(time);
                    if(pdsj != "true" && !"true".equals(pdsj)){
                        String subId = equi.getSubstationId();
                        Substation sub = new Substation();
                        try {
                            sub = substationServer.selectBySubstationId(subId);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if(sub != null && !"".equals(sub)){
                            String subName = sub.getSubstationName();
                            AcpEquipment acpe = new AcpEquipment();
                            acpe.setDeviceNumber(deviceNumber);
                            acpe.setHarvesterStatus("6");
                            acpe.setBoardStatus("6");
                            String str = "";
                            try {
                                str = acpEquipmentServer.insertUpda(acpe);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if(str != null && !"".equals(str) && str != "false" && !"false".equals(str)){
                                //手机号码
                                String phone = equi.getPhone();
                                String equipmentxi = equi.getEquipmentxi();
                                try {
                                    TemplateSms.TimeCha(subName,equipmentxi,phone);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        state();
    }
}
