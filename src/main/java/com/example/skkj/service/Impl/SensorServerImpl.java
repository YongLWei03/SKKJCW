package com.example.skkj.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.skkj.entity.Sensor;
import com.example.skkj.mapper.SensorMapper;
import com.example.skkj.service.SensorServer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("sensorServer")
public class SensorServerImpl implements SensorServer {
    @Autowired
    private SensorMapper sensorMapper;
    @Override
    public List<Sensor> selectBySensor(HttpServletRequest request) throws Exception {
        String deviceType = request.getParameter("deviceType");
        return sensorMapper.selectBySensor(deviceType);
    }
}
