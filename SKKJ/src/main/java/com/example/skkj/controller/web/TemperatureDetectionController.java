package com.example.skkj.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.example.skkj.comment.ConcurrentFenbu;
import com.example.skkj.dingxin.test;
import com.example.skkj.entity.User;
import com.example.skkj.service.AcpEquipmentServer;
import com.example.skkj.service.AlarmInformationServer;
import com.example.skkj.service.TemperatureServer;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@RequestMapping("/webTemperatureDetection")
public class TemperatureDetectionController{
	protected static Logger logger = Logger.getLogger(TemperatureDetectionController.class);
	
	@Autowired
	private TemperatureServer temperatureServer;
	
	@Autowired
	private AlarmInformationServer alarmInformationServer;

	@Autowired
	private AcpEquipmentServer acpEquipmentServer;
	
	//启动UDP
	@RequestMapping("/startUDP")
	@ResponseBody
	public String startUDP(HttpServletRequest request){
		try {
//			UDPEchoServer.setPort(CfFinal.PROT);
//			UDPEchoServer.setAlarmInformationServer(alarmInformationServer);
//			UDPEchoServer.setTemperatureServer(temperatureServer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "true";
	}
	//启动TCP
	@RequestMapping("/startTCP")
	public void startTCP(HttpServletRequest request){
//		 String port = request.getParameter("port");
		  //服务端在20006端口监听客户端请求的TCP连接  
		int count=0;
        ServerSocket server = null;
		try {
			server = new ServerSocket(20006);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("startTCP TCP服务端在20006端口监听客户端请求的TCP连接  :"+e.getMessage(),e);
		}  
        Socket client = null;  
        boolean f = true;  
        while(f){
            //等待客户端的连接，如果没有获取连接  
            try {
				client = server.accept();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("startTCP 创建连接异常:"+e.getMessage(),e);
			}  
            //为每个客户端连接开启一个线程  
            ExecutorService fixedThreadPool = Executors.newFixedThreadPool(100);
//            fixedThreadPool.execute(new TCPEchoServer(client,temperatureServer,alarmInformationServer));
        }  
        try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("startTCP 关闭连接异常:"+e.getMessage(),e);
		}
	}
	//关闭UDP
	@RequestMapping("/outUDP")
	public void outUDP(){
		   test test = new test();
//		   test.aa(alarmInformationServer,temperatureServer);
		 
	}
	
		//进入网络
		@RequestMapping("/voUDP")
		public String voUDP(){

		return "webB/network";
		}
		
		@RequestMapping("/aa")
		@ResponseBody
		public String aa(HttpServletRequest request){
			String deviceNumberXm = request.getParameter("deviceNumberXm");
			String a = null;
			try {
				a = String.valueOf(temperatureServer.selectBydeviceNumber(deviceNumberXm));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return JSON.toJSONStringWithDateFormat(a,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
		}
		
		//进入实时就收数据页面
		@RequestMapping("/realTimeReditect")
		public ModelAndView realTimeReditect(HttpServletRequest request){
			ModelAndView mv = new ModelAndView();
			String deviceNumber = request.getParameter("deviceNumber");
			String substationImage = request.getParameter("substationImage");
			String substationId = request.getParameter("substationId");
			String numberDevices = request.getParameter("numberDevices");
			String isRoot = request.getParameter("isRoot");
			String deviceType = request.getParameter("deviceType");
			String deviceSbId = request.getParameter("deviceSbId");
			String currentPageSb = request.getParameter("currentPageSb");
			request.setAttribute("deviceNumber", deviceNumber);
			if(substationImage != null && !"".equals(substationImage)){
				request.setAttribute("substationImage", substationImage);
			}
			if(substationId != null && !"".equals(substationId)){
				request.setAttribute("substationId", substationId);
			}
			if(currentPageSb != null && !"".equals(currentPageSb)){
				request.setAttribute("currentPageSb", currentPageSb);
			}
			request.setAttribute("numberDevices", numberDevices);
			request.setAttribute("isRoot", isRoot);
			request.setAttribute("deviceSbId", deviceSbId);
			request.setAttribute("deviceType", deviceType);
			if(numberDevices == "6" || "6".equals(numberDevices)){
				mv.setViewName("webB/realTimeRedSix");
			}else if (numberDevices == "3" || "3".equals(numberDevices)){
				mv.setViewName("webB/realTimeRedThree");
			}else if (numberDevices == "9" || "9".equals(numberDevices)){
				mv.setViewName("webB/realTimeRedNine");
			}else {
				mv.setViewName("webB/realTimeRedTwelve");
			}
			return mv;
		}
		
		
		//实时接收数据
		@RequestMapping("/realTime")
		@ResponseBody
		public String realTime(HttpServletRequest request){
			   String deviceNumber = request.getParameter("deviceNumber");
			   String ljcj = request.getParameter("ljcj");
			   String time = request.getParameter("time");
			User user = (User)request.getSession().getAttribute("user");
			if(user != null && !"".equals(user)){
				Integer userId = user.getUserId();
				Map<String, Object> mapte = ConcurrentFenbu.getConcurrent().realTime(deviceNumber,time,ljcj,temperatureServer);
				return JSON.toJSONStringWithDateFormat(mapte,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);

			}else {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("temp","usetnull");
				return JSON.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
			}


		}
		
		//获取历史数据
		@RequestMapping("/historyTemp")
		@ResponseBody
		public String historyTemp(HttpServletRequest request){
			   Map<String, Object> map = new HashMap<String, Object>();
				try {
					map = temperatureServer.selectByTime(request);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("set historyTemp error:"+e.getMessage(),e);
				}
			return JSON.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
		}

		 /**
		      * @Author ZhouNan
		      * @Description  d
		      * @params
		      * @Date 2018/4/28 0028  10:29
		      */
		 @RequestMapping("/daochu")
		 @ResponseBody
		 public String daochu(HttpServletRequest request, HttpServletResponse response){
			 response.setContentType("application/binary;charset=UTF-8");
			 String numberDevices = request.getParameter("numberDevices");
			         try{
			         	if(numberDevices != "12" && !"12".equals(numberDevices)){
							String[] titles = { "时间", "A入温度/A入信号", "B入温度/B入信号", "C入温度/C入信号","A出温度/A出信号","B出温度/B出信号","C出温度/C出信号","状态"};
							HSSFWorkbook acc = temperatureServer.selectByTimeDaochu(request, titles);
							OutputStream output = response.getOutputStream();
							response.addHeader("Content-Disposition", "inline;filename="
									+ new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date()) + ".xls");
							response.setContentType("application/msexcel");
							acc.write(output);
							output.close();
			         	}else {
							String[] titles = { "时间", "A入温度/A入信号", "B入温度/B入信号", "C入温度/C入信号","A出温度/A出信号","B出温度/B出信号","C出温度/C出信号","A线入温度/A线入信号", "B线入温度/B线入信号", "C线入温度/C线入信号","A线出温度/A线出信号","B线出温度/B线出信号","C线出温度/C线出信号","状态"};
							HSSFWorkbook acc = temperatureServer.selectByTimeDaochu(request, titles);
							OutputStream output = response.getOutputStream();
							response.addHeader("Content-Disposition", "inline;filename="
									+ new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date()) + ".xls");
							response.setContentType("application/msexcel");
							acc.write(output);
							output.close();
			         	}


			            return "success";
				         } catch(Exception e){
				             e.printStackTrace();
				             return "导出信息失败";
				         }
		 }

	/**
	 * @Author ZhouNan
	 * @Description 导入excel
	 * @params  Import
	 * @Date 2018/3/6 0006  11:27
	 */
	@RequestMapping("/ImportByTempe")
	@ResponseBody
	public String ImportByTempe(@RequestParam("file")MultipartFile file, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = temperatureServer.ImportByTempe(file.getInputStream(),request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
	}


	/**
	 * @Author ZhouNan
	 * @Description 12G导入excel
	 * @params  Import
	 * @Date 2018/3/6 0006  11:27
	 */
	@RequestMapping("/ImportByTempeSJ")
	@ResponseBody
	public String ImportByTempeSJ(@RequestParam("file")MultipartFile file, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = temperatureServer.ImportByTempeSJ(file.getInputStream(),request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONStringWithDateFormat(map,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
	}


}
