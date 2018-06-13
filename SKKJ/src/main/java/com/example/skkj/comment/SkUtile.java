package com.example.skkj.comment;

import com.example.skkj.Interface.DxPlatform;
import com.example.skkj.entity.CalculationWd;
import com.example.skkj.entity.Equipment;
import com.example.skkj.entity.EquipmentType;
import com.example.skkj.mapper.EquipmentTypeMapper;
import com.example.skkj.test.ReadExcel;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SkUtile {

    private List<Equipment> equiLists;

    private List<CalculationWd> calculationWdsList;

    private List<Equipment> equiListsb;

    /**
         * @Author ZhouNan
         * @Description  总管理设备添加数据
         * @params
         * @Date 2018/5/3 0003  15:23
         */
    public Map<String, Object> skImportAll(List<Equipment> equiList,EquipmentTypeMapper equipmentTypeMapper){
        equiLists = new LinkedList<Equipment>();
        calculationWdsList = new LinkedList<CalculationWd>();
        equiListsb = new LinkedList<Equipment>();
        ReadExcel redad = new ReadExcel();
        Map<String, Object> map = new HashMap<String, Object>();
        if(equiList != null && !"".equals(equiList) && equiList.size()>0){
            for (int i = 0; i <equiList.size() ; i++) {
                Equipment equip = equiList.get(i);
                ypttj(equip, equipmentTypeMapper);
            }
        }
        map.put("equiLists",equiLists);
        map.put("calculationWdsList",calculationWdsList);
        map.put("equiListsb",equiListsb);
        return map;
    }
 /**
      * @Author ZhouNan
      * @Description 像云平台添加数据
      * @params
      * @Date 2018/5/3 0003  15:27
      */
    public void ypttj (Equipment equipment,EquipmentTypeMapper equipmentTypeMapper){
        String message = "";
        EquipmentType equitype = new EquipmentType();
        String eptId = equipment.getEptId();
        RedisUtils redis = new RedisUtils();
        equitype = redis.getObejct("eptid"+eptId,EquipmentType.class);
        if(equitype == null || "".equals(equitype)){
            try {
                equitype = equipmentTypeMapper.selectByEqtId(eptId);
                redis.setObject("eptid"+eptId,equitype);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String deviceId = DxPlatform.addSheBei(equipment, equitype);
        if(deviceId != null && !"".equals(deviceId) && deviceId !="bdsb" && !"bdsb".equals(deviceId)){
            equipment.setDeviceNumber(deviceId);
            equiLists.add(equipment);
            CalculationWd calcu = new CalculationWd();
            calcu.setInA("0");
            calcu.setInB("0");
            calcu.setInC("0");
            calcu.setOutA("0");
            calcu.setOutB("0");
            calcu.setOutC("0");
            calcu.setInAcs("0");
            calcu.setInBcs("0");
            calcu.setInCcs("0");
            calcu.setOutAcs("0");
            calcu.setOutBcs("0");
            calcu.setOutCcs("0");
            calcu.setDeviceNumber(deviceId);
            calculationWdsList.add(calcu);
        }
    }
}
