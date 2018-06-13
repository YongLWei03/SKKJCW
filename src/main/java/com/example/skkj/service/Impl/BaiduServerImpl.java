package com.example.skkj.service.Impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import com.example.skkj.comment.BaiDuMD5;
import com.example.skkj.comment.BaiDuUtil;
import com.example.skkj.comment.CfFinal;
import com.example.skkj.entity.BaiDuAddr;
import com.example.skkj.service.BaiduServer;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.*;

@Service("baiduServer")
public class BaiduServerImpl implements BaiduServer {

    @Override
    public List<BaiDuAddr> serche(HttpServletRequest request) throws Exception{
        String query = request.getParameter("query");
        String region = request.getParameter("region");
        BaiDuMD5 snCal = new BaiDuMD5();
        // 计算sn跟参数对出现顺序有关，get请求请使用LinkedHashMap保存<key,value>，该方法根据key的插入顺序排序；post请使用TreeMap保存<key,value>，该方法会自动将key按照字母a-z顺序排序。所以get请求可自定义参数顺序（sn参数必须在最后）发送请求，但是post请求必须按照字母a-z顺序填充body（sn参数必须在最后）。以get请求为例：http://api.map.baidu.com/geocoder/v2/?address=百度大厦&output=json&ak=yourak，paramsMap中先放入address，再放output，然后放ak，放入顺序必须跟get请求中对应参数的出现顺序保持一致。
        Map paramsMap = new LinkedHashMap<String, String>();

        paramsMap.put("query",query);
        paramsMap.put("output", "json");
        paramsMap.put("region",region);
        paramsMap.put("ak", CfFinal.AK);



        // 调用下面的toQueryString方法，对LinkedHashMap内所有value作utf8编码，拼接返回结果address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourak
        String paramsStr = snCal.toQueryString(paramsMap);

        // 对paramsStr前面拼接上/geocoder/v2/?，后面直接拼接yoursk得到/geocoder/v2/?address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourakyoursk
        String wholeStr = new String("/place/v2/search?" + paramsStr);

        // 对上面wholeStr再作utf8编码
        String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
        // 调用下面的MD5方法得到最后的sn签名7de5a22212ffaa9e326444c75a58f9a0
        System.out.println(snCal.MD5(tempStr));
        //这里调用百度的ip定位api服务 详见 http://api.map.baidu.com/lbsapi/cloud/ip-location-api.htm
        JSONObject json = BaiDuUtil.readJsonFromUrl("http://api.map.baidu.com"+wholeStr+"&sn="+snCal.MD5(tempStr)+"&timestamp="+String.valueOf(new Date().getTime()));
        int status = json.getInt("status");
        if(status == 0){
            JSONArray results = json.getJSONArray("results");
            List<BaiDuAddr> baiduList = new LinkedList<BaiDuAddr>();
            for (int i = 0; i < results.length(); i++) {
                JSONObject cc = results.getJSONObject(i);
                BaiDuAddr bd = new BaiDuAddr();
                bd.setAddress(cc.getString("address"));
                bd.setName(cc.getString("name"));
                JSONObject location = cc.getJSONObject("location");
                bd.setLng(String.valueOf(location.get("lng")));
                bd.setLat(String.valueOf(location.get("lat")));
                baiduList.add(bd);
            }
            return baiduList;
        }else {
            return null;
        }
    }
}
