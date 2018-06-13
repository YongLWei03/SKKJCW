package com.example.skkj.controller.app;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.skkj.comment.BaseController;
import com.example.skkj.entity.Sensor;
import com.example.skkj.service.SensorServer;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
     * @Author ZhouNan
     * @Description 查看传感器设备名称
     * @params
     * @Date 2018/5/24 0024  10:32
     */
@Controller
@RequestMapping("/appSensor")
public class SensorAppController extends BaseController{

   protected static Logger logger = Logger.getLogger(SensorAppController.class);
   @Autowired
   private SensorServer sensorServer;
   @RequestMapping(value = "/appSelectBySensor",method = RequestMethod.POST)
   @ResponseBody
   public void appSelectBySensor(HttpServletRequest request){
       List<Sensor> sensorList = new ArrayList<Sensor>();
       Map<String, Object> map = new HashMap<String, Object>();
       try {
           sensorList = sensorServer.selectBySensor(request);
           getJsonObject().put("sensorList",sensorList);
           getJsonObject().put(RESULT_CODE,1);
       } catch (Exception e) {
           logger.error("set  app查看传感器 error:"+e.getMessage(),e);
           getJsonObject().put(RESULT_CODE,2);
       }
       pushJsonResult();
   }
}
