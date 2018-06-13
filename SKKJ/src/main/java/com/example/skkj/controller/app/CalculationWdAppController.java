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

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/appCalculationWd")
public class CalculationWdAppController extends BaseController{
    protected static Logger logger = Logger.getLogger(CalculationWdAppController.class);
    @Autowired
    private CalculationWdServer calculationWdServer;

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
            logger.error("set  appSelect error:"+e.getMessage(),e);
            getJsonObject().put(RESULT_CODE,3);
        }
        pushJsonResult();
    }

    @RequestMapping(value = "/appInsert",method = RequestMethod.POST)
    @ResponseBody
    public void appInsert(@RequestBody JSONObject jsonObject){
        CalculationWd calculationWd = jsonObject.getObject("data", CalculationWd.class);
        String message="";
        try {
            message = calculationWdServer.insert(calculationWd);
        } catch (Exception e) {
            logger.error("set  appInsert error:"+e.getMessage(),e);
            getJsonObject().put(RESULT_CODE, 3);
        }
        if(message != null && !"".equals(message)){
            if(message != "false" && !"false".equals(message)){
                getJsonObject().put(RESULT_CODE, 1);
            }else {
                getJsonObject().put(RESULT_CODE, 2);
            }

        }
        pushJsonResult();
    }


}
