package com.example.skkj.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.skkj.Interface.DxPlatform;
import com.example.skkj.comment.SkUtile;
import com.example.skkj.entity.*;
import com.example.skkj.mapper.*;
import com.example.skkj.service.EquipmentServer;
import com.example.skkj.test.ReadExcel;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.*;

@Service("equipmentServer")
public class EquipmentServerImpl implements EquipmentServer{

	@Autowired
	private EquipmentMapper equipmentMapper;
	
	@Autowired
	private SubstationMapper substationMapper;
	
	@Autowired
	private AlarmInformationMapper alarmInformationMapper;

	@Autowired
	private EquipmentTypeMapper equipmentTypeMapper;

	@Autowired
	private CommandInformationMapper commandInformationMapper;

	@Autowired
	private CalculationWdMapper calculationWdMapper;
	
	//添加变电设备
	@Override
	public String insetEquipment(Equipment equipment,EquipmentServer equipmentServer) throws Exception {
		String message = "";
		EquipmentType equitype = equipmentTypeMapper.selectByEqtId(equipment.getEptId());
		String deviceId = DxPlatform.addSheBei(equipment, equitype);
		if(deviceId != null && !"".equals(deviceId) && deviceId !="bdsb" && !"bdsb".equals(deviceId)){
//		 message = ConcurrentFenbu.getConcurrent().selectByDeviceId(deviceId, equipmentServer,equipment);
			equipment.setDeviceNumber(deviceId);
			equipmentMapper.insetEquipment(equipment);
			 CalculationWd calcu = new CalculationWd();
			 calcu.setInA("0");
			 calcu.setInB("0");
			 calcu.setInC("0");
			 calcu.setOutA("0");
			 calcu.setOutB("0");
			 calcu.setOutC("0");
			 calcu.setInAcs("0");
			 calcu.setInBcs("0");
			 calcu.setInCcs("0");
			 calcu.setOutAcs("0");
			 calcu.setOutBcs("0");
			 calcu.setOutCcs("0");
			 calcu.setDeviceNumber(deviceId);
			 calculationWdMapper.insert(calcu);
			return "true";
		}else if (deviceId =="bdsb" || "bdsb".equals(deviceId)){
			return deviceId;
		}
		return "false";
	}

    @Override
    public String insetEqui(Equipment equipment) throws Exception {
        return equipmentMapper.insetEquipment(equipment)>0?"true":"false";
    }

    //查询变电站设备
	@Override
	public List<Equipment> selectEquipment(HttpServletRequest request)throws Exception {
		//每页显示多少条
		int pageSize = 10;
		//当前页
		int currentPage = 1;
		Map<String,Object> map = new HashMap<String, Object>();
		//当前页
		String dqpagestr = request.getParameter("currentPage");
		String currentPages = request.getParameter("currentPages");
		//开关柜当前状态
		String type = request.getParameter("type");
		String code = request.getParameter("code");
		String substationId = request.getParameter("substationId");
		if(null!=dqpagestr && !"".equals(dqpagestr)){
				currentPage = Integer.parseInt(dqpagestr);
		}
		if(currentPages != null && !"".equals(currentPages)){
			currentPage = Integer.parseInt(currentPages);
		}
		if(null!=code && !"".equals(code)){
			map.put("code", code);
			request.setAttribute("code", code);
		}
		if(null!=substationId && !"".equals(substationId)){
			request.setAttribute("substationId", substationId);
			map.put("substationId", substationId);
		}
		if(type != null && !"".equals(type)){
			if(type != "0" && !"0".equals(type)){
				map.put("type", type);
			}
			request.setAttribute("type", type);
		}
		map.put("start", ((currentPage-1)*pageSize));
//		map.put("end", pageSize*currentPage);
		map.put("pageSize", pageSize);
		map.put("state", "2");
		Integer pageCount = null;
		List<Equipment> equiList = new ArrayList<Equipment>();
//		if(type != null && !"".equals(type) && type != "0" && !"0".equals(type)){
//			equiList = equipmentMapper.selectEquipmentcc(map);
//		}else {
			equiList = equipmentMapper.selectEquipment(map);
//		}
		List<Equipment> equiListS = new ArrayList<Equipment>();
		int c = 0;
		if(currentPage > 1){
			c= (currentPage * pageSize)-pageSize;
		}

		//查看是否有异常

//			for (int i = 0; i < equiList.size(); i++) {
//				Equipment equi = equiList.get(i);
//				int bj = alarmInformationMapper.selectByYC(equi.getEqId().toString(), "2","2");
//				if(bj > 0){
//					equi.setType("2");
//					if(type != null && !"".equals(type)){
//						if(type == "2" || "2".equals(type)){
//							equiListS.add(equi);
//						}
//					}
//				}else {
//					int yc = alarmInformationMapper.selectByYC(equi.getEqId().toString(), "1","2");
//					if(yc > 0){
//						equi.setType("3");
//						if(type != null && !"".equals(type)){
//							if(type == "3" || "3".equals(type)){
//								equiListS.add(equi);
//							}
//						}
//					}else {
//						equi.setType("1");
//						if(type != null && !"".equals(type)){
//							if(type == "1" || "1".equals(type)){
//								equiListS.add(equi);
//							}
//						}
//					}
//				}
//				if(type == null || "".equals(type) || type == "0" || "0".equals(type)){
//					equiListS.add(equi);
//				}
//			}


//		if(type != null && !"".equals(type)){
//			if(type != "0" && !"0".equals(type)){
//				pageCount = equiListS.size();
//				for (int i = c; i <equiListS.size() ; i++) {
//					equiListSs.add(equiListS.get(i));
//					if(equiListSs.size()==pageSize){
//						break;
//					}
//				}
//			}else {
//				pageCount =  equipmentMapper.selectEquipmentCount(map);
//			}
//		}else {
			pageCount =  equipmentMapper.selectEquipmentCount(map);
//		}
		pageCount = pageCount%pageSize==0?pageCount/pageSize:(pageCount/pageSize)+1;
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageCount", pageCount);
//		if(type != null && !"".equals(type) && type != "0" && !"0".equals(type)){
//			return equiListSs;
//		}else {
			return equiList;
//		}

	}

	//删除变电站设备
	@Override
	public String deletByeqId(String eqId,String deviceId) throws Exception {
		   String message = DxPlatform.deleteSheBei(deviceId);
		   if(message != "false" && !"false".equals(message)){
			   //查询对象
			       int a = equipmentMapper.deletByeqId(eqId);
					calculationWdMapper.deleteByDeviceNumber(deviceId);
				   alarmInformationMapper.deleteByEqId(eqId);
				   commandInformationMapper.deletByDeviceNumber(deviceId);
				   return "true";
		   }else {
		     	return "false";
		   }
	}

	//修改变电站
	@Override
	public String updateByeqId(Equipment equipment,HttpServletRequest request) throws Exception {
		String message = "";
		String deviceNames = request.getParameter("deviceNames");
		String eptIds = request.getParameter("eptIds");
		String deviceNumber = request.getParameter("deviceNumber");
		EquipmentType equitype = null;
		if(deviceNames != equipment.getDeviceName() && !deviceNames.equals(equipment.getDeviceName())){
			if(eptIds != equipment.getEptId() && !eptIds.equals(equipment.getEptId())){
				equitype = equipmentTypeMapper.selectByEqtId(equipment.getEptId());
			}
			message =  DxPlatform.updateShebei(deviceNumber,equitype,equipment.getDeviceName());
		}else if(eptIds != equipment.getEptId() && !eptIds.equals(equipment.getEptId())){
			equitype = equipmentTypeMapper.selectByEqtId(equipment.getEptId());
			message = DxPlatform.updateShebei(deviceNumber,equitype,deviceNames);
		}
		if(message != "false" || !"".equals(message)){
			message = equipmentMapper.updateByeqId(equipment)>0?"true":"false";
		}
		return message;
	}
	
	//查询出对象信息
	@Override
	public Equipment findByeqId(String eqId) throws Exception {
		return equipmentMapper.findByeqId(eqId);
	}

	//查询当前变电站状态数量
	@Override
	public List<Equipment> seleTypeCountBysubsId(String substationId)throws Exception {
		return equipmentMapper.seleTypeCountBysubsId(substationId);
	}

	@Override
	public String insetEquipmentYPT(Equipment equipment) throws Exception {
		return  equipmentMapper.insetEquipment(equipment) > 0?"true":"false";
	}

	//删除平台设备后删除本地
    @Override
    public void deletByDeviceNumber(String deviceNumber) throws Exception {
	    equipmentMapper.deletByDeviceNumber(deviceNumber);
    }

    @Override
    public String updateByDeviceNumber(Equipment equipment) throws Exception {
       return equipmentMapper.updateByDeviceNumber(equipment)>0?"true":"false";
    }

	@Override
	public int selectByDeviceNumber(String DeviceNumber) throws Exception {
		return equipmentMapper.selectByDeviceNumber(DeviceNumber);
	}



	@Override
	public String updateByMust(HttpServletRequest request) throws Exception {
		String mute = request.getParameter("mute");
		String deviceId = request.getParameter("deviceNumber");
		if(mute != null && !"".equals(mute)){
			String message = DxPlatform.updateByMust(mute, deviceId);
				if (message != "false" && !"false".equals(message)){
					Equipment equi = new Equipment();
					equi.setDeviceNumber(deviceId);
					equi.setMute(mute);
					equipmentMapper.updateByDeviceNumber(equi);
					return "true";
				}
		}
		return "false";
	}

	@Override
	public List<String> selectBySubstationId(String substationId) throws Exception {
		return equipmentMapper.selectBySubstationId(substationId);
	}

	@Override
	public List<Equipment> selectDeviceNumberByPhone(String Phone) throws Exception {
		return equipmentMapper.selectDeviceNumberByPhone(Phone);
	}

	@Override
	public List<Equipment> selectAll() throws Exception {
		return equipmentMapper.selectAll();
	}

	@Override
	public Equipment findByDeviceNumberEqui(String deviceNumber) throws Exception {
		return equipmentMapper.findByDeviceNumberEqui(deviceNumber);
	}

	@Override
	public Map<String,Object> Import(InputStream is, EquipmentServer equipmentServer, String subId) throws Exception {
		List<String> linke = new LinkedList<String>();
		List<String> czlinke = new LinkedList<String>();
		List<String> messags = new LinkedList<String>();
		List<Equipment> equiList = new LinkedList<Equipment>();
		ReadExcel redad = new ReadExcel();
		Map<String, Object> map = new HashMap<String, Object>();
		equiList = redad.loadScoreInfo(is);
		if(equiList != null && !"".equals(equiList) && equiList.size()>0){
			for (int i = 0; i <equiList.size() ; i++) {
				Equipment equip = equiList.get(i);
				String messag = insetEquipment(equip, equipmentServer);
				messags.add(messag);
				if(messag != "true" && !"".equals("true") && messag != "bdsb" && !"bdsb".equals(messag)){
					linke.add(equip.getEquipmentxi());
				}else if(messag == "bdsb" || messag.equals("bdsb")){
					czlinke.add(equip.getEquipmentxi());
				}
			}
		}
		map.put("linke",linke);
		map.put("czlinke",czlinke);
		return map;
	}

	@Override
	public List<Equipment> selectByEqui(String substationId) throws Exception {
		return equipmentMapper.selectByEqui(substationId);
	}

	@Override
	public Map<String, Object> appSelectEquipment(HttpServletRequest request) throws Exception {
		//每页显示多少条
		int pageSize = 10;
		//当前页
		int currentPage = 1;
		Map<String,Object> map = new HashMap<String, Object>();
		//当前页
		String dqpagestr = request.getParameter("currentPage");

		//开关柜当前状态
		String type = request.getParameter("type");
		String substationId = request.getParameter("substationId");
		if(null!=dqpagestr && !"".equals(dqpagestr)){
			currentPage = Integer.parseInt(dqpagestr);
		}
		String pageSizes = request.getParameter("pageSize");
		if(pageSizes != null && !"".equals(pageSizes)){
			pageSize = Integer.parseInt(pageSizes);
		}
		if(null!=substationId && !"".equals(substationId)){
			request.setAttribute("substationId", substationId);
			map.put("substationId", substationId);
		}
		if(type != null && !"".equals(type)){
			if(type != "0" && !"0".equals(type)){
				map.put("type", type);
			}
		}
		map.put("start", ((currentPage-1)*pageSize));
		map.put("pageSize", pageSize);
		map.put("state", "2");
		Integer pageCount = null;
		List<Equipment> equiList = new LinkedList<Equipment>();
		equiList = equipmentMapper.selectEquipment(map);
		if(type != null && !"".equals(type) && type != "0" && !"0".equals(type)){
			equiList = equipmentMapper.selectEquipmentcc(map);
		}else {
			equiList = equipmentMapper.selectEquipment(map);
		}
		List<Equipment> equiListS = new ArrayList<Equipment>();
		List<Equipment> equiListSs = new ArrayList<Equipment>();
		int c = 0;
		if(currentPage > 1){
			c= (currentPage * pageSize)-pageSize;
		}
		//查看是否有异常
		for (int i = 0; i < equiList.size(); i++) {
			Equipment equi = equiList.get(i);
			int bj = alarmInformationMapper.selectByYC(equi.getEqId().toString(), "2","2");
			if(bj > 0){
				equi.setType("2");
				if(type != null && !"".equals(type)){
					if(type == "2" || "2".equals(type)){
						equiListS.add(equi);
					}
				}
			}else {
				int yc = alarmInformationMapper.selectByYC(equi.getEqId().toString(), "1","2");
				if(yc > 0){
					equi.setType("3");
					if(type != null && !"".equals(type)){
						if(type == "3" || "3".equals(type)){
							equiListS.add(equi);
						}
					}
				}else {
					equi.setType("1");
					if(type != null && !"".equals(type)){
						if(type == "1" || "1".equals(type)){
							equiListS.add(equi);
						}
					}
				}
			}
			if(type == null || "".equals(type) || type == "0" || "0".equals(type)){
				equiListS.add(equi);
			}

		}
		if(type != null && !"".equals(type)){
			if(type != "0" && !"0".equals(type)){
				pageCount = equiListS.size();
				for (int i = c; i <equiListS.size() ; i++) {
					equiListSs.add(equiListS.get(i));
					if(equiListSs.size()==pageSize){
						break;
					}
				}
			}else {
				pageCount =  equipmentMapper.selectEquipmentCount(map);
			}
		}else {
			pageCount =  equipmentMapper.selectEquipmentCount(map);
		}
		map.put("equiCount",pageCount);
		pageCount = pageCount%pageSize==0?pageCount/pageSize:(pageCount/pageSize)+1;

			map.put("currentPage",currentPage);
			map.put("pageCount",pageCount);
		if(type != null && !"".equals(type) && type != "0" && !"0".equals(type)){
			map.put("equiListS",equiListSs);
		}else {
			map.put("equiListS",equiListS);
		}
		return map;
	}

	@Override
	public String appUpdateByeqId(Equipment equipment, String deviceNames, String deviceNumber, String eptIds) throws Exception {
		String message = "";
		EquipmentType equitype = null;
		if(deviceNames != equipment.getDeviceName() && !deviceNames.equals(equipment.getDeviceName())){
			if(eptIds != equipment.getEptId() && !eptIds.equals(equipment.getEptId())){
				equitype = equipmentTypeMapper.selectByEqtId(equipment.getEptId());
			}
			message =  DxPlatform.updateShebei(deviceNumber,equitype,equipment.getDeviceName());
		}else if(eptIds != equipment.getEptId() && !eptIds.equals(equipment.getEptId())){
			equitype = equipmentTypeMapper.selectByEqtId(equipment.getEptId());
			message = DxPlatform.updateShebei(deviceNumber,equitype,deviceNames);
		}
		if(message != "false" && !"false".equals(message)){
			message = equipmentMapper.updateByeqId(equipment)>0?"true":"false";
		}
		return message;
	}

	@Override
	public void selectByUserType(HttpServletRequest request) throws Exception {
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
		map.put("start", ((currentPage-1)*pageSize));
//		map.put("end", pageSize*currentPage);
		map.put("pageSize", pageSize);
		map.put("state", "2");
		Integer pageCount = null;
		List<Equipment> equiList = new ArrayList<Equipment>();
		List<Substation> subsList = new ArrayList<Substation>();
	    subsList = substationMapper.selectByAll();
		equiList = equipmentMapper.selectByUserType(map);
		pageCount = equipmentMapper.selectByUserTypeCount();
		pageCount = pageCount%pageSize==0?pageCount/pageSize:(pageCount/pageSize)+1;
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("equilist",equiList);
		request.setAttribute("subsList",subsList);
	}

	@Override
	public Map<String,Object> skImport(InputStream is) throws Exception {
		SkUtile skUtile = new SkUtile();
		ReadExcel redad = new ReadExcel();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Equipment> equiList = redad.loadScoreInfo(is);
		 map = skUtile.skImportAll(equiList,equipmentTypeMapper);
		 //添加失败的产品
//		List<Equipment> equiListsbList = (List<Equipment>) map.get("equiListsb");
		//添加成功
		List<CalculationWd> calculationWdList = (List<CalculationWd>) map.get("calculationWdsList");
		List<Equipment> equiLists = (List<Equipment>) map.get("equiLists");
		String intsbtj = equipmentMapper.insertAll(equiLists)>0?"true":"false";
		if(intsbtj != "false" && !"false".equals(intsbtj)){
			calculationWdMapper.insertAll(calculationWdList);
			map.clear();
			map.put("message","true");
		}
		return map;
	}

	@Override
	public String updateBysubIdAndkgg(Equipment equipment) throws Exception {
		return equipmentMapper.updateByeqId(equipment)>0?"true":"false";
	}

	@Override
	public Map<String, Object> Importsq(InputStream is, String subId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ReadExcel redad = new ReadExcel();
		List<Equipment> equiList = redad.loadScoreInfosq(is, subId);
		String message = "";
		if(equiList!= null && !"".equals(equiList) && equiList.size()>0){
			message = equipmentMapper.ImportByEqui(equiList) > 0 ? "true" : "false";
		}else {
			message="传入的表为空";
		}
		map.put("message",message);
		return map;
	}

	@Override
	public String EquiQy(Equipment equipment) throws Exception {
		String devicesbId = equipment.getDeviceSbId();
		String deviceNumber = equipmentMapper.selectByDevicesbId(devicesbId);
		DxPlatform.updateShebei(deviceNumber, null, equipment.getDeviceName());
		return equipmentMapper.EquiQy(equipment)>0?"true":"false";
	}
}
