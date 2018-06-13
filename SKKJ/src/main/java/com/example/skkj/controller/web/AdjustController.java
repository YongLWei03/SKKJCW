package com.example.skkj.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.skkj.entity.Adjust;
import com.example.skkj.service.AdjustServer;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/webAdjust")
public class AdjustController {
    @Autowired
    private AdjustServer adjustServer;

     /**
          * @Author ZhouNan
          * @Description 查看效验数据
          * @params
          * @Date 2018/3/15 0015  11:11
          */
    @RequestMapping("/selectByCommandId")
    public ModelAndView selectByCommandId(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        List<Adjust> adjusLists = new LinkedList<Adjust>();
        String commandId = request.getParameter("commandId");
        String substationId = request.getParameter("substationId");
        String substationImage = request.getParameter("substationImage");
        String deviceNumber = request.getParameter("deviceNumber");
        try {
            adjusLists = adjustServer.selectByCommandId(commandId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("adjusLists",adjusLists);
        request.setAttribute("substationId",substationId);
        request.setAttribute("substationImage",substationImage);
        request.setAttribute("deviceNumber",deviceNumber);
        mv.setViewName("webB/adjustList");
        return mv;
    }

    @RequestMapping("/insertAll")
    @ResponseBody
    public String insertAll(HttpServletRequest request){
        List<Adjust> adju = new LinkedList<Adjust>();

        String message = "";
        for (int i = 0; i < 10; i++) {
            Adjust adjus = new Adjust();
            adjus.setCommandId("1");
            adjus.setValidCount(1);
            adjus.setMinPower(1);
            adjus.setMinFre(1);
            adjus.setMeanPower(1);
          adjus.setMaxPower(1);
          adjus.setMaxFre(1);
          adjus.setInvalidCount(1);
//          adjus.set(false);
//          adjus.setAdjust(false);
          adjus.setID(i);
          adjus.setSensorName("Asdad");
            adju.add(adjus);
        }
        try {
             message = adjustServer.isnertAll(adju,"111");
            adju = adjustServer.selectByCommandId("1");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return JSON.toJSONStringWithDateFormat(adju,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
    }
}
