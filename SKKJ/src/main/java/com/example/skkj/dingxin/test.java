package com.example.skkj.dingxin;

import java.util.ArrayList;
import java.util.List;

public class test {

    public static final int MIN_MID_VALUE = 1;

    public static final int MAX_MID_VALUE = 65535;


    //big little  大端模式转int
    public static int bytes2Int(byte[] b, int start, int length) {
        int sum = 0;
        int end = start + length;
        for (int k = start; k < end; k++) {
            int n = ((int) b[k]) & 0xff;
            n <<= (--length) * 8;
            sum += n;
        }
        return sum;
    }

    //FIXME:没有验证
    public static float bytes2Float_BigLittle(byte[] b, int start, int length) {
        if(length!=4)
        {
            return 0;
        }
        int l;
        l = b[start + 0];
        l &= 0xff;
        l |= ((long) b[start + 1] << 8);
        l &= 0xffff;
        l |= ((long) b[start + 2] << 16);
        l &= 0xffffff;
        l |= ((long) b[start + 3] << 24);
        return Float.intBitsToFloat(l);

    }


    //����תΪ�ֽ���
    public byte[] int2Bytes(int value, int length) {
        byte[] b = new byte[length];
        for (int k = 0; k < length; k++) {
            b[length - k - 1] = (byte) ((value >> 8 * k) & 0xff);
        }
        return b;
    }

    //�ж�mid�Ƿ���Ч
    public boolean isValidofMid(int mId) {
        if (mId < MIN_MID_VALUE || mId > MAX_MID_VALUE) {
            return false;
        }
        return true;
    }

    /***
     * ���ֽ���ת��Ϊ16�����ַ���
     */
    public static String parseByte2HexStr(byte[] buf) {
        if (null == buf) {
            return null;
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
    public static String parseByte2HexStr(byte[] buf,int Datalen)
    {
        if (null == buf) {
            return null;
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < Datalen; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();

    }
    /*
     * 初始化：设备对平台的响应码流
     */
    private static byte[] initDeviceRspByte() {
        /*
         * 测试用例：有命令短mid 设备应答消息:AA7201001A9573A51400
         */
        List<Byte> reportList = new ArrayList<Byte>();
        reportList.add((byte) 0XAA);
        reportList.add((byte) 0X72);

        // msgtype
        reportList.add((byte) 0x01);
        // root_devicetype
        reportList.add((byte) 0x00);
        // deviceid
        reportList.add((byte) 0x1A);
        reportList.add((byte) 0x95);
        reportList.add((byte) 0x73);
        reportList.add((byte) 0xA5);
        // isroot
        reportList.add((byte) 0x01);
        // hasmore
        reportList.add((byte) 0x00);
        // hase ercode
        reportList.add((byte) 0x01);
        reportList.add((byte) 0x00);
        // hasmid
        reportList.add((byte) 0x01);
        reportList.add((byte) 0x00);
        reportList.add((byte) 0x42);

        // timestamp
        reportList.add((byte) 0x2A);
        reportList.add((byte) 0x95);
        reportList.add((byte) 0x73);
        reportList.add((byte) 0xA5);

        // child_devicetype
        reportList.add((byte) 0x00);
        // deviceid
        reportList.add((byte) 0x1A);
        reportList.add((byte) 0x95);
        reportList.add((byte) 0x73);
        reportList.add((byte) 0xA5);

        // cmd
        reportList.add((byte) 0x04);
        // datalen
        reportList.add((byte) 0x00); // 33d

        byte[] byteData = new byte[reportList.size() + 1];

        byte crc = 0;
        int i = 0;
        for (i = 0; i < reportList.size(); i++) {
            byteData[i] = (byte) reportList.get(i);
            crc += byteData[i];
        }
        byteData[i] = crc;

        System.out.println(test.parseByte2HexStr(byteData));
        return byteData;
    }

    public static void main(String[] args) {

//        System.out.println(RedisUtils.getString("dxfscfc5ca5f-c806-45ba-a90f-36f59d01bd9b"));
    }
}
