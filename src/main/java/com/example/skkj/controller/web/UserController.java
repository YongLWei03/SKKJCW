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
import com.example.skkj.entity.User;
import com.example.skkj.entity.UserRegion;
import com.example.skkj.service.RegionServer;
import com.example.skkj.service.SubstationServer;
import com.example.skkj.service.UserRegionService;
import com.example.skkj.service.UserService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("/webUser")
public class UserController {
	protected static Logger logger = Logger.getLogger(UserController.class.getName());
	private Logger log = Logger.getLogger("SystemOut");
	@Autowired
	private UserService userService;
	
	@Autowired
	private RegionServer regionServer;
	
	@Autowired
	private SubstationServer substationServer;
	
	@Autowired
	private UserRegionService userRegionService;
	
	
	/**
	 * 添加用户
	 * */
	@RequestMapping("/insert")
	@ResponseBody
	public String insert(@ModelAttribute User user, HttpServletRequest request){
		  String message ="";
		try {
			 message = userService.insert(user,request);
		} catch (Exception e) {
			logger.error("添加用户信息错误:"+e.getMessage(),e);
		}
		return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**
	 * 添加用户
	 * */
	@RequestMapping("/t")
	public String login(HttpServletRequest request){
		    	  return "index";
		    	
	}
	
	@RequestMapping("/findByUserName")
	public @ResponseBody String findByUserName(@ModelAttribute User user, HttpServletRequest request, HttpServletResponse response){
		String message = "";
		try{
			 message = userService.findByUserName(user,request);
		} catch (Exception e) {
			logger.error("set findByUserName error:"+e.getMessage(),e);
			return "false";
		}
		return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
	}
	
	//删除用户
	@RequestMapping("/deletByuserId")
	@ResponseBody
	public String deletByuserId(HttpServletRequest request){
		String message = "";
		String userId = request.getParameter("userId");
		try {
			message = userService.deletByuserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("set deletByuserId error:"+e.getMessage(),e);
		}
		return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
	}
	
	@RequestMapping("/selectUser")
	public ModelAndView selectUser(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
		    User user = (User) request.getSession().getAttribute("user");
		    String type = user.getType();
		try {
				userService.selectUser(request);
		} catch (Exception e) {
			logger.error("set selectUser error:"+e.getMessage(),e);
		}
        mv.setViewName("webB/userList");
		return mv;
	}
	
	/**
	 * 判断用户名是否重复
	 * 
	 * */
	@RequestMapping("/selectByUserName")
	@ResponseBody
	public String selectByUserName(String userName){
		String message = "";
		try {
			message = userService.selectByUserName(userName);
		} catch (Exception e) {
			logger.error("set selectByUserName error:"+e.getMessage(),e);
		}
		return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**
	 *  查看单个用户信息 
	 * */
	@RequestMapping("/selectByUserId")
	public ModelAndView selectByUserId(HttpServletRequest request){
		String userId = request.getParameter("userId");
        ModelAndView mv = new ModelAndView();
		User user = new User();
		try {
			user = userService.selectByUserId(userId);
		} catch (Exception e) {
			logger.error("set selectByUserId error:"+e.getMessage(),e);
		}
		request.setAttribute("adduser", user);
        mv.setViewName("webB/addUser");
		return mv;
	}
	
	/**
	 *  修改用户信息
	 * */
	@RequestMapping("/updateByUserId")
	@ResponseBody
	public String updateByUserId(@ModelAttribute User user, HttpServletRequest request){
		String message = "";
		try {
			message = userService.updateByUserId(user,request);
		} catch (Exception e) {
			logger.error("set updateByUserId error:"+e.getMessage(),e);
		}
		return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**
	 *  进入添加页面
	 * */
	@RequestMapping("/inserUser")
	public ModelAndView inserUser(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("webB/addUser");
		return mv;
	}
	
	/**
	 * 进入用户APP授权页面
	 * */
	
	@RequestMapping("/userRightsApp")
	public ModelAndView userRightsApp(HttpServletRequest request){
		 List<User> userRiglist = new LinkedList<User>();
        ModelAndView mv = new ModelAndView();
		 List<UserRegion> userRegionLists = null;
		 try {
			userService.selectByTypeApp(request);
			userRegionLists = userRegionService.findUserRegion();
		} catch (Exception e) {
			logger.error("set userRightsApp error:"+e.getMessage(),e);
		}
		 request.setAttribute("userRegionLists", userRegionLists);

        mv.setViewName("webB/userRightsApp");
        return mv;
	}
	
	//进入用户权限页面
	@RequestMapping("/userRights")
	public ModelAndView userRights(HttpServletRequest request){
		 List<UserRegion> userRegionLists = new ArrayList<UserRegion>();
        ModelAndView mv = new ModelAndView();
		 try {
			userService.selectByType(request);
			userRegionLists = userRegionService.findUserRegion();
		} catch (Exception e) {
			logger.error("set userRights error:"+e.getMessage(),e);
		}
		 request.setAttribute("userRegionLists", userRegionLists);
        mv.setViewName("webB/userRights");
		return mv;
	}
	
	//进入用户授权
	@RequestMapping("/serUserRights")
	public ModelAndView serUserRights(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
		   String userId = request.getParameter("userId");
		   String flog = request.getParameter("flog");
		   User userRigh = null;
		   List<Region> region = null;
		 try {
			 userRigh  = userService.selectByUserId(userId);
			 region = regionServer.findeByRegionIdSS(null);
			 substationServer.selectSubstationByDq(request);
			 
		} catch (Exception e) {
			logger.error("set serUserRights error:"+e.getMessage(),e);
		}
		 if(flog != null && !"".equals(flog)){
			 request.setAttribute("flog", flog);
		 }
		 request.setAttribute("region", region);
		 request.setAttribute("userRigh", userRigh);
		 request.setAttribute("userId", userId);
        mv.setViewName("webB/addRights");
		return mv;
	}
	
	@RequestMapping("/selectSubstationByDqMap")
	@ResponseBody
	public String  selectSubstationByDqMap(HttpServletRequest request){
		     Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = substationServer.selectSubstationByDqMap(request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("set selectSubstationByDqMap error:"+e.getMessage(),e);
		}
		return JSON.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
	}
	
		//进入修改或添加
	@RequestMapping("/setUserRights")
	@ResponseBody
	public String setUserRights(@ModelAttribute User user, HttpServletRequest request){
			String message = "";
			 try {
				 message = userService.updateByUserId(user,request);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("set setUserRights error:"+e.getMessage(),e);
			}
			return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
		}
	
	//退出登录
	@RequestMapping("/logout")
	public void logout(){
		
	}
	
	/**
	 * 将cookie封装到Map里面
	 * @param request
	 * @return
	 */
	private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){
	    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
	    Cookie[] cookies = request.getCookies();
	    if(null!=cookies){
	        for(Cookie cookie : cookies){
	            cookieMap.put(cookie.getName(), cookie);
	        }
	    }
	    return cookieMap;
	}
}