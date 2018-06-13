package com.example.skkj.controller.app;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.skkj.comment.BaseController;
import com.example.skkj.entity.Adjust;
import com.example.skkj.service.AdjustServer;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/appAdjust")
public class AdjustAppController extends BaseController{
    protected static Logger logger = Logger.getLogger(AdjustAppController.class);
    @Autowired
    private AdjustServer adjustServer;

     /**
          * @Author ZhouNan
          * @Description 查看效验数据
          * @params
          * @Date 2018/3/15 0015  11:11
          */
    @RequestMapping(value = "/appSelectByCommandId",method = RequestMethod.POST)
    @ResponseBody
    public void appSelectByCommandId(HttpServletRequest request){
        List<Adjust> adjusLists = new LinkedList<Adjust>();
        String commandId = request.getParameter("commandId");
        try {
            adjusLists = adjustServer.selectByCommandId(commandId);
            getJsonObject().put("adjusLists",adjusLists);
            getJsonObject().put(RESULT_CODE,1);
        } catch (Exception e) {
            logger.error("set appSelectByCommandId error:"+e.getMessage(),e);
            getJsonObject().put(RESULT_CODE,3);
        }
       pushJsonResult();
    }

}
