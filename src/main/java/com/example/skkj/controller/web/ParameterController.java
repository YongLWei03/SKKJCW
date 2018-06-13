package com.example.skkj.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.skkj.service.ParameterServer;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/webParameter")
public class ParameterController {
    @Autowired
    private ParameterServer parameterServer;

    @RequestMapping("/selectByDeviceNumber")
    @ResponseBody
    public String selectByDeviceNumber(HttpServletRequest request){

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = parameterServer.selectByDeviceNumber(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
    }

}
