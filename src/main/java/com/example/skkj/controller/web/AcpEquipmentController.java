package com.example.skkj.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.skkj.service.AcpEquipmentServer;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/webAcpEquipment")
public class AcpEquipmentController {
    @Autowired
    private AcpEquipmentServer acpEquipmentServer;


    @RequestMapping("/selectByDeverId")
    public ModelAndView selectByDeverId(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        try {
            acpEquipmentServer.selectByDeverId(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("webB/acpEquiment");
        return mv;
    }
}
