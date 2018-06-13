package com.example.skkj.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.skkj.comment.TimeUtile;
import com.example.skkj.entity.Temperature;
import com.example.skkj.mapper.TemperaturesMapper;
import com.example.skkj.service.TemperaturesServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("temperaturesServer")
public class TemperaturesServerImpl implements TemperaturesServer{
    @Autowired
    private TemperaturesMapper temperaturesMapper;


    @Override
    public String insert(Temperature temperature) throws Exception {
        return temperaturesMapper.insertTemu(temperature)>0?"true":"false";
    }

    @Override
    public List<Temperature> selectByTime(Map<String, Object> map) throws Exception {
        String number = TimeUtile.dateTimess();
        return temperaturesMapper.selectByDinshisTime("",number);
    }


    @Override
    public List<Temperature> selectByDinshisTime(String time,String number) throws Exception {
        return temperaturesMapper.selectByDinshisTime(time,number);
    }

    @Override
    public String deletByTime(String time, String number) throws Exception {
        return temperaturesMapper.deletByTime(time,number)>0?"true":"false";
    }

    @Override
    public int selectCount(String object) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("object",object);
        return temperaturesMapper.selectCount(map);
    }

}
