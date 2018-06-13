package com.example.skkj.controller.app;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.skkj.comment.BaseController;
import com.example.skkj.entity.AppApk;
import com.example.skkj.service.AppApkServer;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/appDown")
public class DownApp extends BaseController{
    protected static Logger logger = Logger.getLogger(DownApp.class);
@Autowired
private AppApkServer appApkServer;

    /**
     * @Author ZhouNan
     * @Description 导出模板
     * @params
     * @Date 2018/3/6 0006  09:32
     */
//    @RequestMapping("/export")
//    @ResponseBody
//    public void export(HttpServletRequest request, HttpServletResponse response) {
//        // 下载文件名
//        String fileName = "车位呢.apk";
//        try {
//            fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
//        } catch (UnsupportedEncodingException e1) {
//            e1.printStackTrace();
//        }
//        // 获取模板位置，读取数据库（也可以读取配置文件或写死）
//        String templatePath = request.getSession().getServletContext().getRealPath("/");
//
//        // 实际位置
//        String path = "http:\\\\www.scominfo.com:89\\SKKJ\\resource\\skkjImage\\2d0c3614904c43958fe484a1eef47755_cw.apk";
//        System.out.println(path);
//        // 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
//        response.setContentType("multipart/form-data");
//        // 2.设置文件头：最后一个参数是设置下载文件名
//        response.setHeader("Content-Disposition", "attachment;fileName="
//                + fileName);
//        response.addHeader("Content-Type", "application/vnd.andriod");
//        OutputStream out;
//        // 通过文件路径获得File对象(假如此路径中有一个download.pdf文件)
//        File file = new File(path);
//        try {
//            FileInputStream inputStream = new FileInputStream(file);
//            // 3.通过response获取OutputStream对象(out)
//            out = response.getOutputStream();
//            byte[] buffer = new byte[512];
//            int b = inputStream.read(buffer);
//            while (b != -1) {
//                // 4.写到输出流(out)中
//                out.write(buffer, 0, b);
//                b = inputStream.read(buffer);
//            }
//            inputStream.close();
//            out.close();
//            out.flush();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    @RequestMapping(value = "/appDownVseion",method = RequestMethod.POST)
    @ResponseBody
    public void appDownVseion(HttpServletRequest request) {
        String vesion = request.getParameter("vesion");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = appApkServer.selectByVesion(vesion);
            Object appApk = map.get("appApk");
            if(appApk != "false" && !"false".equals(appApk) && appApk != "wk" && !"wk".equals(appApk)){
                getJsonObject().put("appApk",(AppApk)appApk);
                getJsonObject().put(RESULT_CODE,1);
            }else if(appApk == "false" || "false".equals(appApk)){
                getJsonObject().put(RESULT_CODE,2);
            }else {
                getJsonObject().put(RESULT_CODE,4);
            }
        } catch (Exception e) {
            getJsonObject().put(RESULT_CODE,3);
            logger.error("查看是否存在最新版本:"+e.getMessage(),e);
        }
        pushJsonResult();
    }

}
