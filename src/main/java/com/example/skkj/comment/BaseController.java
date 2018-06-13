package com.example.skkj.comment;


import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

//import java.util.HashMap;
//import cf.common.util.JSONGenerator;
//import cf.common.util.Paging;


public class BaseController {
	protected static Logger logger = Logger.getLogger(BaseController.class);
	
    protected HttpServletRequest request;
	
	private static ThreadLocal<HttpServletResponse> reponse_threadLocal = new ThreadLocal<HttpServletResponse>();
	private static ThreadLocal<Map<String, Object>> jsonObject_threadLocal = new ThreadLocal<Map<String, Object>>();
	//protected Map<String, Object> jsonObject = new HashMap<String, Object>();
	public static final String RESULT_CODE = "resultCode";
	public static final String RESULT_MSG = "resultMsg";
	public  static final Log log = LogFactory.getLog(BaseController.class);
	public BaseController(){
	}
	/**
	 * 将jsonObject以JSON格式输出到客户端
	 */
	protected void pushJsonResult() {
		pushJsonResult(jsonObject_threadLocal.get());
	}

	/**
	 * 将指定的对数据对象以JSON格式输出到客户端
	 * 
	 * @param jsonObject
	 */
	protected void pushJsonResult(Object jsonObject) {
		getResponse().setContentType("application/json;charset=UTF-8");
		getResponse().setHeader("pragma", "no-cache");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().setDateHeader("Expires", 0L);
		try {
			getResponse().getWriter().write(JSON.toJSONString(jsonObject));
			getResponse().flushBuffer();
		} catch (IOException e) {
			logger.error("set 返回数据异常 error:"+e.getMessage(),e);
		}
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	@Resource
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public static void setResponse(HttpServletResponse response) {
        reponse_threadLocal.set(response);
    }
 
    public static HttpServletResponse getResponse() {
        return reponse_threadLocal.get();
    }
 
    public static void removeResponse() {
        reponse_threadLocal.remove();
    }
    public static void setJsonObject(Map<String, Object> jsonObject) {
    	jsonObject_threadLocal.set(jsonObject);
    }
 
    public static Map<String, Object> getJsonObject() {
        return jsonObject_threadLocal.get();
    }
 
    public static void removeJsonObject() {
    	jsonObject_threadLocal.remove();
    } 
	
}
