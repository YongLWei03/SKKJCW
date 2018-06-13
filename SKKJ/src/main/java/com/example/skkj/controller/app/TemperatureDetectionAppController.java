package com.example.skkj.controller.app;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.skkj.comment.BaseController;
import com.example.skkj.comment.ConcurrentFenbu;
import com.example.skkj.entity.Temperature;
import com.example.skkj.service.AcpEquipmentServer;
import com.example.skkj.service.AlarmInformationServer;
import com.example.skkj.service.TemperatureServer;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/appTemperatureDetection")
public class TemperatureDetectionAppController extends BaseController{
	protected static Logger logger = Logger.getLogger(TemperatureDetectionAppController.class);
	
	@Autowired
	private TemperatureServer temperatureServer;
	
	@Autowired
	private AlarmInformationServer alarmInformationServer;

	@Autowired
	private AcpEquipmentServer acpEquipmentServer;

//
		
		//实时接收数据
		@RequestMapping(value = "/appRealTime",method = RequestMethod.POST)
		@ResponseBody
		public void appRealTime(HttpServletRequest request){
			   String deviceNumber = request.getParameter("deviceNumber");
			   String ljcj = request.getParameter("ljcj");
			   String time = request.getParameter("time");
				Map<String, Object> mapte = ConcurrentFenbu.getConcurrent().realTime(deviceNumber,time,ljcj,temperatureServer);
				if (mapte != null && !"".equals(mapte)) {
					Object temp = mapte.get("temp");
					if(temp !="false" && !"false".equals(temp)){
						Temperature tempui = (Temperature) temp;
						getJsonObject().put("temp",tempui);
						getJsonObject().put(RESULT_CODE,1);
					}else {
						getJsonObject().put(RESULT_CODE,2);
					}
				}else {
					getJsonObject().put(RESULT_CODE,3);
				}
			pushJsonResult();
		}
		
		//获取历史数据
		@RequestMapping(value = "/appHistoryTemp",method = RequestMethod.POST)
		@ResponseBody
		public void appHistoryTemp(HttpServletRequest request){
			   Map<String, Object> map = new HashMap<String, Object>();
				try {
					map = temperatureServer.selectByTime(request);

					Object tempList = map.get("tempList");
					if(tempList != "false" && !"false".equals(tempList)){
						List<Temperature> tempLists = (List<Temperature>) map.get("tempList");
						getJsonObject().put(RESULT_CODE,1);
						getJsonObject().put("tempList",tempLists);
					}else {
						getJsonObject().put(RESULT_CODE,2);
					}
				} catch (Exception e) {
					logger.error("set 获取历史数据 error:"+e.getMessage(),e);
					getJsonObject().put(RESULT_CODE,3);
				}
					pushJsonResult();
		}


}
