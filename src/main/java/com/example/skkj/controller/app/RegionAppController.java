package com.example.skkj.controller.app;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.skkj.comment.BaseController;
import com.example.skkj.controller.web.SubstationController;
import com.example.skkj.entity.Region;
import com.example.skkj.service.RegionServer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/appRegion")
public class RegionAppController extends BaseController{
	protected static Logger logger = Logger.getLogger(SubstationController.class.getName());
	@Autowired
	private RegionServer regionServer;
	/**
	 * 查询省
	 * */
	@RequestMapping(value = "/appFindeByRegionIdSS",method = RequestMethod.POST)
	@ResponseBody
	public void appFindeByRegionIdSS(){
		    List<Region> regionssList = null;
		try {
			regionssList = regionServer.findeByRegionIdSS(null);
			getJsonObject().put("regionssList",regionssList);
			getJsonObject().put(RESULT_CODE,1);
		} catch (Exception e) {
			logger.error("set appFindeByRegionIdSS error:"+e.getMessage(),e);
            getJsonObject().put(RESULT_CODE,3);
		}
          pushJsonResult();
	}
	/**
	 * 查询市
	 * */
	@RequestMapping(value = "/appFindeByRegionIdS",method = RequestMethod.POST)
	@ResponseBody
	public void appFindeByRegionIdS(HttpServletRequest request){
		List<Region> regionsList = null;
		try {
			regionsList = regionServer.findeByRegionIdS(request.getParameter("regionId"));
			getJsonObject().put("regionsList",regionsList);
			getJsonObject().put(RESULT_CODE,1);
		} catch (Exception e) {
			logger.error("set appFindeByRegionIdS error:"+e.getMessage(),e);
            getJsonObject().put(RESULT_CODE,3);
		}
		pushJsonResult();
	}

	/**
	 * 查询区
	 * */
	@RequestMapping(value = "/appFindeByRegionIdX",method = RequestMethod.POST)
	@ResponseBody
	public void appFindeByRegionIdX(HttpServletRequest request){
		List<Region> regionxList = null;
		try {
			regionxList = regionServer.findeByRegionIdX(request.getParameter("regionId"));
            getJsonObject().put("regionxList",regionxList);
            getJsonObject().put(RESULT_CODE,1);
		} catch (Exception e) {
			logger.error("set appFindeByRegionIdX error:"+e.getMessage(),e);
            getJsonObject().put(RESULT_CODE,3);
		}
		pushJsonResult();
	}

}
