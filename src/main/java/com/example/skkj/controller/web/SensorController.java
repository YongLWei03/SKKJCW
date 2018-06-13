package com.example.skkj.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.skkj.entity.Sensor;
import com.example.skkj.service.SensorServer;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/webSensor")
public class SensorController {
  @Autowired
  private SensorServer sensorServer;

  @RequestMapping("/selectBySensor")
  @ResponseBody
  public Map<String,Object> selectBySensor(HttpServletRequest request){
      List<Sensor> sensorList = new ArrayList<Sensor>();
       Map<String, Object> map = new HashMap<String, Object>();
      try {
          sensorList = sensorServer.selectBySensor(request);
      } catch (Exception e) {
          e.printStackTrace();
      }
      map.put("sensorList",sensorList);
      return map;
  }
}
