package com.ninepstudio.ios8Widget.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ninepstudio.ios8Widget.data.Horoscope;
import com.ninepstudio.ios8Widget.exceptions.AppDaoException;

public class HoroscopeDao {
	
	
	private static SqlSessionFactory factory;
	private int count;
		
		public void setFactory(SqlSessionFactory factoryArg) 
		{
			factory = factoryArg;
		}
		
		public int insertHoroscopeDetails(Horoscope hs) throws AppDaoException
		{
				
				SqlSession session = null;
				try{
					session = factory.openSession();
					
					 count = session.insert("insertHoroscopeDetails", hs);
					 session.commit();
		
				}catch(Exception e){
					throw new AppDaoException(e);
		
				}finally{
					if(session!= null){
						session.close();
					}
				}
				return count;
		}	
		
		
		public Horoscope getSignDescription(String sign) throws AppDaoException
		{
				
				SqlSession session = null;
				Horoscope cr = null;
				try{
					session = factory.openSession();
					
					 cr = session.selectOne("getSignDescription", sign);
					 session.commit();
		
				}catch(Exception e){
					throw new AppDaoException(e);
		
				}finally{
					if(session!= null){
						session.close();
					}
				}
				return cr;
		}
		
		
		public int deletehoroscopeData()throws AppDaoException
		{
			SqlSession session = null;
			try{
				session = factory.openSession();
				
				 count = session.delete("deletehoroscopeData");
				 session.commit();

			}catch(Exception e){
				throw new AppDaoException(e);

			}finally{
				if(session!= null){
					session.close();
				}
			}
			return count;
			
		}
		
		
		public List<Horoscope> getAllSignDescriptions()throws AppDaoException
		{
			System.out.println("getAllSignDescriptions method in Dao " ); 
			List<Horoscope> listHoro = null;
			SqlSession session = null;
			try{
				session = factory.openSession();
				
				listHoro = session.selectList("getAllSignDescriptions");
				 session.commit();

			}catch(Exception e){
				throw new AppDaoException(e);

			}finally{
				if(session!= null){
					session.close();
				}
			}
			return listHoro;
			
		}

}
