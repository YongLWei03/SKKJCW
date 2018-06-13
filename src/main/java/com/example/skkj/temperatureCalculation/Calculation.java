package com.example.skkj.temperatureCalculation;



import com.example.skkj.comment.Encoding;
import com.example.skkj.entity.CalculationWd;
import com.example.skkj.entity.Temperature;
import com.example.skkj.service.CalculationWdServer;

import java.text.DecimalFormat;
import java.util.Random;

public class Calculation {

     /**
          * @Author ZhouNan
          * @Description 温度计算查询方式
          * @params
          * @Date 2018/3/12 0012  09:17
          */
    public Temperature phase(CalculationWdServer calculationWdServer, Temperature temperature, String oder, Integer denum){
        CalculationWd calcu = new CalculationWd();
        Temperature temp = new Temperature();
        try {
            calcu = calculationWdServer.select(temperature.getDeviceNumber());
            if(oder != "0" && !"0".equals(oder)){
                temp = enter(temperature,denum);
            }else {
                temp = enterwx(temperature,denum);
            }
            if(calcu != null && !"".equals(calcu)){
                //温度计算方式判断
                temp =  judge(calcu,temperature);
                temp = wdjj(temp,calcu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

     /**
          * @Author ZhouNan
          * @Description 温度计算方式判断
          * @params
          * @Date 2018/3/12 0012  09:54
          */
     public Temperature judge(CalculationWd calculationWd,Temperature temperature){
         String inaf = calculationWd.getInA();
         String inbf = calculationWd.getInB();
         String incf = calculationWd.getInC();
         String outaf = calculationWd.getOutA();
         String outbf = calculationWd.getOutB();
         String outcf = calculationWd.getOutC();
         if(inaf != null && !"".equals(inaf) && inaf != "0" && !"0".equals(inaf)){
             if(inaf == "1" || "1".equals(inaf) || inaf == "2" || "2".equals(inaf)){
                 temperature =  connection(inaf,"1",temperature);
             }else {
                 temperature =  phaseSubtraction(inaf,"1",temperature);
             }
         }
         if(inbf != null && !"".equals(inbf)&& inbf != "0" && !"0".equals(inbf)){
             if(inbf == "1" || "1".equals(inbf) || inbf == "2" || "2".equals(inbf)){
                 temperature =  connection(inbf,"2",temperature);
             }else {
                 temperature =  phaseSubtraction(inbf,"2",temperature);
             }
         }
         if(incf != null && !"".equals(incf)&& incf != "0" && !"0".equals(incf)){
             if(incf == "1" || "1".equals(incf) || incf == "2" || "2".equals(incf)){
                 temperature =  connection(incf,"3",temperature);
             }else {
                 temperature =  phaseSubtraction(incf,"3",temperature);
             }
         }
         if(outaf != null && !"".equals(outaf)&& outaf != "0" && !"0".equals(outaf)){
             if(outaf == "1" || "1".equals(outaf) || outaf == "2" || "2".equals(outaf)){
                 temperature =  connection(outaf,"4",temperature);
             }else {
                 temperature =  phaseSubtraction(outaf,"4",temperature);
             }
         }
         if(outbf != null && !"".equals(outbf)&& outbf != "0" && !"0".equals(outbf)){
             if(outbf == "1" || "1".equals(outbf) || outbf == "2" || "2".equals(outbf)){
                 temperature =  connection(outbf,"5",temperature);
             }else {
                 temperature =  phaseSubtraction(outbf,"5",temperature);
             }
         }
         if(outcf != null && !"".equals(outcf)&& outcf != "0" && !"0".equals(outcf)){
             if(outcf == "1" || "1".equals(outcf) || outcf == "2" || "2".equals(outcf)){
                 temperature =  connection(outcf,"6",temperature);
             }else {
                 temperature =  phaseSubtraction(outcf,"6",temperature);
             }
         }
        return temperature;
     }

      /**
           * @Author ZhouNan
           * @Description 入温度计算 本身  26所
           * @params state 0 ， wd 温度代表的相位
           * @Date 2018/3/12 0012  10:11
           */
      public Temperature enter(Temperature temperature,Integer denum){
              temperature.setInA(Encoding.intpaFour(temperature.getInA()));
              temperature.setInB(Encoding.intpaFour(temperature.getInB()));
              temperature.setInC(Encoding.intpaFour(temperature.getInC()));
              temperature.setOutA(Encoding.intpaFour(temperature.getOutA()));
              temperature.setOutB(Encoding.intpaFour(temperature.getOutB()));
              temperature.setOutC(Encoding.intpaFour(temperature.getOutC()));
              if(denum>6){
                  temperature.setContactA(Encoding.intpaFour(temperature.getContactA()));
                  temperature.setContactB(Encoding.intpaFour(temperature.getContactB()));
                  temperature.setContactC(Encoding.intpaFour(temperature.getContactC()));
              }
              if(denum == 12){
                  temperature.setContactD(Encoding.intpaFour(temperature.getContactD()));
                  temperature.setContactE(Encoding.intpaFour(temperature.getContactE()));
                  temperature.setContactF(Encoding.intpaFour(temperature.getContactF()));
              }
          return temperature;
      }

      /**
           * @Author ZhouNan
           * @Description 入温度计算 本身  微星
           * @params state 0 ， wd 温度代表的相位
           * @Date 2018/3/12 0012  10:11
           */
      public Temperature enterwx(Temperature temperature,Integer denum){
              temperature.setInA(Encoding.intpa(temperature.getInA()));
              temperature.setInB(Encoding.intpa(temperature.getInB()));
              temperature.setInC(Encoding.intpa(temperature.getInC()));
              temperature.setOutA(Encoding.intpa(temperature.getOutA()));
              temperature.setOutB(Encoding.intpa(temperature.getOutB()));
              temperature.setOutC(Encoding.intpa(temperature.getOutC()));
          if(denum>6){
              temperature.setContactA(Encoding.intpa(temperature.getContactA()));
              temperature.setContactB(Encoding.intpa(temperature.getContactB()));
              temperature.setContactC(Encoding.intpa(temperature.getContactC()));
          }
          if(denum == 12){
              temperature.setContactD(Encoding.intpa(temperature.getContactD()));
              temperature.setContactE(Encoding.intpa(temperature.getContactE()));
              temperature.setContactF(Encoding.intpa(temperature.getContactF()));
          }


          return temperature;
      }

    /**
     * @Author ZhouNan
     * @Description 入温度计算 相连相位相加
     * @params state 1，2 ， wd 温度代表的相位
     * @Date 2018/3/12 0012  10:11
     */
    public Temperature connection(String state,String wd,Temperature temperature){
        if(state == "1" || "1".equals(state)){
            if(wd == "1" || "1".equals(wd)){
                temperature.setInA(intpaFourxr(temperature.getInB(),state,"1"));
            }else if(wd == "2" || "2".equals(wd)){
                temperature.setInB(intpaFourxr(temperature.getInA(),state,"2"));
            }else if(wd == "3" || "3".equals(wd)){
                temperature.setInC(intpaFourxr(temperature.getInA(),state,"3"));
            }else if(wd == "4" || "4".equals(wd)){
                temperature.setOutA(intpaFourxr(temperature.getOutB(),state,"4"));
            }else if(wd == "5" || "5".equals(wd)){
                temperature.setOutB(intpaFourxr(temperature.getOutA(),state,"5"));
            }else {
                temperature.setOutC(intpaFourxr(temperature.getOutA(),state,"6"));
            }
        }else {
            if(wd == "1" || "1".equals(wd)){
                temperature.setInA(intpaFourxr(temperature.getInC(),state,"1"));
            }else if(wd == "2" || "2".equals(wd)){
                temperature.setInB(intpaFourxr(temperature.getInC(),state,"2"));
            }else if(wd == "3" || "3".equals(wd)){
                temperature.setInC(intpaFourxr(temperature.getInB(),state,"3"));
            }else if(wd == "4" || "4".equals(wd)){
                temperature.setOutA(intpaFourxr(temperature.getOutC(),state,"4"));
            }else if(wd == "5" || "5".equals(wd)){
                temperature.setOutB(intpaFourxr(temperature.getOutC(),state,"5"));
            }else {
                temperature.setOutC(intpaFourxr(temperature.getOutB(),state,"6"));
            }
        }
        return temperature;
    }

    /**
     * @Author ZhouNan
     * @Description 温度计算 相位相减在计算
     * @params state 3，4， wd 温度代表的相位
     * @Date 2018/3/12 0012  10:11
     */
    public Temperature phaseSubtraction(String state,String wd,Temperature temperature){
        if(state == "3" || "3".equals(state)){
            if(wd == "1" || "1".equals(wd)){
                temperature.setInA(intpaFourxrXj(temperature.getOutA(),state,wd,temperature.getInB(),temperature.getOutB()));
            }else if(wd == "2" || "2".equals(wd)){
                temperature.setInB(intpaFourxrXj(temperature.getOutB(),state,wd,temperature.getInA(),temperature.getOutA()));
            }else if(wd == "3" || "3".equals(wd)){
                temperature.setInC(intpaFourxrXj(temperature.getOutC(),state,wd,temperature.getInA(),temperature.getOutA()));
            }else if(wd == "4" || "4".equals(wd)){
                temperature.setOutA(intpaFourxrXj(temperature.getInA(),state,wd,temperature.getInB(),temperature.getOutB()));
            }else if(wd == "5" || "5".equals(wd)){
                temperature.setOutB(intpaFourxrXj(temperature.getInB(),state,wd,temperature.getInA(),temperature.getOutA()));
            }else {
                temperature.setOutC(intpaFourxrXj(temperature.getInC(),state,wd,temperature.getInA(),temperature.getOutA()));
            }

        }else {
            if(wd == "1" || "1".equals(wd)){
                temperature.setInA(intpaFourxrXj(temperature.getOutA(),state,wd,temperature.getInC(),temperature.getOutC()));
            }else if(wd == "2" || "2".equals(wd)){
                temperature.setInB(intpaFourxrXj(temperature.getOutB(),state,wd,temperature.getInC(),temperature.getOutC()));
            }else if(wd == "3" || "3".equals(wd)){
                temperature.setInC(intpaFourxrXj(temperature.getOutC(),state,wd,temperature.getInB(),temperature.getOutB()));
            }else if(wd == "4" || "4".equals(wd)){
                temperature.setOutA(intpaFourxrXj(temperature.getInA(),state,wd,temperature.getInC(),temperature.getOutC()));
            }else if(wd == "5" || "5".equals(wd)){
                temperature.setOutB(intpaFourxrXj(temperature.getInB(),state,wd,temperature.getInC(),temperature.getOutC()));
            }else {
                temperature.setOutC(intpaFourxrXj(temperature.getInC(),state,wd,temperature.getInB(),temperature.getOutB()));
            }
        }
        return temperature;
    }
    /**
     * @Author ZhouNan
     * @Description  4字节16转10 相连温度相加
     * @params
     * @Date 2018/1/19 0019  16:53
     */
    public  String intpaFourxr(String num,String state,String wd){
        String floa = num;
            DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
            if (floa != "NAN" && !"NAN".equals(floa)) {
                if (state == "1" || "1".equals(state) || state == "2" || "2".equals(state)) {
                    float floas = Float.parseFloat(floa);
                    //判断是否入得3个温度计算
                    if (wd == "1" || "1".equals(wd) || wd == "2" || "2".equals(wd) || wd == "3" || "3".equals(wd)) {
                        Float sjs = sjzhq();
                        floa = decimalFormat.format(floas + sjs);
                    } else {
                        //获取随机值
                        Float sjs = sjzhq();
                        floa = decimalFormat.format(floas - sjs);
                    }
                }
            }
            return floa;
    }


    /**
     * @Author ZhouNan
     * @Description  4字节16转10 对应相加减
     * @params
     * @Date 2018/1/19 0019  16:53
     */
    public  String intpaFourxrXj(String num,String state,String wd,String numone,String numtwo){
        String floa = num;
        DecimalFormat decimalFormat=new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        if(num != "NAN" && !"NAN".equals(num) && numone != "NAN" && !"NAN".equals(numone) && numtwo != "NAN" && !"NAN".equals(numtwo)){
            if(state == "3" || "3".equals(state) || state == "4" || "4".equals(state)){
                                //判断是否入得3个温度计算
                if(wd == "1" || "1".equals(wd) || wd == "2" || "2".equals(wd) || wd == "3" || "3".equals(wd)){
                    //获取随机值
                    Float sjs = sjzhq();
                    float numoneF = Float.parseFloat(numone);
                    float numtwoF = Float.parseFloat(numtwo);
                    float numF = Float.parseFloat(num);
                    floa = decimalFormat.format(numF+(numoneF-numtwoF)+sjs);
                }else {
                    //获取随机值
                    Float sjs = sjzhq();
                    float numoneF = Float.parseFloat(numone);
                    float numtwoF = Float.parseFloat(numtwo);
                    float numF = Float.parseFloat(num);
                    floa = decimalFormat.format(numF-(numoneF-numtwoF)-sjs);
                }
            }
        }
            return floa;

    }
     /**
          * @Author ZhouNan
          * @Description 随机值获取
          * @params
          * @Date 2018/3/12 0012  17:09
          */
    public Float sjzhq(){
        //获取随机值
        Random rand = new Random();
        double sjz = rand.nextInt(20) * 0.01;
        float sjs = Float.parseFloat(String.valueOf(sjz));
        return sjs;
    }



     /**
          * @Author ZhouNan
          * @Description 温度在当前温度加几度
          * @params
          * @Date 2018/3/26 0026  11:17
          */
     public Temperature wdjj(Temperature temperature,CalculationWd calculationWd){
         DecimalFormat decimalFormat=new DecimalFormat("0.00");
            String inAcs = calculationWd.getInAcs();
            String inBcs = calculationWd.getInBcs();
            String inCcs = calculationWd.getInCcs();
            String outAcs = calculationWd.getOutAcs();
            String outBcs = calculationWd.getOutBcs();
            String outCcs = calculationWd.getOutCcs();
             String intA = temperature.getInA();
             String intB = temperature.getInB();
             String intC = temperature.getInC();
             String outA = temperature.getOutA();
             String outB = temperature.getOutB();
             String outC = temperature.getOutC();
             if(intA != "NAN" && !"NAN".equals(intA) && !"".equals(inAcs) && inAcs != null){
                 temperature.setInA(decimalFormat.format(Float.parseFloat(intA) + Float.parseFloat(inAcs)));
             }
             if(intB != "NAN" && !"NAN".equals(intB) && !"".equals(inBcs) && inBcs != null){
                 temperature.setInB(decimalFormat.format(Float.parseFloat(intB) + Float.parseFloat(inBcs)));
             }
             if(intC != "NAN" && !"NAN".equals(intC) && !"".equals(inCcs) && inCcs != null){
                 temperature.setInC(decimalFormat.format(Float.parseFloat(intC) + Float.parseFloat(inCcs)));
             }
             if(outA != "NAN" && !"NAN".equals(outA) && !"".equals(outAcs) && outAcs != null){
                 temperature.setOutA(decimalFormat.format(Float.parseFloat(outA) + Float.parseFloat(outAcs)));
             }
             if(outB != "NAN" && !"NAN".equals(outB) && !"".equals(outBcs) && outBcs != null){
                 temperature.setOutB(decimalFormat.format(Float.parseFloat(outB) + Float.parseFloat(outBcs)));
             }
             if(outC != "NAN" && !"NAN".equals(outC) && !"".equals(outCcs) && outCcs != null){
                 temperature.setOutC(decimalFormat.format(Float.parseFloat(outC) + Float.parseFloat(outCcs)));
             }
            return temperature;
     }
}
