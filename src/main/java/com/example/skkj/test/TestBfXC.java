package com.example.skkj.test;


import com.example.skkj.dingxin.HttpsUtil;
import com.example.skkj.dingxin.StreamClosedHttpResponse;

public class TestBfXC implements Runnable{
    private String  str ;

    private HttpsUtil httpsUtil;
    @Override
    public void run() {

        StreamClosedHttpResponse aa = httpsUtil.doPostJsonGetStatusLine("https://192.168.10.159:8443/SKKJ/DXjktjController/deviceDatasChanged", str);
        System.out.print("访问https://183.61.5.4:8443/SKKJ/DxApplication/deviceDataChanged："+aa.getStatusLine());
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public HttpsUtil getHttpsUtil() {
        return httpsUtil;
    }

    public void setHttpsUtil(HttpsUtil httpsUtil) {
        this.httpsUtil = httpsUtil;
    }

    public TestBfXC(String str,HttpsUtil httpsUtil) {
        this.str = str;
        this.httpsUtil = httpsUtil;
    }
}
