package com.example.skkj.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.example.skkj.entity.DeviceType;
import com.example.skkj.entity.Equipment;
import com.example.skkj.entity.User;
import com.example.skkj.service.DeviceTypeServer;
import com.example.skkj.service.EquipmentServer;
import com.example.skkj.service.SubstationServer;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
     * @Author ZhouNan
     * @Description 变电站信息总管理
     * @params
     * @Date 2018/5/3 0003  14:14
     */
@Controller
@RequestMapping("/webSkEquipment")
public class SkEquipmentController {
   protected static Logger logger = Logger.getLogger(SkEquipmentController.class);

   @Autowired
   private EquipmentServer equipmentServer;

   @Autowired
   private DeviceTypeServer deviceTypeServer;

   @Autowired
   private SubstationServer substationServer;

   //进入设备
   @RequestMapping("/selectEquipment")
   public ModelAndView selectEquipment(HttpServletRequest request){
       ModelAndView mv = new ModelAndView();
       User user = (User)request.getSession().getAttribute("user");
       String usertype = user.getType();
       if(usertype == "1" || "1".equals(usertype)){
           try {
               equipmentServer.selectByUserType(request);

           } catch (Exception e) {
               logger.error("set  selectEquipment error:"+e.getMessage(),e);
           }
           mv.setViewName("webB/skequipmentList");
           return mv;
       }else {
           return mv;
       }
   }

    //添加对象信息
   @RequestMapping("/insetEquipment")
   @ResponseBody
   public String insetEquipment(@ModelAttribute Equipment equipment){
       String message = "";
       try {
            message = equipmentServer.insetEquipment(equipment,equipmentServer);

       } catch (Exception e) {
           e.printStackTrace();
           logger.error("set  insetEquipment error:"+e.getMessage(),e);
       }
       return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
   }

   //修改对象信息
   @RequestMapping("/updateByeqId")
   @ResponseBody
   public String updateByeqId(@ModelAttribute Equipment equipment, HttpServletRequest request){
       String message = "";
       try {
           message = equipmentServer.updateByeqId(equipment,request);
       } catch (Exception e) {
           e.printStackTrace();
           logger.error("set  updateByeqId error:"+e.getMessage(),e);
       }
       return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
   }

   //删除对象信息
   @RequestMapping("/deletByeqId")
   @ResponseBody
   public String deletByeqId(HttpServletRequest request){
       String eqId = request.getParameter("eqId");
       String deviceNumber = request.getParameter("deviceNumber");
       String message = "";
       try {
           message = equipmentServer.deletByeqId(eqId,deviceNumber);
       } catch (Exception e) {
           logger.error("set  deletByeqId error:"+e.getMessage(),e);
       }
       return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
   }

   //查询单个对象信息
   @RequestMapping("/findByeqId")
   public ModelAndView findByeqId(HttpServletRequest request){
       ModelAndView mv = new ModelAndView();
       String eqId = request.getParameter("eqId");
       String currentPage = request.getParameter("currentPageSb");
       Equipment equipment = new Equipment();
       List<DeviceType> deviceIdList = new ArrayList<DeviceType>();
       try {
           equipment = equipmentServer.findByeqId(eqId);
           deviceIdList = deviceTypeServer.selectByDeviceType();

       } catch (Exception e) {
           logger.error("set  findByeqId error:"+e.getMessage(),e);
       }
       request.setAttribute("equipment", equipment);
       request.setAttribute("deviceIdList", deviceIdList);
       request.setAttribute("currentPage", currentPage);
       mv.setViewName("webB/skaddEquipment");
       return mv;
   }

   //进入添加页面
   @RequestMapping("/insert")
   public ModelAndView insert(HttpServletRequest request){
       ModelAndView mv = new ModelAndView();
       List<DeviceType> deviceIdList = new ArrayList<DeviceType>();
       String currentPage = request.getParameter("currentPageSb");
       try {
             deviceIdList = deviceTypeServer.selectByDeviceType();
       } catch (Exception e) {
           logger.error("set  添加insert error:"+e.getMessage(),e);
       }
       request.setAttribute("deviceIdList", deviceIdList);
       request.setAttribute("currentPage", currentPage);
       mv.setViewName("webB/skaddEquipment");
       return mv;
   }

    /**
         * @Author ZhouNan
         * @Description 修改设备的是否上报数据
         * @params
         * @Date 2018/1/2 0002  13:30
         */
    @RequestMapping("/updateByMust")
    @ResponseBody
    public String updateByMust(HttpServletRequest request){
        String message = "";
        try {
           message = equipmentServer.updateByMust(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
    }

     /**
          * @Author ZhouNan
          * @Description 导出模板
          * @params
          * @Date 2018/3/6 0006  09:32
          */
     @RequestMapping("/export")
     @ResponseBody
     public String export(HttpServletRequest request, HttpServletResponse response) {
         // 下载文件名
         String fileName = "授权变电站.xls";
         try {
             fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
         } catch (UnsupportedEncodingException e1) {
             e1.printStackTrace();
         }
         // 获取模板位置，读取数据库（也可以读取配置文件或写死）
         String templatePath = request.getSession().getServletContext().getRealPath("/");

         // 实际位置
         String path = templatePath + File.separator + "授权变电站.xls";
         System.out.println(path);
         // 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
         response.setContentType("multipart/form-data");
         // 2.设置文件头：最后一个参数是设置下载文件名
         response.setHeader("Content-Disposition", "attachment;fileName="
                 + fileName);
         response.addHeader("Content-Type", "application/vnd.ms-excel");
         OutputStream out;
         // 通过文件路径获得File对象(假如此路径中有一个download.pdf文件)
         File file = new File(path);
         try {
             FileInputStream inputStream = new FileInputStream(file);
             // 3.通过response获取OutputStream对象(out)
             out = response.getOutputStream();
             byte[] buffer = new byte[512];
             int b = inputStream.read(buffer);
             while (b != -1) {
                 // 4.写到输出流(out)中
                 out.write(buffer, 0, b);
                 b = inputStream.read(buffer);
             }
             inputStream.close();
             out.close();
             out.flush();

         } catch (Exception e) {
             e.printStackTrace();
             return "false";
         }
         return "true";
     }


      /**
           * @Author ZhouNan
           * @Description 修改开关柜的名称
           * @params  Import
           * @Date 2018/3/6 0006  11:27
           */
      @RequestMapping("/updateBysubIdAndkgg")
      @ResponseBody
      public String updateBysubIdAndkgg(@ModelAttribute Equipment equipment) {
          String message = "";
          try {
              message = equipmentServer.updateBysubIdAndkgg(equipment);
          } catch (Exception e) {
              e.printStackTrace();
          }
          return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
      }

    /**
     * @Author ZhouNan
     * @Description 导入excel
     * @params  Import
     * @Date 2018/3/6 0006  11:27
     */
    @RequestMapping("/skImport")
    @ResponseBody
    public void skImport(@RequestParam("file")MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        try {
            long size = file.getSize();
            if(size < 1048576){
                map = equipmentServer.skImport(file.getInputStream());
            }else {
                map.put("message","wjgd");
            }
        } catch (Exception e) {
            logger.error("set  批量添加失败 error:"+e.getMessage(),e);
            map.put("message","tjyc");
        }
        try {
            response.getWriter().write(JSON.toJSONString(map));
            response.flushBuffer();
        } catch (IOException e) {
            logger.error("set 返回数据异常 error:"+e.getMessage(),e);
        }


    }


}
