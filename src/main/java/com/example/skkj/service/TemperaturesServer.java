package com.example.skkj.service;

import com.example.skkj.entity.Temperature;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface TemperaturesServer {
    //批量添加数据
    @Transactional
    String insert(Temperature temperature)throws Exception;

    //查看历史数据
    @Transactional
    List<Temperature> selectByTime(Map<String, Object> map)throws Exception;

    //定时查看当前时间的历史数据
    @Transactional
    List<Temperature> selectByDinshisTime(String time, String number)throws Exception;

    //删除选定的设备
    @Transactional
    String deletByTime(String time, String number)throws Exception;

    //查看当前数据总数
    @Transactional
    int selectCount(String object)throws Exception;


}
