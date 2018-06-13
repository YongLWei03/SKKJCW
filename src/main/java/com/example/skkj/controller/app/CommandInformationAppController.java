package com.example.skkj.controller.app;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.skkj.comment.BaseController;
import com.example.skkj.comment.RedisUtils;
import com.example.skkj.entity.CommandInformation;
import com.example.skkj.entity.Level;
import com.example.skkj.service.CommandInformationServer;

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
@RequestMapping(value = "/appCommandInformation",method = RequestMethod.POST)
public class CommandInformationAppController extends BaseController{
    protected static Logger logger = Logger.getLogger(CommandInformationAppController.class);
   @Autowired
   private CommandInformationServer commandInformationServer;

     /**
          * @Author ZhouNan
          * @Description  查询当前设备命令发送情况
          * @params
          * @Date 2018/1/16 0016  15:25
          */
     @RequestMapping(value = "/appSelectByDeviceNumber",method = RequestMethod.POST)
     @ResponseBody
     public void appSelectByDeviceNumber(HttpServletRequest request){
         String deviceNumber = request.getParameter("deviceNumber");
         List<CommandInformation> commandInfList = new ArrayList<CommandInformation>();
         try {
             commandInfList = commandInformationServer.selectByDeviceNumber(deviceNumber);
         } catch (Exception e) {
             logger.error("set  app查询当前设备命令发送情况 error:"+e.getMessage(),e);
             getJsonObject().put(RESULT_CODE,3);
         }
         getJsonObject().put("commandInfList",commandInfList);
         getJsonObject().put(RESULT_CODE,1);

         pushJsonResult();
     }

    /**
     * @Author ZhouNan
     * @Description  查看是否存在当前设备参数
     * @params
     * @Date 2018/1/16 0016  15:25
     */
    @RequestMapping(value = "/appSelectByCssb",method = RequestMethod.POST)
    @ResponseBody
    public void appSelectByCssb(HttpServletRequest request){
        RedisUtils redisUtils = new RedisUtils();
        Map<String, Object> map = new HashMap<String, Object>();
        String deviceNumber = request.getParameter("deviceNumber");
        Level leviList = redisUtils.getObejct("selectBoarObj" + deviceNumber, Level.class);
        getJsonObject().put("leviList",leviList);
        if(leviList != null){
            //存在当前返回的数据
            getJsonObject().put(RESULT_CODE,1);
        }else {
            //不存在查看板级参数的数据
            getJsonObject().put(RESULT_CODE,2);
        }
        pushJsonResult();
    }

    /**
     * @Author ZhouNan
     * @Description  删除当前缓存数据
     * @params
     * @Date 2018/1/16 0016  15:25
     */
    @RequestMapping(value = "/appDeleteBydeviceNumber",method = RequestMethod.POST)
    @ResponseBody
    public void appDeleteBydeviceNumber(HttpServletRequest request){
        RedisUtils redisUtils = new RedisUtils();
        String deviceNumber = request.getParameter("deviceNumber");
        redisUtils.delObject("selectBoarObj" + deviceNumber);
        getJsonObject().put(RESULT_CODE,1);
    }
}
