package com.example.skkj.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.skkj.entity.EquipmentType;
import com.example.skkj.service.EquipmentTypeServer;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("webEquipmentType")
public class EquipmentTypeController {
    @Autowired
    private EquipmentTypeServer equipmentTypeServer;

    /**
     * @Author ZhouNan
     * @Description 添加设备类型
     * @params
     * @Date 11:32 2017/12/21 0021
     */
    @RequestMapping("insert")
    @ResponseBody
    public String  insert(@ModelAttribute EquipmentType equipmentType){
        String message = "";
        try {
            message = equipmentTypeServer.insert(equipmentType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
    }

    @RequestMapping("update")
    @ResponseBody
    public String  update(@ModelAttribute EquipmentType equipmentType){
        String message = "";
        try {
            message = equipmentTypeServer.updateByeqtId(equipmentType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
    }

    @RequestMapping("delete")
    @ResponseBody
    public String  delete(HttpServletRequest request){
        String eptId = request.getParameter("eptId");
        String message = "";
        try {
            message = equipmentTypeServer.delete(eptId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
    }

    @RequestMapping("selectById")
    public ModelAndView selectById(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        try {
           equipmentTypeServer.select(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("webB/equipmentTypeList");
        return mv;
    }

    @RequestMapping("addinsert")
    public ModelAndView addinsert(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("webB/addEquipmentType");
        return mv;
    }

    @RequestMapping("jrUpadate")
    public ModelAndView jrUpadate(HttpServletRequest request){
        String eptId = request.getParameter("eptId");
        EquipmentType equipt = new EquipmentType();
        ModelAndView mv = new ModelAndView();

        try {
            equipt = equipmentTypeServer.selectByEqtId(eptId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("equipt",equipt);
        mv.setViewName("webB/addEquipmentType");
        return mv;
    }

    @RequestMapping("finde")
    @ResponseBody
    public String finde(HttpServletRequest request){
        List<EquipmentType> equiTyepList = new ArrayList<EquipmentType>();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
           equiTyepList = equipmentTypeServer.finde();
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("equiTyepList",equiTyepList);
        return JSON.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
    }
}
