package com.ninepstudio.ios8Widget.data;


import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SessionFactory {

	private static SqlSessionFactory factory;
	public static  SqlSessionFactory sessionBuilder()
	{
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("mybatis/mapper/mybatis-config.xml");
		} 
		catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		factory = new SqlSessionFactoryBuilder().build(reader);
		return factory;
	}
}
