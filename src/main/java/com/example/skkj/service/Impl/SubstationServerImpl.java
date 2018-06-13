package com.example.skkj.service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.skkj.Interface.DxPlatform;
import com.example.skkj.comment.RedisUtils;
import com.example.skkj.entity.Substation;
import com.example.skkj.entity.User;
import com.example.skkj.entity.UserRegion;
import com.example.skkj.mapper.*;
import com.example.skkj.service.SubstationServer;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service("substationServer")
public class SubstationServerImpl implements SubstationServer{
	@Autowired
	private SubstationMapper substationMapper;
	
	@Autowired
	private UserRegionMapper userRegionMapper;
	
	@Autowired
	private RegionMapper regionMapper;
	
	@Autowired
	private EquipmentMapper equipmentMapper;
	
	@Autowired
	private AlarmInformationMapper alarmInformationMapper;

	@Autowired
	private CalculationWdMapper calculationWdMapper;

	@Autowired
	private  AlarmHistoryMapper alarmHistoryMapper;

	@Autowired
	private TemperatureMapper temperatureMapper;

	@Autowired
	private UserMapper userMapper;
	/**
	 * 根据所在地区查询出站点 进入授权页面
	 * */
	@Override
	public void selectSubstationByDq(HttpServletRequest request)throws Exception {
		 User user = (User) request.getSession().getAttribute("user");
		 String provinceId = user.getProvinceId();
		 String areaId = user.getAreaId();
		 String cityId = user.getCityId();
		 Map<String, Object> map = new HashMap<String,Object>();
		 if(provinceId != null && !"".equals(provinceId) && provinceId != "0" && !"0".equals(provinceId)){
			 map.put("provinceId", provinceId);
		 }
		 if(areaId != null && !"".equals(areaId) && areaId != "0" && !"0".equals(areaId)){
			 map.put("areaId", areaId);
		 }
		 if(cityId != null && !"".equals(cityId) && cityId != "0" && !"0".equals(cityId)){
			 map.put("cityId", cityId);
		 }
		 List<Substation> substationList = null;
		 if(user.getType() != "1" && !"1".equals(user.getType())){
			 map.put("userId", user.getUserId());
			 substationList = substationMapper.selectByqx(map);
		 }else {
			 substationList = substationMapper.selectSubstationByDq(map);
		}
		 request.setAttribute("substationpacList", substationList);
	}
	
	/**
	 * 根据所在地区查询出站点 AJAX
	 * */
	@Override
	public Map<String, Object> selectSubstationByDqMap(HttpServletRequest request)throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		String provinceId = request.getParameter("provinceId");
		String areaId = request.getParameter("areaId");
		String cityId = request.getParameter("cityId");
		String userId = request.getParameter("userId");
		Map<String, Object> map = new HashMap<String,Object>();
		if(provinceId != null && !"".equals(provinceId) && provinceId != "0" && !"0".equals(provinceId)){
			map.put("provinceId", provinceId);
		}
		if(areaId != null && !"".equals(areaId) && areaId != "0" && !"0".equals(areaId)){
			map.put("areaId", areaId);
		}
		if(cityId != null && !"".equals(cityId) && cityId != "0" && !"0".equals(cityId)){
			map.put("cityId", cityId);
		}
		List<String> userRegionsub = userRegionMapper.selectByUserId(userId);
		List<Substation> substationList = null;
		if(user.getType() == "1" || "1".equals(user.getType())){
			substationList = substationMapper.selectSubstationByDq(map);
		}else {
			map.put("userId", user.getUserId());
			substationList = substationMapper.selectByqx(map);
		}
		map.put("substationList", substationList);
		map.put("userRegionsub", userRegionsub);
		return map;
	}
	
	/**
	 * 变电站查看信息
	 * */
	@Override
	public void selectSubstationByType(HttpServletRequest request)
			throws Exception {
		   User user = (User) request.getSession().getAttribute("user");
		   String type = user.getType();
		//每页显示多少条
		int pageSize = 10;
		//当前页
		int currentPage = 1;
		
		//存到redis总个数名字
		String subsp = "subsp"+user.getUserId();
		Map<String,Object> map = new HashMap<String, Object>();
		//当前页
		String dqpagestr = request.getParameter("currentPage");
		if(null!=dqpagestr && !"".equals(dqpagestr)){
				currentPage = Integer.parseInt(dqpagestr);
		}
		map.put("start", ((currentPage-1)*pageSize));
		map.put("pageSize", pageSize);
		map.put("userId", user.getUserId());
		Integer pageCount = null;
		
		List<Substation> substationList = new LinkedList<Substation>();
		if(type != "1" && !"1".equals(type)){
				pageCount =  substationMapper.selectSubstationByTypeCount(map);
				pageCount = pageCount%pageSize==0?pageCount/pageSize:(pageCount/pageSize)+1; 

				substationList = substationMapper.selectSubstationByType(map);
		}else {

			if(pageCount == null){
				pageCount  = substationMapper.selectSubstationCount(map);
				pageCount = pageCount%pageSize==0?pageCount/pageSize:(pageCount/pageSize)+1;
			}

				substationList = substationMapper.selectSubstation(map);
		}
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("substationList", substationList);
	}
	
	/**
	 * 添加变电站
	 * */
	@Override
	public String insert(Substation substation,HttpServletRequest request) throws Exception {
		        User user = (User) request.getSession().getAttribute("user");
		        String provinceId = substation.getProvinceId();//省
				String cityId = substation.getCityId();//市
			    String areaId = substation.getAreaId();//区/县
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
			    substation.setSubstationArea(region);
		      int c = substationMapper.insert(substation);
		      if(c > 0){
                  String subId = substationMapper.selectBySubstationName(substation.getSubstationName());
                  if(user.getType() == "2" || "2".equals(user.getType())){
					  UserRegion ur = new UserRegion();
					  ur.setSubstationId(subId);
					  ur.setUserId(user.getUserId().toString());
					  List<UserRegion> urList = new LinkedList<UserRegion>();
					  urList.add(ur);
					  userRegionMapper.insert(urList);
				  }
                  //查看是否存在这些设备
                  int equiCount = equipmentMapper.selectByAddRess(substation.getSubstationPY());
                  if(equiCount > 0){
                      equipmentMapper.updateByAddRess(substation.getSubstationPY(),subId);
                  }
                  return  "true";
              }

		     return "false";
	}
	/**
	 * 查询变电站信息  单个
	 * 
	 * */
	@Override
	public Substation selectBySubstationId(String substationId)
			throws Exception {
		return substationMapper.selectBySubstationId(substationId);
	}

	/**
	 * 修改变电站信息
	 * */
	@Override
	public String updateBySubstationId(Substation substation,HttpServletRequest request) throws Exception {
		    User user = (User) request.getSession().getAttribute("user");
		    String provinceId = substation.getProvinceId();//省
			String cityId = substation.getCityId();//市
		    String areaId = substation.getAreaId();//区/县
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
		    substation.setSubstationArea(region);
		    int c = substationMapper.updateBySubstationId(substation);
		    //删除redis的缓存数据
		      if(c > 0){
				  RedisUtils redisUtils = new RedisUtils();
				  String potecolle = redisUtils.getString("subspt");

				  if(potecolle != null){
					  Integer potecolles = Integer.parseInt(potecolle);
					  for (int i = 0; i < potecolles; i++) {
						  int cds = i+1;
						  //删除变电站的缓存数据
						  String Subs = "Subst"+cds+potecolle+user.getUserId();
						  redisUtils.delObject(Subs);
					  }
					  redisUtils.delObject("subspt");
				  }
				  //判断当前修改的变电站对应的用户是否存在
				  List<String> userId = userRegionMapper.selectBysubsId(substation.getSubstationId());
				  for (int i = 0; i < userId.size(); i++) {
					  String substype = "subyhtp"+userId.get(i);
					  List<Substation> substationList = redisUtils.getList(substype,Substation.class);
					  if(substationList != null && substationList.size()>0){
						  redisUtils.delObject(substype);
					  }
				}
				  String substype = "subyhtp"+user.getUserId();
				  List<Substation> substationList =  redisUtils.getList(substype,Substation.class);
				  if(substationList != null && substationList.size()>0){
					  redisUtils.delObject(substype);
				  }
				  return "true";
		     }
		return "false";
	}

	/**
	 * 地图查询使用
	 * */
	@Override
	public Map<String, Object> findByType(HttpServletRequest request) throws Exception {
		    User user = (User) request.getSession().getAttribute("user");
		      String type = user.getType();
		    Map<String, Object> map = new HashMap<String,Object>();
		    String substype = "subyhtp"+user.getUserId();
			//存到redis总个数名字
		    List<Substation> sublist = new ArrayList<Substation>();

		    	 if(type != "1" && !"1".equals(type)){
		    		 sublist = substationMapper.findByType(user.getUserId().toString());
		    	 }else {
		    		 sublist = substationMapper.findByTypeCj();
				}

		     for (int i = 0; i < sublist.size(); i++) {
				   	Substation sub = sublist.get(i);
				    String subId = sub.getSubstationId();
				    List<String> count = sub.getCount();
				    int count1 = alarmInformationMapper.selectTypeCountBysubId(subId, "1");//异常
				    int count2 = alarmInformationMapper.selectTypeCountBysubId(subId, "2");//警告
				    //当前变电站有多少开关柜
				    int equiCount = equipmentMapper.selectBysubID(subId);
				    int count3 = equiCount-count1-count2;
				    
				    if(count1 > 0 ){
				    	count.add(String.valueOf(count1));
				    }else {
				    	count.add("0");
					}
				    if(count2 > 0 ){
				    	count.add(String.valueOf(count2));
				    }else {
				    	count.add("0");
				    }
				    if(count3 > 0 ){
				    	count.add(String.valueOf(count3));
				    }else {
				    	count.add("0");
				    }
				    sub.setCount(count);
		     }
		     map.put("sublist", sublist);
			return map;
	}
	
	//删除变电站
	@Override
	public String deleteSubstation(String substationId,HttpServletRequest request) throws Exception {
		   int c = substationMapper.deleteSubstation(substationId);
		   User user = (User) request.getSession().getAttribute("user");
		int b = 0;
		   if(c > 0){
			   List<String> dinum = equipmentMapper.findByDeviceNumber(substationId);
			   for (int i = 0; i < dinum.size(); i++) {
                   String dmund = dinum.get(i);
				   String message = DxPlatform.deleteSheBei(dmund);
				   if(message != "false" && !"false".equals(message)){
					   equipmentMapper.deleteBysubID(substationId);
					   calculationWdMapper.deleteByDeviceNumber(dmund);
					   alarmInformationMapper.deleteBySubstationId(substationId);
					   alarmHistoryMapper.deletByDevierNumber(dmund);
					   temperatureMapper.deleteBydeviceNumber(dmund);
				   }
			   }
			   //通过需要删除的substationId开查询出拥有这个变电站的用户ID
			   List<String> userId = userRegionMapper.selectBysubsId(substationId);
			   userRegionMapper.deletBysubsId(substationId);
				   RedisUtils redisutils = new RedisUtils();
				   if(userId != null && userId.size()>0){
					   for (int i = 0; i < userId.size(); i++) {
						   String userIdty = userId.get(i);
						   String substype = "subyhtp"+userIdty;//地图查询使用
						   String subsp = "subsp"+userIdty;//这个是非超级管理员的查询长度的名称
						   String userIdTyp = redisutils.getString(subsp);
						   Integer userIdType = null;
						   if(userIdTyp != null && !"".equals(userIdTyp)){
							   userIdType = Integer.parseInt(userIdTyp);//这个是非超级管理员的查询长度
						   }
						    
						   if(userIdType != null && userIdType > 0){
							   for (int j = 0; j < userIdType; j++) {
								   int ptcd = j+1;
								   String Subs = "Subs"+ptcd+userIdType+userIdty;//非超级管理员
								   //删除redis缓存数据userId
								   redisutils.delObject(Subs);
							   }
						   }
						   redisutils.delObject(subsp);
						   redisutils.delObject(substype);
					   }
				   }

			   return "true";
		   }
		return "false";
	}

	@Override
	public String selectBySubstationPY(String substationPY) throws Exception {
		return substationMapper.selectBySubstationPY(substationPY);
	}

	@Override
	public List<Substation> findByTypeCj() throws Exception {
		return substationMapper.findByTypeCj();
	}

	@Override
	public Substation findByArer(HttpServletRequest request) throws Exception {
//		User user = (User)request.getSession().getAttribute("substationArea");
		String substationArea = request.getParameter("substationArea");

		return substationMapper.findByArer(substationArea);
	}

	@Override
	public Map<String, Object> APPselectSubstationByType(HttpServletRequest request) throws Exception {
		String userId = request.getParameter("userId");
		String type = request.getParameter("type");
		//每页显示多少条
		int pageSize = 10;
		//当前页
		int currentPage = 1;

		//存到redis总个数名字
		String subsp = "subsp"+userId;
		Map<String,Object> map = new HashMap<String, Object>();
		//当前页
		String dqpagestr = request.getParameter("currentPage");
		if(null!=dqpagestr && !"".equals(dqpagestr)){
			currentPage = Integer.parseInt(dqpagestr);
		}
		String pageSizes = request.getParameter("pageSize");
		if(pageSizes != null && !"".equals(pageSizes)){
			pageSize = Integer.parseInt(pageSizes);
		}
		map.put("start", ((currentPage-1)*pageSize));
		map.put("pageSize", pageSize);
		map.put("userId", userId);
		Integer pageCount = null;

		List<Substation> substationList = new LinkedList<Substation>();
		if(type != "1" && !"1".equals(type)){
			pageCount =  substationMapper.selectSubstationByTypeCount(map);
			pageCount = pageCount%pageSize==0?pageCount/pageSize:(pageCount/pageSize)+1;

			substationList = substationMapper.selectSubstationByType(map);
		}else {

			if(pageCount == null){
				pageCount  = substationMapper.selectSubstationCount(map);
				pageCount = pageCount%pageSize==0?pageCount/pageSize:(pageCount/pageSize)+1;
			}

			substationList = substationMapper.selectSubstation(map);
		}
		map.clear();
		map.put("currentPage", currentPage);
		map.put("pageCount", pageCount);
		map.put("substationList", substationList);
		return map;
	}

	@Override
	public String appUpdateBySubstationId(Substation substation, String userId) throws Exception {
		String provinceId = substation.getProvinceId();//省
		String cityId = substation.getCityId();//市
		String areaId = substation.getAreaId();//区/县
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
		substation.setSubstationArea(region);
		int c = substationMapper.updateBySubstationId(substation);
		//删除redis的缓存数据
		if(c > 0){
			RedisUtils redisUtils = new RedisUtils();
			String potecolle = redisUtils.getString("subspt");

			if(potecolle != null){
				Integer potecolles = Integer.parseInt(potecolle);
				for (int i = 0; i < potecolles; i++) {
					int cds = i+1;
					//删除变电站的缓存数据
					String Subs = "Subst"+cds+potecolle+userId;
					redisUtils.delObject(Subs);
				}
				redisUtils.delObject("subspt");
			}
			//判断当前修改的变电站对应的用户是否存在
			List<String> userIds = userRegionMapper.selectBysubsId(substation.getSubstationId());
			for (int i = 0; i < userIds.size(); i++) {
				String substype = "subyhtp"+userIds.get(i);
				List<Substation> substationList = redisUtils.getList(substype,Substation.class);
				if(substationList != null && substationList.size()>0){
					redisUtils.delObject(substype);
				}
			}
			String substype = "subyhtp"+userId;
			List<Substation> substationList =  redisUtils.getList(substype,Substation.class);
			if(substationList != null && substationList.size()>0){
				redisUtils.delObject(substype);
			}
			return "true";
		}
		return "false";
	}

	@Override
	public String appInsert(Substation substation, String userId) throws Exception {
		User user = userMapper.selectByUserId(userId);
		String provinceId = substation.getProvinceId();//省
		String cityId = substation.getCityId();//市
		String areaId = substation.getAreaId();//区/县
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
		substation.setSubstationArea(region);
		int c = substationMapper.insert(substation);
		if(c > 0){
			String subId = substationMapper.selectBySubstationName(substation.getSubstationName());
			if(user.getType() == "2" || "2".equals(user.getType())){
				UserRegion ur = new UserRegion();
				ur.setSubstationId(subId);
				ur.setUserId(user.getUserId().toString());
				List<UserRegion> urList = new LinkedList<UserRegion>();
				urList.add(ur);
				userRegionMapper.insert(urList);
			}
			//查看是否存在这些设备
			int equiCount = equipmentMapper.selectByAddRess(substation.getSubstationPY());
			if(equiCount > 0){
				equipmentMapper.updateByAddRess(substation.getSubstationPY(),subId);
			}
			return  "true";
		}

		return "false";
	}

	@Override
	public List<Substation> appSelectSubstationByDq(User user) throws Exception {
        String provinceId = user.getProvinceId();
        String areaId = user.getAreaId();
        String cityId = user.getCityId();
        Map<String, Object> map = new HashMap<String,Object>();
        if(provinceId != null && !"".equals(provinceId) && provinceId != "0" && !"0".equals(provinceId)){
            map.put("provinceId", provinceId);
        }
        if(areaId != null && !"".equals(areaId) && areaId != "0" && !"0".equals(areaId)){
            map.put("areaId", areaId);
        }
        if(cityId != null && !"".equals(cityId) && cityId != "0" && !"0".equals(cityId)){
            map.put("cityId", cityId);
        }
        List<Substation> substationList = null;
        if(user.getType() != "1" && !"1".equals(user.getType())){
            map.put("userId", user.getUserId());
            substationList = substationMapper.selectByqx(map);
        }else {
            substationList = substationMapper.selectSubstationByDq(map);
        }
		return substationList;
	}

	@Override
	public Map<String, Object> appSelectSubstationByDqMap(JSONObject jsonObject) throws Exception {
		String provinceId = jsonObject.getString("provinceId");
		String userIdad = jsonObject.getString("userIdad");
		String areaId = jsonObject.getString("areaId");
		String cityId = jsonObject.getString("cityId");
		String userId = jsonObject.getString("userId");
		User user = userMapper.selectByUserId(userIdad);
		Map<String, Object> map = new HashMap<String,Object>();
		if(provinceId != null && !"".equals(provinceId) && provinceId != "0" && !"0".equals(provinceId)){
			map.put("provinceId", provinceId);
		}
		if(areaId != null && !"".equals(areaId) && areaId != "0" && !"0".equals(areaId)){
			map.put("areaId", areaId);
		}
		if(cityId != null && !"".equals(cityId) && cityId != "0" && !"0".equals(cityId)){
			map.put("cityId", cityId);
		}
		List<String> userRegionsub = userRegionMapper.selectByUserId(userId);
		List<Substation> substationList = null;
		if(user.getType() == "1" || "1".equals(user.getType())){
			substationList = substationMapper.selectSubstationByDq(map);
		}else {
			map.put("userId", user.getUserId());
			substationList = substationMapper.selectByqx(map);
		}
		map.clear();
		map.put("substationList", substationList);
		map.put("userRegionsub", userRegionsub);
		return map;
	}



	@Override
	public String appDeleteSubstation(String substationId) throws Exception {
		int c = substationMapper.deleteSubstation(substationId);
		int b = 0;
		if(c > 0){
			List<String> dinum = equipmentMapper.findByDeviceNumber(substationId);
			for (int i = 0; i < dinum.size(); i++) {
				String dmund = dinum.get(i);
				String message = DxPlatform.deleteSheBei(dmund);
				if(message != "false" && !"false".equals(message)){
					equipmentMapper.deleteBysubID(substationId);
					calculationWdMapper.deleteByDeviceNumber(dmund);
					alarmInformationMapper.deleteBySubstationId(substationId);
					alarmHistoryMapper.deletByDevierNumber(dmund);
					temperatureMapper.deleteBydeviceNumber(dmund);
				}
			}
			//通过需要删除的substationId开查询出拥有这个变电站的用户ID
			List<String> userId = userRegionMapper.selectBysubsId(substationId);
			int a = userRegionMapper.deletBysubsId(substationId);
			if(a > 0){
				RedisUtils redisutils = new RedisUtils();
				if(userId != null && userId.size()>0){
					for (int i = 0; i < userId.size(); i++) {
						String userIdty = userId.get(i);
						String substype = "subyhtp"+userIdty;//地图查询使用
						String subsp = "subsp"+userIdty;//这个是非超级管理员的查询长度的名称
						String userIdTyp = redisutils.getString(subsp);
						Integer userIdType = null;
						if(userIdTyp != null && !"".equals(userIdTyp)){
							userIdType = Integer.parseInt(userIdTyp);//这个是非超级管理员的查询长度
						}

						if(userIdType != null && userIdType > 0){
							for (int j = 0; j < userIdType; j++) {
								int ptcd = j+1;
								String Subs = "Subs"+ptcd+userIdType+userIdty;//非超级管理员
								//删除redis缓存数据userId
								redisutils.delObject(Subs);
							}
						}
						redisutils.delObject(subsp);
						redisutils.delObject(substype);
					}
				}
			}
			return "true";
		}
		return "false";
	}

	@Override
	public List<Substation> selectByAll() throws Exception {
		List<Substation> subsList = substationMapper.selectByAll();
		return subsList;
	}

}
