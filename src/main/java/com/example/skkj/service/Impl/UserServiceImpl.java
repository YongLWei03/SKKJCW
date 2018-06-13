package com.example.skkj.service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.skkj.comment.RedisUtils;
import com.example.skkj.comment.ShiroMD5;
import com.example.skkj.entity.AdministratorAndUser;
import com.example.skkj.entity.Substation;
import com.example.skkj.entity.User;
import com.example.skkj.entity.UserRegion;
import com.example.skkj.mapper.AdministratorAndUserMapper;
import com.example.skkj.mapper.RegionMapper;
import com.example.skkj.mapper.UserMapper;
import com.example.skkj.mapper.UserRegionMapper;
import com.example.skkj.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.*;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RegionMapper regionMapper;
	@Autowired
	private UserRegionMapper userRegionMapper;
	@Autowired
	private AdministratorAndUserMapper administratorAndUserMapper;

    /**
         * @Author ZhouNan
         * @Description 后台登录
         * @params
         * @Date 2018/3/22 0022  10:13
         */
	@Override
	public String findByUserName(User user, HttpServletRequest request) throws Exception {
		User user2 = userMapper.findByUserName(user.getUserName());
		if(user2 != null){
			//盐值
			String salt = user2.getSalt();
			String pws = ShiroMD5.md5yzjm(user.getPassword(), salt);
			if(pws != null && !"".equals(pws)){
				if(pws == user2.getPassword() || pws.equals(user2.getPassword())){
					String type = user2.getType();
					request.getSession().setAttribute("user", user2);
					if(type == "4" || "4".equals(type)){
						return "ptdl";
					}else if(type == "3" || "3".equals(type)){
						return "fzr";
					}else {
						return "true";
					}
				}
			}else {
				return "false";
			}
		}
		return "false";
	}
	/**
	 * @Author ZhouNan
	 * @Description app登录
	 * @params
	 * @Date 2018/3/22 0022  10:14
	 */
	@Override
	public User findByUserNameApp(User user) throws Exception {
		User user2 = userMapper.findByUserName(user.getUserName());
		if(user2 != null){
			//盐值
			String salt = user2.getSalt();
			String pws = ShiroMD5.md5yzjm(user.getPassword(),salt);
			if(pws != null && !"".equals(pws)){
				if(pws == user2.getPassword() || pws.equals(user2.getPassword())){
					return user2;
				}
			}else {
				return null;
			}
		}
		return null;
	}




	/***
	 * 查询用户
	 * */
	@Override
	public void selectUser(HttpServletRequest request) throws Exception {
		     User user = (User) request.getSession().getAttribute("user");
			//每页显示多少条
			int pageSize = 10;
			//当前页
			int currentPage = 1;
			
			Map<String,Object> map = new HashMap<String, Object>();
			//当前页
			String dqpagestr = request.getParameter("currentPage");
			String type = request.getParameter("type");
			String keyword = request.getParameter("keyword");
			String department = request.getParameter("department");
			if(null!=dqpagestr && !"".equals(dqpagestr)){
				currentPage = Integer.parseInt(dqpagestr);
			}
			map.put("start", ((currentPage-1)*pageSize));
//			map.put("end", pageSize*currentPage);
			map.put("pageSize", pageSize);
			if(type != null && !"".equals(type)){
				map.put("type", type);
				request.setAttribute("type", type);
			}
			
			if(department != null && !"".equals(department)){
				String department1 = URLDecoder.decode(department, "UTF-8");
				map.put("department", department1);
				request.setAttribute("department", department1);
			}
			if(keyword != null && !"".equals(keyword)){
				keyword = URLDecoder.decode(keyword, "UTF-8");
				map.put("keyword", keyword);
				request.setAttribute("keyword", keyword);
			}
			  String usertype = user.getType();
			  map.put("usertype", usertype);
			  Integer pageCount = null;
			  List<User> userlist = new ArrayList<User>();
			  if(usertype == "2" || "2".equals(usertype)){
				  String provinceId = user.getProvinceId();
				  Integer userId = user.getUserId();
				  map.put("userId",userId.toString());
				  if(provinceId != null && !"".equals(provinceId)){
					    map.put("provinceId", provinceId);
					    String areaId = user.getAreaId();
					    String cityId = user.getCityId();
					    if(areaId != null && !"".equals(areaId)){
					    	map.put("areaId", areaId);
					    }
					    if(cityId != null && !"".equals(cityId)){
					    	map.put("cityId", cityId);
					    }
					  pageCount = userMapper.selectUserCount(map);
					  userlist = userMapper.selectUser(map);
				  }else {
					  pageCount = 0;
					  userlist = null;
				  }
			  }else {
				  pageCount = userMapper.selectUserCount(map);
				  userlist = userMapper.selectUser(map);
			}
			
			pageCount = pageCount%pageSize==0?pageCount/pageSize:(pageCount/pageSize)+1; 
			List<String> departmengtList = userMapper.selectDepartmentByUser();
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("departmengtList", departmengtList);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("userlist", userlist);
			request.setAttribute("map", map);
	}
	
	@Override
	public String deletByuserId(String userId) throws Exception {
		RedisUtils redisUtils = new RedisUtils();
		 String message = userMapper.deletByuserId(userId)>0?"true":"false";
		  if(message == "true"){
			  administratorAndUserMapper.delet(userId);
			  String subsp = "subsp"+userId;
			  String substype = "subyhtp"+userId;
			  Integer potecolle = null;
			  String potecoll = redisUtils.getString(subsp);
			  if(potecoll != null && !"".equals(potecoll)){
				  potecolle = Integer.parseInt(potecoll);
			  }
			  if(potecolle != null){
				 for (int i = 0; i < potecolle; i++) {
					 int cds = i+1;
					 //删除变电站的缓存数据
					 String SubcurrentPage = "Subs"+cds+potecolle+userId;
					 redisUtils.delObject(SubcurrentPage);
				}
				  redisUtils.delObject(subsp);
			  }
			  List<Substation> subslist = redisUtils.getList(subsp,Substation.class);
			   if(subslist!=null && subslist.size()>0){
				   redisUtils.delObject(subsp);
			   }
		  }
		return message;
	}
	/**
	 * @Author ZhouNan
	 * @Description
	 * @params user d对象
	 * @params reuquest 注册的对象
	 * @Date 09:51 2017/12/8 0008
	 */
	@Override
	public String insert(User user,HttpServletRequest request) throws Exception {
		    User adminsuer = (User) request.getSession().getAttribute("user");
			user = ShiroMD5.md5jm(user);
			user.setFlog("1");
			String type = adminsuer.getType();
			String message = userMapper.insert(user) > 0 ? "true" : "false";
			if(message != "false" && !"false".equals(message)){
			    //添加用户状态为管理员
				if(type == "2" || "2".equals(type)){
                    User user3 = userMapper.findByUserName(user.getUserName());
					Integer userId = user3.getUserId();
					AdministratorAndUser adminUser = new AdministratorAndUser();
					adminUser.setUserId(userId.toString());
					adminUser.setAdminId(adminsuer.getUserId().toString());
					administratorAndUserMapper.insert(adminUser);
				}

			}
		return message;
	}
	@Override
	public String selectByUserName(String userName) throws Exception {
		       User user = new User();
         	   user = userMapper.findByUserName(userName);
         	  if(user!=null){
         		 return "false";
         	  }else {
         		 return "true";
         	  }
	}

	@Override
	public String updateByUserId(User user,HttpServletRequest request) throws Exception {
		RedisUtils redisUtils = new RedisUtils();
		    String[] substationId = request.getParameterValues("substationId");
		     String p = request.getParameter("p");
			String provinceId = user.getProvinceId();//省
			String cityId = user.getCityId();//市
		    String areaId = user.getAreaId();//区/县
		    String region = "";
		    if(provinceId != null && !"".equals(provinceId)){
		    	String province = "";
		    	if(provinceId != "0" &&!"0".equals(provinceId)){
		    		province = regionMapper.findeByRegionId(provinceId);
		    	}else {
		    		province += "全省";
				}
		       region += province;
		     if(cityId != null && !"".equals(cityId)){
		    	 String city = "";
			    	if(cityId != "0" &&!"0".equals(cityId)){
			    		city = regionMapper.findeByRegionId(cityId);
			    	}else {
			    		city += "全市";
					}
		        region += city;
		     if(areaId != null && !"".equals(areaId)){
		    	 String area = "";
			    	if(areaId != "0" && !"0".equals(areaId)){
			    		area = regionMapper.findeByRegionId(areaId);
			    	}else {
			    		area += "全区";
					}
			     region += area;
		       }
		    }
	   }
		     user.setRegion(region);
		     String message = "";
		      String usename = user.getUserName();
		      if(usename != null && !"".equals(usename)){
		    	  if(p != null && !"".equals(p) && p != user.getPassword() && !p.equals(user.getPassword())){
		    			  user = ShiroMD5.md5jm(user);
		    	  }
		    	  message = userMapper.updateByUserId(user)>0?"true":"false";
		      }else {
		    	  message = userMapper.updateByUserId(user)>0?"true":"false";
		    	  userRegionMapper.deletByUserId(user.getUserId().toString());
			}
		     //变电站添加
		     if(message != "false" && !"false".equals(message)){
		    	 if(substationId != null && substationId.length>0){
		    		 List<UserRegion> UserRegionList = new LinkedList<UserRegion>();
		    		 for (int i = 0; i < substationId.length; i++) {
		    			 UserRegion UserR = new UserRegion();
		    			 UserR.setUserId(user.getUserId().toString());
		    			 UserR.setSubstationId(substationId[i]);
		    			 UserRegionList.add(UserR);
		    		 }
		    			  message = userRegionMapper.insert(UserRegionList)>0?"true":"false";
		    			  if(message == "true"){
		    				  String subsp = "subsp"+user.getUserId();
		    				  String substype = "subyhtp"+user.getUserId();
		    				   String potecolleS = redisUtils.getString(subsp);
		    				  if(potecolleS != null){
		    					  int potecolle = Integer.parseInt(potecolleS);
		    					  if(potecolle > 0){
		    						  for (int i = 0; i < potecolle; i++) {
		    							  int cds = i+1;
		    							  //删除变电站的缓存数据
		    							  String SubcurrentPage = "Subs"+cds+potecolle+user.getUserId();
										  redisUtils.delObject(SubcurrentPage);
		    						  }
		    					  }

								  redisUtils.delObject(subsp);
		    				  }
							  redisUtils.delObject(substype);
		    				   List<Substation> subslist = redisUtils.getList(subsp,Substation.class);
		    				   if(subslist!=null && subslist.size()>0){
								   redisUtils.delObject(subsp);
		    				   }
		    			  }
		    	 }

		     }

		return message;
	}

	@Override
	public User selectByUserId(String userId) throws Exception {
		return userMapper.selectByUserId(userId);
	}

	@Override
	public void selectByType(HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		//每页显示多少条
		int pageSize = 10;
		//当前页
		int currentPage = 1;
		
		Map<String,Object> map = new HashMap<String, Object>();
		//当前页
		String dqpagestr = request.getParameter("currentPage");
		if(null!=dqpagestr && !"".equals(dqpagestr)){
			currentPage = Integer.parseInt(dqpagestr);
		}
			 String type = user.getType();
			if(type != "1" && !"1".equals(type)){
				map.put("type", type);
			}
			 String provinceId = user.getProvinceId();
			 String cityId = user.getCityId();
			 String areaId = user.getAreaId();
			 if(provinceId != null && !"".equals(provinceId) && provinceId != "0" && !"0".equals(provinceId)){
				 map.put("provinceId", provinceId);
			 }
			 if(cityId != null && !"".equals(cityId) && cityId != "0" && !"0".equals(cityId)){
				 map.put("cityId", cityId);
			 }
			 if(areaId != null && !"".equals(areaId) && areaId != "0" && !"0".equals(areaId)){
				 map.put("areaId", areaId);
			 }
		map.put("start", ((currentPage-1)*pageSize));
//		map.put("end", pageSize*currentPage);
		map.put("pageSize", pageSize);
		Integer pageCount = null;
		List<User> userRiglist = new ArrayList<User>();
		if(type != "1" && !"1".equals(type)){
			if(type == "2" || "2".equals(type)){
				map.put("userId",user.getUserId().toString());
			}
			pageCount = userMapper.selectByTypeCount(map);
			userRiglist = userMapper.selectByType(map);
		}else {
			pageCount = userMapper.selectByTypeGCount(map);
			userRiglist = userMapper.selectByTypeG(map);
		}
		pageCount = pageCount%pageSize==0?pageCount/pageSize:(pageCount/pageSize)+1; 
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("userRiglist", userRiglist);
		System.out.println("获取的数据userRiglist:"+userRiglist);
	}

	@Override
	public void selectByTypeApp(HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		//每页显示多少条
		int pageSize = 10;
		//当前页
		int currentPage = 1;
		
		Map<String,Object> map = new HashMap<String, Object>();
		//当前页
		String dqpagestr = request.getParameter("currentPage");
		if(null!=dqpagestr && !"".equals(dqpagestr)){
			currentPage = Integer.parseInt(dqpagestr);
		}
		  
		if(user!=null){
			   String type = user.getType();
			   if(type != "1" && !"1".equals(type)){
				   String provinceId = user.getProvinceId();
				   String cityId = user.getCityId();
				   String areaId = user.getAreaId();
				   if(provinceId != null && provinceId != "0" && !"0".equals(provinceId)){
					   map.put("provinceId", provinceId);
				   }
				   if(cityId != null && cityId != "0" && !"0".equals(cityId)){
					   map.put("cityId", cityId);
				   }
				   if(areaId != null && areaId != "0" && !"0".equals(areaId)){
					   map.put("areaId", areaId);
				   }
			   }
		}
		map.put("start", ((currentPage-1)*pageSize));
		map.put("pageSize", pageSize);
		Integer pageCount = null;
		pageCount = userMapper.selectByTypeCountApp(map);
		List<User> userRiglist = userMapper.selectByTypeApp(map);
		pageCount = pageCount%pageSize==0?pageCount/pageSize:(pageCount/pageSize)+1; 
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("userRiglist", userRiglist);
	}

	@Override
	public String appInsert(User user,String type,String userId) throws Exception {
		user = ShiroMD5.md5jm(user);
		user.setFlog("1");
		String message = userMapper.insert(user) > 0 ? "true" : "false";
		if(message != "false" && !"false".equals(message)){
			//添加用户状态为管理员
			if(type == "2" || "2".equals(type)){
				User user3 = userMapper.findByUserName(user.getUserName());
				Integer userId3 = user3.getUserId();
				AdministratorAndUser adminUser = new AdministratorAndUser();
				adminUser.setUserId(userId3.toString());
				adminUser.setAdminId(userId);
				administratorAndUserMapper.insert(adminUser);
			}

		}
		return message;

		}

	@Override
	public Map<String, Object> appSelectByType(HttpServletRequest request) throws Exception {
		String userId = request.getParameter("userId");
		User user = userMapper.selectByUserId(userId);
		//每页显示多少条
		int pageSize = 10;
		//当前页
		int currentPage = 1;

		Map<String,Object> map = new HashMap<String, Object>();
		//当前页
		String dqpagestr = request.getParameter("currentPage");
		String pageSizes = request.getParameter("pageSize");
		if(null!=dqpagestr && !"".equals(dqpagestr)){
			currentPage = Integer.parseInt(dqpagestr);
		}
		if(null!=pageSizes && !"".equals(pageSizes)){
			pageSize = Integer.parseInt(pageSizes);
		}
		String type = user.getType();
		map.put("type", type);
		String provinceId = user.getProvinceId();
		String cityId = user.getCityId();
		String areaId = user.getAreaId();
		if(provinceId != null && !"".equals(provinceId) && provinceId != "0" && !"0".equals(provinceId)){
			map.put("provinceId", provinceId);
		}
		if(cityId != null && !"".equals(cityId) && cityId != "0" && !"0".equals(cityId)){
			map.put("cityId", cityId);
		}
		if(areaId != null && !"".equals(areaId) && areaId != "0" && !"0".equals(areaId)){
			map.put("areaId", areaId);
		}
		map.put("start", ((currentPage-1)*pageSize));
//		map.put("end", pageSize*currentPage);
		map.put("pageSize", pageSize);
		Integer pageCount = null;
		List<User> userRiglist = new ArrayList<User>();
		if(type != "1" && !"1".equals(type)){
			if(type == "2" || "2".equals(type)){
				map.put("userId",user.getUserId().toString());
			}
			pageCount = userMapper.selectByTypeCount(map);
			userRiglist = userMapper.selectByType(map);
		}else {
			pageCount = userMapper.selectByTypeGCount(map);
			userRiglist = userMapper.selectByTypeG(map);
		}
		pageCount = pageCount%pageSize==0?pageCount/pageSize:(pageCount/pageSize)+1;
		map.clear();
		map.put("currentPage", currentPage);
		map.put("pageCount", pageCount);
		map.put("userRiglist", userRiglist);
		return map;
	}

	@Override
	public Map<String, Object> appSelectUser(JSONObject jsonObject) throws Exception {
		//每页显示多少条
		int pageSize = 10;
		//当前页
		int currentPage = 1;

		Map<String,Object> map = new HashMap<String, Object>();
		//当前页

		String dqpagestr = jsonObject.getString("currentPage");
		String type = jsonObject.getString("type");
		String keyword = jsonObject.getString("keyword");
		String department = jsonObject.getString("department");
		String pageSizes = jsonObject.getString("pageSize");
		String userId = jsonObject.getString("userId");
		User user = userMapper.selectByUserId(userId);
		if(null!=dqpagestr && !"".equals(dqpagestr)){
			currentPage = Integer.parseInt(dqpagestr);
		}
		if(null!=pageSizes && !"".equals(pageSizes)){
			pageSize = Integer.parseInt(pageSizes);
		}
		map.put("start", ((currentPage-1)*pageSize));
//			map.put("end", pageSize*currentPage);
		map.put("pageSize", pageSize);
		if(type != null && !"".equals(type)){
			map.put("type", type);

		}

		if(department != null && !"".equals(department)){
			String department1 = URLDecoder.decode(department, "UTF-8");
			map.put("department", department1);

		}
		if(keyword != null && !"".equals(keyword)){
			keyword = URLDecoder.decode(keyword, "UTF-8");
			map.put("keyword", keyword);
		}
		String usertype = user.getType();
		map.put("usertype", usertype);
		Integer pageCount = null;
		List<User> userlist = new ArrayList<User>();
		if(usertype == "2" || "2".equals(usertype)){
			String provinceId = user.getProvinceId();
			map.put("userId",userId);
			if(provinceId != null && !"".equals(provinceId)){
				map.put("provinceId", provinceId);
				String areaId = user.getAreaId();
				String cityId = user.getCityId();
				if(areaId != null && !"".equals(areaId)){
					map.put("areaId", areaId);
				}
				if(cityId != null && !"".equals(cityId)){
					map.put("cityId", cityId);
				}
				pageCount = userMapper.selectUserCount(map);
				userlist = userMapper.selectUser(map);
			}else {
				pageCount = 0;
				userlist = null;
			}
		}else {
			pageCount = userMapper.selectUserCount(map);
			userlist = userMapper.selectUser(map);
		}

		pageCount = pageCount%pageSize==0?pageCount/pageSize:(pageCount/pageSize)+1;
		List<String> departmengtList = userMapper.selectDepartmentByUser();
		map.clear();

		map.put("currentPage", currentPage);
		map.put("departmengtList", departmengtList);
		map.put("pageCount", pageCount);
		map.put("userlist", userlist);
		return map;
	}

    @Override
    public String appUpdateByUserId(User user,String p) throws Exception {
            String message = "";
            if(p != null && !"".equals(p) && p != user.getPassword() && !p.equals(user.getPassword())){
                user = ShiroMD5.md5jm(user);
            }
            message = userMapper.updateByUserId(user)>0?"true":"false";
        return message;
    }

    @Override
    public String appUpdateByUserIdRegis(User user,String substationIds) throws Exception {
        RedisUtils redisUtils = new RedisUtils();
        String[] substationId = substationIds.split(",");
        String provinceId = user.getProvinceId();//省
        String cityId = user.getCityId();//市
        String areaId = user.getAreaId();//区/县
        String region = "";
        if(provinceId != null && !"".equals(provinceId)){
            String province = "";
            if(provinceId != "0" &&!"0".equals(provinceId)){
                province = regionMapper.findeByRegionId(provinceId);
            }else {
                province += "全省";
            }
            region += province;
            if(cityId != null && !"".equals(cityId)){
                String city = "";
                if(cityId != "0" &&!"0".equals(cityId)){
                    city = regionMapper.findeByRegionId(cityId);
                }else {
                    city += "全市";
                }
                region += city;
                if(areaId != null && !"".equals(areaId)){
                    String area = "";
                    if(areaId != "0" && !"0".equals(areaId)){
                        area = regionMapper.findeByRegionId(areaId);
                    }else {
                        area += "全区";
                    }
                    region += area;
                }
            }
        }
        user.setRegion(region);
        String message = "";
        message = userMapper.updateByUserId(user)>0?"true":"false";
        userRegionMapper.deletByUserId(user.getUserId().toString());
        //变电站添加
        if(message != "false" && !"false".equals(message)){
            if(substationId != null && substationId.length>0){
                List<UserRegion> UserRegionList = new LinkedList<UserRegion>();
                for (int i = 0; i < substationId.length; i++) {
                    UserRegion UserR = new UserRegion();
                    UserR.setUserId(user.getUserId().toString());
                    UserR.setSubstationId(substationId[i]);
                    UserRegionList.add(UserR);
                }
                message = userRegionMapper.insert(UserRegionList)>0?"true":"false";
                if(message == "true"){
                    String subsp = "subsp"+user.getUserId();
                    String substype = "subyhtp"+user.getUserId();
                    String potecolleS = redisUtils.getString(subsp);
                    if(potecolleS != null){
                        int potecolle = Integer.parseInt(potecolleS);
                        if(potecolle > 0){
                            for (int i = 0; i < potecolle; i++) {
                                int cds = i+1;
                                //删除变电站的缓存数据
                                String SubcurrentPage = "Subs"+cds+potecolle+user.getUserId();
                                redisUtils.delObject(SubcurrentPage);
                            }
                        }
                        redisUtils.delObject(subsp);
                    }
                    redisUtils.delObject(substype);
                    List<Substation> subslist = redisUtils.getList(subsp,Substation.class);
                    if(subslist!=null && subslist.size()>0){
                        redisUtils.delObject(subsp);
                    }
                }
            }

        }
        return message;
    }

}
