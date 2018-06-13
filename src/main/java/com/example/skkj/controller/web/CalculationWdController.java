package com.example.skkj.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.skkj.entity.CalculationWd;
import com.example.skkj.service.CalculationWdServer;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/webCalculationWd")
public class CalculationWdController {
    @Autowired
    private CalculationWdServer calculationWdServer;

    @RequestMapping("/select")
    @ResponseBody
    public String select(HttpServletRequest request){
        String deviceNumber = request.getParameter("deviceNumber");
        CalculationWd calcul = new CalculationWd();
        try {
            calcul = calculationWdServer.select(deviceNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONStringWithDateFormat(calcul,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
    }

    @RequestMapping("/insert")
    @ResponseBody
    public String insert(@ModelAttribute CalculationWd calculationWd){
        String message="";
        try {
            message = calculationWdServer.insert(calculationWd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
    }


}
