package com.example.skkj.controller.app;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.skkj.comment.BaseController;
import com.example.skkj.entity.AlarmHistory;
import com.example.skkj.service.AlarmHistoryServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/appAlarmHistory")
public class AlarmHistoryAppController extends BaseController{
    protected static Logger logger = Logger.getLogger(AlarmHistoryAppController.class);
    @Autowired
    private AlarmHistoryServer alarmHistoryServer;

    @RequestMapping(value = "/appSelectAlarmHistory",method = RequestMethod.POST)
    @ResponseBody
    public void appSelectAlarmHistory(@RequestBody JSONObject jsonObject){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = alarmHistoryServer.appSelectAlarmHistory(jsonObject);
            getJsonObject().put("currentPage",(Integer)map.get("currentPage"));
            getJsonObject().put("deviceNumber",(String)map.get("deviceNumber"));
            getJsonObject().put("pageCount",(Integer)map.get("pageCount"));
            getJsonObject().put("alarmHistoryList",(List<AlarmHistory>)map.get("alarmHistoryList"));
            getJsonObject().put("number",(String)map.get("number"));
            getJsonObject().put(RESULT_CODE,1);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("set appSelectAlarmHistory error:"+e.getMessage(),e);
            getJsonObject().put(RESULT_CODE,3);
        }
      pushJsonResult();
    }
}
