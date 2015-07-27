package com.ninepstudio.ios8Widget.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ninepstudio.ios8Widget.data.CurrencyRates;
import com.ninepstudio.ios8Widget.exceptions.AppDaoException;

public class CurrencyRateDao {
	
private static SqlSessionFactory factory;
private int count;
	
	public void setFactory(SqlSessionFactory factoryArg) 
	{
		factory = factoryArg;
	}
	
	public int insertCurrencyDetails(CurrencyRates currency) throws AppDaoException
	{
			
			SqlSession session = null;
			try{
				session = factory.openSession();
				
				 count = session.insert("insertCurrencyDetails", currency);
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
	
	
	public CurrencyRates getCurrencyRate(String currency) throws AppDaoException
	{
			
			SqlSession session = null;
			CurrencyRates cr = null;
			try{
				session = factory.openSession();
				
				 cr = session.selectOne("getCurrencyRate", currency);
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
	
	
	public int deletePreviousData()throws AppDaoException
	{
		SqlSession session = null;
		try{
			session = factory.openSession();
			
			 count = session.delete("deletePreviousData");
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
