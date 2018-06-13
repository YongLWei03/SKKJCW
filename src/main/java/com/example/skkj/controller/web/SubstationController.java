package com.example.skkj.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.skkj.entity.Region;
import com.example.skkj.entity.Substation;
import com.example.skkj.service.RegionServer;
import com.example.skkj.service.SubstationServer;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/webSubstation")
public class SubstationController {
	protected static Logger logger = Logger.getLogger(SubstationController.class.getName());
	@Autowired
	private SubstationServer substationServer;
	
	@Autowired
	private RegionServer regionServer;

	
	
	@RequestMapping("/selectSubstationByType")
	public ModelAndView selectSubstationByType(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		try {
			substationServer.selectSubstationByType(request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("set selectSubstationByType error:"+e.getMessage(),e);
		}
		mv.setViewName("webB/substationList");
		return mv;
	}
	
	/***
	 * 进入变电站添加
	 * */
	@RequestMapping("/insert")
	public ModelAndView insert(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		List<Region> regionList = null;
		try {
			regionList = regionServer.findeByRegionIdSS(null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("set insert error:"+e.getMessage(),e);
		}
		request.setAttribute("regionList", regionList);
		mv.setViewName("webB/addSubstation");
		return mv;
	}
	
	/**
	 * 添加变电站信息
	 * */
	@RequestMapping("/insertSubstation")
	@ResponseBody
	public String insertSubstation(@ModelAttribute Substation substation, HttpServletRequest request){
		String message = "";
		try {
			message = substationServer.insert(substation, request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("set insertSubstation error:"+e.getMessage(),e);
		}
		return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**
	 * 修改进入变电站信息
	 * */
	@RequestMapping("/selectBySubstationId")
	public ModelAndView selectBySubstationId(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		String substationId = request.getParameter("substationId");
		 Substation sustation = null;
		 List<Region> regionList = null;
		try {
			sustation = substationServer.selectBySubstationId(substationId);
			regionList = regionServer.findeByRegionIdSS(null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("set selectBySubstationId error:"+e.getMessage(),e);
		}
		request.setAttribute("sustation", sustation);
		request.setAttribute("regionList", regionList);
		mv.setViewName("webB/addSubstation");
		return mv;
	}
	
	/***
	 * 修改变电站信息
	 * */
	@RequestMapping("/updateBySubstationId")
	@ResponseBody
	public String updateBySubstationId(@ModelAttribute Substation substation, HttpServletRequest request){
		 String message = "";
		try {
			message = substationServer.updateBySubstationId(substation, request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("set updateBySubstationId error:"+e.getMessage(),e);
		}
		return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/***
	 * 删除变电站
	 * */
	@RequestMapping("/deleteSubstation")
	@ResponseBody
	public String deleteSubstation(HttpServletRequest request){
		String substationId = request.getParameter("substationId");
		String message = "";
		try {
			message = substationServer.deleteSubstation(substationId, request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("set deleteSubstation error:"+e.getMessage(),e);
		}
		return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
	}
	
	
	
}
