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
import com.example.skkj.entity.AlarmInformation;
import com.example.skkj.service.AlarmInformationServer;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/appAlarmInformation")
public class AlarmInformationAppController extends BaseController{
	protected static Logger logger = Logger.getLogger(AlarmInformationAppController.class);
	@Autowired
	private AlarmInformationServer alarmInformationServer;
	
	//查看报警状态
	@RequestMapping(value = "/appSelectAlarmInformation",method = RequestMethod.POST)
	@ResponseBody
	public void appSelectAlarmInformation(@RequestBody JSONObject jsonObject){
		Map<String, Object> map = new HashMap<>();
		try {
			map = alarmInformationServer.appSelectAlarmInformation(jsonObject);
			getJsonObject().put(RESULT_CODE,1);
			getJsonObject().put("alarmInformationList",(List<AlarmInformation>)map.get("alarmInformationList"));
			getJsonObject().put("currentPage",(Integer)map.get("currentPage"));
			getJsonObject().put("pageCount",(Integer)map.get("pageCount"));
			getJsonObject().put("alarmInformationCount",(Integer)map.get("alarmInformationCount"));
		} catch (Exception e) {
			logger.error("set appSelectAlarmInformation error:"+e.getMessage(),e);
			getJsonObject().put(RESULT_CODE,3);
		}
		pushJsonResult();
	}
	
	//修改报警状态
	@RequestMapping(value = "/appUpdateByafId",method = RequestMethod.POST)
	@ResponseBody
	public void appUpdateByafId(HttpServletRequest request){
			String message = "";
		try {
			 message = alarmInformationServer.updateByafId(request);

			 	if(message != "false" && !"false".equals(message)){
			 		getJsonObject().put(RESULT_CODE,1);
				}else {
					getJsonObject().put(RESULT_CODE,2);
				}

		} catch (Exception e) {
			logger.error("set appUpdateByafId error:"+e.getMessage(),e);
			getJsonObject().put(RESULT_CODE,2);
		}
		pushJsonResult();
	}
	
	/**
	 * 删除报警信息
	 * */
	@RequestMapping(value = "/appDeleteByAfId",method = RequestMethod.POST)
	@ResponseBody
	public void appDeleteByAfId(HttpServletRequest request){
			String message = "";
		try {
			 message = alarmInformationServer.deleteByAfId(request);
			if(message != "false" && !"false".equals(message)){
				getJsonObject().put(RESULT_CODE,1);
			}else {
				getJsonObject().put(RESULT_CODE,2);
			}
		} catch (Exception e) {
			logger.error("set appDeleteByAfId error:"+e.getMessage(),e);
			getJsonObject().put(RESULT_CODE,2);
		}
		pushJsonResult();
	}
	/**
	 * 批量删除报警信息
	 * */
	@RequestMapping(value = "/appDeleteByAfIdList",method = RequestMethod.POST)
	@ResponseBody
	public void appDeleteByAfIdList(@RequestBody JSONObject jsonObject){
		String message = "";
		String afIds = jsonObject.getString("afIdJ");
		String[] afId = afIds.split(",");
		List<String> afIdList = new LinkedList<String>();
		for (int i = 0; i < afId.length; i++) {
			afIdList.add(afId[i]);
		}
		try {
			message = alarmInformationServer.deleteByAfIdList(afIdList);
			if(message != "false" && !"false".equals(message)){
				getJsonObject().put(RESULT_CODE,1);
			}else {
				getJsonObject().put(RESULT_CODE,2);
			}
		} catch (Exception e) {
			logger.error("set appDeleteByAfIdList error:"+e.getMessage(),e);
			getJsonObject().put(RESULT_CODE,3);
		}
		pushJsonResult();
	}
	/**
	 * 查看是否存在报警信息
	 * */
	@RequestMapping("/selectByType")
	@ResponseBody
	public String selectByType(HttpServletRequest request){
		String message = "";
		try {
			message = alarmInformationServer.selectByType(request);
		} catch (Exception e) {
			logger.error("set selectByType error:"+e.getMessage(),e);
		}
		return message;
	}
}
