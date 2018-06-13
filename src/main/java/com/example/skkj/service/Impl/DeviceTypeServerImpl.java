package com.example.skkj.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.skkj.entity.DeviceType;
import com.example.skkj.mapper.DeviceTypeMapper;
import com.example.skkj.service.DeviceTypeServer;

import java.util.List;

@Service("deviceTypeServer")
public class DeviceTypeServerImpl implements DeviceTypeServer {
    @Autowired
    private DeviceTypeMapper deviceTypeMapper;
    @Override
    public List<DeviceType> selectByDeviceType() throws Exception {
        return deviceTypeMapper.selectByDeviceType();
    }
}
