package com.ninepstudio.ios8Widget.services;


import org.apache.ibatis.session.SqlSessionFactory;
import org.json.JSONObject;

import com.ninepstudio.ios8Widget.dao.QuotesDao;
import com.ninepstudio.ios8Widget.data.DailyQuoteSingletone;
import com.ninepstudio.ios8Widget.data.Quotes;
import com.ninepstudio.ios8Widget.data.SessionFactory;
import com.ninepstudio.ios8Widget.exceptions.AppDaoException;

public class QuotesGetService {
	
	public String setQuoteRandom(){
		
		SqlSessionFactory factory;
		factory=SessionFactory.sessionBuilder();
		
		JSONObject result = new JSONObject();
		JSONObject metajson = new JSONObject();
		JSONObject jsonparams = new JSONObject();
		JSONObject responsejson = new JSONObject();
		JSONObject error = new JSONObject();
		try 
		{
			 Quotes qc = null;
			 QuotesDao qd = new QuotesDao();
			 qd.setFactory(factory);
			 qc =qd.getQuote();

			 DailyQuoteSingletone dqs = DailyQuoteSingletone.getInstance();
			 
			 dqs.setQuote(qc.getQuote());
			 dqs.setQuoteAuthor(qc.getQuoteAuthor());
			 int count = qd.updateQuote(qc.getQuoteId());
			 
			 System.out.println("updated rows in InspiringQuotes table"+count);
			 
			 	jsonparams.putOpt("quote",qc.getQuote());
				jsonparams.putOpt("author",qc.getQuoteAuthor());
				metajson.putOpt("ts",qc.getTimedate());
			    metajson.putOpt("status","true");
				responsejson.putOpt("datatype","obj");
				responsejson.putOpt("result",jsonparams); 
				
				result.putOpt("meta",metajson);
				result.putOpt("response",responsejson);
				result.putOpt("error", error);
			
		}catch (AppDaoException e) {
			e.printStackTrace();
		}
		return result.toString();
	}


	
	public String getDailyQuote(JSONObject jsonData) 
	{
		SqlSessionFactory factory;
		factory=SessionFactory.sessionBuilder();
		JSONObject result = new JSONObject();
		JSONObject metajson = new JSONObject();
		JSONObject jsonparams = new JSONObject();
		JSONObject responsejson = new JSONObject();
		JSONObject error = new JSONObject();
		 try {
			 DailyQuoteSingletone dqs1 = DailyQuoteSingletone.getInstance();
			 
			 String quote =null;
			 quote= dqs1.getDailyQuote();
			 if(quote == null)
			 {
				 Quotes qc = null;
				 QuotesDao qd = new QuotesDao();
				 qd.setFactory(factory);
				 qc =qd.getQuote();

				 DailyQuoteSingletone dqs = DailyQuoteSingletone.getInstance();
				 
				 dqs.setQuote(qc.getQuote());
				 dqs.setQuoteAuthor(qc.getQuoteAuthor());
				
				    qd.updateQuote(qc.getQuoteId());
				    jsonparams.putOpt("quote",dqs.getDailyQuote());
					jsonparams.putOpt("author", dqs.getQuoteAuthor());
				    metajson.putOpt("status","true");
					responsejson.putOpt("datatype","obj");
					responsejson.putOpt("result",jsonparams);
			 }
			 else
			 {
				    jsonparams.putOpt("quote",dqs1.getDailyQuote());
					jsonparams.putOpt("author", dqs1.getQuoteAuthor());
				    metajson.putOpt("status","true");
					responsejson.putOpt("datatype","obj");
					responsejson.putOpt("result",jsonparams); 
			 }
				result.putOpt("meta",metajson);
				result.putOpt("response",responsejson);
				result.putOpt("error", error);
		 }catch (AppDaoException e)
		 {
				e.printStackTrace();
		 }
		return result.toString();
	}

}
