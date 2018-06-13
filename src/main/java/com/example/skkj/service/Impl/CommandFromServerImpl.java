package com.example.skkj.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.skkj.comment.RedisUtils;
import com.example.skkj.entity.Adjust;
import com.example.skkj.entity.CommandInformation;
import com.example.skkj.entity.Level;
import com.example.skkj.entity.Sensor;
import com.example.skkj.mapper.CommandInformationMapper;
import com.example.skkj.mapper.SensorMapper;
import com.example.skkj.service.AdjustServer;
import com.example.skkj.service.CommandFromServer;

import java.util.ArrayList;
import java.util.List;

@Service("commandFromServer")
public class CommandFromServerImpl implements CommandFromServer {
    @Autowired
    private CommandInformationMapper commandInformationMapper;

    @Autowired
    private SensorMapper sensorMapper;

    @Autowired
    private AdjustServer adjustServer;

    //使能采样通知
    @Override
    public synchronized void samplingEnable(JSONObject param) throws Exception {
        JSONObject result = param.getJSONObject("result");
        String resultCode = (String) result.get("resultCode");
        JSONObject resultDetail = result.getJSONObject("resultDetail");
        RedisUtils redisUtils = new RedisUtils();
            if(resultCode == "DELIVERED" || "DELIVERED".equals(resultCode)){
                String commandId = (String) param.get("commandId");
                String deviceId = (String) param.get("deviceId");
                CommandInformation comman = new CommandInformation();
                comman.setCommandId(commandId);
                comman.setDeviceNumber(deviceId);
                comman.setType("2");
                commandInformationMapper.updateByDeviceNumber(comman);
                redisUtils.setString("samplingEnable"+deviceId,"5");
            }else if (resultCode == "TIMEOUT" || "TIMEOUT".equals(resultCode)){
                String commandId = (String) param.get("commandId");
                String deviceId = (String) param.get("deviceId");
                CommandInformation comman = new CommandInformation();
                comman.setCommandId(commandId);
                comman.setDeviceNumber(deviceId);
                comman.setType("4");
                commandInformationMapper.updateByDeviceNumber(comman);
                redisUtils.setString("samplingEnable"+deviceId,"4");
            }else if(resultCode == "SUCCESSFUL" || "SUCCESSFUL".equals(resultCode)){
                String commandId = (String) param.get("commandId");
                String deviceId = (String) param.get("deviceId");
                CommandInformation comman = new CommandInformation();
                comman.setCommandId(commandId);
                comman.setDeviceNumber(deviceId);
                comman.setType("1");
                commandInformationMapper.updateByDeviceNumber(comman);
                redisUtils.setString("samplingEnable"+deviceId,"1");
            }else if(resultCode == "FAILED" || "FAILED".equals(resultCode)){
                String commandId = (String) param.get("commandId");
                String deviceId = (String) param.get("deviceId");
                CommandInformation comman = new CommandInformation();
                comman.setCommandId(commandId);
                comman.setDeviceNumber(deviceId);
                comman.setType("5");
                commandInformationMapper.updateByDeviceNumber(comman);
                redisUtils.setString("samplingEnable"+deviceId,"5");
            }
    }

    //板级参数设置
    @Override
    public synchronized void boardLevel(JSONObject param) throws Exception {
        JSONObject result = param.getJSONObject("result");
        String resultCode = (String) result.get("resultCode");
        JSONObject resultDetail = result.getJSONObject("resultDetail");
        RedisUtils redisUtils = new RedisUtils();
            if (resultCode == "DELIVERED" || "DELIVERED".equals(resultCode)) {
                String commandId = (String) param.get("commandId");
                String deviceId = (String) param.get("deviceId");
                CommandInformation comman = new CommandInformation();
                comman.setCommandId(commandId);
                comman.setDeviceNumber(deviceId);
                comman.setType("2");
                commandInformationMapper.updateByDeviceNumber(comman);
                redisUtils.setString("boardLevel" + deviceId, "5");
            } else if (resultCode == "TIMEOUT" || "TIMEOUT".equals(resultCode)) {
                String commandId = (String) param.get("commandId");
                String deviceId = (String) param.get("deviceId");
                CommandInformation comman = new CommandInformation();
                comman.setCommandId(commandId);
                comman.setDeviceNumber(deviceId);
                comman.setType("4");
                commandInformationMapper.updateByDeviceNumber(comman);
                redisUtils.setString("boardLevel" + deviceId, "4");
            } else if (resultCode == "SUCCESSFUL" || "SUCCESSFUL".equals(resultCode)) {
                String commandId = (String) param.get("commandId");
                String deviceId = (String) param.get("deviceId");
                CommandInformation comman = new CommandInformation();
                comman.setCommandId(commandId);
                comman.setDeviceNumber(deviceId);
                comman.setType("1");
                commandInformationMapper.updateByDeviceNumber(comman);
                redisUtils.setString("boardLevel" + deviceId, "1");
            } else if (resultCode == "FAILED" || "FAILED".equals(resultCode)) {
                String commandId = (String) param.get("commandId");
                String deviceId = (String) param.get("deviceId");
                CommandInformation comman = new CommandInformation();
                comman.setCommandId(commandId);
                comman.setDeviceNumber(deviceId);
                comman.setType("5");
                commandInformationMapper.updateByDeviceNumber(comman);
                redisUtils.setString("boardLevel" + deviceId, "5");
            }
    }
    //查看板级参数设置
    @Override
    public  synchronized void selectBoar(JSONObject param) throws Exception {
        System.out.println("查询参数或的json:"+param);
        JSONObject result = param.getJSONObject("result");
        String resultCode = (String) result.get("resultCode");
        JSONObject resultDetail = result.getJSONObject("resultDetail");
        RedisUtils redisUtils = new RedisUtils();
            if (resultCode == "DELIVERED" || "DELIVERED".equals(resultCode)) {
                String commandId = (String) param.get("commandId");
                String deviceId = (String) param.get("deviceId");
                CommandInformation comman = new CommandInformation();
                comman.setCommandId(commandId);
                comman.setDeviceNumber(deviceId);
                comman.setType("2");
                commandInformationMapper.updateByDeviceNumber(comman);
                redisUtils.setString("selectBoar" + deviceId, "5");
            } else if (resultCode == "TIMEOUT" || "TIMEOUT".equals(resultCode)) {
                String commandId = (String) param.get("commandId");
                String deviceId = (String) param.get("deviceId");
                CommandInformation comman = new CommandInformation();
                comman.setCommandId(commandId);
                comman.setDeviceNumber(deviceId);
                comman.setType("4");
                commandInformationMapper.updateByDeviceNumber(comman);
                redisUtils.setString("selectBoar" + deviceId, "4");
            } else if (resultCode == "SUCCESSFUL" || "SUCCESSFUL".equals(resultCode)) {
                String commandId = (String) param.get("commandId");
                String deviceId = (String) param.get("deviceId");
                CommandInformation comman = new CommandInformation();
                comman.setCommandId(commandId);
                comman.setDeviceNumber(deviceId);
                comman.setType("1");
                commandInformationMapper.updateByDeviceNumber(comman);
                String capturePeriod = "";
                String zoneInTatol = "";
                String zoneInNum = "";
                Object capturePeriods = resultDetail.get("capturePeriod");
                Object zoneInNums = resultDetail.get("zoneInNum");
                Object zoneInTatols = resultDetail.get("zoneInTatol");
                if(capturePeriods != null && !"".equals(capturePeriods)){
                    capturePeriod= String.valueOf(capturePeriods);
                }
                if(zoneInNums != null && !"".equals(zoneInNums)){
                    zoneInNum= String.valueOf(zoneInNums);
                }
                if(zoneInTatols != null && !"".equals(zoneInTatols)){
                    zoneInTatol= String.valueOf(zoneInTatols);
                }
                String deviceType = String.valueOf(resultDetail.get("deviceType"));
                String deviceIds = String.valueOf(resultDetail.get("deviceId"));
                String isRoot = (String) resultDetail.get("isRoot");
                String reportPeriod = String.valueOf(resultDetail.get("reportPeriod"));
                String captureMode = (String) resultDetail.get("captureMode");
                String sensorAddr = String.valueOf(resultDetail.get("sensorAddr"));
                String underTemp = String.valueOf(resultDetail.get("underTemp"));
                String overTemp = String.valueOf(resultDetail.get("overTemp"));
                String underSignal = String.valueOf(resultDetail.get("underSignal"));
                String overSignal = String.valueOf(resultDetail.get("overSignal"));
                String sensorNum = String.valueOf(resultDetail.get("sensorNum"));
                String sequence = (String) resultDetail.get("sequence");
                String NCCID = (String) resultDetail.get("NCCID");
                String southIP = (String) resultDetail.get("southIP");
                String southPort = (String) resultDetail.get("southPort");
                String isAutoMode = String.valueOf(resultDetail.get("isAutoMode"));
                String d[] = sequence.split(",");
                List<String> list = new ArrayList<String>();
                List<String> sensorName = new ArrayList<>();
                for (int i = 0; i < d.length; i++) {
                    list.add(d[i]);
                }
                List<Sensor> sensor = sensorMapper.selectBySensorNum(list);
                for (int j = 0; j < d.length; j++) {
                    String numb = d[j];
                    for (int i = 0; i < sensor.size(); i++) {
                        Sensor sencer = sensor.get(i);
                        String sencerNumber = sencer.getSensorNum();
                        if (numb == sencerNumber || sencerNumber.equals(numb)) {
                            sensorName.add(sencer.getSensorName());
                            break;
                        }
                    }
                }
                Level level = new Level();
                if(capturePeriod != null && !"".equals(capturePeriod)){
                    level.setCapturePeriod(capturePeriod);
                }
                if(zoneInNum != null && !"".equals(zoneInNum)){
                  level.setZoneInNum(zoneInNum);
                }
                if(zoneInTatol != null && !"".equals(zoneInTatol)){
                    level.setZoneInTatol(zoneInTatol);
                }
                level.setCaptureMode(captureMode);
                level.setDeviceId(deviceIds);
                level.setDeviceType(deviceType);
                level.setIsRoot(isRoot);
                level.setOverSignal(overSignal);
                level.setOverTemp(overTemp);
                level.setReportPeriod(reportPeriod);
                level.setSensorAddr(sensorAddr);
                level.setSensorName(sensorName);
                level.setSensorNum(sensorNum);
                level.setSequence(sequence);
                level.setUnderSignal(underSignal);
                level.setUnderTemp(underTemp);
                level.setIsAutoMode(isAutoMode);
                level.setSouthIP(southIP.trim());
                level.setSouthPort(southPort.trim());
                level.setNccId(NCCID);
                redisUtils.setObject("selectBoarObj" + deviceId, level);
                redisUtils.setString("selectBoar" + deviceId, "1");
            } else if (resultCode == "FAILED" || "FAILED".equals(resultCode)) {
                String commandId = (String) param.get("commandId");
                String deviceId = (String) param.get("deviceId");
                CommandInformation comman = new CommandInformation();
                comman.setCommandId(commandId);
                comman.setDeviceNumber(deviceId);
                comman.setType("5");
                commandInformationMapper.updateByDeviceNumber(comman);
                redisUtils.setString("selectBoar" + deviceId, "5");
            }
    }

    //立即采样
    @Override
    public  synchronized void immediateSampling(JSONObject param) throws Exception {
        JSONObject result = param.getJSONObject("result");
        String resultCode = (String) result.get("resultCode");
        RedisUtils redisUtils = new RedisUtils();
            if (resultCode == "DELIVERED" || "DELIVERED".equals(resultCode)) {
                String commandId = (String) param.get("commandId");
                String deviceId = (String) param.get("deviceId");
                CommandInformation comman = new CommandInformation();
                comman.setCommandId(commandId);
                comman.setDeviceNumber(deviceId);
                comman.setType("2");
                commandInformationMapper.updateByDeviceNumber(comman);
                redisUtils.setString("immediateSampling" + deviceId, "5");
            } else if (resultCode == "TIMEOUT" || "TIMEOUT".equals(resultCode)) {
                String commandId = (String) param.get("commandId");
                String deviceId = (String) param.get("deviceId");
                CommandInformation comman = new CommandInformation();
                comman.setCommandId(commandId);
                comman.setDeviceNumber(deviceId);
                comman.setType("4");
                commandInformationMapper.updateByDeviceNumber(comman);
                redisUtils.setString("immediateSampling" + deviceId, "4");
            } else if (resultCode == "SUCCESSFUL" || "SUCCESSFUL".equals(resultCode)) {
                String commandId = (String) param.get("commandId");
                String deviceId = (String) param.get("deviceId");
                CommandInformation comman = new CommandInformation();
                comman.setCommandId(commandId);
                comman.setDeviceNumber(deviceId);
                comman.setType("1");
                commandInformationMapper.updateByDeviceNumber(comman);
                redisUtils.setString("immediateSampling" + deviceId, "1");
            } else if (resultCode == "FAILED" || "FAILED".equals(resultCode)) {
                String commandId = (String) param.get("commandId");
                String deviceId = (String) param.get("deviceId");
                CommandInformation comman = new CommandInformation();
                comman.setCommandId(commandId);
                comman.setDeviceNumber(deviceId);
                comman.setType("5");
                commandInformationMapper.updateByDeviceNumber(comman);
                redisUtils.setString("immediateSampling" + deviceId, "5");
            }
    }

    //效验设置
    @Override
    public void calibration(JSONObject param) throws Exception {
        JSONObject result = param.getJSONObject("result");
        String resultCode = (String) result.get("resultCode");
        JSONObject resultDetail = result.getJSONObject("resultDetail");
        RedisUtils redisUtils = new RedisUtils();
        synchronized (Object.class) {
            if (resultCode == "DELIVERED" || "DELIVERED".equals(resultCode)) {
                String commandId = (String) param.get("commandId");
                String deviceId = (String) param.get("deviceId");
                CommandInformation comman = new CommandInformation();
                comman.setCommandId(commandId);
                comman.setDeviceNumber(deviceId);
                comman.setType("2");
                commandInformationMapper.updateByDeviceNumber(comman);
                redisUtils.setString("calibration" + deviceId, "5");
            } else if (resultCode == "TIMEOUT" || "TIMEOUT".equals(resultCode)) {
                String commandId = (String) param.get("commandId");
                String deviceId = (String) param.get("deviceId");
                CommandInformation comman = new CommandInformation();
                comman.setCommandId(commandId);
                comman.setDeviceNumber(deviceId);
                comman.setType("4");
                commandInformationMapper.updateByDeviceNumber(comman);
                redisUtils.setString("calibration" + deviceId, "4");
            } else if (resultCode == "SUCCESSFUL" || "SUCCESSFUL".equals(resultCode)) {
                String commandId = (String) param.get("commandId");
                String deviceId = (String) param.get("deviceId");
                CommandInformation comman = new CommandInformation();
                comman.setCommandId(commandId);
                comman.setDeviceNumber(deviceId);
                comman.setType("1");
//                commandInformationMapper.updateByDeviceNumber(comman);
                String data = resultDetail.getString("data");
                if(data != null && !"".equals(data)){
                    List<Adjust> adjList = JSONArray.parseArray(data, Adjust.class);
                    adjustServer.isnertAll(adjList,commandId);
                }
                commandInformationMapper.updateByDeviceNumber(comman);
                redisUtils.setString("calibration" + deviceId, "1");
            } else if (resultCode == "FAILED" || "FAILED".equals(resultCode)) {
                String commandId = (String) param.get("commandId");
                String deviceId = (String) param.get("deviceId");
                CommandInformation comman = new CommandInformation();
                comman.setCommandId(commandId);
                comman.setDeviceNumber(deviceId);
                comman.setType("5");
                commandInformationMapper.updateByDeviceNumber(comman);
                redisUtils.setString("calibration" + deviceId, "5");
            }
        }
    }
    //效验设置
    @Override
    public void algorithm(JSONObject param) throws Exception {
        JSONObject result = param.getJSONObject("result");
        String resultCode = (String) result.get("resultCode");
        JSONObject resultDetail = result.getJSONObject("resultDetail");
        RedisUtils redisUtils = new RedisUtils();
        synchronized (Object.class) {
            if (resultCode == "DELIVERED" || "DELIVERED".equals(resultCode)) {
                String commandId = (String) param.get("commandId");
                String deviceId = (String) param.get("deviceId");
                CommandInformation comman = new CommandInformation();
                comman.setCommandId(commandId);
                comman.setDeviceNumber(deviceId);
                comman.setType("2");
                commandInformationMapper.updateByDeviceNumber(comman);
                redisUtils.setString("algorithm" + deviceId, "5");
            } else if (resultCode == "TIMEOUT" || "TIMEOUT".equals(resultCode)) {
                String commandId = (String) param.get("commandId");
                String deviceId = (String) param.get("deviceId");
                CommandInformation comman = new CommandInformation();
                comman.setCommandId(commandId);
                comman.setDeviceNumber(deviceId);
                comman.setType("4");
                commandInformationMapper.updateByDeviceNumber(comman);
                redisUtils.setString("algorithm" + deviceId, "4");
            } else if (resultCode == "SUCCESSFUL" || "SUCCESSFUL".equals(resultCode)) {
                String commandId = (String) param.get("commandId");
                String deviceId = (String) param.get("deviceId");
                CommandInformation comman = new CommandInformation();
                comman.setCommandId(commandId);
                comman.setDeviceNumber(deviceId);
                comman.setType("1");
                commandInformationMapper.updateByDeviceNumber(comman);
                redisUtils.setString("algorithm" + deviceId, "1");
            } else if (resultCode == "FAILED" || "FAILED".equals(resultCode)) {
                String commandId = (String) param.get("commandId");
                String deviceId = (String) param.get("deviceId");
                CommandInformation comman = new CommandInformation();
                comman.setCommandId(commandId);
                comman.setDeviceNumber(deviceId);
                comman.setType("5");
                commandInformationMapper.updateByDeviceNumber(comman);
                redisUtils.setString("algorithm" + deviceId, "5");
            }
        }
    }

    public static void main(String[] args) {
//        RedisUtils.setString("boardLevelcfc5ca5f-c806-45ba-a90f-36f59d01bd9b","1");
//       System.out.println(RedisUtils.getObejct("boardLeveld7501a3e-c812-47a5-8e13-79763ba0f7b6",Level.class));
        String str ="{\"result\":{\"resultDetail\":{\"result\":0,\"data\":[{\"IsFlag\":true,\"InvalidCount\":0,\"meanPower\":14,\"IsAdjust\":true,\"ID\":1,\"maxFre\":424.33096,\"maxPower\":15,\"minPower\":14,\"minFre\":424.32828,\"validCount\":6,\"meanFre\":424.32996},{\"IsFlag\":true,\"InvalidCount\":0,\"meanPower\":28,\"IsAdjust\":true,\"ID\":2,\"maxFre\":426.03503,\"maxPower\":28,\"minPower\":28,\"minFre\":426.03357,\"validCount\":6,\"meanFre\":426.03482},{\"IsFlag\":true,\"InvalidCount\":0,\"meanPower\":23,\"IsAdjust\":true,\"ID\":3,\"maxFre\":427.4553,\"maxPower\":24,\"minPower\":22,\"minFre\":427.45248,\"validCount\":6,\"meanFre\":427.45413},{\"IsFlag\":true,\"InvalidCount\":0,\"meanPower\":17,\"IsAdjust\":true,\"ID\":4,\"maxFre\":428.9708,\"maxPower\":20,\"minPower\":15,\"minFre\":428.96878,\"validCount\":6,\"meanFre\":428.9701},{\"IsFlag\":true,\"InvalidCount\":0,\"meanPower\":13,\"IsAdjust\":true,\"ID\":5,\"maxFre\":430.4897,\"maxPower\":14,\"minPower\":12,\"minFre\":430.48773,\"validCount\":6,\"meanFre\":430.48877},{\"IsFlag\":true,\"InvalidCount\":0,\"meanPower\":10,\"IsAdjust\":true,\"ID\":6,\"maxFre\":432.00055,\"maxPower\":11,\"minPower\":10,\"minFre\":431.99713,\"validCount\":6,\"meanFre\":431.9995}],\"cmd\":\"CMD_SERSONR_ADJUST\",\"status\":0},\"resultCode\":\"SUCCESSFUL\"},\"deviceId\":\"2c1ba0d9-3409-459c-808c-3b744f3dd12c\",\"commandId\":\"a674415052b54c4fba9c6106af5819de\"}";
        try {
            new CommandFromServerImpl().calibration(JSONObject.parseObject(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
