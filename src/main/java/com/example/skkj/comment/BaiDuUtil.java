package com.example.skkj.comment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * java根据url获取json对象
 * @author openks
 * @since 2013-7-16
 * 需要添加java-json.jar才能运行
 */
public class BaiDuUtil {
  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }
  //访问百度接口返回数据
  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
//      json.get("point");
      return json;
    } finally {
      is.close();
     // System.out.println("同时 从这里也能看出 即便return了，仍然会执行finally的！");
    }
  }
  public static void main(String[] args) throws IOException, JSONException {
	  BaiDuMD5 snCal = new BaiDuMD5();
		// 计算sn跟参数对出现顺序有关，get请求请使用LinkedHashMap保存<key,value>，该方法根据key的插入顺序排序；post请使用TreeMap保存<key,value>，该方法会自动将key按照字母a-z顺序排序。所以get请求可自定义参数顺序（sn参数必须在最后）发送请求，但是post请求必须按照字母a-z顺序填充body（sn参数必须在最后）。以get请求为例：http://api.map.baidu.com/geocoder/v2/?address=百度大厦&output=json&ak=yourak，paramsMap中先放入address，再放output，然后放ak，放入顺序必须跟get请求中对应参数的出现顺序保持一致。
		Map paramsMap = new LinkedHashMap<String, String>();
//      "http://api.map.baidu.com/location/ip?ak=0bcrcezCrEBGNzv13ohdTCcr4F2w52Tz&="
		paramsMap.put("coor", "gcj02");
		paramsMap.put("ak", "0bcrcezCrEBGNzv13ohdTCcr4F2w52Tz");



		// 调用下面的toQueryString方法，对LinkedHashMap内所有value作utf8编码，拼接返回结果address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourak
		String paramsStr = snCal.toQueryString(paramsMap);

		// 对paramsStr前面拼接上/geocoder/v2/?，后面直接拼接yoursk得到/geocoder/v2/?address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourakyoursk
		String wholeStr = new String("/location/ip?" + paramsStr);
 
		// 对上面wholeStr再作utf8编码
		String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
		// 调用下面的MD5方法得到最后的sn签名7de5a22212ffaa9e326444c75a58f9a0
		System.out.println(snCal.MD5(tempStr));
	   //这里调用百度的ip定位api服务 详见 http://api.map.baidu.com/lbsapi/cloud/ip-location-api.htm
	    JSONObject json = readJsonFromUrl("http://api.map.baidu.com"+wholeStr+"&sn="+snCal.MD5(tempStr)+"&timestamp="+String.valueOf(new Date().getTime()));
	    
//	    System.out.println("获取的json数据:"+json.toString());
//      JSONArray results = json.getJSONArray("results");
    System.out.println(json);
//      for (int i = 0; i < results.length(); i++) {
//          JSONObject cc = results.getJSONObject(i);
//          BaiDuAddr bd = new BaiDuAddr();
//          System.out.println("获取的jsonde json:"+cc.getString("address"));
//          bd.setAddress(cc.getString("address"));
//          bd.setName(cc.getString("name"));
//          JSONObject location = cc.getJSONObject("location");
//          bd.setLng(String.valueOf(location.get("lng")));
//          bd.setLat(String.valueOf(location.get("lat")));
////          baiduList.add(bd);
//      }
	    //System.out.println(json.getString("address"));
    
  }
}