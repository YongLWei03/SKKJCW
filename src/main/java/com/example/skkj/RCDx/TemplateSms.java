package com.example.skkj.RCDx;

import com.alibaba.fastjson.JSONObject;
import com.example.skkj.Interface.HttpClientUtil;
import com.example.skkj.comment.TimeUtile;
import com.example.skkj.entity.AlarmInformation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 模板短信DEMO
 *
 */
public class TemplateSms {
    public static String APP_ID = "540259480000274085";//应用ID------登录平台在应用设置可以找到
    public static String APP_SECRET = "8343f2c094e2e73e8d87843d61eb2f7c";//应用secret-----登录平台在应用设置可以找到
    public static String TEMPLATE_ID = "91553947";//模板ID
    public static String TEMPLATE_ID_TWO = "91553959";//设备属性通知


        //报警信息短信
    public static String sendSms(AlarmInformation alarmInformation, String tel, String number) throws Exception {
        Date date = new Date();
        String ACCESS_TOKEN = token();//访问令牌AT-------CC模式，AC模式都可，推荐CC模式获取令牌
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String timestamp = dateFormat.format(date);
        System.err.println(timestamp);
        Gson gson = new Gson();
        Map<String, String> map = new HashMap<String, String>();
        String inA = alarmInformation.getInA();
        String inB = alarmInformation.getInB();
        String inC = alarmInformation.getInC();
        String outA = alarmInformation.getOutA();
        String outB = alarmInformation.getOutB();
        String outC = alarmInformation.getOutC();
        String contactA = alarmInformation.getContactA();
        String contactB = alarmInformation.getContactB();
        String contactC = alarmInformation.getContactC();
        String idertifier = "";
        if(number != "12" && !"12".equals(number)){
            StringBuilder strWd = new StringBuilder();
            String time = alarmInformation.getDay();
            if (inA != null && !"".equals(inA)){
                strWd.append("A入"+inA);
            }
            if (inB != null && !"".equals(inB)){
                strWd.append(";").append("B入"+inB);
            }
            if (inC != null && !"".equals(inC)){
                strWd.append(";").append("C入"+inC);
            }
            if (outA != null && !"".equals(outA)){
                strWd.append(";").append("A出"+outA);
            }
            if (outB != null && !"".equals(outB)){
                strWd.append(";").append("B出"+outB);
            }
            if (outC != null && !"".equals(outC)){
                strWd.append(";").append("C出"+outC);
            }
            //这里存放模板参数，如果模板没有参数直接用template_param={}
            map.put("param1", alarmInformation.getSubstationName());
            map.put("param2", alarmInformation.getEquipmentxi());
            map.put("param3", TimeUtile.sfm(time));
            map.put("param4",strWd.toString());
            String template_param = gson.toJson(map);
            System.out.println(template_param);
            String postUrl = "http://api.189.cn/v2/emp/templateSms/sendSms";

            String postEntity = "app_id=" + APP_ID + "&access_token="
                    + ACCESS_TOKEN + "&acceptor_tel=" + tel + "&template_id="
                    + TEMPLATE_ID + "&template_param=" + template_param
                    + "&timestamp=" + URLEncoder.encode(timestamp, "utf-8");
            System.out.println(postUrl);
            System.out.println(postEntity);
            idertifier = TemplateSms.httpPost1(postUrl,postEntity);
        }else {
            String contactD = alarmInformation.getContactD();
            String contactE = alarmInformation.getContactE();
            String contactF = alarmInformation.getContactF();

            for (int i = 0; i < 2; i++) {
                StringBuilder strWd = new StringBuilder();
                if(i==0){
                    if (inA != null && !"".equals(inA)){
                        strWd.append("A入"+inA);
                    }
                    if (inB != null && !"".equals(inB)){
                        strWd.append(";").append("B入"+inB);
                    }
                    if (inC != null && !"".equals(inC)){
                        strWd.append(";").append("C入"+inC);
                    }
                    if (outA != null && !"".equals(outA)){
                        strWd.append(";").append("A出"+outA);
                    }
                    if (outB != null && !"".equals(outB)){
                        strWd.append(";").append("B出"+outB);
                    }
                    if (outC != null && !"".equals(outC)){
                        strWd.append(";").append("C出"+outC);
                    }
                }else {
                    if (contactA != null && !"".equals(contactA)){
                        strWd.append(";").append("A线"+contactA);
                    }
                    if (contactB != null && !"".equals(contactB)){
                        strWd.append(";").append("B线"+contactB);
                    }
                    if (contactC != null && !"".equals(contactC)){
                        strWd.append(";").append("C线"+contactC);
                    }
                    if (contactD != null && !"".equals(contactD)){
                        strWd.append(";").append("D线"+contactD);
                    }
                    if (contactE != null && !"".equals(contactE)){
                        strWd.append(";").append("E线"+contactE);
                    }
                    if (contactF != null && !"".equals(contactF)){
                        strWd.append(";").append("F线"+contactF);
                    }
                }
                String time = alarmInformation.getDay();
                //这里存放模板参数，如果模板没有参数直接用template_param={}
                map.put("param1", alarmInformation.getSubstationName());
                map.put("param2", alarmInformation.getEquipmentxi());
                map.put("param3",TimeUtile.sfm(time));
                map.put("param4",strWd.toString());
                String template_param = gson.toJson(map);
                System.out.println(template_param);
                String postUrl = "http://api.189.cn/v2/emp/templateSms/sendSms";

                String postEntity = "app_id=" + APP_ID + "&access_token="
                        + ACCESS_TOKEN + "&acceptor_tel=" + tel + "&template_id="
                        + TEMPLATE_ID + "&template_param=" + template_param
                        + "&timestamp=" + URLEncoder.encode(timestamp, "utf-8");
                System.out.println(postUrl);
                System.out.println(postEntity);
                idertifier = TemplateSms.httpPost1(postUrl,postEntity);
            }
        }
        String resmesss = "";
        if(idertifier != null && !"".equals(idertifier)){
            JSONObject idertifierjs = JSONObject.parseObject(idertifier);
            resmesss = idertifierjs.getString("res_message");
        }
        if(resmesss != "Success" && !"Success".equals(resmesss)){
            return "false";
        }else {

        }
        return "true";
    }

    //设备没有接收到数据发送的短信
    public static String TimeCha(String subName,String equipmentxi,String phone) throws Exception {
        StringBuilder strWd = new StringBuilder();
        Date date = new Date();
        String ACCESS_TOKEN = token();//访问令牌AT-------CC模式，AC模式都可，推荐CC模式获取令牌
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String timestamp = dateFormat.format(date);
        System.err.println(timestamp);
        Gson gson = new Gson();
        Map<String, String> map = new HashMap<String, String>();

        //这里存放模板参数，如果模板没有参数直接用template_param={}
        map.put("param1", subName);
        map.put("param2", equipmentxi);
        map.put("param3","5个小时以上没有接收到上报数据信息");
        System.out.println("报警信息:"+map);

        String template_param = gson.toJson(map);
        System.out.println(template_param);
        String postUrl = "http://api.189.cn/v2/emp/templateSms/sendSms";

        String postEntity = "app_id=" + APP_ID + "&access_token="
                + ACCESS_TOKEN + "&acceptor_tel=" + phone + "&template_id="
                + TEMPLATE_ID_TWO + "&template_param=" + template_param
                + "&timestamp=" + URLEncoder.encode(timestamp, "utf-8");
        System.out.println(postUrl);
        System.out.println(postEntity);

        String idertifier = null;
        idertifier = TemplateSms.httpPost1(postUrl,postEntity);

        return idertifier;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
//        String result = "";
//        try {
//            String charset = "utf-8";
//            String postUrl = "https://oauth.api.189.cn/emp/oauth2/v3/access_token";
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("grant_type","client_credentials");
//            map.put("app_id","540259480000274085");
//            map.put("app_secret","8343f2c094e2e73e8d87843d61eb2f7c");
//            HttpClientUtil httpClientUtil = new HttpClientUtil();
//            String httpOrgCreateTestRtn = httpClientUtil.doPost(postUrl,map,charset);
//            System.out.println("result:"+httpOrgCreateTestRtn);
//            JSONObject httporJson = JSONObject.parseObject(httpOrgCreateTestRtn);
//            String aceess = httporJson.getString("access_token");
//            System.out.println("token:"+aceess);
////            AlarmInformation alar = new AlarmInformation();
////            Date date = new Date();
////           SimpleDateFormat dateFormat = new SimpleDateFormat(
////                   "yyyy-MM-dd HH:mm");
////           String timestamp = dateFormat.format(date);
////           alar.setActualTemperature("10");
////            alar.setDay(timestamp);
////          alar.setAlarmX("A相");
////            alar.setEquipmentxi("成都");
////           alar.setSubstationName("has杜卡迪");
////           result = sendSms(alar,"15228628280");
////           System.out.println(result);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
       String  str = "A入100.23;B入100.23;C入100.23;A出105.33;B出105.33;C出105.33;A线100.33;B线100.33;C线100.33;C线100.33;C线100.33;C线100.33";
        System.out.println("获取的长度:"+str.length());
    }

    public static String token(){
        String charset = "utf-8";
        String postUrl = "https://oauth.api.189.cn/emp/oauth2/v3/access_token";
        Map<String, String> map = new HashMap<String, String>();
        map.put("grant_type","client_credentials");
        map.put("app_id",APP_ID);
        map.put("app_secret",APP_SECRET);
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String httpOrgCreateTestRtn = httpClientUtil.doPost(postUrl,map,charset);
        System.out.println("result:"+httpOrgCreateTestRtn);
        JSONObject httporJson = JSONObject.parseObject(httpOrgCreateTestRtn);
        String aceess = httporJson.getString("access_token");
        return aceess;
    }

    public static String httpPost1(String postUrl,String postEntity){
        Map<String, String> map2 =null;
        String resJson ="";
        Gson gson = new Gson();
        String idertifier = null;
        try {
            resJson = HttpInvoker.httpPost1(postUrl, null, postEntity);
            map2 = gson.fromJson(resJson,
                    new TypeToken<Map<String, String>>() {
                    }.getType());
//            idertifier = map2.get("idertifier").toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println(resJson);
        return resJson;
    }
}
