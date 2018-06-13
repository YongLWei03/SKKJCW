package com.example.skkj.entity;
 /**
      * @Author ZhouNan
      * @Description 更新消息
      * @params
      * @Date 2018/6/11 0011  14:20
      */
public class Notification {

    private Integer notId;

    private String notinfName;

    private String time;

     public Integer getNotId() {
         return notId;
     }

     public void setNotId(Integer notId) {
         this.notId = notId;
     }

     public String getNotinfName() {
         return notinfName;
     }

     public String getTime() {
         return time;
     }

     public void setTime(String time) {
         this.time = time;
     }

     public void setNotinfName(String notinfName) {
         this.notinfName = notinfName;
     }

     @Override
     public String toString() {
         return "Notification{" +
                 "notId=" + notId +
                 ", notinfName='" + notinfName + '\'' +
                 ", time='" + time + '\'' +
                 '}';
     }
 }
