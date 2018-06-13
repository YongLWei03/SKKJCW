package com.example.skkj.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.skkj.comment.CfFinal;
import com.example.skkj.comment.ConcurrentFenbu;
import com.example.skkj.comment.RedisUtils;
import com.example.skkj.comment.TimeUtile;
import com.example.skkj.dingxin.Constant;
import com.example.skkj.dingxin.HttpsUtil;
import com.example.skkj.dingxin.JsonUtil;
import com.example.skkj.entity.*;
import com.example.skkj.mapper.CalculationWdMapper;
import com.example.skkj.mapper.CommandInformationMapper;
import com.example.skkj.mapper.EquipmentMapper;
import com.example.skkj.mapper.ParameterMapper;
import com.example.skkj.service.CommandServer;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.*;

@Service("commandServer")
public class CommandServerImpl implements CommandServer {

    @Autowired
    private CommandInformationMapper commandInformationMapper;

    @Autowired
    private ParameterMapper parameterMapper;

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Autowired
    private CalculationWdMapper calculationWdMapper;

    /**
     * @Author ZhouNan
     * @Description
     * @params sensorAddr 传感器地址
     * @params DeviceType 设备类型
     * @params swId 传感器
     * @params underTemp 温度上限
     * @params overTemp 温度下限
     * @params overSignal 温度信息号下限温度
     * @params underSignal 温度信息号上限温度
     * @params reportPeriod 读取温度时间
     * @params isRoot 节点
     * @params deviceNumber 设备ID（平台）
     * @params deviceSbId 设备出厂ID
     * @Date 2018/1/10 0010  15:03
     */
    @Override
    public String boardLevel(HttpServletRequest request) throws Exception {
        RedisUtils redisUtils = new RedisUtils();
        String deviceNumber = request.getParameter("deviceNumber");
        String message = ConcurrentFenbu.getConcurrent().boardLevel(deviceNumber,commandInformationMapper);
        if(message != "false" && !"false".equals(message)){
            String sensorAddr = request.getParameter("sensorAddr");
            String deviceType = request.getParameter("DeviceType");
            String sensorNum = request.getParameter("sensorNum");
            String[] swId = request.getParameterValues("swId");
            String underTemp = request.getParameter("underTemp");
            String overTemp = request.getParameter("overTemp");
            String overSignal = request.getParameter("overSignal");
            String underSignal = request.getParameter("underSignal");
            String reportPeriod = request.getParameter("reportPeriod");
            String isRoot = request.getParameter("isRoot");
            String captureMode = request.getParameter("captureMode");
            String capturePeriod = request.getParameter("capturePeriod");
//            String deviceNumber = request.getParameter("deviceNumber");
            String deviceSbId = request.getParameter("deviceSbId");
            String isAutoMode = request.getParameter("isAutoMode");
            String zoneInTatol = request.getParameter("zoneInTatol");
            String zoneInNum = request.getParameter("zoneInNum");
            String southIP = request.getParameter("southIP");
            String southPort = request.getParameter("southPort");
            String isModifyDeviceType = request.getParameter("isModifyDeviceType");
            String modifyDeviceType = request.getParameter("modifyDeviceType");
            int mid = TimeUtile.random();
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();
            String accessToken = ConcurrentFenbu.getConcurrent().accessTokenPt(httpsUtil,"2");
            List<Integer> sensorSequenceList = new ArrayList<Integer>();
            if(swId != null && swId.length > 0){
                for (int i = 0; i < swId.length; i++) {
                    int cgq = Integer.parseInt(swId[i]);
                    sensorSequenceList.add(cgq);
                }
            }
            StringBuilder sensorSequence = new StringBuilder();
            for (int j = 0; j < sensorSequenceList.size(); j++) {
                Integer c = sensorSequenceList.get(j);
                sensorSequence.append(c);
                if(j>=0 && sensorSequenceList.size()-1 != j){
                    sensorSequence.append(",");
                }
            }
            //Please make sure that the following parameter values have been modified in the Constant file.
            String urlPostAsynCmd = Constant.POST_ASYN_CMD;
            String appId = Constant.APPID;

            //please replace the deviceId, when you use the demo.
            String deviceId = deviceNumber;
//        String deviceId = "8c23b6b4-ea68-48fb-9c2f-90452a81ebb1";
            String urlPostAsynCmd1 = Constant.POST_ASYN_CMD;
            Map<String, String> param = new HashMap<>();
            param.put("app_key", appId);
            param.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
            Map<String, Object> param2 = new HashMap<>();
            param2.put("deviceId",deviceId);
            Map<String, Object> param3 = new HashMap<>();
            param3.put("serviceId","ServieCommand");
            param3.put("method","COMMAND_IOT_SET_PARAMS");
            param3.put("identifier",deviceSbId);
            param3.put("deviceType",Integer.parseInt(deviceType));
            param3.put("msgType","cloudReq");
            param3.put("isRoot",Integer.parseInt(isRoot));
//        param4.put("serviceId","ServieCommand");
//        param4.put("cmd","COMMAND_IOT_REPORT_ENABLE");
            param3.put("hasMore",0);
            Map<String, Object> param5 = new HashMap<>();
            param5.put("deviceId",Integer.parseInt(deviceSbId));
            param5.put("deviceType",Integer.parseInt(deviceType));
            param5.put("sensorSequence",sensorSequence.toString());
            param5.put("sensorAddr",Integer.parseInt(sensorAddr));
            param5.put("sensorNum",Integer.parseInt(sensorNum));
            param5.put("underTemp",Integer.parseInt(underTemp));
            param5.put("capturePeriod",Integer.parseInt(capturePeriod));
            param5.put("overTemp",Integer.parseInt(overTemp));
            param5.put("underSignal",Integer.parseInt(underSignal));
            param5.put("overSignal",Integer.parseInt(overSignal));
            param5.put("reportPeriod",Integer.parseInt(reportPeriod));
            param5.put("zoneInTatol",Integer.parseInt(zoneInTatol));
            param5.put("zoneInNum",Integer.parseInt(zoneInNum));
            param5.put("isRoot",Integer.parseInt(isRoot));
            param5.put("isModifyDeviceType",Integer.parseInt(isModifyDeviceType));
            param5.put("modifyDeviceType",Integer.parseInt(modifyDeviceType));
            param5.put("captureMode",Integer.parseInt(captureMode));
            param5.put("isAutoMode",Integer.parseInt(isAutoMode));
            param5.put("southIP",southIP);
            param5.put("southPort",southPort);
//            param5.put("timeStamps", TimeUtile.shijiancuo());
            ObjectNode paras = JsonUtil.convertObject2ObjectNode(param5);
            param3.put("paras",param5);
            ObjectNode command = JsonUtil.convertObject2ObjectNode(param3);
            param2.put("command",command);
            param2.put("callbackUrl", CfFinal.URL_PORT+"/SKKJ/DXjktjController/deviceCommands");
            param2.put("expireTime",Integer.parseInt(String.valueOf(1000*60*60)));
            String param6 = JsonUtil.jsonObj2Sting(param2);
            HttpResponse responsePostAsynCmd = httpsUtil.doPostJson(urlPostAsynCmd1, param, param6);
            String responseBody = httpsUtil.getHttpResponseBody(responsePostAsynCmd);
            StatusLine statusLine = responsePostAsynCmd.getStatusLine();
            System.out.println("statusLine:"+statusLine);
            System.out.println("responseBody:"+responseBody);
            if("HTTP/1.1 201 Created".equals(statusLine.toString()) || "HTTP/1.1 201 Created" == statusLine.toString()){
                String date = TimeUtile.dateTimeFomar();
                Parameter parameter = new Parameter();
                User user = (User)request.getSession().getAttribute("user");
                JSONObject jsonCom = JSONObject.parseObject(responseBody);
                String commandId = (String) jsonCom.get("commandId");
                CommandInformation commad = new CommandInformation();
                commad.setCommandId(commandId);
                commad.setDeviceNumber(deviceNumber);
                commad.setName("设置参数命令");
                commad.setType("3");
                commad.setTime(date);
                commad.setUserName(user.getUserName());
                commad.setMethod("COMMAND_IOT_SET_PARAMS");
                commandInformationMapper.insertCommand(commad);
                parameter.setCaptureMode(captureMode);
                parameter.setCapturePeriod(capturePeriod);
                parameter.setDeviceNumber(deviceNumber);
                parameter.setIsAutoMode(isAutoMode);
                parameter.setOverSignal(overSignal);
                parameter.setReportPeriod(reportPeriod);
                parameter.setSensorAddr(sensorAddr);
                parameter.setSensorNum(sensorNum);
                parameter.setSouthIP(southIP);
                parameter.setSouthPort(southPort);
                parameter.setSwId(sensorSequence.toString());
                parameter.setUnderSignal(underSignal);
                parameter.setUnderTemp(underTemp);
                parameter.setZoneInNum(zoneInNum);
                parameter.setZoneInTatol(zoneInTatol);
                parameter.setTime(date);
                parameter.setOverTemp(overTemp);
                parameter.setModifyDeviceType(modifyDeviceType);
                parameterMapper.insert(parameter);
                Equipment equi = new Equipment();
                equi.setDeviceNumber(deviceNumber);
                equi.setDeviceType(deviceType);
                equipmentMapper.updateByDeviceNumber(equi);
                return "true";
            }else {
                redisUtils.setString("boardLevel"+deviceNumber,"5");
                return "false";
            }
        }else {
            //命令已经执行成功或者正在执行
            return "no";
        }

    }
    /**
     * @Author ZhouNan
     * @Description 板级参数信息查询
     * @params DeviceType 设备类型
     * @params isRoot 节点
     * @params deviceNumber 设备ID（平台）
     * @params deviceSbId 设备出厂ID
     * @Date 2018/1/11 0011  15:38
     */
    @Override
    public String selectBoar(HttpServletRequest request) throws Exception {
        RedisUtils redisUtils = new RedisUtils();
        String deviceNumber = request.getParameter("deviceNumber");
        String message = ConcurrentFenbu.getConcurrent().selectBoar(deviceNumber,commandInformationMapper);
        if (message != "false" && !"false".equals(message)){
            String deviceType = request.getParameter("DeviceType");
            String isRoot = request.getParameter("isRoot");
//            String deviceNumber = request.getParameter("deviceNumber");
            String deviceSbId = request.getParameter("deviceSbId");
            int mid = TimeUtile.random();
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();
            String accessToken = ConcurrentFenbu.getConcurrent().accessTokenPt(httpsUtil,"2");
            List<Integer> sensorSequence = new ArrayList<Integer>();
            //Please make sure that the following parameter values have been modified in the Constant file.
            String urlPostAsynCmd = Constant.POST_ASYN_CMD;
            String appId = Constant.APPID;
            //please replace the deviceId, when you use the demo.
            String deviceId = deviceNumber;
//        String deviceId = "8c23b6b4-ea68-48fb-9c2f-90452a81ebb1";
            String urlPostAsynCmd1 = Constant.POST_ASYN_CMD;
            Map<String, String> param = new HashMap<>();
            param.put("app_key", appId);
            param.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
            Map<String, Object> param2 = new HashMap<>();
            param2.put("deviceId",deviceId);
            Map<String, Object> param3 = new HashMap<>();
            param3.put("serviceId","ServieCommand");
            param3.put("method","COMMAND_IOT_GET_PARAMS");
            param3.put("identifier",deviceSbId);
            param3.put("deviceType",Integer.parseInt(deviceType));
            param3.put("msgType","cloudReq");
            param3.put("hasMore",0);
            Map<String, Object> param5 = new HashMap<>();
            param5.put("deviceId",Integer.parseInt(deviceSbId));
            param5.put("deviceType",Integer.parseInt(deviceType));
            param5.put("isRoot",Integer.parseInt(isRoot));
            ObjectNode paras = JsonUtil.convertObject2ObjectNode(param5);
            param3.put("paras",param5);
            ObjectNode command = JsonUtil.convertObject2ObjectNode(param3);
            param2.put("command",command);
            param2.put("callbackUrl",CfFinal.URL_PORT+"/SKKJ/DXjktjController/deviceCommandsCs");
            param2.put("expireTime",Integer.parseInt(String.valueOf(1000*60*60)));
            String param6 = JsonUtil.jsonObj2Sting(param2);
            HttpResponse responsePostAsynCmd = httpsUtil.doPostJson(urlPostAsynCmd1, param, param6);
            String responseBody = httpsUtil.getHttpResponseBody(responsePostAsynCmd);
            StatusLine statusLine = responsePostAsynCmd.getStatusLine();
            System.out.println("statusLine:"+statusLine);
            System.out.println("responseBody:"+responseBody);
            if("HTTP/1.1 201 Created".equals(statusLine.toString()) || "HTTP/1.1 201 Created" == statusLine.toString()){
                JSONObject jsonCom = JSONObject.parseObject(responseBody);
                String date = TimeUtile.dateTimeFomar();
                User user = (User)request.getSession().getAttribute("user");
                String commandId = (String) jsonCom.get("commandId");
                CommandInformation commad = new CommandInformation();
                commad.setDeviceNumber(deviceNumber);
                commad.setCommandId(commandId);
                commad.setName("查询板级参数命令");
                commad.setType("3");
                commad.setTime(date);
                commad.setUserName(user.getUserName());
                commad.setMethod("COMMAND_IOT_GET_PARAMS");
                commandInformationMapper.insertCommand(commad);
                Integer userId = user.getUserId();
                return "true";
            }else {
                redisUtils.setString("selectBoar"+deviceNumber,"5");
                return "false";
            }
        }else {
            return "no";
        }

    }

    /**
     * @Author ZhouNan
     * @Description 采样史能
     * @params DeviceType 设备类型
     * @params isRoot 节点
     * @params deviceNumber 设备ID（平台）
     * @params deviceSbId 设备出厂ID
     * @Date 2018/1/11 0011  15:38
     */
    @Override
    public String samplingEnable(HttpServletRequest request) throws Exception {
        RedisUtils redisUtils = new RedisUtils();
        String deviceNumber = request.getParameter("deviceNumber");
        String message = ConcurrentFenbu.getConcurrent().samplingEnable(deviceNumber,commandInformationMapper);
        if(message != "false" && !"false".equals(message)){
            String deviceType = request.getParameter("DeviceType");
            String isRoot = request.getParameter("isRoot");
//            String deviceNumber = request.getParameter("deviceNumber");
            String deviceSbId = request.getParameter("deviceSbId");
            String captureMode = request.getParameter("captureMode");
            String reportPeriod = request.getParameter("reportPeriod");
            String capturePeriod = request.getParameter("capturePeriod");
            String zoneInTatol = request.getParameter("zoneInTatol");
            String zoneInNum = request.getParameter("zoneInNum");
            int mid = TimeUtile.random();
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();
            String accessToken = ConcurrentFenbu.getConcurrent().accessTokenPt(httpsUtil,"2");
            List<Integer> sensorSequence = new ArrayList<Integer>();

            //Please make sure that the following parameter values have been modified in the Constant file.
            String urlPostAsynCmd = Constant.POST_ASYN_CMD;
            String appId = Constant.APPID;

            //please replace the deviceId, when you use the demo.
            String deviceId = deviceNumber;
//        String deviceId = "8c23b6b4-ea68-48fb-9c2f-90452a81ebb1";
            String urlPostAsynCmd1 = Constant.POST_ASYN_CMD;
            Map<String, String> param = new HashMap<>();
            param.put("app_key", appId);
            param.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
            Map<String, Object> param2 = new HashMap<>();
            param2.put("deviceId",deviceId);
            Map<String, Object> param3 = new HashMap<>();
            param3.put("serviceId","ServieCommand");
            param3.put("method","COMMAND_IOT_REPORT_ENABLE");
            param3.put("identifier",deviceSbId);
            param3.put("deviceType",Integer.parseInt(deviceType));
            param3.put("msgType","cloudReq");
            param3.put("hasMore",0);
            param3.put("mid",mid);
            Map<String, Object> param5 = new HashMap<>();
            param5.put("deviceId",Integer.parseInt(deviceSbId));
            param5.put("deviceType",Integer.parseInt(deviceType));
            param5.put("isRoot",Integer.parseInt(isRoot));
            param5.put("captureMode",Integer.parseInt(captureMode));
            param5.put("zoneInTatol",Integer.parseInt(zoneInTatol));
            param5.put("zoneInNum",Integer.parseInt(zoneInNum));
            param5.put("capturePeriod",Integer.parseInt(capturePeriod));
            param5.put("reportPeriod",Integer.parseInt(reportPeriod));
            ObjectNode paras = JsonUtil.convertObject2ObjectNode(param5);
            param3.put("paras",param5);
            ObjectNode command = JsonUtil.convertObject2ObjectNode(param3);
            param2.put("command",command);
            param2.put("callbackUrl",CfFinal.URL_PORT+"/SKKJ/DXjktjController/samplingEnable");
            param2.put("expireTime",Integer.parseInt(String.valueOf(1000*60*60)));
            String param6 = JsonUtil.jsonObj2Sting(param2);
            HttpResponse responsePostAsynCmd = httpsUtil.doPostJson(urlPostAsynCmd1, param, param6);
            String responseBody = httpsUtil.getHttpResponseBody(responsePostAsynCmd);
            StatusLine statusLine = responsePostAsynCmd.getStatusLine();
            if("HTTP/1.1 201 Created".equals(statusLine.toString()) || "HTTP/1.1 201 Created" == statusLine.toString()){
                String date = TimeUtile.dateTimeFomar();
                User user = (User)request.getSession().getAttribute("user");
                JSONObject jsonCom = JSONObject.parseObject(responseBody);
                String commandId = (String) jsonCom.get("commandId");
                CommandInformation commad = new CommandInformation();
                commad.setDeviceNumber(deviceNumber);
                commad.setName("设置定时采集使能");
                commad.setCommandId(commandId);
                commad.setType("3");
                commad.setTime(date);
                commad.setUserName(user.getUserName());
                commad.setMethod("COMMAND_IOT_REPORT_ENABLE");
                commandInformationMapper.insertCommand(commad);
                Parameter para = new Parameter();
                para.setCaptureMode(captureMode);
                para.setZoneInTatol(zoneInTatol);
                para.setZoneInNum(zoneInNum);
                para.setReportPeriod(reportPeriod);
                para.setCapturePeriod(capturePeriod);
                para.setDeviceNumber(deviceNumber);
                Parameter pasd = parameterMapper.selectByDeviceNumber(deviceNumber);
                if(pasd != null && !"".equals(pasd)){
                    para.setTime(pasd.getTime());
                    parameterMapper.updateByTimeAndDeviceNumber(para);
                }
                return "true";
            }else {
                redisUtils.setString("samplingEnable"+deviceNumber,"5");
                return "false";
            }
        }else {
            return "no";
        }
    }
    /**
     * @Author ZhouNan
     * @Description 立即采样
     * @params
     * @Date 2018/1/12 0012  13:27
     */
    @Override
    public String immediateSampling(HttpServletRequest request) throws Exception {
        RedisUtils redisUtils = new RedisUtils();
        String deviceNumber = request.getParameter("deviceNumber");
        String message = ConcurrentFenbu.getConcurrent().immediateSampling(deviceNumber,commandInformationMapper);
        if(message != "false" && !"false".equals(message)){
            String deviceType = request.getParameter("DeviceType");
            String isRoot = request.getParameter("isRoot");
//            String deviceNumber = request.getParameter("deviceNumber");
            String deviceSbId = request.getParameter("deviceSbId");
            int mid = TimeUtile.random();
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();
            String accessToken = ConcurrentFenbu.getConcurrent().accessTokenPt(httpsUtil,"2");
            List<Integer> sensorSequence = new ArrayList<Integer>();

            //Please make sure that the following parameter values have been modified in the Constant file.
            String urlPostAsynCmd = Constant.POST_ASYN_CMD;
            String appId = Constant.APPID;

            //please replace the deviceId, when you use the demo.
            String deviceId = deviceNumber;
//        String deviceId = "8c23b6b4-ea68-48fb-9c2f-90452a81ebb1";
            String urlPostAsynCmd1 = Constant.POST_ASYN_CMD;
            Map<String, String> param = new HashMap<>();
            param.put("app_key", appId);
            param.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
            Map<String, Object> param2 = new HashMap<>();
            param2.put("deviceId",deviceId);
            Map<String, Object> param3 = new HashMap<>();
            param3.put("serviceId","ServieCommand");
            param3.put("method","COMMAND_IOT_IMMEDIATELY_REPORT");
            param3.put("identifier",deviceSbId);
            param3.put("deviceType",Integer.parseInt(deviceType));
            param3.put("msgType","cloudReq");
            param3.put("hasMore",0);
            Map<String, Object> param5 = new HashMap<>();
            param5.put("deviceId",Integer.parseInt(deviceSbId));
            param5.put("deviceType",Integer.parseInt(deviceType));
            param5.put("isRoot",Integer.parseInt(isRoot));
            ObjectNode paras = JsonUtil.convertObject2ObjectNode(param5);
            param3.put("paras",param5);
            ObjectNode command = JsonUtil.convertObject2ObjectNode(param3);
            param2.put("command",command);
            param2.put("callbackUrl",CfFinal.URL_PORT+"/SKKJ/DXjktjController/immediateSampling");
            param2.put("expireTime",Integer.parseInt(String.valueOf(1000*60*60)));
            String param6 = JsonUtil.jsonObj2Sting(param2);
            HttpResponse responsePostAsynCmd = httpsUtil.doPostJson(urlPostAsynCmd1, param, param6);
            String responseBody = httpsUtil.getHttpResponseBody(responsePostAsynCmd);
            StatusLine statusLine = responsePostAsynCmd.getStatusLine();
            if("HTTP/1.1 201 Created".equals(statusLine.toString()) || "HTTP/1.1 201 Created" == statusLine.toString()){
                String date = TimeUtile.dateTimeFomar();
                User user = (User)request.getSession().getAttribute("user");
                JSONObject jsonCom = JSONObject.parseObject(responseBody);
                String commandId = (String) jsonCom.get("commandId");
                CommandInformation commad = new CommandInformation();
                commad.setDeviceNumber(deviceNumber);
                commad.setCommandId(commandId);
                commad.setName("立即采集命令");
                commad.setType("3");
                commad.setTime(date);
                commad.setUserName(user.getUserName());
                commad.setMethod("COMMAND_IOT_IMMEDIATELY_REPORT");
                commandInformationMapper.insertCommand(commad);
                return "true";
            }else {
                redisUtils.setString("immediateSampling"+deviceNumber,"5");
                return "false";
            }
        }else {
            return "no";
        }

    }
    //设置
    @Override
    public String calibration(HttpServletRequest request) throws Exception {
        RedisUtils redisUtils = new RedisUtils();
        String deviceNumber = request.getParameter("deviceNumber");
        String message = ConcurrentFenbu.getConcurrent().calibration(deviceNumber,commandInformationMapper);
        if(message != "false" && !"false".equals(message)){
            String deviceType = request.getParameter("DeviceType");
            String isRoot = request.getParameter("isRoot");
            String[] switchFlag = request.getParameterValues("switchFlag");
//            String deviceNumber = request.getParameter("deviceNumber");
            String deviceSbId = request.getParameter("deviceSbId");
            String adjustValues = request.getParameter("adjustValues");
            String adjustTimes = request.getParameter("adjustTimes");
            int mid = TimeUtile.random();
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();
            String accessToken = ConcurrentFenbu.getConcurrent().accessTokenPt(httpsUtil,"2");
            List<Integer> sensorSequence = new ArrayList<Integer>();
            StringBuilder budei = new StringBuilder("00000000");
            if(switchFlag != null && switchFlag.length > 0){
                for (int j = 23; j >= 0; j--) {
                    int h = 0;
                    for (int i = 0; i < switchFlag.length; i++) {
                        int cgq = Integer.parseInt(switchFlag[i]);
                        if(j == cgq){
                            budei.append("1");
                            h += 1;
                            break;
                        }
                        if(switchFlag.length -1 == i && h == 0){
                            budei.append("0");
                        }
                    }
                }

            }
            //Please make sure that the following parameter values have been modified in the Constant file.
            String urlPostAsynCmd = Constant.POST_ASYN_CMD;
            String appId = Constant.APPID;

            //please replace the deviceId, when you use the demo.
            String deviceId = deviceNumber;
//        String deviceId = "8c23b6b4-ea68-48fb-9c2f-90452a81ebb1";
            String urlPostAsynCmd1 = Constant.POST_ASYN_CMD;
            Map<String, String> param = new HashMap<>();
            param.put("app_key", appId);
            param.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
            Map<String, Object> param2 = new HashMap<>();
            param2.put("deviceId",deviceId);
            Map<String, Object> param3 = new HashMap<>();
            param3.put("serviceId","ServieCommand");
            param3.put("method","CMD_SERSONR_ADJUST");
            param3.put("identifier",deviceSbId);
            param3.put("deviceType",Integer.parseInt(deviceType));
            param3.put("msgType","cloudReq");
            param3.put("hasMore",0);
            Map<String, Object> param5 = new HashMap<>();
            param5.put("deviceId",Integer.parseInt(deviceSbId));
            param5.put("deviceType",Integer.parseInt(deviceType));
            param5.put("isRoot",Integer.parseInt(isRoot));
            param5.put("adjustValues",Float.parseFloat(adjustValues));
            param5.put("switchFlag",Integer.parseInt(budei.toString(),2));
            param5.put("adjustTimes",Integer.parseInt(adjustTimes));
            ObjectNode paras = JsonUtil.convertObject2ObjectNode(param5);
            param3.put("paras",param5);
            ObjectNode command = JsonUtil.convertObject2ObjectNode(param3);
            param2.put("command",command);
            param2.put("callbackUrl",CfFinal.URL_PORT+"/SKKJ/DXjktjController/calibration");
            param2.put("expireTime",Integer.parseInt(String.valueOf(1000*60*60)));
            String param6 = JsonUtil.jsonObj2Sting(param2);
            System.out.println("param6："+param6);
            HttpResponse responsePostAsynCmd = httpsUtil.doPostJson(urlPostAsynCmd1, param, param6);
            String responseBody = httpsUtil.getHttpResponseBody(responsePostAsynCmd);
            StatusLine statusLine = responsePostAsynCmd.getStatusLine();
            if("HTTP/1.1 201 Created".equals(statusLine.toString()) || "HTTP/1.1 201 Created" == statusLine.toString()){
                String date = TimeUtile.dateTimeFomar();
                User user = (User)request.getSession().getAttribute("user");
                JSONObject jsonCom = JSONObject.parseObject(responseBody);
                String commandId = (String) jsonCom.get("commandId");
                CommandInformation commad = new CommandInformation();
                commad.setDeviceNumber(deviceNumber);
                commad.setCommandId(commandId);
                commad.setName("效验设置");
                commad.setType("3");
                commad.setTime(date);
                commad.setUserName(user.getUserName());
                commad.setMethod("CMD_SERSONR_ADJUST");
                commandInformationMapper.insertCommand(commad);
                return "true";
            }else {
                redisUtils.setString("calibration"+deviceNumber,"5");
                return "false";
            }
        }else {
            return "no";
        }
    }

    /**
     * @Author ZhouNan
     * @Description app设置板级参数
     * @params
     * @Date 2018/3/23 0023  17:24
     */
    @Override
    public String appBoardLevel(JSONObject jsonObject) throws Exception {
        RedisUtils redisUtils = new RedisUtils();
        String deviceNumber = jsonObject.getString("deviceNumber");
        String message = ConcurrentFenbu.getConcurrent().boardLevel(deviceNumber,commandInformationMapper);
        if(message != "false" && !"false".equals(message)){
            JSONObject data = jsonObject.getJSONObject("data");
            String sensorAddr = data.getString("sensorAddr");
            String deviceType = jsonObject.getString("DeviceType");
            String sensorNum = data.getString("sensorNum");
            String swId = data.getString("swId");
            String underTemp = data.getString("underTemp");
            String overTemp = data.getString("overTemp");
            String overSignal = data.getString("overSignal");
            String underSignal = data.getString("underSignal");
            String reportPeriod = data.getString("reportPeriod");
            String isRoot = jsonObject.getString("isRoot");
            String captureMode = data.getString("captureMode");
            String capturePeriod =data.getString("capturePeriod");
//            String deviceNumber = request.getParameter("deviceNumber");
            String deviceSbId = jsonObject.getString("deviceSbId");
            String isAutoMode = data.getString("isAutoMode");
            String zoneInTatol = data.getString("zoneInTatol");
            String zoneInNum =data.getString("zoneInNum");
            String southIP = data.getString("southIP");
            String southPort = data.getString("southPort");
            String modifyDeviceType = data.getString("modifyDeviceType");
            int mid = TimeUtile.random();
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();
            String accessToken = ConcurrentFenbu.getConcurrent().accessTokenPt(httpsUtil,"2");
            //Please make sure that the following parameter values have been modified in the Constant file.
            String urlPostAsynCmd = Constant.POST_ASYN_CMD;
            String appId = Constant.APPID;
            String[] swIdString = swId.split(",");
            List<Integer> sensorSequenceList = new ArrayList<Integer>();
            if(swId != null && swIdString.length > 0){
                for (int i = 0; i < swIdString.length; i++) {
                    int cgq = Integer.parseInt(swIdString[i]);
                    sensorSequenceList.add(cgq);
                }
            }
            StringBuilder sensorSequence = new StringBuilder();
            for (int j = 0; j < sensorSequenceList.size(); j++) {
                Integer c = sensorSequenceList.get(j);
                sensorSequence.append(c);
                if(j>=0 && sensorSequenceList.size()-1 != j){
                    sensorSequence.append(",");
                }
            }

            //please replace the deviceId, when you use the demo.
            String deviceId = deviceNumber;
//        String deviceId = "8c23b6b4-ea68-48fb-9c2f-90452a81ebb1";
            String urlPostAsynCmd1 = Constant.POST_ASYN_CMD;
            Map<String, String> param = new HashMap<>();
            param.put("app_key", appId);
            param.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
            Map<String, Object> param2 = new HashMap<>();
            param2.put("deviceId",deviceId);
            Map<String, Object> param3 = new HashMap<>();
            param3.put("serviceId","ServieCommand");
            param3.put("method","COMMAND_IOT_SET_PARAMS");
            param3.put("identifier",deviceSbId);
            param3.put("deviceType",Integer.parseInt(deviceType));
            param3.put("msgType","cloudReq");
            param3.put("isRoot",Integer.parseInt(isRoot));
//        param4.put("serviceId","ServieCommand");
//        param4.put("cmd","COMMAND_IOT_REPORT_ENABLE");
            param3.put("hasMore",0);
            Map<String, Object> param5 = new HashMap<>();
            param5.put("deviceId",Integer.parseInt(deviceSbId));
            param5.put("deviceType",Integer.parseInt(deviceType));
            param5.put("sensorSequence",sensorSequence.toString());
            param5.put("sensorAddr",Integer.parseInt(sensorAddr));
            param5.put("sensorNum",Integer.parseInt(sensorNum));
            param5.put("underTemp",Integer.parseInt(underTemp));
            param5.put("capturePeriod",Integer.parseInt(capturePeriod));
            param5.put("overTemp",Integer.parseInt(overTemp));
            param5.put("underSignal",Integer.parseInt(underSignal));
            param5.put("overSignal",Integer.parseInt(overSignal));
            param5.put("reportPeriod",Integer.parseInt(reportPeriod));
            param5.put("zoneInTatol",Integer.parseInt(zoneInTatol));
            param5.put("zoneInNum",Integer.parseInt(zoneInNum));
            param5.put("isRoot",Integer.parseInt(isRoot));
            param5.put("isModifyDeviceType","0");
            param5.put("captureMode",Integer.parseInt(captureMode));
            param5.put("isAutoMode",Integer.parseInt(isAutoMode));
            param5.put("southIP",southIP);
            param5.put("southPort",southPort);
            param5.put("modifyDeviceType",modifyDeviceType);
//            param5.put("timeStamps", TimeUtile.shijiancuo());
            ObjectNode paras = JsonUtil.convertObject2ObjectNode(param5);
            param3.put("paras",param5);
            ObjectNode command = JsonUtil.convertObject2ObjectNode(param3);
            param2.put("command",command);
            param2.put("callbackUrl", CfFinal.URL_PORT+"/SKKJ/DXjktjController/deviceCommands");
            param2.put("expireTime",Integer.parseInt(String.valueOf(1000*60*60)));
            String param6 = JsonUtil.jsonObj2Sting(param2);
            HttpResponse responsePostAsynCmd = httpsUtil.doPostJson(urlPostAsynCmd1, param, param6);
            String responseBody = httpsUtil.getHttpResponseBody(responsePostAsynCmd);
            StatusLine statusLine = responsePostAsynCmd.getStatusLine();
            System.out.println("statusLine:"+statusLine);
            System.out.println("responseBody:"+responseBody);
            if("HTTP/1.1 201 Created".equals(statusLine.toString()) || "HTTP/1.1 201 Created" == statusLine.toString()){
                String date = TimeUtile.dateTimeFomar();
                Parameter parameter = new Parameter();
                String userName = jsonObject.getString("userName");
                JSONObject jsonCom = JSONObject.parseObject(responseBody);
                String commandId = (String) jsonCom.get("commandId");
                CommandInformation commad = new CommandInformation();
                commad.setCommandId(commandId);
                commad.setDeviceNumber(deviceNumber);
                commad.setName("设置参数命令");
                commad.setType("3");
                commad.setTime(date);
                commad.setUserName(userName);
                commad.setMethod("COMMAND_IOT_SET_PARAMS");
                commandInformationMapper.insertCommand(commad);
                parameter.setCaptureMode(captureMode);
                parameter.setCapturePeriod(capturePeriod);
                parameter.setDeviceNumber(deviceNumber);
                parameter.setIsAutoMode(isAutoMode);
                parameter.setOverSignal(overSignal);
                parameter.setReportPeriod(reportPeriod);
                parameter.setSensorAddr(sensorAddr);
                parameter.setSensorNum(sensorNum);
                parameter.setSouthIP(southIP);
                parameter.setSouthPort(southPort);
                parameter.setSwId(sensorSequence.toString());
                parameter.setUnderSignal(underSignal);
                parameter.setUnderTemp(underTemp);
                parameter.setZoneInNum(zoneInNum);
                parameter.setZoneInTatol(zoneInTatol);
                parameter.setTime(date);
                parameter.setOverTemp(overTemp);
                parameter.setModifyDeviceType(modifyDeviceType);
                parameterMapper.insert(parameter);
                Equipment equi = new Equipment();
                equi.setDeviceNumber(deviceNumber);
                equi.setDeviceType(deviceType);
                equipmentMapper.updateByDeviceNumber(equi);
                return "true";
            }else {
                redisUtils.setString("boardLevel"+deviceNumber,"5");
                return "false";
            }
        }else {
            //命令已经执行成功或者正在执行
            return "no";
        }
    }

    /**
     * @Author ZhouNan
     * @Description 板级参数信息查询
     * @params DeviceType 设备类型
     * @params isRoot 节点
     * @params deviceNumber 设备ID（平台）
     * @params deviceSbId 设备出厂ID
     * @Date 2018/1/11 0011  15:38
     */
    @Override
    public String appSelectBoar(JSONObject jsonObject) throws Exception {
        RedisUtils redisUtils = new RedisUtils();
        String deviceNumber = jsonObject.getString("deviceNumber");
        String message = ConcurrentFenbu.getConcurrent().selectBoar(deviceNumber,commandInformationMapper);
        if (message != "false" && !"false".equals(message)){
            String deviceType = jsonObject.getString("deviceType");
            String isRoot = jsonObject.getString("isRoot");
//            String deviceNumber = request.getParameter("deviceNumber");
            String deviceSbId = jsonObject.getString("deviceSbId");
            int mid = TimeUtile.random();
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();
            String accessToken = ConcurrentFenbu.getConcurrent().accessTokenPt(httpsUtil,"2");
            List<Integer> sensorSequence = new ArrayList<Integer>();

            //Please make sure that the following parameter values have been modified in the Constant file.
            String urlPostAsynCmd = Constant.POST_ASYN_CMD;
            String appId = Constant.APPID;

            //please replace the deviceId, when you use the demo.
            String deviceId = deviceNumber;
//        String deviceId = "8c23b6b4-ea68-48fb-9c2f-90452a81ebb1";
            String urlPostAsynCmd1 = Constant.POST_ASYN_CMD;
            Map<String, String> param = new HashMap<>();
            param.put("app_key", appId);
            param.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
            Map<String, Object> param2 = new HashMap<>();
            param2.put("deviceId",deviceId);
            Map<String, Object> param3 = new HashMap<>();
            param3.put("serviceId","ServieCommand");
            param3.put("method","COMMAND_IOT_GET_PARAMS");
            param3.put("identifier",deviceSbId);
            param3.put("deviceType",Integer.parseInt(deviceType));
            param3.put("msgType","cloudReq");
            param3.put("hasMore",0);
            Map<String, Object> param5 = new HashMap<>();
            param5.put("deviceId",Integer.parseInt(deviceSbId));
            param5.put("deviceType",Integer.parseInt(deviceType));
            param5.put("isRoot",Integer.parseInt(isRoot));
            ObjectNode paras = JsonUtil.convertObject2ObjectNode(param5);
            param3.put("paras",param5);
            ObjectNode command = JsonUtil.convertObject2ObjectNode(param3);
            param2.put("command",command);
            param2.put("callbackUrl",CfFinal.URL_PORT+"/SKKJ/DXjktjController/deviceCommandsCs");
            param2.put("expireTime",Integer.parseInt(String.valueOf(1000*60*60)));
            String param6 = JsonUtil.jsonObj2Sting(param2);
            HttpResponse responsePostAsynCmd = httpsUtil.doPostJson(urlPostAsynCmd1, param, param6);
            String responseBody = httpsUtil.getHttpResponseBody(responsePostAsynCmd);
            StatusLine statusLine = responsePostAsynCmd.getStatusLine();
            if("HTTP/1.1 201 Created".equals(statusLine.toString()) || "HTTP/1.1 201 Created" == statusLine.toString()){
                JSONObject jsonCom = JSONObject.parseObject(responseBody);
                String date = TimeUtile.dateTimeFomar();
                String userName = jsonObject.getString("userName");
                String commandId = (String) jsonCom.get("commandId");
                CommandInformation commad = new CommandInformation();
                commad.setDeviceNumber(deviceNumber);
                commad.setCommandId(commandId);
                commad.setName("查询板级参数命令");
                commad.setType("3");
                commad.setTime(date);
                commad.setUserName(userName);
                commad.setMethod("COMMAND_IOT_GET_PARAMS");
                commandInformationMapper.insertCommand(commad);
                return "true";
            }else {
                redisUtils.setString("selectBoar"+deviceNumber,"5");
                return "false";
            }
        }else {
            return "no";
        }
    }

    /**
     * @Author ZhouNan
     * @Description 采样史能
     * @params DeviceType 设备类型
     * @params isRoot 节点
     * @params deviceNumber 设备ID（平台）
     * @params deviceSbId 设备出厂ID
     * @Date 2018/1/11 0011  15:38
     */
    @Override
    public String appSamplingEnable(JSONObject jsonObject) throws Exception {
        RedisUtils redisUtils = new RedisUtils();
        String deviceNumber = jsonObject.getString("deviceNumber");
        String message = ConcurrentFenbu.getConcurrent().samplingEnable(deviceNumber,commandInformationMapper);
        if(message != "false" && !"false".equals(message)){
            String deviceType = jsonObject.getString("DeviceType");
            String isRoot = jsonObject.getString("isRoot");
//            String deviceNumber = request.getParameter("deviceNumber");
            String deviceSbId = jsonObject.getString("deviceSbId");
            JSONObject data = jsonObject.getJSONObject("data");
            String captureMode = data.getString("captureMode");
            String reportPeriod = data.getString("reportPeriod");
            String capturePeriod = data.getString("capturePeriod");
            String zoneInTatol = data.getString("zoneInTatol");
            String zoneInNum = data.getString("zoneInNum");
            int mid = TimeUtile.random();
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();
            String accessToken = ConcurrentFenbu.getConcurrent().accessTokenPt(httpsUtil,"2");
            String urlPostAsynCmd = Constant.POST_ASYN_CMD;
            String appId = Constant.APPID;

            String deviceId = deviceNumber;
            String urlPostAsynCmd1 = Constant.POST_ASYN_CMD;
            Map<String, String> param = new HashMap<>();
            param.put("app_key", appId);
            param.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
            Map<String, Object> param2 = new HashMap<>();
            param2.put("deviceId",deviceId);
            Map<String, Object> param3 = new HashMap<>();
            param3.put("serviceId","ServieCommand");
            param3.put("method","COMMAND_IOT_REPORT_ENABLE");
            param3.put("identifier",deviceSbId);
            param3.put("deviceType",Integer.parseInt(deviceType));
            param3.put("msgType","cloudReq");
            param3.put("hasMore",0);
            param3.put("mid",mid);
            Map<String, Object> param5 = new HashMap<>();
            param5.put("deviceId",Integer.parseInt(deviceSbId));
            param5.put("deviceType",Integer.parseInt(deviceType));
            param5.put("isRoot",Integer.parseInt(isRoot));
            param5.put("captureMode",Integer.parseInt(captureMode));
            param5.put("zoneInTatol",Integer.parseInt(zoneInTatol));
            param5.put("zoneInNum",Integer.parseInt(zoneInNum));
            param5.put("capturePeriod",Integer.parseInt(capturePeriod));
            param5.put("reportPeriod",Integer.parseInt(reportPeriod));
            ObjectNode paras = JsonUtil.convertObject2ObjectNode(param5);
            param3.put("paras",param5);
            ObjectNode command = JsonUtil.convertObject2ObjectNode(param3);
            param2.put("command",command);
            param2.put("callbackUrl",CfFinal.URL_PORT+"/SKKJ/DXjktjController/samplingEnable");
            param2.put("expireTime",Integer.parseInt(String.valueOf(1000*60*60)));
            String param6 = JsonUtil.jsonObj2Sting(param2);
            HttpResponse responsePostAsynCmd = httpsUtil.doPostJson(urlPostAsynCmd1, param, param6);
            String responseBody = httpsUtil.getHttpResponseBody(responsePostAsynCmd);
            StatusLine statusLine = responsePostAsynCmd.getStatusLine();
            if("HTTP/1.1 201 Created".equals(statusLine.toString()) || "HTTP/1.1 201 Created" == statusLine.toString()){
                String date = TimeUtile.dateTimeFomar();
                String userName = jsonObject.getString("userName");
                JSONObject jsonCom = JSONObject.parseObject(responseBody);
                String commandId = (String) jsonCom.get("commandId");
                CommandInformation commad = new CommandInformation();
                commad.setDeviceNumber(deviceNumber);
                commad.setName("设置定时采集使能");
                commad.setCommandId(commandId);
                commad.setType("3");
                commad.setTime(date);
                commad.setUserName(userName);
                commad.setMethod("COMMAND_IOT_REPORT_ENABLE");
                commandInformationMapper.insertCommand(commad);
                return "true";
            }else {
                redisUtils.setString("samplingEnable"+deviceNumber,"5");
                return "false";
            }
        }else {
            return "no";
        }
    }

    /**
     * @Author ZhouNan
     * @Description 立即采样
     * @params
     * @Date 2018/1/12 0012  13:27
     */
    @Override
    public String appImmediateSampling(JSONObject jsonObject) throws Exception {
        RedisUtils redisUtils = new RedisUtils();
        String deviceNumber = jsonObject.getString("deviceNumber");
        String message = ConcurrentFenbu.getConcurrent().immediateSampling(deviceNumber,commandInformationMapper);
        if(message != "false" && !"false".equals(message)){
            String deviceType = jsonObject.getString("DeviceType");
            String isRoot = jsonObject.getString("isRoot");
//            String deviceNumber = request.getParameter("deviceNumber");
            String deviceSbId = jsonObject.getString("deviceSbId");
            int mid = TimeUtile.random();
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();
            String accessToken = ConcurrentFenbu.getConcurrent().accessTokenPt(httpsUtil,"2");
            List<Integer> sensorSequence = new ArrayList<Integer>();

            //Please make sure that the following parameter values have been modified in the Constant file.
            String urlPostAsynCmd = Constant.POST_ASYN_CMD;
            String appId = Constant.APPID;

            //please replace the deviceId, when you use the demo.
            String deviceId = deviceNumber;
//        String deviceId = "8c23b6b4-ea68-48fb-9c2f-90452a81ebb1";
            String urlPostAsynCmd1 = Constant.POST_ASYN_CMD;
            Map<String, String> param = new HashMap<>();
            param.put("app_key", appId);
            param.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
            Map<String, Object> param2 = new HashMap<>();
            param2.put("deviceId",deviceId);
            Map<String, Object> param3 = new HashMap<>();
            param3.put("serviceId","ServieCommand");
            param3.put("method","COMMAND_IOT_IMMEDIATELY_REPORT");
            param3.put("identifier",deviceSbId);
            param3.put("deviceType",Integer.parseInt(deviceType));
            param3.put("msgType","cloudReq");
            param3.put("hasMore",0);
            Map<String, Object> param5 = new HashMap<>();
            param5.put("deviceId",Integer.parseInt(deviceSbId));
            param5.put("deviceType",Integer.parseInt(deviceType));
            param5.put("isRoot",Integer.parseInt(isRoot));
            ObjectNode paras = JsonUtil.convertObject2ObjectNode(param5);
            param3.put("paras",param5);
            ObjectNode command = JsonUtil.convertObject2ObjectNode(param3);
            param2.put("command",command);
            param2.put("callbackUrl",CfFinal.URL_PORT+"/SKKJ/DXjktjController/immediateSampling");
            param2.put("expireTime",Integer.parseInt(String.valueOf(1000*60*60)));
            String param6 = JsonUtil.jsonObj2Sting(param2);
            HttpResponse responsePostAsynCmd = httpsUtil.doPostJson(urlPostAsynCmd1, param, param6);
            String responseBody = httpsUtil.getHttpResponseBody(responsePostAsynCmd);
            StatusLine statusLine = responsePostAsynCmd.getStatusLine();
            if("HTTP/1.1 201 Created".equals(statusLine.toString()) || "HTTP/1.1 201 Created" == statusLine.toString()){
                String date = TimeUtile.dateTimeFomar();
                String userName = jsonObject.getString("userName");
                JSONObject jsonCom = JSONObject.parseObject(responseBody);
                String commandId = (String) jsonCom.get("commandId");
                CommandInformation commad = new CommandInformation();
                commad.setDeviceNumber(deviceNumber);
                commad.setCommandId(commandId);
                commad.setName("立即采集命令");
                commad.setType("3");
                commad.setTime(date);
                commad.setUserName(userName);
                commad.setMethod("COMMAND_IOT_IMMEDIATELY_REPORT");
                commandInformationMapper.insertCommand(commad);
                return "true";
            }else {
                redisUtils.setString("immediateSampling"+deviceNumber,"5");
                return "false";
            }
        }else {
            return "no";
        }
    }

    //设置
    @Override
    public String appCalibration(JSONObject jsonObject) throws Exception {
        RedisUtils redisUtils = new RedisUtils();
        String deviceNumber = jsonObject.getString("deviceNumber");
        String message = ConcurrentFenbu.getConcurrent().calibration(deviceNumber,commandInformationMapper);
        if(message != "false" && !"false".equals(message)){
            String deviceType = jsonObject.getString("DeviceType");
            String isRoot = jsonObject.getString("isRoot");
            JSONObject data = jsonObject.getJSONObject("data");
            String switchFlags = data.getString("switchFlag");
//            String deviceNumber = request.getParameter("deviceNumber");
            String deviceSbId = jsonObject.getString("deviceSbId");
            String adjustValues = data.getString("adjustValues");
            String adjustTimes = data.getString("adjustTimes");
            int mid = TimeUtile.random();
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();
            String accessToken = ConcurrentFenbu.getConcurrent().accessTokenPt(httpsUtil,"2");
            List<Integer> sensorSequence = new ArrayList<Integer>();
            StringBuilder budei = new StringBuilder("00000000");
            String switchFlag[] = switchFlags.split(",");
            if(switchFlag != null && switchFlag.length > 0){
                for (int j = 23; j >= 0; j--) {
                    int h = 0;
                    for (int i = 0; i < switchFlag.length; i++) {
                        int cgq = Integer.parseInt(switchFlag[i]);
                        if(j == cgq){
                            budei.append("1");
                            h += 1;
                            break;
                        }
                        if(switchFlag.length -1 == i && h == 0){
                            budei.append("0");
                        }
                    }
                }

            }
            String appId = Constant.APPID;
            String deviceId = deviceNumber;
            String urlPostAsynCmd1 = Constant.POST_ASYN_CMD;
            Map<String, String> param = new HashMap<>();
            param.put("app_key", appId);
            param.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
            Map<String, Object> param2 = new HashMap<>();
            param2.put("deviceId",deviceId);
            Map<String, Object> param3 = new HashMap<>();
            param3.put("serviceId","ServieCommand");
            param3.put("method","CMD_SERSONR_ADJUST");
            param3.put("identifier",deviceSbId);
            param3.put("deviceType",Integer.parseInt(deviceType));
            param3.put("msgType","cloudReq");
            param3.put("hasMore",0);
            Map<String, Object> param5 = new HashMap<>();
            param5.put("deviceId",Integer.parseInt(deviceSbId));
            param5.put("deviceType",Integer.parseInt(deviceType));
            param5.put("isRoot",Integer.parseInt(isRoot));
            param5.put("adjustValues",Float.parseFloat(adjustValues));
            param5.put("switchFlag",Integer.parseInt(budei.toString(),2));
            param5.put("adjustTimes",Integer.parseInt(adjustTimes));
            ObjectNode paras = JsonUtil.convertObject2ObjectNode(param5);
            param3.put("paras",param5);
            ObjectNode command = JsonUtil.convertObject2ObjectNode(param3);
            param2.put("command",command);
            param2.put("callbackUrl",CfFinal.URL_PORT+"/SKKJ/DXjktjController/calibration");
            param2.put("expireTime",Integer.parseInt(String.valueOf(1000*60*60)));
            String param6 = JsonUtil.jsonObj2Sting(param2);
            System.out.println("param6："+param6);
            HttpResponse responsePostAsynCmd = httpsUtil.doPostJson(urlPostAsynCmd1, param, param6);
            String responseBody = httpsUtil.getHttpResponseBody(responsePostAsynCmd);
            StatusLine statusLine = responsePostAsynCmd.getStatusLine();
            if("HTTP/1.1 201 Created".equals(statusLine.toString()) || "HTTP/1.1 201 Created" == statusLine.toString()){
                String date = TimeUtile.dateTimeFomar();
                String userName = jsonObject.getString("userName");
                JSONObject jsonCom = JSONObject.parseObject(responseBody);
                String commandId = (String) jsonCom.get("commandId");
                CommandInformation commad = new CommandInformation();
                commad.setDeviceNumber(deviceNumber);
                commad.setCommandId(commandId);
                commad.setName("效验设置");
                commad.setType("3");
                commad.setTime(date);
                commad.setUserName(userName);
                commad.setMethod("CMD_SERSONR_ADJUST");
                commandInformationMapper.insertCommand(commad);
                return "true";
            }else {
                redisUtils.setString("calibration"+deviceNumber,"5");
                return "false";
            }
        }else {
            return "no";
        }
    }

    @Override
    public String algorithm(HttpServletRequest request) throws Exception {
        String deviceNumber = request.getParameter("deviceNumber");
        RedisUtils redsi = new RedisUtils();
        String message = ConcurrentFenbu.getConcurrent().algorithm(deviceNumber,commandInformationMapper);
        if(message != "false" && !"false".equals(message)){
            //A入
            String ain = request.getParameter("inA");
            String inAcs = request.getParameter("inAcs");
            //B入
            String bin = request.getParameter("inB");
            String inBcs = request.getParameter("inBcs");
            //C入
            String cin = request.getParameter("inC");
            String inCcs = request.getParameter("inCcs");
            //A出
            String aout = request.getParameter("outA");
            String outAcs = request.getParameter("outAcs");
            //B出
            String bout = request.getParameter("outB");
            String outBcs = request.getParameter("outBcs");
            //C出
            String cout = request.getParameter("outC");
            String outCcs = request.getParameter("outCcs");
//            //A线
//            String acin = request.getParameter("ACin");
//            //B线
//            String bcin = request.getParameter("BCin");
//            //C线
//            String ccin = request.getParameter("CCin");
//            //A线出
//            String acout = request.getParameter("ACout");
//            //B线出
//            String bcout = request.getParameter("BCout");
//            //C线出
//            String ccout = request.getParameter("CCout");
            String deviceSbId = request.getParameter("deviceSbId");
            String deviceType = request.getParameter("deviceType");
            String isRoot = request.getParameter("isRoot");
            int mid = TimeUtile.random();
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();
            String accessToken = ConcurrentFenbu.getConcurrent().accessTokenPt(httpsUtil,"2");
            String appId = Constant.APPID;
            String deviceId = deviceNumber;
            String urlPostAsynCmd1 = Constant.POST_ASYN_CMD;
            Map<String, String> param = new HashMap<>();
            param.put("app_key", appId);
            param.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
            Map<String, Object> param2 = new HashMap<>();
            param2.put("deviceId",deviceId);
            Map<String, Object> param3 = new HashMap<>();
            param3.put("serviceId","ServieCommand");
            param3.put("method","COMMAND_SET_PHASE_TEMP");
            param3.put("identifier",deviceSbId);
//            param3.put("deviceType",Integer.parseInt(deviceType));
            param3.put("msgType","cloudReq");
            param3.put("hasMore",0);
            Map<String, Object> param5 = new HashMap<>();
            param5.put("deviceId",Integer.parseInt(deviceSbId));
            param5.put("isRoot",isRoot);
            param5.put("deviceType",Integer.parseInt(deviceType));
            param5.put("Ain",Float.parseFloat(ain));
            param5.put("Bin",Float.parseFloat(bin));
            param5.put("Cin",Float.parseFloat(cin));
            param5.put("Aout",Float.parseFloat(aout));
            param5.put("Bout",Float.parseFloat(bout));
            param5.put("Cout",Float.parseFloat(cout));
            param5.put("ACin",0);
            param5.put("BCin",0);
            param5.put("CCin",0);
            param5.put("ACout",0);
            param5.put("BCout",0);
            param5.put("CCout",0);
            param5.put("Ain_fixed",Float.parseFloat(inAcs));
            param5.put("Bin_fixed",Float.parseFloat(inBcs));
            param5.put("Cin_fixed",Float.parseFloat(inCcs));
            param5.put("Aout_fixed",Float.parseFloat(outAcs));
            param5.put("Bout_fixed",Float.parseFloat(outBcs));
            param5.put("Cout_fixed",Float.parseFloat(outCcs));
            param5.put("ACin_fixed",0);
            param5.put("BCin_fixed",0);
            param5.put("CCin_fixed",0);
            param5.put("ACout_fixed",0);
            param5.put("BCout_fixed",0);
            param5.put("CCout_fixed",0);
            ObjectNode paras = JsonUtil.convertObject2ObjectNode(param5);
            param3.put("paras",param5);
            ObjectNode command = JsonUtil.convertObject2ObjectNode(param3);
            param2.put("command",command);
            param2.put("callbackUrl",CfFinal.URL_PORT+"/SKKJ/DXjktjController/algorithm");
            param2.put("expireTime",Integer.parseInt(String.valueOf(1000*60*60)));
            String param6 = JsonUtil.jsonObj2Sting(param2);
            System.out.println("param6："+param6);
            HttpResponse responsePostAsynCmd = httpsUtil.doPostJson(urlPostAsynCmd1, param, param6);
            String responseBody = httpsUtil.getHttpResponseBody(responsePostAsynCmd);
            StatusLine statusLine = responsePostAsynCmd.getStatusLine();
            if("HTTP/1.1 201 Created".equals(statusLine.toString()) || "HTTP/1.1 201 Created" == statusLine.toString()){
                String date = TimeUtile.dateTimeFomar();
                User user = (User) request.getSession().getAttribute("user");
                String userName = user.getUserName();
                CalculationWd calcu = new CalculationWd();
                JSONObject jsonCom = JSONObject.parseObject(responseBody);
                String commandId = (String) jsonCom.get("commandId");
                CommandInformation commad = new CommandInformation();
                commad.setDeviceNumber(deviceNumber);
                commad.setCommandId(commandId);
                commad.setName("相位设置");
                commad.setType("3");
                commad.setTime(date);
                commad.setUserName(userName);
                commad.setMethod("COMMAND_SET_PHASE_TEMP");
                commandInformationMapper.insertCommand(commad);
                calcu.setInA(ain);
                calcu.setInAcs(inAcs);
                calcu.setInB(bin);
                calcu.setInBcs(inBcs);
                calcu.setInC(cin);
                calcu.setInCcs(inCcs);
                calcu.setOutA(aout);
                calcu.setOutAcs(outAcs);
                calcu.setOutB(bout);
                calcu.setOutBcs(outBcs);
                calcu.setOutC(cout);
                calcu.setOutCcs(outCcs);
                calcu.setDeviceNumber(deviceId);
                calculationWdMapper.update(calcu);
                return "true";
            }else {
                redsi.setString("algorithm"+deviceNumber,"5");
                return "false";
            }
        }else {
            return "no";
        }
    }

    @Override
    public String appAlgorithm(JSONObject json) throws Exception {
        String deviceNumber = json.getString("deviceNumber");
        RedisUtils redsi = new RedisUtils();
        String message = ConcurrentFenbu.getConcurrent().algorithm(deviceNumber,commandInformationMapper);
        if(message != "false" && !"false".equals(message)){
            //A入
            String ain = json.getString("Ain");
            String inAcs = json.getString("inAcs");
            //B入
            String bin = json.getString("Bin");
            String inBcs = json.getString("inBcs");
            //C入
            String cin = json.getString("Cin");
            String inCcs = json.getString("inCcs");
            //A出
            String aout = json.getString("Aout");
            String outAcs = json.getString("outAcs");
            //B出
            String bout = json.getString("Bout");
            String outBcs = json.getString("outBcs");
            //C出
            String cout = json.getString("Cout");
            String outCcs = json.getString("outCcs");
//            //A线
//            String acin = request.getParameter("ACin");
//            //B线
//            String bcin = request.getParameter("BCin");
//            //C线
//            String ccin = request.getParameter("CCin");
//            //A线出
//            String acout = request.getParameter("ACout");
//            //B线出
//            String bcout = request.getParameter("BCout");
//            //C线出
//            String ccout = request.getParameter("CCout");
            String deviceSbId = json.getString("deviceSbId");
            String deviceType = json.getString("DeviceType");
            String isRoot = json.getString("isRoot");
            int mid = TimeUtile.random();
            HttpsUtil httpsUtil = new HttpsUtil();
            httpsUtil.initSSLConfigForTwoWay();
            String accessToken = ConcurrentFenbu.getConcurrent().accessTokenPt(httpsUtil,"2");
            String appId = Constant.APPID;
            String deviceId = deviceNumber;
            String urlPostAsynCmd1 = Constant.POST_ASYN_CMD;
            Map<String, String> param = new HashMap<>();
            param.put("app_key", appId);
            param.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
            Map<String, Object> param2 = new HashMap<>();
            param2.put("deviceId",deviceId);
            Map<String, Object> param3 = new HashMap<>();
            param3.put("serviceId","ServieCommand");
            param3.put("method","COMMAND_SET_PHASE_TEMP");
            param3.put("identifier",deviceSbId);
//            param3.put("deviceType",Integer.parseInt(deviceType));
            param3.put("msgType","cloudReq");
            param3.put("hasMore",0);
            DecimalFormat decimalFormat=new DecimalFormat("0.00");
            Map<String, Object> param5 = new HashMap<>();
            param5.put("deviceId",Integer.parseInt(deviceSbId));
            param5.put("isRoot",isRoot);
            param5.put("deviceType",Integer.parseInt(deviceType));
            param5.put("Ain",decimalFormat.format(Float.parseFloat(ain)));
            param5.put("Bin",Float.parseFloat(bin));
            param5.put("Cin",Float.parseFloat(cin));
            param5.put("Aout",Float.parseFloat(aout));
            param5.put("Bout",Float.parseFloat(bout));
            param5.put("Cout",Float.parseFloat(cout));
            param5.put("ACin",0);
            param5.put("BCin",0);
            param5.put("CCin",0);
            param5.put("ACout",0);
            param5.put("BCout",0);
            param5.put("CCout",0);
            param5.put("Ain_fixed",Float.parseFloat(inAcs));
            param5.put("Bin_fixed",Float.parseFloat(inBcs));
            param5.put("Cin_fixed",Float.parseFloat(inCcs));
            param5.put("Aout_fixed",Float.parseFloat(outAcs));
            param5.put("Bout_fixed",Float.parseFloat(outBcs));
            param5.put("Cout_fixed",Float.parseFloat(outCcs));
            param5.put("ACin_fixed",0);
            param5.put("BCin_fixed",0);
            param5.put("CCin_fixed",0);
            param5.put("ACout_fixed",0);
            param5.put("BCout_fixed",0);
            param5.put("CCout_fixed",0);
            ObjectNode paras = JsonUtil.convertObject2ObjectNode(param5);
            param3.put("paras",param5);
            ObjectNode command = JsonUtil.convertObject2ObjectNode(param3);
            param2.put("command",command);
            param2.put("callbackUrl",CfFinal.URL_PORT+"/SKKJ/DXjktjController/algorithm");
            param2.put("expireTime",Integer.parseInt(String.valueOf(1000*60*60)));
            String param6 = JsonUtil.jsonObj2Sting(param2);
            System.out.println("param6："+param6);
            HttpResponse responsePostAsynCmd = httpsUtil.doPostJson(urlPostAsynCmd1, param, param6);
            String responseBody = httpsUtil.getHttpResponseBody(responsePostAsynCmd);
            StatusLine statusLine = responsePostAsynCmd.getStatusLine();
            if("HTTP/1.1 201 Created".equals(statusLine.toString()) || "HTTP/1.1 201 Created" == statusLine.toString()){
                String date = TimeUtile.dateTimeFomar();
                String userName = json.getString("userName");
                CalculationWd calcul = new CalculationWd();
                calcul.setOutAcs(outAcs);
                calcul.setOutBcs(outBcs);
                calcul.setOutCcs(outCcs);
                calcul.setInA(ain);
                calcul.setInB(bin);
                calcul.setInB(cin);
                calcul.setInAcs(inAcs);
                calcul.setInBcs(inBcs);
                calcul.setInCcs(inCcs);
                calcul.setOutA(aout);
                calcul.setOutB(bout);
                calcul.setOutC(cout);
                calcul.setDeviceNumber(deviceNumber);
                calculationWdMapper.update(calcul);
                JSONObject jsonCom = JSONObject.parseObject(responseBody);
                String commandId = (String) jsonCom.get("commandId");
                CommandInformation commad = new CommandInformation();
                commad.setDeviceNumber(deviceNumber);
                commad.setCommandId(commandId);
                commad.setName("相位设置");
                commad.setType("3");
                commad.setTime(date);
                commad.setUserName(userName);
                commad.setMethod("COMMAND_SET_PHASE_TEMP");
                commandInformationMapper.insertCommand(commad);
                return "true";
            }else {
                redsi.setString("algorithm"+deviceNumber,"5");
                return "false";
            }
        }else {
            return "no";
        }
    }

    public static void main(String[] args) {
//        RedisUtils.delObject("selectBoarcfc5ca5f-c806-45ba-a90f-36f59d01bd9b");
        System.out.println("00000000000011000011000001000001".length());

    }
}
