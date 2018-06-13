package com.example.skkj.service;

import com.example.skkj.entity.Sensor;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SensorServer {
    @Transactional
    List<Sensor> selectBySensor(HttpServletRequest request)throws Exception;
}
