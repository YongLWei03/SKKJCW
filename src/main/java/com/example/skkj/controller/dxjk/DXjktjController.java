package com.example.skkj.controller.dxjk;

import com.alibaba.fastjson.JSONObject;
import com.example.skkj.controller.app.AcpEquipmentAppController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.skkj.Interface.Cloudplatform;
import com.example.skkj.comment.BaseController;
import com.example.skkj.service.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/DXjktjController")
public class DXjktjController extends BaseController {
    protected static Logger logger = Logger.getLogger(DXjktjController.class);
    @Autowired
    private AlarmInformationServer alarmInformationServer;

    @Autowired
    private EquipmentServer equipmentServer;

    @Autowired
    private EquipmentTypeServer equipmentTypeServer;

    @Autowired
    private SubstationServer substationServer;

    @Autowired
    private CommandFromServer commandFromServer;

    @Autowired
    private AcpEquipmentServer acpEquipmentServer;

    @Autowired
    private AlarmHistoryServer alarmHistoryServer;

    @Autowired
    private PowerOnServer powerOnServer;

    @Autowired
    private TemperatureServer temperatureServer;

    @Autowired
    private CalculationWdServer calculationWdServer;

    @Autowired
    private TemperaturesServer temperaturesServer;

    @Autowired
    private AddTableServer addTableServer;


    /**
     * @Author ZhouNan
     * @Description 设备注册接口
     * @params
     * @Date 2017/12/26 0026  13:37
     */
    @RequestMapping(value = "/deviceAdded",method = RequestMethod.POST)
    @ResponseBody
    public void deviceAdded(@RequestBody JSONObject param, HttpServletRequest request){
        String deviceId = (String) param.get("deviceId");
//        new Cloudplatform(param,equipmentServer,equipmentTypeServer,substationServer).deviceAdded();
    }

    /**
     * @Author ZhouNan
     * @Description 数据变化批量
     * @params
     * @Date 2017/12/26 0026  13:37
     */
    @RequestMapping(value ="/deviceDatasChanged",method = RequestMethod.POST)
    @ResponseBody
    public void deviceDatasChanged(@RequestBody JSONObject param){
        logger.info("数据变化信息:"+param);
        try {
            new Cloudplatform(alarmInformationServer,param,acpEquipmentServer,alarmHistoryServer,powerOnServer,temperatureServer,calculationWdServer,temperaturesServer,addTableServer).analysis();
        }catch (Exception e){
            logger.error("数据变换信息异常:"+e.getMessage(),e);
        }

    }

    /**
     * @Author ZhouNan
     * @Description  修改设备信息
     * @params
     * @Date 2017/12/27 0027  16:42
     */
    @RequestMapping(value = "/deviceInfoChanged",method = RequestMethod.POST)
    @ResponseBody
    public void deviceInfoChanged(@RequestBody JSONObject param){
//        new Cloudplatform(param, equipmentServer,equipmentTypeServer,substationServer).deviceInfoChanged();
    }

    /**
     * @Author ZhouNan
     * @Description
     * @params
     * @Date 2017/12/27 0027  16:43
     */
    @RequestMapping(value = "/deviceDeleted",method = RequestMethod.POST)
    @ResponseBody
    public void deviceDeleted(@RequestBody JSONObject param){
        String deviceId = (String) param.get("deviceId");
        try {
            equipmentServer.deletByDeviceNumber(deviceId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author ZhouNan
     * @Description  设备创建板级参数命令
     * @params
     * @Date 2018/1/9 0009  14:14
     */
    @RequestMapping(value = "/deviceCommands",method = RequestMethod.POST)
    @ResponseBody
    public void deviceCommands(@RequestBody JSONObject param){
        try {
            commandFromServer.boardLevel(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author ZhouNan
     * @Description  查看板级参数命令通知
     * @params
     * @Date 2018/1/11 0011  11:07
     */
    @RequestMapping(value ="/deviceCommandsCs",method = RequestMethod.POST)
    @ResponseBody
    public void deviceCommandsCs(@RequestBody JSONObject param){
        try {
            commandFromServer.selectBoar(param);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @Author ZhouNan
     * @Description 使能采样通知
     * @params
     * @Date 2018/1/11 0011  11:07
     */
    @RequestMapping(value ="/samplingEnable",method = RequestMethod.POST)
    @ResponseBody
    public void samplingEnable(@RequestBody JSONObject param){
        try {
            commandFromServer.samplingEnable(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author ZhouNan
     * @Description 立即采集通知
     * @params
     * @Date 2018/1/11 0011  11:07
     */
    @RequestMapping(value ="/immediateSampling",method = RequestMethod.POST)
    @ResponseBody
    public void immediateSampling(@RequestBody JSONObject param){
        try {
            commandFromServer.immediateSampling(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @Author ZhouNan
     * @Description 效验设置
     * @params
     * @Date 2018/1/11 0011  11:07
     */
    @RequestMapping(value ="/calibration",method = RequestMethod.POST)
    @ResponseBody
    public void calibration(@RequestBody JSONObject param){
        try {
            commandFromServer.calibration(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author ZhouNan
     * @Description 简单设置
     * @params
     * @Date 2018/1/11 0011  11:07
     */
    @RequestMapping(value ="/messageConfirm",method = RequestMethod.POST)
    @ResponseBody
    public void messageConfirm(@RequestBody JSONObject param){

    }

    /**
     * @Author ZhouNan
     * @Description 效验设置
     * @params
     * @Date 2018/1/11 0011  11:07
     */
    @RequestMapping(value ="/deviceDataChanged",method = RequestMethod.POST)
    @ResponseBody
    public void deviceDataChanged(@RequestBody JSONObject param){

    }

    /**
     * @Author ZhouNan
     * @Description 效验设置
     * @params
     * @Date 2018/1/11 0011  11:07
     */
    @RequestMapping(value ="/commandRsp",method = RequestMethod.POST)
    @ResponseBody
    public void commandRsp(@RequestBody JSONObject param){


    }
 /**
     * @Author ZhouNan
     * @Description 相位设置
     * @params
     * @Date 2018/1/11 0011  11:07
     */
    @RequestMapping(value ="/algorithm",method = RequestMethod.POST)
    @ResponseBody
    public void algorithm(@RequestBody JSONObject param){
        System.out.println("获取的参数相位设置:"+param);
        try {
            commandFromServer.algorithm(param);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
