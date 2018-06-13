package com.example.skkj.service.Impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.skkj.comment.*;
import com.example.skkj.entity.AddTable;
import com.example.skkj.entity.Temperature;
import com.example.skkj.mapper.*;
import com.example.skkj.service.TemperatureServer;
import com.example.skkj.test.ReadExcel;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("temperatureServer")
public class TemperatureServerImpl implements TemperatureServer{
	@Autowired
	private TemperatureMapper temperatureMapper;
	
	@Autowired
	private FileLocationMapper fileLocationMapper;
	
	@Autowired
	private EquipmentMapper equipmentMapper;

	@Autowired
	private TemperaturesMapper temperaturesMapper;

	@Autowired
	private AddTableMapper addTableMapper;
	
	@Override
	public int selectBydeviceNumber(String deviceNumber) throws Exception {
		return temperatureMapper.selectBydeviceNumber(deviceNumber);
	}

	@Override
	public int deleteBydeviceNumber(String deviceNumber) throws Exception {
		return 0;
	}

	@Override
	public int insert(List<Temperature> temperature,String deviceNumber) throws Exception {
	        /**
	         * 映射sql的标识字符串，
	         * com.oumyye.mapping.UserMapping是userMapper.xml文件中mapper标签的namespace属性的值，
	         * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
	         */
//	        String statement = "com.example.skkj.mapper.TemperatureMapper.selectBydeviceNumber";//映射sql的标识字符串
	        //执行查询返回一个唯一user对象的sql
		 SqlSession session = SqlsessionFctrouceUtil.getSession();
        	int a = 0; 
			   String statement1 = "com.example.skkj.mapper.TemperatureMapper.insert";
			   try {
				   a = session.insert(statement1, temperature);
				   if(a > 0){
					   session.commit();
				   }
			} catch (Exception e) {
				session.rollback();
			}finally{
				session.close();
			}
		return a;
	}


	@Override
	public HSSFWorkbook selectByTimeDaochu(HttpServletRequest request, String[] titles) throws Exception {
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String deviceNumber = request.getParameter("deviceNumber");
		String sort = request.getParameter("sort");
		String numberDevices = request.getParameter("numberDevices");
		String startTimes = "";
		String endTimes = "";

//		 String file = "fileTemper"+endTime;
		String time = TimeUtile.dateTimeYY();
		String times = TimeUtile.dateTimess();
		List<Temperature> tempList = new ArrayList<Temperature>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("object","temperatures2018032301");
		map.put("deviceNumber",deviceNumber);
		map.put("number",numberDevices);
		map.put("endTime",endTime);
		map.put("startTime",startTime);
		tempList  = temperaturesMapper.selectBySE(map);
		Daocexcl da = new Daocexcl();
		HSSFWorkbook adsda = da.export(titles, tempList);
		return adsda;
	}

	/**
	 * 查询历史数据
	 * @params startTime 开始时间
	 * @params endTime 结束时间
	 * @params deviceNumber 哪个信息
	 * @params sort 是第几个查询
	 * */
	@Override
	public Map<String, Object> selectByTime(HttpServletRequest request)
			throws Exception {
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String deviceNumber = request.getParameter("deviceNumber");
		String numberDevices = request.getParameter("numberDevices");
		String sort = request.getParameter("sort");
        String startTimes = "";
        String endTimes = "";

		 List<Temperature> tempList = new ArrayList<Temperature>();
//
		Map<String, Object> map = new HashMap<String, Object>();
		if(startTime != null && !"".equals(startTime)){
			map.put("startTime",startTime);
		}
		if(endTime != null && !"".equals(endTime)){

			map.put("endTime",endTime);
		}
		if(numberDevices != null && !"".equals(numberDevices)){

			map.put("number",numberDevices);
		}
		map.put("deviceNumber",deviceNumber);
	    if(sort != null && !"".equals(sort) && sort == "0" || sort != null && !"".equals(sort) && "0".equals(sort)) {
			List<AddTable> addTableList = addTableMapper.selectByse(startTime, endTime);
			RedisUtils redis = new RedisUtils();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			long startTimesa = df.parse(startTime).getTime();
			long endTimesa = df.parse(endTime).getTime();
            List<Temperature> temperatureList = new ArrayList<Temperature>();
			if(addTableList != null && addTableList.size() > 0){
                for (int i = 0; i < addTableList.size(); i++) {
                    AddTable addtable = addTableList.get(i);
                    String tableName = addtable.getTableName();
                    temperatureList = redis.getList(tableName, Temperature.class);
                    if(temperatureList == null || temperatureList.size() <= 0){
                        map.put("object",tableName);
						tempList = temperaturesMapper.selectBySE(map);
                        redis.setList(tableName,temperatureList);
                    }else if(temperatureList != null && temperatureList.size() > 0){
                        for (int j = 0; j < temperatureList.size(); j++) {
                            Temperature tempera = temperatureList.get(j);
                            String deviceNumbers = tempera.getDeviceNumber();
                            String time = tempera.getTime();
                            if(deviceNumber == deviceNumbers || deviceNumber.equals(deviceNumbers)){
                                Date timesa = df.parse(time);
                                long tiesa = timesa.getTime();
                                if(tiesa>= startTimesa && tiesa<=endTimesa){
                                    tempList.add(tempera);
                                }
                            }
                        }
                    }
                }
            }else {
                AddTable addtable = addTableMapper.selectByTime();
                map.put("object",addtable.getTableName());
				tempList = temperaturesMapper.selectBySE(map);
//                if(temperatureList != null && temperatureList.size() > 0){
//                    for (int j = 0; j < temperatureList.size(); j++) {
//                        Temperature tempera = temperatureList.get(j);
//                        String deviceNumbers = tempera.getDeviceNumber();
//                        String time = tempera.getTime();
//                        if(deviceNumber == deviceNumbers || deviceNumber.equals(deviceNumbers)){
//                            Date timesa = df.parse(time);
//                            long tiesa = timesa.getTime();
//                            if(tiesa>= startTimesa && tiesa<=endTimesa){
//                                tempList.add(tempera);
//                            }
//                        }
//                    }
//                }
            }
		}
		map.clear();
		if(tempList != null && tempList.size()>0){
            map.put("tempList", tempList);
		}else {
            map.put("tempList", "false");
		}
		return map;
	 }
	 
	@Override
	public int insertOne(Temperature temperature) throws Exception {
		 SqlSession session = SqlsessionFctrouceUtil.getSession();
     	int a = 0; 
			   String statement1 = "com.example.skkj.mapper.TemperatureMapper.insertOne";
			   try {
				   a = session.insert(statement1, temperature);
				   if(a > 0){
					   session.commit();
				   }else {
					   session.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
				session.rollback();
			}finally{
				session.close();
			}
		return a;
	}

	@Override
	public String insertTemu(Temperature temperature) throws Exception {
		return temperatureMapper.insertTemu(temperature)>0?"true":"false";
	}

	@Override
	public String updateTemu(Temperature temperature) throws Exception {
		return temperatureMapper.updateTemu(temperature)>0?"true":"false";
	}

	@Override
	public String selectByTemu(String deviceNumber,Temperature temper) throws Exception {
		Temperature temp = temperatureMapper.selectByTemu(deviceNumber);
		String time1 = "";
		String time2 = temper.getTime();
		if(temp != null && !"".equals(temp)){
				time1 = temp.getTime();
			if(!time1.equals(time2) && time2 != time1){
				updateTemu(temper);
			}else {
				return "false";
			}
		}else {
			insertTemu(temper);
		}
		return "true";
	}

	@Override
	public Temperature selectByTemuSb(String deviceNumber) throws Exception {
		return temperatureMapper.selectByTemu(deviceNumber);
	}

	@Override
	public Map<String, Object> ImportByTempe(InputStream is, HttpServletRequest request) throws Exception {
		String deviceNumber = request.getParameter("deviceNumber");
		List<Temperature> temple = new LinkedList<Temperature>();
		ReadExcel redad = new ReadExcel();
		Map<String, Object> map = new HashMap<String, Object>();
		temple = redad.loadScoreInfoBytemp(is,deviceNumber);
		String messags = temperaturesMapper.ImportByTempe(temple) > 0?"true" : "false";
		map.put("linke",messags);
		return map;
	}

	@Override
	public Map<String, Object> ImportByTempeSJ(InputStream is, HttpServletRequest request) throws Exception {
		String deviceNumber = request.getParameter("deviceNumber");
		List<Temperature> temple = new LinkedList<Temperature>();
		ReadExcel redad = new ReadExcel();
		Map<String, Object> map = new HashMap<String, Object>();
		temple = redad.loadScoreInfoBytempSr(is,deviceNumber);
		String messags = temperaturesMapper.ImportByTempe(temple) > 0?"true" : "false";
		map.put("linke",messags);
		return map;
	}

	@Override
	public Temperature selectByTemuS(String deviceNumber) throws Exception {
		return temperatureMapper.selectByTemu(deviceNumber);
	}


}
