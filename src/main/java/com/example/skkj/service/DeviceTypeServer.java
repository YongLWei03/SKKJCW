package com.example.skkj.service;

import com.example.skkj.entity.DeviceType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DeviceTypeServer {
    @Transactional
    List<DeviceType> selectByDeviceType()throws Exception;
}
