package com.example.skkj.entity;
 /**
      * @Author ZhouNan
      * @Description 温度的计算方法
      * @params
      * @Date 2018/3/12 0012  09:25
      */
public class CalculationWd {
     private String inA;//0.表示自身温度，1.表示b入+一个随机数，2.表示c入+一个随机数，3.表示A出+(B入-B出)+一个随机数，4.表示A出+(C入-C出)+一个随机数

     private String inB;//0.表示自身温度，1.表示a入+一个随机数，2.表示c入+一个随机数，3.表示B出+(A入-A出)+一个随机数，4.表示B出+(C入-C出)+一个随机数

     private String inC;//0.表示自身温度，1.表示a入+一个随机数，2.表示B入+一个随机数，3.表示A出+(B入-B出)+一个随机数，4.表示B出+(A入-A出)+一个随机数

     private String outA;//0.表示自身温度，1.表示B出-一个随机数，2.表示C出-一个随机数，3.表示A入-(B入-B出)-一个随机数，4.表示A入-(C入-C出)-一个随机数

     private String outB;//0.表示自身温度，1.表示A出-一个随机数，2.表示C出-一个随机数，3.表示B入-(A入-A出)-一个随机数，4.表示B入-(C入-C出)-一个随机数

     private String outC;//0.表示自身温度，1.表示A出-一个随机数，2.表示B出-一个随机数，3.表示C入-(A入-A出)-一个随机数，4.表示C入-(B入-B出)-一个随机数

     private String deviceNumber;

     private String caId;

     private String inAcs;//A修改温度

     private String inBcs;

     private String inCcs;

     private String outAcs;

     private String outBcs;

     private String outCcs;

     public String getInAcs() {
         return inAcs;
     }

     public void setInAcs(String inAcs) {
         this.inAcs = inAcs;
     }

     public String getInBcs() {
         return inBcs;
     }

     public void setInBcs(String inBcs) {
         this.inBcs = inBcs;
     }

     public String getInCcs() {
         return inCcs;
     }

     public void setInCcs(String inCcs) {
         this.inCcs = inCcs;
     }

     public String getOutAcs() {
         return outAcs;
     }

     public void setOutAcs(String outAcs) {
         this.outAcs = outAcs;
     }

     public String getOutBcs() {
         return outBcs;
     }

     public void setOutBcs(String outBcs) {
         this.outBcs = outBcs;
     }

     public String getOutCcs() {
         return outCcs;
     }

     public void setOutCcs(String outCcs) {
         this.outCcs = outCcs;
     }

     public String getInA() {
         return inA;
     }

     public void setInA(String inA) {
         this.inA = inA;
     }

     public String getInB() {
         return inB;
     }

     public void setInB(String inB) {
         this.inB = inB;
     }

     public String getInC() {
         return inC;
     }

     public void setInC(String inC) {
         this.inC = inC;
     }

     public String getOutA() {
         return outA;
     }

     public void setOutA(String outA) {
         this.outA = outA;
     }

     public String getOutB() {
         return outB;
     }

     public void setOutB(String outB) {
         this.outB = outB;
     }

     public String getOutC() {
         return outC;
     }

     public void setOutC(String outC) {
         this.outC = outC;
     }

     public String getDeviceNumber() {
         return deviceNumber;
     }

     public void setDeviceNumber(String deviceNumber) {
         this.deviceNumber = deviceNumber;
     }

     public String getCaId() {
         return caId;
     }

     public void setCaId(String caId) {
         this.caId = caId;
     }

     @Override
     public String toString() {
         return "CalculationWd{" +
                 "inA='" + inA + '\'' +
                 ", inB='" + inB + '\'' +
                 ", inC='" + inC + '\'' +
                 ", outA='" + outA + '\'' +
                 ", outB='" + outB + '\'' +
                 ", outC='" + outC + '\'' +
                 ", deviceNumber='" + deviceNumber + '\'' +
                 ", caId='" + caId + '\'' +
                 '}';
     }
 }
