package com.example.skkj.service.Impl;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.skkj.entity.AlarmHistory;
import com.example.skkj.mapper.AlarmHistoryMapper;
import com.example.skkj.mapper.EquipmentMapper;
import com.example.skkj.service.AlarmHistoryServer;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("alarmHistoryServer")
public class AlarmHistoryServerImpl implements AlarmHistoryServer {

    @Autowired
    private AlarmHistoryMapper alarmHistoryMapper;
    
    @Autowired
    private EquipmentMapper  equipmentMapper;
     /**
          * @Author ZhouNan
          * @Description 查看当前报警设备信信息之前的温度数据
          * @params
          * @Date 2018/1/31 0031  11:22
          */
    @Override
    public void selectAlarmHistory(HttpServletRequest request) throws Exception {
        //每页显示多少条
        int pageSize = 10;
        //当前页
        int currentPage = 1;

        Map<String,Object> map = new HashMap<String, Object>();
        //当前页
        String dqpagestr = request.getParameter("currentPage");
        String deviceNumber = request.getParameter("deviceNumber");
        String substationId = request.getParameter("substationId");
        String substationImage = request.getParameter("substationImage");
        String ssty = request.getParameter("ssty");
        String state = request.getParameter("state");
        String type = request.getParameter("type");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String keyword = request.getParameter("keyword");
        String time = request.getParameter("time");
        if(null!=dqpagestr && !"".equals(dqpagestr)){
            currentPage = Integer.parseInt(dqpagestr);
        }
        if(substationId != null && !"".equals(substationId)){
            request.setAttribute("substationId", substationId);
        }
        if(ssty != null && !"".equals(ssty)){
            request.setAttribute("ssty", ssty);
        }
        if(substationImage != null && !"".equals(substationImage)){
            request.setAttribute("substationImage", substationImage);
        }
        if(state != null && !"".equals(state)){
            request.setAttribute("state", state);
        }
        if(type != null && !"".equals(type)){
            request.setAttribute("type", type);
        }
        if(startTime != null && !"".equals(startTime)){
            request.setAttribute("startTime", startTime);
        }
        if(endTime != null && !"".equals(endTime)){
            request.setAttribute("endTime", endTime);
        }
        if(keyword != null && !"".equals(keyword)){
            request.setAttribute("keyword", keyword);
        }
        String equi = equipmentMapper.selectByDeviceNumberEqui(deviceNumber);
        map.put("deviceNumber",deviceNumber);
        map.put("time",time);
        map.put("start", ((currentPage-1)*pageSize));
//		map.put("end", pageSize*currentPage);
        map.put("pageSize", pageSize);
        Integer pageCount = null;
        pageCount = alarmHistoryMapper.selectAlarmHistoryCount(map);
        List<AlarmHistory> alarmHistoryList = alarmHistoryMapper.selectAlarmHistory(map);
        pageCount = pageCount%pageSize==0?pageCount/pageSize:(pageCount/pageSize)+1;
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("deviceNumber", deviceNumber);
        request.setAttribute("time", time);
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("alarmHistoryList", alarmHistoryList);
        request.setAttribute("number", equi);
    }

    @Override
    public String insert(AlarmHistory alarmHistory) throws Exception {
        return alarmHistoryMapper.insert(alarmHistory)>0?"true":"false";
    }

    @Override
    public Map<String, Object> appSelectAlarmHistory(JSONObject jsonObject) throws Exception {
        //每页显示多少条
        int pageSize = 10;
        //当前页
        int currentPage = 1;

        Map<String,Object> map = new HashMap<String, Object>();
        //当前页
        String dqpagestr = jsonObject.getString("currentPage");
        String deviceNumber = jsonObject.getString("deviceNumber");
        String pageSizes = jsonObject.getString("pageSize");
        String time = jsonObject.getString("time");
        if(null!=dqpagestr && !"".equals(dqpagestr)){
            currentPage = Integer.parseInt(dqpagestr);
        }
        if(null!=pageSizes && !"".equals(pageSizes)){
            pageSize = Integer.parseInt(pageSizes);
        }
        String equi = equipmentMapper.selectByDeviceNumberEqui(deviceNumber);
        map.put("deviceNumber",deviceNumber);
        map.put("time",time);
        map.put("start", ((currentPage-1)*pageSize));
        map.put("pageSize", pageSize);
        Integer pageCount = null;
        pageCount = alarmHistoryMapper.selectAlarmHistoryCount(map);
        List<AlarmHistory> alarmHistoryList = alarmHistoryMapper.selectAlarmHistory(map);
        pageCount = pageCount%pageSize==0?pageCount/pageSize:(pageCount/pageSize)+1;
        map.clear();
        map.put("currentPage", currentPage);
        map.put("deviceNumber", deviceNumber);
        map.put("time", time);
        map.put("pageCount", pageCount);
        map.put("alarmHistoryList", alarmHistoryList);
        map.put("number", equi);
        return map;
    }
}
