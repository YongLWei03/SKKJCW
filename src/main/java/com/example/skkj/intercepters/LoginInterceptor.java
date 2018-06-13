package com.example.skkj.intercepters;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.skkj.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0,
								HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
						   Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
							 Object arg2) throws Exception {
		String uri = request.getRequestURI();
		if(uri.contains("/SKKJCW/webUser/findByUserName")||uri.contains("/resource/")||uri.contains("/SKKJCW/webTemperatureDetection/startUDP")||uri.contains("SKKJCW/webTemperatureDetection/startTCP")){
			return true;
		}else if(uri.contains("/SKKJCW/web")){
			User user = (User) request.getSession().getAttribute("user");

			if(user!=null&&!"".equals(user.getUserId())){
				return true;
			}else {
				String XRequested =request.getHeader("X-Requested-With");
				if("XMLHttpRequest".equals(XRequested)){
					response.getWriter().write("IsAjax");
				}else{
					response.sendRedirect("/SKKJCW");
				}
				return false;
			}
		}else{
			return true;
		}

	}


}
