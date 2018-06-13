package com.example.skkj.Interface;

import net.sf.json.JSONObject;
import org.apache.http.StatusLine;
import com.example.skkj.comment.ConcurrentFenbu;
import com.example.skkj.dingxin.*;
import com.example.skkj.entity.Equipment;
import com.example.skkj.entity.EquipmentType;

import java.util.HashMap;
import java.util.Map;

public class DxPlatform {

    public static String addSheBei(Equipment equipment, EquipmentType equipmentType){
        HttpsUtil httpsUtil = new HttpsUtil();
        try {
            httpsUtil.initSSLConfigForTwoWay();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Authentication，get token
        String accessToken = null;
        try {
            accessToken = ConcurrentFenbu.getConcurrent().accessTokenPt(httpsUtil,"2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String appId = Constant.APPID;
        String secret = Constant.SECRET;
        Map<String, String> param = new HashMap<>();
        param.put("app_key", appId);
        param.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        String urlQueryDevices = Constant.REGISTER_DEVICE;
        String verifyCode = equipment.getCode();
        String nodeId = verifyCode;
        Integer timeout = 0;
        Map<String, Object> paramReg = new HashMap<>();
        paramReg.put("verifyCode", verifyCode.toUpperCase());
        paramReg.put("nodeId", nodeId.toUpperCase());
        paramReg.put("timeout", timeout);
        String jsonRequest = JsonUtil.jsonObj2Sting(paramReg);
        StreamClosedHttpResponse responseReg = httpsUtil.doPostJsonGetStatusLine(urlQueryDevices, param, jsonRequest);
        String contet = responseReg.getContent();
        //修改设备信息
        JSONObject jsonObject=JSONObject.fromObject(contet);
        String deviceId = (String) jsonObject.get("deviceId");
        String error_code = (String) jsonObject.get("error_code");
        String message = "";

        if(deviceId != null && !"".equals(deviceId)){
            message = DxPlatform.updateShebei(deviceId, equipmentType, equipment.getDeviceName());
        }

        //设备已经绑定
        if(error_code != null && !"".equals(error_code)){
            if(error_code == "100416" || error_code.equals("100416")){
                message = "bdsb";
            }
        }

        if(message != "false" && !"false".equals(message) && message != "bdsb" && !"bdsb".equals(message)){
            return deviceId;
        }else if (message == "bdsb" || "bdsb".equals(message)){
            return message;
        }else {
            return null;
        }
    }
    /**
     * @Author ZhouNan
     * @Description 修改设备
     * @params
     * @Date 15:00 2017/12/21 0021
     */
    public static String updateShebei(String deviceId,EquipmentType equipmentType,String name){
        HttpsUtil httpsUtil = new HttpsUtil();
        try {
            httpsUtil.initSSLConfigForTwoWay();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Authentication，get token
//        String accessToken = null;
//        try {
//            accessToken = LoginUtile.login(httpsUtil);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        String accessToken =  ConcurrentFenbu.getConcurrent().accessTokenPt(httpsUtil,"2");
        String appId = Constant.APPID;
        String secret = Constant.SECRET;
        Map<String, String> param = new HashMap<>();
        param.put("app_key", appId);
        param.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);


        String urlModifyDeviceInfo = Constant.MODIFY_DEVICE_INFO + "/" + deviceId;
        Map<String, Object> paramModifyDeviceInfo = new HashMap<>();
        if(equipmentType != null && !"".equals(equipmentType)){
            String manufacturerName = equipmentType.getManufacturerName();
            String manufacturerId = equipmentType.getManufacturerId();
            String protocolType = equipmentType.getProtocolType();
            String model = equipmentType.getModel();
            String deviceType = equipmentType.getDeviceType();
            paramModifyDeviceInfo.put("manufacturerId", manufacturerId);
            paramModifyDeviceInfo.put("manufacturerName", manufacturerName);
            paramModifyDeviceInfo.put("deviceType", deviceType);
            paramModifyDeviceInfo.put("model", model);
            paramModifyDeviceInfo.put("mute", "FALSE");
            paramModifyDeviceInfo.put("protocolType", protocolType);
        }
        if(name != null && !"".equals(name)){
            paramModifyDeviceInfo.put("name", name);
        }

        String jsonRequest1 = JsonUtil.jsonObj2Sting(paramModifyDeviceInfo);
        StreamClosedHttpResponse responseModifyDeviceInfo = httpsUtil.doPutJsonGetStatusLine(urlModifyDeviceInfo,param, jsonRequest1);
        StatusLine c = responseModifyDeviceInfo.getStatusLine();
            if(c.toString().equals("HTTP/1.1 204 No Content") || c.toString() == "HTTP/1.1 204 No Content"){
                 return "true";
            }else {
                return "false";
            }
    }

    /**
     * @Author ZhouNan
     * @Description 删除直连设备
     * @params
     * @Date 16:31 2017/12/21 0021
     */
    public  static String deleteSheBei(String deviceId){
        HttpsUtil httpsUtil = new HttpsUtil();
        try {
            httpsUtil.initSSLConfigForTwoWay();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String accessToken =  ConcurrentFenbu.getConcurrent().accessTokenPt(httpsUtil,"2");
        String appId = Constant.APPID;
        String secret = Constant.SECRET;
        Map<String, String> param = new HashMap<>();
        param.put("app_key", appId);
        param.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        String url = Constant.DELETE_DEVICE +"/"+deviceId;
        StreamClosedHttpResponse responseDelete = httpsUtil.doDeleteGetStatusLine(url, param);
        StatusLine status = responseDelete.getStatusLine();
        if(status.toString().equals("HTTP/1.1 204 No Content") || status.toString() == "HTTP/1.1 204 No Content"){
             return "true";
        }else {
            return "false";
        }
    }

    /**
     * @Author ZhouNan
     * @Description 修改设备
     * @params
     * @Date 15:00 2017/12/21 0021
     */
    public static String updateByMust(String mute,String deviceId){
        HttpsUtil httpsUtil = new HttpsUtil();
        try {
            httpsUtil.initSSLConfigForTwoWay();
        } catch (Exception e) {
            e.printStackTrace();
        }
     String accessToken =  ConcurrentFenbu.getConcurrent().accessTokenPt(httpsUtil,"2");
        String appId = Constant.APPID;
        String secret = Constant.SECRET;
        Map<String, String> param = new HashMap<>();
        param.put("app_key", appId);
        param.put(Constant.HEADER_APP_AUTH, "Bearer" + " " + accessToken);
        String urlModifyDeviceInfo = Constant.MODIFY_DEVICE_INFO + "/" + deviceId;
        Map<String, Object> paramModifyDeviceInfo = new HashMap<>();
        if(mute != "1" && !"1".equals(mute)){
            paramModifyDeviceInfo.put("mute","TRUE");
        }else {
            paramModifyDeviceInfo.put("mute","FALSE");
        }

        String jsonRequest1 = JsonUtil.jsonObj2Sting(paramModifyDeviceInfo);
        StreamClosedHttpResponse responseModifyDeviceInfo = httpsUtil.doPutJsonGetStatusLine(urlModifyDeviceInfo,param, jsonRequest1);
        StatusLine c = responseModifyDeviceInfo.getStatusLine();
//        System.out.print(responseModifyDeviceInfo.getStatusLine());
//        System.out.println(responseModifyDeviceInfo.getContent());
        if(c.toString().equals("HTTP/1.1 204 No Content") || c.toString() == "HTTP/1.1 204 No Content"){
            return "true";
        }else {
            return "false";
        }
    }

    public static void main(String[] args) {

               new DxPlatform().updateByMust("2","6f1cc9d9-179a-4927-a6df-a9df5b9a68a7");
    }
}
