package com.example.skkj.controller.app;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.skkj.comment.BaseController;
import com.example.skkj.entity.Region;
import com.example.skkj.entity.Substation;
import com.example.skkj.entity.User;
import com.example.skkj.entity.UserRegion;
import com.example.skkj.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/appUser")
public class UserAppController extends BaseController{
	protected static Logger logger = Logger.getLogger(UserAppController.class.getName());
	@Autowired
	private UserService userService;

	@Autowired
	private UserRegionService userRegionService;

	@Autowired
    private RegionServer regionServer;

	@Autowired
    private SubstationServer substationServer;
	/**
	 * 添加APP用户添加
	 * RESULT_CODE 1.成功,2.失败
	 * */
	@RequestMapping(value ="/login",method = RequestMethod.POST)
	@ResponseBody
	public void login(@ModelAttribute User user){
		User users = new User();
		try {
			users = userService.findByUserNameApp(user);
			if(users != null && !"".equals(users)){
				getJsonObject().put(RESULT_CODE, 1);
			}else {
				getJsonObject().put(RESULT_CODE, 2);
			}
		} catch (Exception e) {
			logger.error("aap登录用户:"+e.getMessage(),e);
            getJsonObject().put(RESULT_CODE, 3);
		}

		getJsonObject().put("user",users);
		pushJsonResult();
	}

	/**
	 * APP添加用户
	 * */
	@RequestMapping(value = "/appInsert",method = RequestMethod.POST)
	@ResponseBody
	public void insert(@RequestBody JSONObject jsonObject){
		String message ="";
		User user = jsonObject.getObject("user", User.class);
		String type = jsonObject.getString("type");
		String userId = jsonObject.getString("userId");
		try {
			message = userService.appInsert(user,type,userId);
			if(message != "false" && !"false".equals(message)){
				getJsonObject().put(RESULT_CODE,1);
			}else {
				getJsonObject().put(RESULT_CODE,2);
			}
		} catch (Exception e) {
			logger.error("添加用户信息错误:"+e.getMessage(),e);
			getJsonObject().put(RESULT_CODE,3);
		}
		pushJsonResult();
	}

	/**
	 * 判断用户名是否重复
	 *
	 * */
	@RequestMapping(value = "/appSelectByUserName",method = RequestMethod.POST)
	@ResponseBody
	public void appSelectByUserName(String userName){
		String message = "";
		try {
			message = userService.selectByUserName(userName);
			if(message != "false" && !"false".equals(message)){
				getJsonObject().put(RESULT_CODE,1);
			}else {
				getJsonObject().put(RESULT_CODE,2);
			}
		} catch (Exception e) {
			logger.error("set app判断用户名是否重复 error:"+e.getMessage(),e);
		}
		pushJsonResult();
	}

	//app进入用户权限页面
	@RequestMapping(value = "/appUserRights",method = RequestMethod.POST)
	@ResponseBody
	public void userRights(HttpServletRequest request){
		List<UserRegion> userRegionLists = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = userService.appSelectByType(request);
			userRegionLists = userRegionService.findUserRegion();
			getJsonObject().put(RESULT_CODE,1);
			getJsonObject().put("userList",(List<User>)map.get("userRiglist"));
			getJsonObject().put("currentPage",(Integer)map.get("currentPage"));
			getJsonObject().put("pageCount",(Integer)map.get("pageCount"));
			getJsonObject().put("userRegionLists",userRegionLists);
		} catch (Exception e) {
			logger.error("set appUserRights error:"+e.getMessage(),e);
			getJsonObject().put(RESULT_CODE,1);
		}
		pushJsonResult();
	}

    //进入用户授权
    @RequestMapping(value = "/appSerUserRights",method = RequestMethod.POST)
    @ResponseBody
    public void appSerUserRights(HttpServletRequest request){
        String userId = request.getParameter("userId");
        User userRigh = null;
        List<Region> region = null;
        List<Substation> sublist = new ArrayList<Substation>();
        try {
            //传入的是授权用户的user
            userRigh  = userService.selectByUserId(userId);
            region = regionServer.findeByRegionIdSS(null);
            sublist = substationServer.appSelectSubstationByDq(userRigh);
            getJsonObject().put(RESULT_CODE,1);
            getJsonObject().put("region",region);
            getJsonObject().put("userRigh",userRigh);
            getJsonObject().put("sublist",sublist);
            getJsonObject().put("userId",userId);
        } catch (Exception e) {
            logger.error("set serUserRights error:"+e.getMessage(),e);
            getJsonObject().put(RESULT_CODE,3);
        }
        pushJsonResult();
    }
 /**
      * @Author ZhouNan
      * @Description 查看当前对象对应的变电站
      * @params
      * @Date 2018/3/27 0027  14:56
      */
	@RequestMapping(value = "/appSelectSubstationByDqMap",method = RequestMethod.POST)
	@ResponseBody
	public void appSelectSubstationByDqMap(@RequestBody JSONObject jsonObject){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = substationServer.appSelectSubstationByDqMap(jsonObject);
			getJsonObject().put("substationList",(List<Substation>)map.get("substationList"));
			getJsonObject().put("userRegionsub",(List<String>)map.get("userRegionsub"));
		} catch (Exception e) {
			logger.error("set appSelectSubstationByDqMap error:"+e.getMessage(),e);
		}
		pushJsonResult();
	}

	 /**
	      * @Author ZhouNan
	      * @Description 用户查看
	      * @params
	      * @Date 2018/3/28 0028  13:54
	      */
	 @RequestMapping(value = "/appSelectUser",method = RequestMethod.POST )
	 @ResponseBody
	 public void appSelectUser(@RequestBody JSONObject jsonObject){
		 Map<String, Object> map = new HashMap<String, Object>();
		 try {
			 map =  userService.appSelectUser(jsonObject);
			 getJsonObject().put("currentPage",(Integer)map.get("currentPage"));
			 getJsonObject().put("pageCount",(Integer)map.get("pageCount"));
			 getJsonObject().put("departmengtList",(List<String>)map.get("departmengtList"));
			 getJsonObject().put("userlist",(List<User>)map.get("userlist"));
			 getJsonObject().put(RESULT_CODE,1);
		 } catch (Exception e) {
			 logger.error("set appSelectUser error:"+e.getMessage(),e);
			 getJsonObject().put(RESULT_CODE,3);
		 }
		 pushJsonResult();
	 }


	@RequestMapping(value ="/login1",method = RequestMethod.POST)
	@ResponseBody
	public void login1(@RequestBody JSONObject jsonObject){
		String userName = jsonObject.getString("userName");
		String password = jsonObject.getString("password");
		User users = new User();
		try {
//			users = userService.findByUserNameApp(user);
		} catch (Exception e) {
			e.printStackTrace();
			getJsonObject().put(RESULT_CODE, 3);
		}
		if(users != null && !"".equals(users)){
			getJsonObject().put(RESULT_CODE, 1);
		}else {
			getJsonObject().put(RESULT_CODE, 2);
		}
		getJsonObject().put("user",users);
		pushJsonResult();
	}

	/**
	 *  修改用户信息
	 * */
	@RequestMapping(value = "/appUpdateByUserId",method = RequestMethod.POST)
	@ResponseBody
	public void appUpdateByUserId(@RequestBody JSONObject jsonObject){
        User user = jsonObject.getObject("user", User.class);
        String passwordLs = jsonObject.getString("passwordLs");
		String message = "";
		try {
			message = userService.appUpdateByUserId(user,passwordLs);
			if(message != "false" && !"false".equals(message)){
                getJsonObject().put(RESULT_CODE,1);
            }else {
                getJsonObject().put(RESULT_CODE,2);
            }

		} catch (Exception e) {
            getJsonObject().put(RESULT_CODE,3);
			logger.error("set updateByUserId error:"+e.getMessage(),e);
		}
		pushJsonResult();
	}

    //进入修改或添加
    @RequestMapping(value = "/appSetUserRights",method = RequestMethod.POST)
    @ResponseBody
    public void appSetUserRights(@RequestBody JSONObject jsonObject){
        User user = jsonObject.getObject("user", User.class);
        String substationId = jsonObject.getString("substationId");
        String message = "";
        try {
            message = userService.appUpdateByUserIdRegis(user,substationId);
            if(message != "false" && !"false".equals(message)){
                getJsonObject().put(RESULT_CODE,1);
            }else {
                getJsonObject().put(RESULT_CODE,2);
            }
        } catch (Exception e) {
            getJsonObject().put(RESULT_CODE,3);
            logger.error("set appSetUserRights error:"+e.getMessage(),e);
        }
        pushJsonResult();
    }

    //删除用户
    @RequestMapping(value = "/appDeletByuserId",method = RequestMethod.POST)
    @ResponseBody
    public void appDeletByuserId(HttpServletRequest request){
        String message = "";
        String userId = request.getParameter("userId");
        try {
            message = userService.deletByuserId(userId);
            if(message != "false" && !"false".equals(message)){
                getJsonObject().put(RESULT_CODE,1);
            }else {
                getJsonObject().put(RESULT_CODE,2);
            }
        } catch (Exception e) {
            getJsonObject().put(RESULT_CODE,3);
            logger.error("set deletByuserId error:"+e.getMessage(),e);
        }
        pushJsonResult();
    }

     /**
          * @Author ZhouNan
          * @Description 用户查看自己的信息
          * @params
          * @Date 2018/4/10 0010  14:39
          */
	 @RequestMapping(value = "/appSelectUserId",method = RequestMethod.POST)
	 @ResponseBody
	 public void appSelectUserId(HttpServletRequest request){
		 String message = "";
		 String userId = request.getParameter("userId");
		 try {
			 User user = userService.selectByUserId(userId);
			     getJsonObject().put("user",user);
				 getJsonObject().put(RESULT_CODE,1);

		 } catch (Exception e) {
			 getJsonObject().put(RESULT_CODE,3);
			 logger.error("set deletByuserId error:"+e.getMessage(),e);
		 }
		 pushJsonResult();
	 }

}