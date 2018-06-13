package com.example.skkj.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.skkj.entity.Parameter;
import com.example.skkj.mapper.ParameterMapper;
import com.example.skkj.service.ParameterServer;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service("parameterServer")
public class ParameterServerImpl implements ParameterServer {

    @Autowired
    private ParameterMapper parameterMapper;
    @Override
    public Map<String,Object> selectByDeviceNumber(HttpServletRequest request) throws Exception {
        String deviceNumber = request.getParameter("deviceNumber");
        Parameter parament = parameterMapper.selectByDeviceNumber(deviceNumber);
        Map<String, Object> map = new HashMap<String, Object>();
        if(parament != null && !"".equals(parament)){
            map.put("parament",parament);
        }
        return map;
    }

    public static void main(String[] args) {
        String  a = "a,b,c,d";
        String[] cc = a.split("\\,");
        for (int i = 0; i < cc.length; i++) {
            System.out.println(cc[i]);
        }

    }
}
