package com.example.skkj.comment;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理list
 * */
public class ObjectsTranscoder{  
      //转换成一个2进制进行存储
    public static byte[] serialize(List value) { 
        if (value == null) { 
            throw new NullPointerException("Can't serialize null");  
        }  
        byte[] rv=null;  
        ByteArrayOutputStream bos = null;  
        ObjectOutputStream os = null;  
        try {  
            bos = new ByteArrayOutputStream();  
            os = new ObjectOutputStream(bos);  
            for(Object obj : value){  
            	os.writeObject(obj);
            }  
            os.close();  
            bos.close();  
            rv = bos.toByteArray();  
        } catch (Exception e) {  
            throw new IllegalArgumentException("Non-serializable object", e);  
        } finally {  
            ListTranscoder.close(os);  
            ListTranscoder.close(bos);  
        }  
        return rv;  
    }  
    //解析2进制
    public static List deserialize(byte[] in) {  
        List list = new ArrayList();  
        ByteArrayInputStream bis = null;  
        ObjectInputStream is = null;  
        try {  
            if(in != null) {  
                bis=new ByteArrayInputStream(in);  
                is=new ObjectInputStream(bis);  
                while (true) {  
                	Object Obj = (Object) is.readObject();  
                    if(Obj == null){  
                        break;  
                    }else{  
                        list.add(Obj);  
                    }  
                }  
                is.close();  
                bis.close();  
            }  
        } catch (Exception e) {  
        } finally {  
            ListTranscoder.close(is);  
            ListTranscoder.close(bis);  
        }  
        return list;  
    }  
}  