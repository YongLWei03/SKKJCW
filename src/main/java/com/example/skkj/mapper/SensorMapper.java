package com.example.skkj.mapper;

import com.example.skkj.entity.Sensor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component(value = "sensorMapper")
public interface SensorMapper {
    List<Sensor> selectBySensor(@Param("deviceType") String deviceType)throws Exception;

    //更据sensorNum查询
    List<Sensor> selectBySensorNum(List<String> list)throws Exception;
}
