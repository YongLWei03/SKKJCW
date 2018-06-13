package com.example.skkj.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.skkj.entity.CalculationWd;
import com.example.skkj.mapper.CalculationWdMapper;
import com.example.skkj.service.CalculationWdServer;

@Service("calculationWdServer")
public class CalculationWdServerImpl implements CalculationWdServer {
    @Autowired
    private CalculationWdMapper calculationWdMapper;
    @Override
    public String insert(CalculationWd calculationWd) throws Exception {
        String messag = "";
        String deviceNumber = calculationWd.getDeviceNumber();
        CalculationWd cade = calculationWdMapper.select(deviceNumber);
        if(cade != null && !"".equals(cade)){
            messag = calculationWdMapper.update(calculationWd) > 0 ? "true" : "false";
        }else {
            messag = calculationWdMapper.insert(calculationWd) > 0 ? "true" : "false";
        }
        return messag;
    }

    @Override
    public String update(CalculationWd calculationWd) throws Exception {
        return calculationWdMapper.update(calculationWd)>0?"true":"false";
    }

    @Override
    public CalculationWd select(String deviceNumber) throws Exception {
        return calculationWdMapper.select(deviceNumber);
    }
}
