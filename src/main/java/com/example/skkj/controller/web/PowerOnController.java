package com.example.skkj.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.skkj.service.PowerOnServer;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/webPowerOn")
public class PowerOnController {
    @Autowired
    private PowerOnServer powerOnServer;

    @RequestMapping("/selectPowerOn")
    public ModelAndView selectPowerOn(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        try {
            powerOnServer.select(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("webB/powerOnList");
        return mv;
    }
}
