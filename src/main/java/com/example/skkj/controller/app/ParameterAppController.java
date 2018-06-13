package com.example.skkj.controller.app;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.skkj.comment.BaseController;
import com.example.skkj.service.ParameterServer;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/appParameter")
public class ParameterAppController extends BaseController{
    protected static Logger logger = Logger.getLogger(ParameterAppController.class);
    @Autowired
    private ParameterServer parameterServer;

    @RequestMapping("/appSelectByDeviceNumber")
    @ResponseBody
    public void appSelectByDeviceNumber(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = parameterServer.selectByDeviceNumber(request);
            Object parament = map.get("parament");
            if(parament != null && !"".equals(parament)){
                getJsonObject().put("parament",parament);
                getJsonObject().put(RESULT_CODE,1);
            }else {
                getJsonObject().put(RESULT_CODE,2);
            }

        } catch (Exception e) {
            logger.error("查看板级参数设置问题:"+e.getMessage(),e);
            getJsonObject().put(RESULT_CODE,3);
        }
        pushJsonResult();
    }
}
