package com.example.skkj.mapper;

import com.example.skkj.entity.AcpEquipment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Mapper
@Component(value = "acpEquipmentMapper")
public interface AcpEquipmentMapper {

    int insert(AcpEquipment acpEquipment)throws Exception;

    int update(AcpEquipment acpEquipment)throws Exception;

    List<AcpEquipment> selectByDeverId(Map<String, Object> map)throws Exception;
    int selectByDeverIdCount(Map<String, Object> map)throws Exception;
}
