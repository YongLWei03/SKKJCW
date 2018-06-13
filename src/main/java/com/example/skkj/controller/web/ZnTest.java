package com.example.skkj.controller.web;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.skkj.comment.ConcurrentFenbu;
import com.example.skkj.comment.RedisUtils;
import com.example.skkj.dingxin.Constant;
import com.example.skkj.dingxin.HttpsUtil;
import com.example.skkj.dingxin.JsonUtil;
import com.example.skkj.entity.CommandInformation;
import com.example.skkj.service.CommandInformationServer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/webZnTest")
public class ZnTest {
    @Autowired
    private CommandInformationServer commandInformationServer;
    @RequestMapping("/scduixiang")
    @ResponseBody
    public String scduixiang(HttpServletRequest request){
        String deviceNumber = request.getParameter("deviceNumber");
        HttpsUtil httpsUtil = new HttpsUtil();
        try {
            httpsUtil.initSSLConfigForTwoWay();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String accessToken = ConcurrentFenbu.getConcurrent().accessTokenPt(httpsUtil,"0");
        String appId = Constant.APPID;
        Map<String, String> param = new HashMap<>();
        Map<String, String> param2 = new HashMap<>();
        param.put("app_key", appId);
        param.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
//        String a = "https://device.api.ct10649.com:8743/iocm/app/cmd/v1.4.0/deviceCommandCancelTasks";
        String a = "https://180.101.147.89:8743/iocm/app/cmd/v1.4.0/deviceCommandCancelTasks";
        param2.put("deviceId",deviceNumber);
        String str = JsonUtil.jsonObj2Sting(param2);
        HttpResponse cc = httpsUtil.doPostJson(a,param,str);
        try {
            String bodySubscribe_deviceInfoChanged = httpsUtil.getHttpResponseBody(cc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if("HTTP/1.1 201 Created".equals(cc.getStatusLine().toString())){
            RedisUtils redisUtils = new RedisUtils();
            try {
                List<CommandInformation> list = commandInformationServer.selectByEqId(deviceNumber);
                commandInformationServer.deletByEqId(deviceNumber);
                if(list!=null && !"".equals(list) && list.size()>0){
                    for (int i = 0; i < list.size(); i++) {
                        String name = list.get(i).getName();
                        if(name == "设置参数命令" || "设置参数命令".equals(name)){
                            redisUtils.delObject("boardLevel"+deviceNumber);
                        }else if(name == "查询板级参数命令" || "查询板级参数命令".equals(name)){
                            redisUtils.delObject("selectBoar"+deviceNumber);
                        }else if(name == "设置定时采集使能" || "设置定时采集使能".equals(name)){
                            redisUtils.delObject("samplingEnable"+deviceNumber);
                        }else if(name == "立即采集命令" || "立即采集命令".equals(name)){
                            redisUtils.delObject("immediateSampling"+deviceNumber);
                        }else if(name == "效验设置" || "效验设置".equals(name)){
                            redisUtils.delObject("calibration"+deviceNumber);
                        }else {
                            redisUtils.delObject("algorithm"+deviceNumber);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "true";
        }else {
            return "false";
        }

    }
}
