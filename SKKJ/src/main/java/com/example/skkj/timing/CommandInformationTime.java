package com.example.skkj.timing;
 /**
      * @Author ZhouNan
      * @Description 每填0点清空所有的命令发送
      * @params
      * @Date 2018/1/17 0017  14:47
      */

import com.example.skkj.comment.ConcurrentFenbu;
import com.example.skkj.comment.RedisUtils;
import com.example.skkj.dingxin.Constant;
import com.example.skkj.dingxin.HttpsUtil;
import com.example.skkj.dingxin.JsonUtil;
import com.example.skkj.entity.CommandInformation;
import com.example.skkj.service.AdjustServer;
import com.example.skkj.service.CommandInformationServer;
import org.apache.http.HttpResponse;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;


import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CommandInformationTime implements Job {
    @Autowired
    private CommandInformationServer commandInformationServer;

    @Autowired
    private AdjustServer adjustServer;

    public void deletByCommandInformation() {
        RedisUtils redisUtils = new RedisUtils();
        List<CommandInformation> commandInformationList = new LinkedList<CommandInformation>();
        String message = "false";
        try {
            commandInformationList = commandInformationServer.selectTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            message = commandInformationServer.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (message != "false" && !"false".equals(message)) {
            if (commandInformationList.size() > 0) {
                try {
                    adjustServer.delet();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    for (int i = 0; i < commandInformationList.size(); i++) {
                        String method = commandInformationList.get(i).getMethod();
                        String deviceNumber = commandInformationList.get(i).getDeviceNumber();
                        if (method == "COMMAND_IOT_SET_PARAMS" || "COMMAND_IOT_SET_PARAMS".equals(method)) {
                            redisUtils.delObject("boardLevel" + deviceNumber);
                        } else if (method == "COMMAND_IOT_GET_PARAMS" || "COMMAND_IOT_GET_PARAMS".equals(method)) {
                            redisUtils.delObject("selectBoar" + deviceNumber);
                        } else if (method == "COMMAND_IOT_REPORT_ENABLE" || "COMMAND_IOT_REPORT_ENABLE".equals(method)) {
                            redisUtils.delObject("samplingEnable" + deviceNumber);
                        } else if (method == "COMMAND_IOT_IMMEDIATELY_REPORT" || "COMMAND_IOT_IMMEDIATELY_REPORT".equals(method)) {
                            redisUtils.delObject("immediateSampling" + deviceNumber);
                        }
                        HttpsUtil httpsUtil = new HttpsUtil();
                        try {
                            httpsUtil.initSSLConfigForTwoWay();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        String accessToken = ConcurrentFenbu.getConcurrent().accessTokenPt(httpsUtil, "0");
                        String appId = Constant.APPID;
                        Map<String, String> param = new HashMap<>();
                        Map<String, String> param2 = new HashMap<>();
                        param.put("app_key", appId);
                        param.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
                        String a = "https://" + Constant.BASE_URL + "/iocm/app/cmd/v1.4.0/deviceCommandCancelTasks";
                        param2.put("deviceId", deviceNumber);
                        String str = JsonUtil.jsonObj2Sting(param2);
                        HttpResponse cc = httpsUtil.doPostJson(a, param, str);
                    }

                }

            }

        }


    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            deletByCommandInformation();
            System.out.println("commandInformationServer:获取" + commandInformationServer);

    }
}