package com.example.skkj.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.example.skkj.comment.BaseController;
import com.example.skkj.comment.CfFinal;
import com.example.skkj.entity.AppApk;
import com.example.skkj.service.AppApkServer;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

@Controller
@RequestMapping("/webAppApk")
public class AppApkController extends BaseController{
    @Autowired
    private AppApkServer appApkServer;

    @RequestMapping("/doGet")
    @ResponseBody
    public void doGet(@RequestParam("file")MultipartFile files, HttpServletRequest request, HttpServletResponse response){
                        //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
                     String savePath = CfFinal.SAVEPATH;
                        File file = new File(savePath);
                       //判断上传文件的保存目录是否存在
                         if (!file.exists() && !file.isDirectory()) {
                              System.out.println(savePath+"目录不存在，需要创建");
                              //创建目录
                               file.mkdir();
                          }
                         //消息提示
                      String message = "";
                        try{

                            String prefix = UUID.randomUUID().toString();
                            prefix = prefix.replace("-","");
                            String fileName = prefix+"_"+files.getOriginalFilename();//使用UUID加前缀命名文件，防止名字重复被覆盖
                            String finames=savePath+"\\"+fileName;
                            String  path = CfFinal.IPAPK+"/SKKJ/resource/skkjapp"+"/"+fileName;
                            InputStream in= files.getInputStream();;//声明输入输出流
                            OutputStream out=new FileOutputStream(new File(finames));//指定输出流的位置;
                            byte []buffer =new byte[1024];
                            int len=0;
                            while((len=in.read(buffer))!=-1){
                                out.write(buffer, 0, len);
                                out.flush();                //类似于文件复制，将文件存储到输入流，再通过输出流写入到上传位置
                            }                               //这段代码也可以用IOUtils.copy(in, out)工具类的copy方法完成
                            out.close();
                            in.close();
                            getJsonObject().put("rest","SUSSCES");
                            getJsonObject().put("url",path);
                            }catch (Exception e) {
                              message= "文件上传失败！";
                             getJsonObject().put("rest","false");
                                e.printStackTrace();
                          }
                        pushJsonResult();
                  }
             @RequestMapping("insert")
             @ResponseBody
             public String insert(@ModelAttribute AppApk appApk){
                String  message = "";
                 try {
                     message = appApkServer.insert(appApk);
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
                 return JSON.toJSONStringWithDateFormat(message,"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
           }
    @RequestMapping("/select")
    public ModelAndView select(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        try {
            appApkServer.select(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("/webB/appApkList");
        return mv;
    }

}
