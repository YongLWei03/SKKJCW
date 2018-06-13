package com.example.skkj.comment;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;


public class SqlsessionFctrouceUtil {

	//加载
	private static Reader resource= null;
	private static SqlSessionFactory sessionFactory = null;
	static{
		try {
			 resource = Resources.getResourceAsReader("mybatis.xml");
			  sessionFactory = new SqlSessionFactoryBuilder().build(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSession getSession(){
        return sessionFactory.openSession();  
    }  
	
	
}
