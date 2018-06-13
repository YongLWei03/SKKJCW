package com.example.skkj.mapper;


import com.example.skkj.entity.DeviceType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component(value = "deviceTypeMapper")
public interface DeviceTypeMapper {
    List<DeviceType> selectByDeviceType()throws Exception;
}
