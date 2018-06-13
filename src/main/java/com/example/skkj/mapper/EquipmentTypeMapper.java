package com.example.skkj.mapper;

import com.example.skkj.entity.EquipmentType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Mapper
@Component(value = "equipmentTypeMapper")
public interface EquipmentTypeMapper {

    //添加设备类型
    int insert(EquipmentType equipmentType)throws Exception;

    //修改设备
    int updateByeqtId(EquipmentType equipmentType)throws Exception;

    //删除
    int delete(String eqtId)throws Exception;

    //根据ID查询
    EquipmentType selectByEqtId(String eqtId)throws Exception;

    //查询出所有设备
    List<EquipmentType> select(Map<String, Object> map)throws Exception;

    //查询出所有设备总数
    int selectCount(Map<String, Object> map)throws Exception;

    //不分页查询设备类型
    List<EquipmentType> finde()throws Exception;

    //根据名称查询ID
     String findBydeviceType(String deviceType)throws Exception;

}
