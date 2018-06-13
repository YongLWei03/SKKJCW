package com.example.skkj.comment;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.text.DecimalFormat;

public class Encoding {
	 //计算16进制对应的数值
    public static int GetHex(char ch) throws Exception{  
        if ( ch>='0' && ch<='9' )  
            return (int)(ch-'0');  
        if ( ch>='a' && ch<='f' )  
            return (int)(ch-'a'+10);  
        if ( ch>='A' && ch<='F' )  
            return (int)(ch-'A'+10);  
        throw new Exception("error param");  
    }  
	  
	  public static int HexToInt(String strHex){  
	        int nResult = 0;  
	        if ( !IsHex(strHex) )  
	            return nResult;  
	        String str = strHex.toUpperCase();  
	        if ( str.length() > 2 ){  
	            if ( str.charAt(0) == '0' && str.charAt(1) == 'X' ){  
	                str = str.substring(2);  
	            }  
	        }  
	        int nLen = str.length();  
	        for ( int i=0; i<nLen; ++i ){  
	            char ch = str.charAt(nLen-i-1);  
	            try {  
	                nResult += (GetHex(ch)*GetPower(16, i));  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        }  
	        return nResult;  
	    }  
	  //判断是否是16进制数  
	  public static boolean IsHex(String strHex){  
	  int i = 0;  
	  if ( strHex.length() > 2 ){  
	  if ( strHex.charAt(0) == '0' && (strHex.charAt(1) == 'X' || strHex.charAt(1) == 'x') ){  
	  i = 2;  
	  }  
	  }  
	  for ( ; i<strHex.length(); ++i ){  
	  char ch = strHex.charAt(i);  
	  if ( (ch>='0' && ch<='9') || (ch>='A' && ch<='F') || (ch>='a' && ch<='f') )  
	  continue;  
	  return false;  
	  }  
	  return true;  
	  }  
	//计算幂  
	    public static int GetPower(int nValue, int nCount) throws Exception{  
	        if ( nCount <0 )  
	            throw new Exception("nCount can't small than 1!");  
	        if ( nCount == 0 )  
	            return 1;  
	        int nSum = 1;  
	        for ( int i=0; i<nCount; ++i ){  
	            nSum = nSum*nValue;  
	        }  
	        return nSum;  
	    }  
	    
	    /**
	     * 十六进制字符串转float.
	     * 
	     * @param hexStr
	     * @return
	     * @throws DecoderException
	     */
	    public static Float hexStr2Float(String hexStr) throws DecoderException {
	        Float result = null;
	        // 先通过apahce的 hex类转换十六进制字符串为byte数组. 也可以自己用JDK原声的方法来循环转
	        // Character.digit(ch, 16);
	        byte[] decodes = Hex.decodeHex(hexStr.toCharArray());
	        // 获得byte转float的结果
	        result = getFloat(decodes, 0);
	        return result;
	    }
	    
	    /**
	     * 通过byte数组取得float
	     * 
	     * @param b
	     * @param index
	     *            第几位开始取.
	     * @return
	     */
	    public static float getFloat(byte[] b, int index) {
	        int l;
	        l = b[index + 0];
	        l &= 0xff;
	        l |= ((long) b[index + 1] << 8);
	        l &= 0xffff;
	        l |= ((long) b[index + 2] << 16);
	        l &= 0xffffff;
	        l |= ((long) b[index + 3] << 24);
	        return Float.intBitsToFloat(l);
	    }


	     /**
	          * @Author ZhouNan
	          * @Description  16进制转10进制数据
	          * @params
	          * @Date 2018/1/4 0004  17:18
	          */
	     public static String intpa(String num){
			 String floa = "NAN";
			 try {
				 String numShort = String.valueOf(Integer.valueOf(num, 16).shortValue());
				 if(numShort != "-10000" && !"-10000".equals(numShort)) {
					 float floas = Float.parseFloat(numShort) / 100;
					 DecimalFormat decimalFormat=new DecimalFormat("0.00");
					 floa =  decimalFormat.format(floas);
					 if (floas < -50f || floas > 180f) {
						 floa = "NAN";
					 }
				 }
			 }catch (Exception e){
				 System.out.println("数据类型不服解析错误");
			 }finally {
				 return floa;
			 }

         }

          /**
               * @Author ZhouNan
               * @Description  4字节16转10
               * @params
               * @Date 2018/1/19 0019  16:53
               */
          public static String intpaFour(String num){
              String floa = "NAN";
              try {
					if(!"FFFFFFFF".equals(num) && "FFFFFFFF"!=num&&num!="EEEEEEEE" &&!"EEEEEEEE".equals(num)&& num!="DDDDDDDD" &&!"DDDDDDDD".equals(num)){
						Float value = BinaryStringToFloat(HexString2binaryString(num));
						DecimalFormat decimalFormat=new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
						floa = decimalFormat.format(value);//format 返回的是字符串
						float fals = Float.parseFloat(floa);
						if(fals < -50f || fals >200f){
							floa = "NAN";
						}
				}
//				else if(temper != null && !"".equals(temper)){
//					float aaa = Float.parseFloat(temper.getInB()) - Float.parseFloat(temper.getOutB());
//					Random rand = new Random();
//					double aaac = rand.nextInt(20) * 0.01;
//					System.out.println(aaac);
//					Float AAA = Float.parseFloat(temper.getInC());
//					DecimalFormat decimalFormat=new DecimalFormat("0.00");
//					floa = decimalFormat.format(AAA-(Float.parseFloat(String.valueOf(aaac))+aaa));
//				}
              }catch (Exception e){
//                    e.printStackTrace();
                  System.out.println("数据类型不服解析错误");
              }finally {
				  return floa;
			  }

          }

	/**
	 * @Author ZhouNan
	 * @Description  4字节16转10
	 * @params
	 * @Date 2018/1/19 0019  16:53
	 */
	public static String intpaFourXh(String num){
		String floa = "NAN";
		try {
			if(!"FFFFFFFF".equals(num) && "FFFFFFFF"!=num&&num!="EEEEEEEE" &&!"EEEEEEEE".equals(num)&& num!="DDDDDDDD" &&!"DDDDDDDD".equals(num)){
				floa = String.valueOf(Integer.valueOf(num, 16));
			}
		}catch (Exception e){
//                    e.printStackTrace();
			System.out.println("数据类型不服解析错误");
		}finally {
			return floa;
		}

	}

	 /**
	      * @Author ZhouNan
	      * @Description 微星温度信号解析
	      * @params
	      * @Date 2018/3/27 0027  09:27
	      */
	 public static String wexin(String num){
	     String flat="";
        try {
            flat = String.valueOf(Integer.valueOf(num, 16));
            if(flat != "0" && !"0".equals(flat)){
                DecimalFormat decimalFormat=new DecimalFormat("0.00");
                flat =  decimalFormat.format(Float.parseFloat(flat)/100);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return flat;
        }
	 }

	 public static int gs(String A){
	 	int a = 0;
	 	try {
			a = Integer.valueOf(A, 16).shortValue();
		}catch (Exception e){
	 		e.printStackTrace();
		}
		return a;
	 }

	/**
	 * 这里只处理规格化数，非规格化数，NaN，finite等没有考虑
	 * 2进制字符串转Float数，包括（正数和负数）
	 * @param binaryString
	 * @return
	 */
	static float BinaryStringToFloat(final String binaryString) {
		// float是32位，将这个binaryString左边补0补足32位，如果是Double补足64位。
		final String stringValue = LeftPad(binaryString, '0', 32);
		// 首位是符号部分，占1位。
		// 如果符号位是0则代表正数，1代表负数
		final int sign = stringValue.charAt(0) == '0' ? 1 : -1;
		// 第2到9位是指数部分，float占8位，double占11位。
		final String exponentStr = stringValue.substring(1, 9);
		// 将这个二进制字符串转成整数，由于指数部分加了偏移量（float偏移量是127，double是1023）
		// 所以实际值要减去127
		final int exponent = Integer.parseInt(exponentStr, 2) - 127;
		// 最后的23位是尾数部分，由于规格化数，小数点左边隐含一个1，现在加上
		final String mantissaStr = "1".concat(stringValue.substring(9, 32));
		// 这里用double，尽量保持精度，最好用BigDecimal，这里只是方便计算所以用double
		double mantissa = 0.0;

		for (int i = 0; i < mantissaStr.length(); i++) {
			final int intValue = Character.getNumericValue(mantissaStr.charAt(i));
			// 计算小数部分，具体请查阅二进制小数转10进制数相关资料
			mantissa += (intValue * Math.pow(2, -i));
		}
		// 根据IEEE 754 标准计算：符号位 * 2的指数次方 * 尾数部分
		return (float) (sign * Math.pow(2, exponent) * mantissa);
	}


	static String LeftPad(final String str, final char padChar, int length) {
		final int repeat = length - str.length();

		if (repeat <= 0) {
			return str;
		}

		final char[] buf = new char[repeat];
		for (int i = 0; i < buf.length; i++) {
			buf[i] = padChar;
		}
		return new String(buf).concat(str);
	}

	/**
	 * 16进制字符串转化二进制字符中
	 *
	 * @param hexString
	 * @return
	 */
	public static String HexString2binaryString(String hexString) {
		if (hexString == null || hexString.length() % 2 != 0)
			return null;
		String bString = "", tmp;
		for (int i = 0; i < hexString.length(); i++) {
			tmp = "0000" + Integer.toBinaryString(Integer.parseInt(hexString.substring(i, i + 1), 16));
			bString += tmp.substring(tmp.length() - 4);
		}
		return bString;
	}
	public static void main(String[] args) {
//		Float value = Float.intBitsToFloat(Integer.valueOf("00000000", 16));
////		DecimalFormat decimalFormat=new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
////		String p=decimalFormat.format(value);//format 返回的是字符串
////		System.out.println(p);

//		int c = Integer.parseInt("00000100",16);

//		Float.

//		for (int i = 0; i < 10; i++) {
//			System.out.println();
//		}

		try {
//            Float f = Float.intBitsToFloat((int)
//			FFFF0003     Long.parseLong("00000100, 16));
////////       419E9C97
//			Float c = hexStr2Float("79C9E914");
//			Float c = intpa("419E9C97");
//			System.out.println(Integer.valueOf("00000010", 16));
//			String hex = x.toHexString(x);
//			System.out.println(hex);
//			System.out.println(intpaFour("4D980000"));
//			Integer x = 80;
//			String hex = x.toHexString(x);
		    String str = "1.11";
			System.out.println(Float.parseFloat(str));
//			System.out.println(String.valueOf(Integer.valueOf("0C", 16).shortValue()));
//			System.out.println(2+Float.valueOf("-1"));
			//二进制转10进制
//			System.out.println("Output: "+Integer.parseInt("00000000000011000011000001000001",2));
//			intpaFour("4D980000")
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
