package com.example.skkj.comment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class TimeUtile {
	/**
	 * 时间字符串比较
	 * */
	public static int compareToTime(String startTime,String endTime){
		int result=startTime.compareTo(endTime);
		return result;
	}
	
	/**
	 * 时间格式用做文件名
	 * */
	public static String dateTime(){
		   Date date = new Date();
	       SimpleDateFormat fomartt = new SimpleDateFormat("yyyyMMddH");
	       String time = fomartt.format(date);
	       String uuid = UUID.randomUUID().toString().replaceAll("-", "");
	       return uuid+time;
	}
	
	//时间格式
	public static String dateTimeFomar(){
		   Date date = new Date();
	       SimpleDateFormat fomartt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       String time = fomartt.format(date);
	       return time;
	}
	
	//时间格式
	public static String dateTimeYY(){
		Date date = new Date();
		SimpleDateFormat fomartt = new SimpleDateFormat("yyyy-MM-dd");
		String time = fomartt.format(date);
		return time;
	}


    //时间格式
    public static String dateTimess(){
        Date date = new Date();
        SimpleDateFormat fomartt = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
        String time = fomartt.format(date);
        return time;
    }
	
	//时间字符串格式转换
	public static String dateTimeString(String DateTime){
	       SimpleDateFormat fomartt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	         Date date = new Date(DateTime);
	       String time = fomartt.format(date);
	       return time;
	}
	
	 /**
	      * @Author ZhouNan
	      * @Description  电信时间搓解析
	      * @params
	      * @Date 2018/1/5 0005  14:52
	      */
	 public static String dateTimeStringDx(String DateTime){
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
		 Date date = null;
		 try {
			 date = sdf.parse(DateTime);
		 } catch (ParseException e) {
			 e.printStackTrace();
		 }
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(date);
		 /* HOUR_OF_DAY 指示一天中的小时 */
		 calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + 8);
		 SimpleDateFormat fomartt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String time = fomartt.format(calendar.getTime());
		 return time;
	 }

    /**
     * @Author ZhouNan
     * @Description  日加一
     * @params
     * @Date 2018/1/5 0005  14:52
     */
    public static String dateTimeStringR(String DateTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(DateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        /* HOUR_OF_DAY 指示一天中的小时 */
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.DAY_OF_MONTH) + 1);
        SimpleDateFormat fomartt = new SimpleDateFormat("yyyy-MM-dd");
        String time = fomartt.format(calendar.getTime());
        return time;
    }

	/**
	 * @Author ZhouNan
	 * @Description  日减
	 * @params
	 * @Date 2018/1/5 0005  14:52
	 */
	public static String dateTimeStringRjian(String DateTime){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(DateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		/* HOUR_OF_DAY 指示一天中的小时 */
		calendar.add(calendar.DATE, -7);
		SimpleDateFormat fomartt = new SimpleDateFormat("yyyy-MM-dd");
		String time = fomartt.format(calendar.getTime());
		return time;
	}

	/**
	 * @Author ZhouNan
	 * @Description  月减
	 * @params
	 * @Date 2018/1/5 0005  14:52
	 */
	public static String dateTimeStringYjian(String DateTime){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(DateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		/* HOUR_OF_DAY 指示一天中的小时 */
		calendar.add(Calendar.MONTH, -1);
		SimpleDateFormat fomartt = new SimpleDateFormat("yyyy-MM-dd");
		String time = fomartt.format(calendar.getTime());
		return time;
	}


    /**
	       * @Author ZhouNan
	       * @Description  历史数据查询时间搓
	       * @params
	       * @Date 2018/1/27 0027  15:43
	       */
        public static String lishicx(String st){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                date = sdf.parse(st);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat fomartt = new SimpleDateFormat("yyyy-MM-dd");
            String time = fomartt.format(date);
            return time;
        }

         /**
              * @Author ZhouNan
              * @Description 时间格式转换
              * @params
              * @Date 2018/2/26 0026  10:19
              */
		 public static String sfm(String st){
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 Date date = null;
			 try {
				 date = sdf.parse(st);
			 } catch (ParseException e) {
				 e.printStackTrace();
			 }
			 SimpleDateFormat fomartt = new SimpleDateFormat("HH:mm");
			 String time = fomartt.format(date);
			 return time;
		 }

	/**
	 * @Author ZhouNan
	 * @Description 时间格式转换 年
	 * @params
	 * @Date 2018/2/26 0026  10:19
	 */
	public static String dataqNian(String st){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(st);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat fomartt = new SimpleDateFormat("yyyy");
		String time = fomartt.format(date);
		return time;
	}

	/**
	 * @Author ZhouNan
	 * @Description  定时发送信息的结束时间处理
	 * @params
	 * @Date 2018/1/27 0027  15:43
	 */
	public static String endTime(String st){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(st);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat fomartt = new SimpleDateFormat("yyyy-MM-dd 00:30:00");
		String time = fomartt.format(date);
		return time;
	}

	 /**
	      * @Author ZhouNan
	      * @Description 前一个小时计算
	      * @params
	      * @Date 2018/3/21 0021  09:35
	      */
	 public static String TimeJ(String st){
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date = null;
		 try {
			 date = sdf.parse(st);
		 } catch (ParseException e) {
			 e.printStackTrace();
		 }

		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(date);
		 /* HOUR_OF_DAY 指示一天中的小时 */
		 calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
		 String time = sdf.format(calendar.getTime());
		 return time;
	 }


	  /**
	       * @Author ZhouNan
	       * @Description 时间搓
	       * @params
	       * @Date 2018/1/10 0010  17:18
	       */
	  public static long shijiancuo(){
		  Date data = new Date();
		  long daTime = data.getTime()/1000;
		  return daTime;
	  }

	   /**
	        * @Author ZhouNan
	        * @Description 时间搓转换
	        * @params
	        * @Date 2018/2/9 0009  14:31
	        */
	   public static String timeStamp2Date(String seconds) {
		          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		          return sdf.format(new Date(Long.valueOf(seconds+"000")));
	   }

	    /**
	         * @Author ZhouNan
	         * @Description 当前时间和现在时间的时间差
	         * @params
	         * @Date 2018/2/26 0026  16:26
	         */
		public static String sjc(String sj) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = null;
			try {
				date = sdf.parse(sj);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			long ss = date.getTime();
			Date aa = new Date();
			long cc = aa.getTime();
			long sjcc = (cc - ss) / (1000 * 60 * 60);
			if(sjcc >= 5){
				return "false";
			}else {
				return "true";
			}
		}
	public static void main(String[] args) {
		//T代表后面跟着时间，Z代表UTC统一时间
//格式化时间
//		String time = sdf.format(new Date());
//		System.out.println(time);
//		String a = TimeUtile.dateTimeStringDx("20180127T045130Z");
//		System.out.println("a:"+a);
//        System.out.println(time);
//解析时间 2016-01-05T15:09:54

//        System.out.println(TimeUtile.sfm("2018-02-06 09:45:30"));
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = null;
//        try {
//            date = sdf.parse("2018-02-06 09:45:30");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//		long ss = date.getTime();
//		Date aa = new Date();
//		long cc = aa.getTime();
//		System.out.println((cc-ss)/(1000*60*60));
		String s = dateTimess();

		System.out.println(timeStamp2Date("1525964289"));

	}

	//随机数
	public static int random(){
		int max=9999;
		int min=1000;
		Random random = new Random();
		int s = random.nextInt(max)%(max-min+1) + min;
		return s;
	}

	
}
