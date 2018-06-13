package com.example.skkj.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.skkj.service.AlarmHistoryServer;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/webAlarmHistory")
public class AlarmHistoryController {
    @Autowired
    private AlarmHistoryServer alarmHistoryServer;

    @RequestMapping("/selectAlarmHistory")
    public ModelAndView selectAlarmHistory(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        try {
            alarmHistoryServer.selectAlarmHistory(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("webB/alarmTemperature");
        return mv;
    }
}
