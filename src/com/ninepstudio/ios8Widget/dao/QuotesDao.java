package com.ninepstudio.ios8Widget.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.ninepstudio.ios8Widget.data.Quotes;
import com.ninepstudio.ios8Widget.exceptions.AppDaoException;

public class QuotesDao {
	
	private static SqlSessionFactory factory;
	private int count;
		
		public void setFactory(SqlSessionFactory factoryArg) 
		{
			factory = factoryArg;
		}

	public int insertQuotesDetails(Quotes hs) throws AppDaoException
	{
			
			SqlSession session = null;
			try{
				session = factory.openSession();
				
				 count = session.insert("insertQuotesDetails", hs);
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
	
	
	public Quotes getQuote() throws AppDaoException
	{
			
			SqlSession session = null;
			Quotes cr = null;
			try{
				session = factory.openSession();
				
				 cr = session.selectOne("getQuote");
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
	
	public int updateQuote(int quoteId) throws AppDaoException
	{
			
			SqlSession session = null;
			try{
				session = factory.openSession();
				 count = session.update("updateQuote", quoteId);
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
	
}
