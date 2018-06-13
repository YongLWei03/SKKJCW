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
import com.example.skkj.controller.web.SubstationController;
import com.example.skkj.entity.Region;
import com.example.skkj.entity.Substation;
import com.example.skkj.service.RegionServer;
import com.example.skkj.service.SubstationServer;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/appSubstation")
public class SubstationAppController extends BaseController {
	protected static Logger logger = Logger.getLogger(SubstationController.class.getName());
	@Autowired
	private SubstationServer substationServer;
	
	@Autowired
	private RegionServer regionServer;

	 /**
	      * @Author ZhouNan
	      * @Description
	      * @params userId 用户ID  currentPage当前页数 type用户的权限
	      * @Date 2018/3/23 0023  09:20
	      */
	@RequestMapping(value = "/appSelectSubstationByType",method = RequestMethod.POST)
	@ResponseBody
	public void selectSubstationByType(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = substationServer.APPselectSubstationByType(request);
			getJsonObject().put("currentPage",String.valueOf(map.get("currentPage")));
			getJsonObject().put("pageCount",String.valueOf(map.get("pageCount")));
			getJsonObject().put("substationList",(List<Substation>)map.get("substationList"));
			getJsonObject().put(RESULT_CODE, 1);
		} catch (Exception e) {
			logger.error("set appSelectSubstationByType error:"+e.getMessage(),e);
			getJsonObject().put(RESULT_CODE, 3);
		}
		pushJsonResult();
	}

	/**
	 * app添加变电站信息
	 * */
	@RequestMapping(value = "/appInsertSubstation",method = RequestMethod.POST)
	@ResponseBody
	public void appInsertSubstation(@RequestBody JSONObject jsonObject){
		Substation substation = jsonObject.getObject("data", Substation.class);
		String userId = jsonObject.getString("userId");
		String message = "";
		try {
			message = substationServer.appInsert(substation, userId);
		} catch (Exception e) {
			logger.error("set app添加变电站信息 error:"+e.getMessage(),e);
			getJsonObject().put(RESULT_CODE,3);
		}
		if(message != null && !"".equals(message)){
			if(message != "false" && !"false".equals(message)){
				getJsonObject().put(RESULT_CODE,1);
			}else {
				getJsonObject().put(RESULT_CODE,2);
			}
		}
		pushJsonResult();
	}

	/***
	 * 修改变电站信息
	 * */
	@RequestMapping(value = "/appUpdateBySubstationId",method = RequestMethod.POST)
	@ResponseBody
	public void appUpdateBySubstationId(@RequestBody JSONObject jsonObject){
		Substation substation = jsonObject.getObject("data", Substation.class);
		String userId = jsonObject.getString("userId");

		String message = "";
		try {
			message = substationServer.appUpdateBySubstationId(substation, userId);
		} catch (Exception e) {
			logger.error("set 修改变电站信息 error:"+e.getMessage(),e);
			getJsonObject().put(RESULT_CODE,3);
		}
		if(message != null && !"".equals(message)){
			if(message != "false" && !"false".equals(message)){
				getJsonObject().put(RESULT_CODE,1);
			}else {
				getJsonObject().put(RESULT_CODE,2);
			}
		}
		pushJsonResult();
	}
	
	/***
	 * 删除变电站
	 * */
	@RequestMapping(value = "/appDeleteSubstation",method = RequestMethod.POST)
	@ResponseBody
	public void appDeleteSubstation(HttpServletRequest request){
		String substationId = request.getParameter("substationId");
		String message = "";
		try {
			message = substationServer.appDeleteSubstation(substationId);
		} catch (Exception e) {
			logger.error("set 删除变电站 error:"+e.getMessage(),e);
			getJsonObject().put(RESULT_CODE,3);
		}
		if(message != null && !"".equals(message)){
				if(message != "false" && !"false".equals(message)){
					getJsonObject().put(RESULT_CODE,1);
				}else {
					getJsonObject().put(RESULT_CODE,2);
				}
		}
    pushJsonResult();
	}

	/**
	 * 修改进入变电站信息
	 * */
	@RequestMapping(value = "/appSelectBySubstationId",method = RequestMethod.POST)
	@ResponseBody
	public void selectBySubstationId(HttpServletRequest request){
		String substationId = request.getParameter("substationId");
		Substation sustation = null;
		List<Region> regionList = null;
		try {
			sustation = substationServer.selectBySubstationId(substationId);
			regionList = regionServer.findeByRegionIdSS(null);
			getJsonObject().put("sustation",sustation);
			getJsonObject().put("regionList",regionList);
			getJsonObject().put(RESULT_CODE,1);
		} catch (Exception e) {
			logger.error("set selectBySubstationId error:"+e.getMessage(),e);
			getJsonObject().put(RESULT_CODE,3);
		}

		pushJsonResult();
	}
	
	
}
