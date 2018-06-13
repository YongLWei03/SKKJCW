package com.example.skkj.timing;

import com.example.skkj.comment.RedisUtils;
import com.example.skkj.entity.AddTable;
import com.example.skkj.service.AddTableServer;
import com.example.skkj.service.TemperaturesServer;
import org.springframework.beans.factory.annotation.Autowired;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
      * @Author ZhouNan
      * @Description 创建Mysql表 定时
      * @params
      * @Date 2018/4/25 0025  10:35
      */
public class JdbcTime {

    @Autowired
    private TemperaturesServer temperaturesServer;

    @Autowired
    private AddTableServer addTableServer;

      final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
         //指定连接数据库的url
//         final static String DB_URL = "jdbc:mysql://183.61.5.4:3306/scskkj?useUnicode=true&characterEncoding=utf8";
         final static String DB_URL = "jdbc:mysql://183.61.5.4:3306/skkjsc?useUnicode=true&characterEncoding=utf8";
     //mysql用户名
             final static String name = "root";
         //mysql密码
             final static String pwd = "skkjadmin";
//
     public  String jdbcUp(TemperaturesServer temperaturesServer,AddTableServer addTableServer,String tablenamess){
             Date date= new Date();
             SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHH");//设置日期格式
             SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
             String d= df.format(date);// new Date()为获取当前系统时间
             String d2= df.format(date);// new Date()为获取当前系统时间

             String temperatures = "temperatures"+d;
             String tablename = null;
             String time = null;
         AddTable addtable = new AddTable();
         try {
              addtable = addTableServer.selectByTime();
         } catch (Exception e) {
             e.printStackTrace();
         }

         //我么要执行创建表的DDl语句
             String creatsql = "CREATE TABLE `"+temperatures+"` (\n" +
                     "  `TEM_ID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                     "  `INA` varchar(255) DEFAULT NULL,\n" +
                     "  `INB` varchar(255) DEFAULT NULL,\n" +
                     "  `INC` varchar(255) DEFAULT NULL,\n" +
                     "  `OUTA` varchar(255) DEFAULT NULL,\n" +
                     "  `OUTB` varchar(255) DEFAULT NULL,\n" +
                     "  `OUTC` varchar(255) DEFAULT NULL,\n" +
                     "  `DEVICE_NUMBER` varchar(255) DEFAULT NULL,\n" +
                     "  `TIME` varchar(255) DEFAULT NULL,\n" +
                     "  `DEVICENUMBERXM` varchar(255) DEFAULT NULL,\n" +
                     "  `TYPE` varchar(255) DEFAULT NULL,\n" +
                     "  `CONTACTA` varchar(255) DEFAULT NULL,\n" +
                     "  `CONTACTB` varchar(255) DEFAULT NULL,\n" +
                     "  `CONTACTC` varchar(255) DEFAULT NULL,\n" +
                     "  `CONTACTD` varchar(255) DEFAULT NULL,\n" +
                     "  `CONTACTE` varchar(255) DEFAULT NULL,\n" +
                     "  `CONTACTF` varchar(255) DEFAULT NULL,\n" +
                     "  `INAXH` varchar(255) DEFAULT NULL,\n" +
                     "  `INBXH` varchar(255) DEFAULT NULL,\n" +
                     "  `INCXH` varchar(255) DEFAULT NULL,\n" +
                     "  `OUTAXH` varchar(255) DEFAULT NULL,\n" +
                     "  `OUTBXH` varchar(255) DEFAULT NULL,\n" +
                     "  `OUTCXH` varchar(255) DEFAULT NULL,\n" +
                     "  `CONTACTAXH` varchar(255) DEFAULT NULL,\n" +
                     "  `CONTACTBXH` varchar(255) DEFAULT NULL,\n" +
                     "  `CONTACTCXH` varchar(255) DEFAULT NULL,\n" +
                     "  `CONTACTDXH` varchar(255) DEFAULT NULL,\n" +
                     "  `CONTACTEXH` varchar(255) DEFAULT NULL,\n" +
                     "  `CONTACTFXH` varchar(255) DEFAULT NULL,\n" +
                     "  `ANTSIGNAL` varchar(255) DEFAULT NULL,\n" +
                     "  `NUMBER` varchar(255) DEFAULT NULL,\n" +
                     "  PRIMARY KEY (`TEM_ID`)\n" +
                     ") ENGINE=InnoDB AUTO_INCREMENT=82575 DEFAULT CHARSET=utf8;";
             Connection conn = null;
             Statement stmt = null;
             try{
                 //注册jdbc驱动
                 Class.forName(JDBC_DRIVER);
                 //打开连接
                 System.out.println("//连接数据库");
                 conn = DriverManager.getConnection(DB_URL,name,pwd);
                 //执行创建表
                 System.out.println("//创建表");
                 stmt = conn.createStatement();
                 int cc = stmt.executeUpdate(creatsql);
                 //
                 stmt.close();
                 conn.close();
                 if(cc==0) {
                     System.out.println("成功创建表！");
                     AddTable addtables = new AddTable();
                     addtables.setTableName(temperatures);
                     addtables.setTime(d2);
                     String a = addTableServer.insert(addtables);
                     if(a != "false" && !"false".equals(a)){
                         AddTable addt = new AddTable();
                         addt.setTableName(tablenamess);
                         addt.setEndTime(d2);
                         addTableServer.update(addt);
                         RedisUtils redis = new RedisUtils();
                         redis.setString("tablenamesc",temperatures);
                         redis.setObject("addtablesc",addtable);
//                         redis.setString("tablename",temperatures);
//                         redis.setObject("addtable",addtable);
                     }
                 }else{
                     System.out.println("创建表失败！");
                     return "false";
                 }
             }catch(Exception e){
                 System.out.println("创建表失败！");
                 return "false";
             }
         return temperatures;
     }



}
