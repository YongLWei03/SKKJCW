package com.example.skkj.controller.app;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.skkj.comment.BaseController;
import com.example.skkj.entity.EquipmentType;
import com.example.skkj.service.EquipmentTypeServer;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/appEquipmentType")
public class EquipmentTypeAppController extends BaseController{
    protected static Logger logger = Logger.getLogger(EquipmentTypeAppController.class);
    @Autowired
    private EquipmentTypeServer equipmentTypeServer;

    @RequestMapping("/appSelectById")
    @ResponseBody
    public void   appSelectById(HttpServletRequest request){
        List<EquipmentType> equiTypeList = new ArrayList<EquipmentType>();
        try {
            equiTypeList = equipmentTypeServer.finde();
            getJsonObject().put("equiTypeList",equiTypeList);
            getJsonObject().put(RESULT_CODE,1);
        } catch (Exception e) {
            logger.error("set appSelectById error:"+e.getMessage(),e);
            getJsonObject().put(RESULT_CODE,3);
        }
            pushJsonResult();
    }

}
