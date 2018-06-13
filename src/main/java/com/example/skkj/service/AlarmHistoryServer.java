package com.example.skkj.service;



import com.alibaba.fastjson.JSONObject;
import com.example.skkj.entity.AlarmHistory;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface AlarmHistoryServer {
    //查看报警信息
    @Transactional
    void selectAlarmHistory(HttpServletRequest request)throws Exception;
    @Transactional
    String insert(AlarmHistory alarmHistory)throws Exception;

    //查看报警前数据
    @Transactional
    Map<String,Object> appSelectAlarmHistory(JSONObject jsonObject)throws Exception;

}
