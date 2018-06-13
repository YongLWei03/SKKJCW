package com.example.skkj.comment;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

public class Upload {
	
	public static String fileUpload(MultipartFile f, HttpServletRequest request) throws Exception {
		String path = "";
		String dir = CfFinal.PATH+File.separator;
		String type = f.getOriginalFilename().substring(f.getOriginalFilename().indexOf("."));
		String filename = System.currentTimeMillis() + type;
		File destFile = new File(dir+filename);
		FileUtils.copyInputStreamToFile(f.getInputStream(), destFile);
		InetAddress addr = InetAddress.getLocalHost();
		String ip=addr.getHostAddress().toString();//获得本机IP
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
		String patht = request.getContextPath();
		path += basePath+patht+"/resource/skkjImage/"+filename;
		System.out.println(path+"-----------------------------");
		//path+="http://"+ip+":80/SDY/resource/sdyImg/"+filename;
		return path;
	}
	
	
	
	public static InputStream getData(String str) {
		  InputStream is ;
	       try {

	           URL urlGet = new URL(str);

	           HttpURLConnection http = (HttpURLConnection) urlGet
	                   .openConnection();

	           http.setRequestMethod("GET"); // 必须是get方式请求

	           http.setRequestProperty("Content-Type",

	                   "application/x-www-form-urlencoded");

	           http.setDoOutput(true);

	           http.setDoInput(true);

	           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒

	           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒

	           http.connect();

	           // 获取文件转化为byte流
	           is = http.getInputStream();

	       } catch (Exception e) {

	           e.printStackTrace();
	           is= null;
	       }

	       return is;

	   }
	 public  static void saveImageToDisk(String str,String name) throws Exception {

	       InputStream inputStream = getData(str);
	       byte[] data = new byte[1024];

	       int len = 0;

	       FileOutputStream fileOutputStream = null;

	       try {
	           fileOutputStream = new FileOutputStream(CfFinal.PATH+File.separator+name);

	           while ((len = inputStream.read(data)) != -1) {

	               fileOutputStream.write(data, 0, len);

	           }

	       } catch (IOException e) {

	           e.printStackTrace();

	       } finally {

	           if (inputStream != null) {

	               try {

	                   inputStream.close();

	               } catch (IOException e) {

	                   e.printStackTrace();

	               }

	           }

	           if (fileOutputStream != null) {

	               try {

	                   fileOutputStream.close();

	               } catch (IOException e) {

	                   e.printStackTrace();

	               }

	           }

	       }

	   }
}
