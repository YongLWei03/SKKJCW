package com.example.skkj.service;

import com.example.skkj.entity.EquipmentType;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EquipmentTypeServer {
    //添加设备类型
    @Transactional
    String insert(EquipmentType equipmentType)throws Exception;

    //修改设备
    @Transactional
    String updateByeqtId(EquipmentType equipmentType)throws Exception;

    //删除
    @Transactional
    String delete(String eqtId)throws Exception;

    //根据ID查询
    @Transactional
    EquipmentType selectByEqtId(String eqtId)throws Exception;

    //查询出所有设备
    @Transactional
     void select(HttpServletRequest request)throws Exception;
    //不分页查询设备类型
    @Transactional
    List<EquipmentType> finde()throws Exception;

    //根据名称查询ID
    @Transactional
    String findBydeviceType(String deviceType)throws Exception;
}
