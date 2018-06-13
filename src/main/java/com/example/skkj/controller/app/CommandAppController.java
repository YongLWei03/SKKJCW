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
import com.example.skkj.entity.CalculationWd;
import com.example.skkj.service.CalculationWdServer;
import com.example.skkj.service.CommandServer;

import javax.servlet.http.HttpServletRequest;

/**
     * @Author ZhouNan
     * @Description 对设备操作的类
     * @params
     * @Date 2018/1/11 0011  09:20
     */
@Controller
@RequestMapping("/appCommand")
public class CommandAppController extends BaseController{
    protected static Logger logger = Logger.getLogger(CommandAppController.class);
   @Autowired
   private CommandServer commandServer;

   @Autowired
   private CalculationWdServer calculationWdServer;
    /**
         * @Author ZhouNan
         * @Description  设置板级参数
         * @params
         * @Date 2018/1/11 0011  09:21
         */
   @RequestMapping(value = "/appBoardLevel",method = RequestMethod.POST)
   @ResponseBody
   public void appBoardLevel(@RequestBody JSONObject jsonObject){
       String message = "";
       try {
           message = commandServer.appBoardLevel(jsonObject);
       } catch (Exception e) {
           logger.error("set  设置板级参数 error:"+e.getMessage(),e);
           //数据异常
           getJsonObject().put(RESULT_CODE,3);
       }
       if(message !="" && !"".equals(message)){
           if(message == "no" || "no".equals(message)){
               //命令发送成功
               getJsonObject().put(RESULT_CODE,4);
           }else if(message == "false" || "false".equals(false)){
               //命令发送失败
               getJsonObject().put(RESULT_CODE,2);
           }else {
               //命正在执行中
               getJsonObject().put(RESULT_CODE,1);
           }
       }
       pushJsonResult();
   }

    /**
         * @Author ZhouNan
         * @Description 查看板级参数
         * @params
         * @Date 2018/1/11 0011  09:21
         */
    @RequestMapping(value = "/appSelectBoar",method = RequestMethod.POST)
    @ResponseBody
    public void appSelectBoar(@RequestBody JSONObject jsonObject){
        String message = "";
        try {
            message = commandServer.appSelectBoar(jsonObject);
        } catch (Exception e) {
            logger.error("set  查看板级参数 error:"+e.getMessage(),e);
            //数据异常
            getJsonObject().put(RESULT_CODE,3);
        }
        if(message !="" && !"".equals(message)){
            if(message == "no" || "no".equals(message)){
                //命令发送成功
                getJsonObject().put(RESULT_CODE,4);
            }else if(message == "false" || "false".equals(false)){
                //命令发送失败
                getJsonObject().put(RESULT_CODE,2);
            }else {
                //命正在执行中
                getJsonObject().put(RESULT_CODE,1);
            }
        }
        pushJsonResult();
    }
    /**
         * @Author ZhouNan
         * @Description 使能采样
         * @params
         * @Date 2018/1/11 0011  09:21
         */
    @RequestMapping(value = "/appSamplingEnable",method = RequestMethod.POST)
    @ResponseBody
    public void appSamplingEnable(@RequestBody JSONObject jsonObject){
        String message = "";
        try {
            message = commandServer.appSamplingEnable(jsonObject);
        } catch (Exception e) {
            logger.error("set  app使能采样 error:"+e.getMessage(),e);
            //数据异常
            getJsonObject().put(RESULT_CODE,3);
        }
        if(message !="" && !"".equals(message)){
            if(message == "no" || "no".equals(message)){
                //命令发送成功
                getJsonObject().put(RESULT_CODE,4);
            }else if(message == "false" || "false".equals(false)){
                //命令发送失败
                getJsonObject().put(RESULT_CODE,2);
            }else {
                //命正在执行中
                getJsonObject().put(RESULT_CODE,1);
            }
        }
        pushJsonResult();
    }
//
    /**
         * @Author ZhouNan
         * @Description app立即采样
         * @params
         * @Date 2018/1/11 0011  09:21
         */
    @RequestMapping(value = "/appImmediateSampling",method = RequestMethod.POST)
    @ResponseBody
    public void appImmediateSampling(@RequestBody JSONObject jsonObject){
        String message = "";
        try {
            message = commandServer.appImmediateSampling(jsonObject);
        } catch (Exception e) {
            logger.error("set  app立即采样 error:"+e.getMessage(),e);
            //数据异常
            getJsonObject().put(RESULT_CODE,3);
        }
        if(message !="" && !"".equals(message)){
            if(message == "no" || "no".equals(message)){
                //命令发送成功
                getJsonObject().put(RESULT_CODE,4);
            }else if(message == "false" || "false".equals(false)){
                //命令发送失败
                getJsonObject().put(RESULT_CODE,2);
            }else {
                //命正在执行中
                getJsonObject().put(RESULT_CODE,1);
            }
        }
        pushJsonResult();
    }
/**
     * @Author ZhouNan
     * @Description 效验设置
     * @params   calibration
     * @Date 2018/3/7 0007  10:10
     */
@RequestMapping(value = "/appCalibration",method = RequestMethod.POST)
@ResponseBody
public void appCalibration(@RequestBody JSONObject jsonObject){
    String message = "";
    try {
        message = commandServer.appCalibration(jsonObject);
    } catch (Exception e) {
        logger.error("set  app效验设置 error:"+e.getMessage(),e);
        //数据异常
        getJsonObject().put(RESULT_CODE,3);
    }
    if(message !="" && !"".equals(message)){
        if(message == "no" || "no".equals(message)){
            //命令发送成功
            getJsonObject().put(RESULT_CODE,4);
        }else if(message == "false" || "false".equals(false)){
            //命令发送失败
            getJsonObject().put(RESULT_CODE,2);
        }else {
            //命正在执行中
            getJsonObject().put(RESULT_CODE,1);
        }
    }
    pushJsonResult();
}

/**
     * @Author ZhouNan
     * @Description 相位设置
     * @params   calibration
     * @Date 2018/3/7 0007  10:10
     */
@RequestMapping(value = "/appAlgorithm",method = RequestMethod.POST)
@ResponseBody
public void appAlgorithm(@RequestBody JSONObject jsonObject){
    String message = "";
    try {
        message = commandServer.appAlgorithm(jsonObject);
    } catch (Exception e) {
        logger.error("set  app相位设置 error:"+e.getMessage(),e);
        //数据异常
        getJsonObject().put(RESULT_CODE,3);
    }
    if(message !="" && !"".equals(message)){
        if(message == "no" || "no".equals(message)){
            //命令发送成功
            getJsonObject().put(RESULT_CODE,4);
        }else if(message == "false" || "false".equals(false)){
            //命令发送失败
            getJsonObject().put(RESULT_CODE,2);
        }else {
            //命正在执行中
            getJsonObject().put(RESULT_CODE,1);
        }
    }
    pushJsonResult();
}

/**
     * @Author ZhouNan
     * @Description 相位温度设置查看
     * @params   calibration
     * @Date 2018/3/7 0007  10:10
     */
@RequestMapping(value = "/appSelect",method = RequestMethod.POST)
@ResponseBody
public void appSelect(HttpServletRequest request){
    String deviceNumber = request.getParameter("deviceNumber");
    CalculationWd calcul = new CalculationWd();
    try {
        calcul = calculationWdServer.select(deviceNumber);
        getJsonObject().put("calcul",calcul);
        getJsonObject().put(RESULT_CODE,1);
    } catch (Exception e) {
        logger.error("set  app相位查看失败 error:"+e.getMessage(),e);
        getJsonObject().put(RESULT_CODE,3);
    }
    pushJsonResult();
}


}
