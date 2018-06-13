package com.example.skkj.controller.app;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.skkj.comment.BaseController;
import com.example.skkj.entity.Equipment;
import com.example.skkj.service.DeviceTypeServer;
import com.example.skkj.service.EquipmentServer;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/appEquipment")
public class EquipmentAppController extends BaseController{
	protected static Logger logger = Logger.getLogger(EquipmentAppController.class);
	
	@Autowired
	private EquipmentServer equipmentServer;

	@Autowired
	private DeviceTypeServer deviceTypeServer;
	 /**
	      * @Author ZhouNan
	      * @Description  app查看当前变电站开关柜
	      * @params userId 用户ID currentPage当前页  substationId变电站Id
	      * @Date 2018/3/23 0023  10:25
	      */
	@RequestMapping(value = "/appSelectEquipment",method = RequestMethod.POST)
	@ResponseBody
	public void appselectEquipment(HttpServletRequest request){
        String userId = request.getParameter("userId");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
            map = equipmentServer.appSelectEquipment(request);
            getJsonObject().put("currentPage",String.valueOf(map.get("currentPage")));
            getJsonObject().put("pageCount",String.valueOf(map.get("pageCount")));
            getJsonObject().put("equiCount",String.valueOf(map.get("equiCount")));
            getJsonObject().put("equipmentList",(List<Equipment>)map.get("equiListS"));
            getJsonObject().put("type",(String)map.get("type"));
            getJsonObject().put(RESULT_CODE,"1");
		} catch (Exception e) {
			logger.error("set  app查看当前变电站开关柜 error:"+e.getMessage(),e);
            getJsonObject().put(RESULT_CODE,"3");
		}
		pushJsonResult();
	}
	
	 //添加对象信息传入一个对象
	@RequestMapping(value = "/appInsetEquipment",method = RequestMethod.POST)
	@ResponseBody
	public void appInsetEquipment(@RequestBody JSONObject jsonObject){
		Equipment equipment = jsonObject.getObject("equiment",Equipment.class);
		String message = "";
		try {
			 message = equipmentServer.insetEquipment(equipment,equipmentServer);
		} catch (Exception e) {
			logger.error("set  appInsetEquipment error:"+e.getMessage(),e);
			//数据异常
			getJsonObject().put(RESULT_CODE,3);
		}
		if(message !="" && !"".equals(message)){
			if(message == "true" || "true".equals(message)){
				//添加成功
				getJsonObject().put(RESULT_CODE,1);
			}else if(message == "bdsb" || "bdsb".equals(message)){
				//设备已经绑定
				getJsonObject().put(RESULT_CODE,2);
			}else {
				//设备创建失败
				getJsonObject().put(RESULT_CODE,4);
			}
		}
		pushJsonResult();
	}
	
	//修改对象信息
	@RequestMapping(value = "/appUpdateByeqId",method = RequestMethod.POST)
	@ResponseBody
	public void appUpdateByeqId(@RequestBody JSONObject jsonObject){
		String message = "";
        Equipment equipment = jsonObject.getObject("equiment",Equipment.class);
        String deviceNames = jsonObject.getString("deviceNames");
        String deviceNumber = jsonObject.getString("deviceNumber");
        String eptIds = jsonObject.getString("eptIds");
		try {
			message = equipmentServer.appUpdateByeqId(equipment,deviceNames,deviceNumber,eptIds);
		} catch (Exception e) {
			logger.error("set  appUpdateByeqId error:"+e.getMessage(),e);
            //数据异常
            getJsonObject().put(RESULT_CODE,3);
		}
		if(message != "false" && !"false".equals(message)){
            getJsonObject().put(RESULT_CODE,1);
        }else {
			getJsonObject().put(RESULT_CODE,2);
		}
		pushJsonResult();
	}
	
	//删除对象信息
	@RequestMapping(value = "/appDeletByeqId",method = RequestMethod.POST)
	@ResponseBody
	public void appDeletByeqId(HttpServletRequest request){
		String eqId = request.getParameter("eqId");
		String deviceNumber = request.getParameter("deviceNumber");
		String message = ""; 
		try {
			message = equipmentServer.deletByeqId(eqId,deviceNumber);
		} catch (Exception e) {
			logger.error("set  appDeletByeqId error:"+e.getMessage(),e);
			getJsonObject().put(RESULT_CODE,3);
		}
		if(message != "false" && !"false".equals(message)){
			//删除成功
			getJsonObject().put(RESULT_CODE,1);
		}else {
			//删除失败
			getJsonObject().put(RESULT_CODE,2);
		}
		pushJsonResult();
	}
}
