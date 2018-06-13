package com.example.skkj.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.skkj.service.CommandServer;

import javax.servlet.http.HttpServletRequest;

/**
     * @Author ZhouNan
     * @Description 对设备操作的类
     * @params
     * @Date 2018/1/11 0011  09:20
     */
@Controller
@RequestMapping("/webCommand")
public class CommandController {
   @Autowired
   private CommandServer commandServer;
    /**
         * @Author ZhouNan
         * @Description  设置板级参数
         * @params
         * @Date 2018/1/11 0011  09:21
         */
   @RequestMapping("/boardLevel")
   @ResponseBody
   public String boardLevel(HttpServletRequest request){
       String message = "";
       try {
           message = commandServer.boardLevel(request);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
   }

    /**
         * @Author ZhouNan
         * @Description 查看板级参数
         * @params
         * @Date 2018/1/11 0011  09:21
         */
    @RequestMapping("/selectBoar")
    @ResponseBody
    public String selectBoar(HttpServletRequest request){
        String message = "";
        try {
            message = commandServer.selectBoar(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
    }
    /**
         * @Author ZhouNan
         * @Description 使能采样
         * @params
         * @Date 2018/1/11 0011  09:21
         */
    @RequestMapping("/samplingEnable")
    @ResponseBody
    public String samplingEnable(HttpServletRequest request){
        String message = "";
        try {
            message = commandServer.samplingEnable(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
         * @Author ZhouNan
         * @Description 立即采样
         * @params
         * @Date 2018/1/11 0011  09:21
         */
    @RequestMapping("/immediateSampling")
    @ResponseBody
    public String immediateSampling(HttpServletRequest request){
        String message = "";
        try {
            message = commandServer.immediateSampling(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
    }
/**
     * @Author ZhouNan
     * @Description 效验设置
     * @params   calibration
     * @Date 2018/3/7 0007  10:10
     */
@RequestMapping("/calibration")
@ResponseBody
public String calibration(HttpServletRequest request){
    String message = "";
    try {
        message = commandServer.calibration(request);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
}
/**
     * @Author ZhouNan
     * @Description 相位设置
     * @params   calibration
     * @Date 2018/3/7 0007  10:10
     */
@RequestMapping("/algorithm")
@ResponseBody
public String algorithm(HttpServletRequest request){
    String message = "";
    try {
        message = commandServer.algorithm(request);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
}


}
