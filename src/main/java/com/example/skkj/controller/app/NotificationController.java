package com.example.skkj.controller.app;

import com.example.skkj.comment.BaseController;
import com.example.skkj.entity.Notification;
import com.example.skkj.service.NotificationServer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/appNotification")
public class NotificationController extends BaseController {
    protected static Logger logger = Logger.getLogger(NotificationController.class);
    @Autowired
    private NotificationServer notificationServer;

    @RequestMapping(value = "/appSelect",method = RequestMethod.POST)
    @ResponseBody
    public void select(HttpServletRequest request){
        try {
            Notification noti = notificationServer.select();
            if(noti != null && !"".equals(noti)){
                getJsonObject().put("noti",noti);
                getJsonObject().put(RESULT_CODE,1);
            }else {
                getJsonObject().put(RESULT_CODE,2);
            }
        } catch (Exception e) {
            logger.error("消息提示；"+e.getMessage(),e);
        }
        pushJsonResult();
    }

}
