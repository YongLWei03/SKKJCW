package com.example.skkj.service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.skkj.RCDx.TemplateSms;
import com.example.skkj.comment.ConcurrentFenbu;
import com.example.skkj.entity.AlarmInformation;
import com.example.skkj.entity.Equipment;
import com.example.skkj.entity.User;
import com.example.skkj.mapper.AlarmHistoryMapper;
import com.example.skkj.mapper.AlarmInformationMapper;
import com.example.skkj.mapper.EquipmentMapper;
import com.example.skkj.service.AlarmInformationServer;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service("alarmInformationServer")
public class AlarmInformationServerImpl implements AlarmInformationServer{
	@Autowired
	private AlarmInformationMapper alarmInformationMapper;
	
	@Autowired
	private EquipmentMapper equipmentMapper;

	@Autowired
	private AlarmHistoryMapper alarmHistoryMapper;
	
	
	/**
	 * 添加报警信息
	 * @params deviceNumber 设备号
	 * @params serialNumber 序号
	 * @params information 报警信息
	 * @params type 报警状态s
	 * @params  order 是哪个相
	 * */
	@Override
	public  String insert(String deviceNumber,String serialNumber,String order,AlarmInformation alarmInformation) throws Exception {
		try {
//            Equipment equipment = ConcurrentFenbu.getConcurrent().redsiEqui(deviceNumber, serialNumber, equipmentMapper);
			Equipment equipment = new Equipment();
			equipment.setDeviceNumber(deviceNumber);
			equipment = equipmentMapper.selectByEquipmentxi(equipment);
			   if(equipment != null){
					alarmInformation.setEquipmentxi(equipment.getEquipmentxi());
				   alarmInformation.setEqId(equipment.getEqId().toString());
				   //变电站Id
					String subsId = equipment.getSubstationId();
					alarmInformation.setSubstationId(subsId);
					alarmInformation.setDeviceNumber(deviceNumber);
					alarmInformation.setSubstationName(equipment.getSubstationName());
					alarmInformation.setState("2");

			    //查看是否纯在当前问题是否解决
						int a = alarmInformationMapper.insert(alarmInformation);
							   if(a > 0){
							   	if(order != "0" && !"0".equals(order)){
									TemplateSms.sendSms(alarmInformation, equipment.getPhone(),equipment.getNumberDevices());
								}else {
							   		//查看当前在80-105之间的温度是否已经设为拒绝发送
									String sffs = ConcurrentFenbu.getConcurrent().shortMessage(deviceNumber, "1", "0","0");
									if(sffs == "2" || "2".equals(sffs) || sffs == "5" || "5".equals(sffs) || sffs == "6" || "6".equals(sffs)){
										 TemplateSms.sendSms(alarmInformation, equipment.getPhone(),equipment.getNumberDevices());
									}else {

									}
								}
								   return "true";
							   }else {
								   return "false";
							   }
			  }
			} catch (Exception e) {
				e.printStackTrace();
			}
		 return null;
	}
	
	
	/**
	 * 修改错误状态
	 * @params afId 异常Id
	 * @params eqId 开关柜Id
	 * @params type 当前异常状态
	 * */
	@Override
	public String updateByafId(HttpServletRequest request) throws Exception {
		String afId = request.getParameter("afId");
		String deviceNumber = request.getParameter("deviceNumber");
		AlarmInformation alarmin = new AlarmInformation();
		alarmin.setAfId(afId);
		alarmin.setState("1");
		int a = alarmInformationMapper.updateByafId(alarmin);
		if(a > 0){
			ConcurrentFenbu.getConcurrent().shortMessage(deviceNumber,"2","4","1");
		 }
		return a > 0?"true":"false";
	}

	/**
	 * 查看报警信息
	 * @params type 用户权限
	 * @params state 1.已处理 2.为处理
	 * @params userId 用户登录的Id
	 * @params type 1.异常 2.报警
	 * */
	@Override
	public List<AlarmInformation> selectAlarmInformation(HttpServletRequest request)
			throws Exception {
				User user = (User) request.getSession().getAttribute("user");
				//每页显示多少条
				int pageSize = 10;
				//当前页
				int currentPage = 1;
				//用户登录权限
				String userType = user.getType();
				
				Map<String,Object> map = new HashMap<String, Object>();
				String deviceNumber = request.getParameter("deviceNumber");
				String substationId = request.getParameter("substationId");
				String state = request.getParameter("state");
				String substationImage = request.getParameter("substationImage");
				String currentPageSb = request.getParameter("currentPageSb");
				//开始时间
				String startTime = request.getParameter("startTime");
				//结束时间
				String endTime = request.getParameter("endTime");
				//异常状态
				String type = request.getParameter("type");

				String keyword = request.getParameter("keyword");
				//当前页
				String dqpagestr = request.getParameter("currentPage");
				if(null!=dqpagestr && !"".equals(dqpagestr)){
						currentPage = Integer.parseInt(dqpagestr);
				}
				if(state != null && !"".equals(state)){
					map.put("state", state);
					request.setAttribute("state", state);
				}
				if(currentPageSb != null && !"".equals(currentPageSb)){ ;
					request.setAttribute("currentPageSb", currentPageSb);
				}
				if(deviceNumber != null && !"".equals(deviceNumber)){
					map.put("deviceNumber", deviceNumber);
					request.setAttribute("deviceNumber", deviceNumber);
				}
				if(substationId != null && !"".equals(substationId)){
					request.setAttribute("substationId", substationId);
				}
				if(substationImage != null && !"".equals(substationImage)){
					request.setAttribute("substationImage", substationImage);
				}
				if(userType != "1" && !"1".equals(userType)){
					map.put("type", 2);
					map.put("userId", user.getUserId());
				}else {
					map.put("type", type);
					request.setAttribute("type", type);
				}
				if(startTime != null && !"".equals(startTime)){
					startTime = URLDecoder.decode(startTime, "UTF-8");
					map.put("startTime", startTime);
					request.setAttribute("startTime", startTime);
				}
				if(endTime != null && !"".equals(endTime)){
					endTime = URLDecoder.decode(endTime, "UTF-8");
					map.put("endTime", endTime);
					request.setAttribute("endTime", endTime);
				}
				if(keyword != null && !"".equals(keyword)){
					keyword = URLDecoder.decode(keyword, "UTF-8");
					map.put("keyword",keyword);
					request.setAttribute("keyword",keyword);
				}
				map.put("start", ((currentPage-1)*pageSize));
				map.put("pageSize", pageSize);
				Integer pageCount = null;
				List<AlarmInformation> alarmInformationList = new LinkedList<AlarmInformation>();
				alarmInformationList = alarmInformationMapper.selectAlarmInformation(map);
				pageCount =  alarmInformationMapper.selectAlarmInformationCount(map);
				pageCount = pageCount%pageSize==0?pageCount/pageSize:(pageCount/pageSize)+1;
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("pageCount", pageCount);
				return alarmInformationList;
	}

	/**
	 * 批量删除对象信息
	 * @param afId 
	 * */
	@Override
	public String deleteByAfIdList(List<String> afId) throws Exception {

		List<AlarmInformation> alarmList = alarmInformationMapper.selectByAfId(afId);
		String message = alarmInformationMapper.deleteByAfIdList(afId) > 0 ? "true" : "false";
		if(message != "false" && !"false".equals(message)){
			alarmHistoryMapper.deleteByAlarmHistory(alarmList);
		}
		return "true";
	}
	
	/**
	 * 删除对象信息
	 * @params afId
	 * */
	@Override
	public String deleteByAfId(HttpServletRequest request) throws Exception {
		String afId = request.getParameter("afId");
		String deviceNumber = request.getParameter("deviceNumber");
		String time = request.getParameter("time");
		String message = alarmInformationMapper.deleteByAfId(afId) > 0 ? "true" : "false";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deviceNumber",deviceNumber);
		map.put("time",time);
		if(message != "false" && !"false".equals(message)){
			alarmHistoryMapper.deleteByTimeDNum(map);
		}
		return message;
	}

	/**
	 * 查看当前登录用户是否存在报警信息 
	 * @params userId 用户Id
	 * @params type 报警状态     2 报警
	 * @params state 是否处理  2 为处理
	 * */
	@Override
	public String selectByType(HttpServletRequest request)throws Exception {
		  User user = (User) request.getSession().getAttribute("user");
		    Integer userId = user.getUserId();
		return alarmInformationMapper.selectByType(userId.toString(), "2", "2") > 0?"true":"false";
	}


	/****
	 * 修改报警状态温度
	 * @param 
	 * @param
	 * @param
	 * @param
	 * */
	@Override
	public void UpdateByType(AlarmInformation alarmInformation)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

    @Override
    public Map<String,Object> appSelectAlarmInformation(JSONObject jsonObject) throws Exception {
        //每页显示多少条
        int pageSize = 10;
        //当前页
        int currentPage = 1;

        String userType = (String) jsonObject.get("userType");
        String userId = (String) jsonObject.get("userId");
        String state = (String) jsonObject.get("state");
        String deviceNumber = (String) jsonObject.get("deviceNumber");

        Map<String,Object> map = new HashMap<String, Object>();
        //开始时间
		String startTime = (String) jsonObject.get("startTime");
        //结束时间
		String endTime = (String) jsonObject.get("endTime");
        //异常状态
		String type = (String) jsonObject.get("type");
		String keyword = (String) jsonObject.get("keyword");
		String dqpagestr = (String) jsonObject.get("currentPage");
		String pageSizes = (String) jsonObject.get("pageSize");
		if(pageSizes != null && !"".equals(pageSizes)){
			pageSize = Integer.parseInt(pageSizes);
		}
        //当前页
        if(null!=dqpagestr && !"".equals(dqpagestr)){
            currentPage = Integer.parseInt(dqpagestr);
        }
        if(state != null && !"".equals(state)){
            map.put("state", state);
        }
        if(deviceNumber != null && !"".equals(deviceNumber)){
            map.put("deviceNumber", deviceNumber);
        }
        if(userType != "1" && !"1".equals(userType)){
            map.put("type", 2);
            map.put("userId", userId);
        }else {
            map.put("type", type);
        }
        if(startTime != null && !"".equals(startTime)){
            startTime = URLDecoder.decode(startTime, "UTF-8");
            map.put("startTime", startTime);
        }
        if(endTime != null && !"".equals(endTime)){
            endTime = URLDecoder.decode(endTime, "UTF-8");
            map.put("endTime", endTime);
        }
        if(keyword != null && !"".equals(keyword)){
            keyword = URLDecoder.decode(keyword, "UTF-8");
            map.put("keyword",keyword);
        }
        map.put("start", ((currentPage-1)*pageSize));
        map.put("pageSize", pageSize);
        Integer pageCount = null;
        List<AlarmInformation> alarmInformationList = new LinkedList<AlarmInformation>();
        alarmInformationList = alarmInformationMapper.selectAlarmInformation(map);
        pageCount =  alarmInformationMapper.selectAlarmInformationCount(map);
        map.put("alarmInformationCount",pageCount);
        pageCount = pageCount%pageSize==0?pageCount/pageSize:(pageCount/pageSize)+1;
        map.put("currentPage",currentPage);
        map.put("pageCount",pageCount);
        map.put("alarmInformationList",alarmInformationList);
        return map;
    }

}
