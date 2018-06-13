package com.example.skkj.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.skkj.comment.RedisUtils;
import com.example.skkj.entity.CommandInformation;
import com.example.skkj.entity.Level;
import com.example.skkj.service.CommandInformationServer;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
     * @Author ZhouNan
     * @Description 对设备操作的类
     * @params
     * @Date 2018/1/11 0011  09:20
     */
@Controller
@RequestMapping("/webCommandInformation")
public class CommandInformationController {
   @Autowired
   private CommandInformationServer commandInformationServer;
    /**
         * @Author ZhouNan
         * @Description  查看当前设备的命令执行
         * @params
         * @Date 2018/1/11 0011  09:21
         */
   @RequestMapping("/select")
   public ModelAndView select(HttpServletRequest request){
       ModelAndView mv = new ModelAndView();
       try {
          commandInformationServer.select(request);
       } catch (Exception e) {
           e.printStackTrace();
       }
       mv.setViewName("webB/commandInfList");
       return mv;
   }

    /**
         * @Author ZhouNan
         * @Description 删除命令情况
         * @params
         * @Date 2018/1/11 0011  09:21
         */
    @RequestMapping("/deleteById")
    @ResponseBody
    public String deleteById(HttpServletRequest request){
        String id = request.getParameter("Id");
        String message = "";
        try {
            message = commandInformationServer.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
    }

     /**
          * @Author ZhouNan
          * @Description  查询当前设备命令发送情况
          * @params
          * @Date 2018/1/16 0016  15:25
          */
     @RequestMapping("/selectByDeviceNumber")
     @ResponseBody
     public String selectByDeviceNumber(HttpServletRequest request){
         Map<String, Object> map = new HashMap<String, Object>();
         String deviceNumber = request.getParameter("deviceNumber");
         List<CommandInformation> commandInfList = new ArrayList<CommandInformation>();
         try {
             commandInfList = commandInformationServer.selectByDeviceNumber(deviceNumber);
         } catch (Exception e) {
             e.printStackTrace();
         }
         map.put("commandInfList",commandInfList);
         return JSON.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
     }

    /**
     * @Author ZhouNan
     * @Description  查看是否存在当前设备参数
     * @params
     * @Date 2018/1/16 0016  15:25
     */
    @RequestMapping("/selectByCssb")
    @ResponseBody
    public String selectByCssb(HttpServletRequest request){
        RedisUtils redisUtils = new RedisUtils();
        Map<String, Object> map = new HashMap<String, Object>();
        String deviceNumber = request.getParameter("deviceNumber");
        Level leviList = redisUtils.getObejct("selectBoarObj" + deviceNumber, Level.class);
        if(leviList != null){
            map.put("leviList",leviList);
        }else {
            map.put("leviList","false");
        }
        return JSON.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * @Author ZhouNan
     * @Description  删除当前缓存数据
     * @params
     * @Date 2018/1/16 0016  15:25
     */
    @RequestMapping("/deleteBydeviceNumber")
    @ResponseBody
    public String deleteBydeviceNumber(HttpServletRequest request){
        RedisUtils redisUtils = new RedisUtils();
        String deviceNumber = request.getParameter("deviceNumber");
        redisUtils.delObject("selectBoarObj" + deviceNumber);
        return JSON.toJSONStringWithDateFormat("true","yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
    }
}
