package com.example.skkj.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.skkj.comment.Uploader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/webImage")
public class ImageController {
	
	@RequestMapping("/imagecc")
	@ResponseBody
	public String imagecc(HttpServletRequest request, HttpServletResponse response){
		    try {
				request.setCharacterEncoding("utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			response.setCharacterEncoding("utf-8");
			String path = request.getContextPath();
		    Uploader up = new Uploader(request);
		    up.setSavePath("");
		    String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
		    up.setAllowFiles(fileType);
		    up.setMaxSize(30000); 
		    try {
				up.upload();
			} catch (Exception e) {
				e.printStackTrace();
			}

		    String callback = request.getParameter("callback");

		    String result = "{\"name\":\""+ up.getFileName() +"\", \"originalName\": \""+ up.getOriginalName() +"\", \"size\": "+ up.getSize() +", \"state\": \""+ up.getState() +"\", \"type\": \""+ up.getType() +"\", \"url\": \"resource\\/yhxImage\\"+ up.getUrl() +"\"}";
		    result = result.replaceAll( "\\\\", "\\\\" );
		    if( callback == null ){
		        try {
					response.getWriter().print( result );
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }else{
		        try {
					response.getWriter().print("<script>"+ callback +"(" + result + ")</script>");
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
			return null;
	}
}
