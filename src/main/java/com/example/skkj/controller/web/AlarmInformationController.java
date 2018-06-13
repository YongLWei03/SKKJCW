package com.example.skkj.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.skkj.entity.AlarmInformation;
import com.example.skkj.service.AlarmInformationServer;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/webAlarmInformation")
public class AlarmInformationController {
	protected static Logger logger = Logger.getLogger(AlarmInformationController.class);
	@Autowired
	private AlarmInformationServer alarmInformationServer;
	
	//进入报警信息
	@RequestMapping("/selectAlarmInformation")
	public ModelAndView selectAlarmInformation(HttpServletRequest request){
		List<AlarmInformation> alarmInformationList = new ArrayList<AlarmInformation>();
		ModelAndView mv = new ModelAndView();
		try {
			alarmInformationList = alarmInformationServer.selectAlarmInformation(request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("set selectAlarmInformation error:"+e.getMessage(),e);
		}
		request.setAttribute("alarmInformationList", alarmInformationList);
		mv.setViewName("webB/alarmInformationList");
		return mv;
	}
	
	//修改报警状态
	@RequestMapping("/updateByafId")
	@ResponseBody
	public String updateByafId(HttpServletRequest request){
			String message = "";
		try {
			 message = alarmInformationServer.updateByafId(request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("set updateByafId error:"+e.getMessage(),e);
		}
		return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**
	 * 删除报警信息
	 * */
	@RequestMapping("/deleteByAfId")
	@ResponseBody
	public String deleteByAfId(HttpServletRequest request){
			String message = "";
		try {
			 message = alarmInformationServer.deleteByAfId(request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("set deleteByAfId error:"+e.getMessage(),e);
		}
		return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
	}
	/**
	 * 批量删除报警信息
	 * */
	@RequestMapping("/deleteByAfIdList")
	@ResponseBody
	public String deleteByAfIdList(HttpServletRequest request){
		String message = "";
		String[] afId = request.getParameterValues("afId");
		List<String> afIdList = new LinkedList<String>();
		for (int i = 0; i < afId.length; i++) {
			afIdList.add(afId[i]);
		}
		try {
			message = alarmInformationServer.deleteByAfIdList(afIdList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("set deleteByAfIdList error:"+e.getMessage(),e);
		}
		return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
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
			e.printStackTrace();
			logger.error("set selectByType error:"+e.getMessage(),e);
		}
		return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
	}
}
