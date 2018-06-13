package com.example.skkj.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.skkj.entity.BaiDuAddr;
import com.example.skkj.entity.Substation;
import com.example.skkj.service.BaiduServer;
import com.example.skkj.service.SubstationServer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/webBaiDu")
public class BaiDuController {
	protected static Logger logger = Logger.getLogger(AlarmInformationController.class);
	@Autowired
	private SubstationServer substationServer;
	@Autowired
	private BaiduServer baiduServer;
	
	@RequestMapping("/dtl")
	@ResponseBody
	public String dt(HttpServletRequest request){
		   Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = substationServer.findByType(request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("set  dtl error:"+e.getMessage(),e);
		}
		return JSON.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
	}
	@RequestMapping("/dtol")
	public String dtol(HttpServletRequest request){
		return "webB/dt";
	}
	/**
	 *根据地址名搜索 
	 * */
	@RequestMapping("/serche")
	@ResponseBody
	public String serche(HttpServletRequest request) throws IOException, JSONException {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<BaiDuAddr> baduList = new LinkedList<BaiDuAddr>();
		try {
			baduList = baiduServer.serche(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(baduList != null && baduList.size()>0){
		    map.put("baduList",baduList);
		}else {
			map.put("baduList","false");
		}
		return JSON.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
	  }

	   /**
	        * @Author ZhouNan
	        * @Description 变电站地图的当前位置的的变电站搜索
	        * @params
	        * @Date 2018/3/5 0005  13:41  findByArer
	        */
	   @RequestMapping("/findByArer")
	   @ResponseBody
	   public String findByArer(HttpServletRequest request) throws IOException, JSONException {
		   Map<String, Object> map = new LinkedHashMap<String, Object>();

		   Substation subs = new Substation();
		   try {
			   subs = substationServer.findByArer(request);
		   } catch (Exception e) {
			   e.printStackTrace();
		   }
			if(subs != null && !"".equals(subs)){
				map.put("subs",subs);
			}else {
				map.put("subs","false");
			}
		   return JSON.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
	   }
}
