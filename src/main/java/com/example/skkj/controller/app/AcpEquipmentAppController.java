package com.example.skkj.controller.app;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.skkj.comment.BaseController;
import com.example.skkj.entity.AcpEquipment;
import com.example.skkj.service.AcpEquipmentServer;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/appAcpEquipment")
public class AcpEquipmentAppController extends BaseController{
    protected static Logger logger = Logger.getLogger(AcpEquipmentAppController.class);
    @Autowired
    private AcpEquipmentServer acpEquipmentServer;


    @RequestMapping(value = "/appSelectByDeverId",method = RequestMethod.POST)
    @ResponseBody
    public void appSelectByDeverId(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = acpEquipmentServer.appSelectByDeverId(request);
            getJsonObject().put("acpequi",(AcpEquipment)map.get("acpequi"));
            getJsonObject().put("currentPage",(Integer)map.get("currentPage"));
            getJsonObject().put("pageCount",(Integer)map.get("pageCount"));
            getJsonObject().put(RESULT_CODE,1);
        } catch (Exception e) {
            logger.error("set appSelectByDeverId error:"+e.getMessage(),e);
            getJsonObject().put(RESULT_CODE,3);
        }
        pushJsonResult();
    }
}
