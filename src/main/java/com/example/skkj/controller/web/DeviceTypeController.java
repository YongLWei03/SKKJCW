package com.example.skkj.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.skkj.entity.DeviceType;
import com.example.skkj.service.DeviceTypeServer;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/webDeviceType")
public class DeviceTypeController {
    @Autowired
    private DeviceTypeServer deviceTypeServer;

    @RequestMapping("/select")
    @ResponseBody
    public String  DeviceTypeSelect(HttpServletRequest request){
        List<DeviceType> deviceTypeList = new ArrayList<DeviceType>();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            deviceTypeList = deviceTypeServer.selectByDeviceType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("deviceTypeList",deviceTypeList);
        return JSON.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
    }
}
