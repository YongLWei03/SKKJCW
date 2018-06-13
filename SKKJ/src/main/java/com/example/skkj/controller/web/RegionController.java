package com.example.skkj.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.skkj.entity.Region;
import com.example.skkj.service.RegionServer;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/webRegion")
public class RegionController {
	protected static Logger logger = Logger.getLogger(SubstationController.class.getName());
	@Autowired
	private RegionServer regionServer;
	/**
	 * 查询省
	 * */
	@RequestMapping("/findeByRegionIdSS")
	@ResponseBody
	public String findeByRegionIdSS(){
		    Map<String, Object> map = new HashMap<String,Object>();
		    List<Region> regionssList = null;
		try {
			regionssList = regionServer.findeByRegionIdSS(null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("set findeByRegionIdSS error:"+e.getMessage(),e);
		}
			map.put("regionssList", regionssList);
		return JSON.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
	}
	/**
	 * 查询市
	 * */
	@RequestMapping("/findeByRegionIdS")
	@ResponseBody
	public String findeByRegionIdS(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String,Object>();
		List<Region> regionsList = null;
		try {
			regionsList = regionServer.findeByRegionIdS(request.getParameter("regionId"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("set findeByRegionIdS error:"+e.getMessage(),e);
		}
		map.put("regionsList", regionsList);
		return JSON.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**
	 * 查询区
	 * */
	@RequestMapping("/findeByRegionIdX")
	@ResponseBody
	public String findeByRegionIdX(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String,Object>();
		List<Region> regionxList = null;
		try {
			regionxList = regionServer.findeByRegionIdX(request.getParameter("regionId"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("set findeByRegionIdX error:"+e.getMessage(),e);
		}
		map.put("regionxList", regionxList);
		return JSON.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
	}
	
}
