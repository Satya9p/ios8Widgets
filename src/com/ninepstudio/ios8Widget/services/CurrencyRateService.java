package com.ninepstudio.ios8Widget.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ninepstudio.ios8Widget.dao.CurrencyRateDao;
import com.ninepstudio.ios8Widget.data.CurrencyRates;
import com.ninepstudio.ios8Widget.data.SessionFactory;
import com.ninepstudio.ios8Widget.exceptions.AppDaoException;

public class CurrencyRateService {
	
	public int insertCurrencyRatestoDb(JSONObject jsonData){
		
		SqlSessionFactory factory;
		factory=SessionFactory.sessionBuilder();
		int count = 0;
		int insertedRows = 0;
		try 
		{
			
			JSONObject query = jsonData.getJSONObject("query");
			JSONObject results = query.getJSONObject("results");
			JSONArray rateArray = results.getJSONArray("rate");
			
			CurrencyRateDao crd = new CurrencyRateDao();
		    crd.setFactory(factory);
		    int deletedrows = crd.deletePreviousData();
		    System.out.println(deletedrows);
			for (int i = 0; i < rateArray.length(); ++i) {
				
			    JSONObject rec = rateArray.getJSONObject(i);
			    String id = rec.getString("id");
			    float rate = Float.parseFloat(rec.getString("Rate"));
			    String date = rec.getString("Date");
			    String time = rec.getString("Time");
			    
			    String baseCurrency = id.substring(0, 3);
			    String targetCurrency = id.substring(3,id.length());
			    
//			    System.out.println(baseCurrency);
//			    System.out.println(targetCurrency);
			    
			    CurrencyRates ccr = new CurrencyRates();
			    ccr.setBaseCurrency(baseCurrency);
			    ccr.setDate(date);
			    ccr.setId(id);
			    ccr.setTargetCurrency(targetCurrency);
			    ccr.setTime(time);
			    ccr.setRate(rate);
			  
			    count = crd.insertCurrencyDetails(ccr);
			    
			    if(count ==1){
			    	insertedRows ++;
			    }else{
			    	
			    }
			}
		}catch (AppDaoException e) {
			e.printStackTrace();
		}
		
		return insertedRows;
	}

	public String getCurrencyRate(JSONObject jsonData) {
		
		SqlSessionFactory factory;
		factory=SessionFactory.sessionBuilder();
	
		JSONObject result = new JSONObject();
		JSONObject metajson = new JSONObject();
		JSONObject jsonparams = new JSONObject();
		JSONObject responsejson = new JSONObject();
		JSONObject error = new JSONObject();
		
		try 
		{
			JSONObject json1 =jsonData.getJSONObject("request");
			JSONObject json =(JSONObject) json1.get("params");
			
			String sourceCurrency = json.getString("source");
			String targetCurrency = json.getString("target");
			String baseCurrency = "USD";
			String id1 = baseCurrency.trim() + targetCurrency.trim();
			String id2 = baseCurrency.trim() + sourceCurrency.trim();
			CurrencyRateDao crd = new CurrencyRateDao();
		    crd.setFactory(factory);
		    
		    CurrencyRates baseTarget = crd.getCurrencyRate(id1);
		    CurrencyRates baseSource = crd.getCurrencyRate(id2);
		    
		    float btrate =  baseTarget.getRate();
		    String date = baseTarget.getDate(); 
		    String datetime = baseTarget.getDatetime();
		    
		    System.out.println(date);
		    
		    float bsrate = baseSource.getRate();
		    
		    float rate = btrate/bsrate ; 
		    
		    jsonparams.putOpt("rate",rate);
		    metajson.putOpt("ts",datetime);
		    metajson.putOpt("status","true");
			responsejson.putOpt("datatype","obj");
			responsejson.putOpt("result",jsonparams); 
			
			result.putOpt("meta",metajson);
			result.putOpt("response",responsejson);
			result.putOpt("error", error);
			
			System.out.println(result.toString());
		}catch (AppDaoException e) {
			e.printStackTrace();
		}
		
		return result.toString();
	}
	
	
public String getArrayOfCurrencyRates(JSONObject jsonData) {
		
		SqlSessionFactory factory;
		factory=SessionFactory.sessionBuilder();
	
		JSONObject result = new JSONObject();
		JSONObject metajson = new JSONObject();
		List<JSONObject> jsonparams = new ArrayList<JSONObject>();
		JSONObject responsejson = new JSONObject();
		JSONObject error = new JSONObject();
		try 
		{
			JSONObject json1 =jsonData.getJSONObject("request");
			JSONArray json =(JSONArray) json1.get("params");
			String datetime =""; ;
			for(int n = 0; n < json.length(); n++)
			{
				
			    JSONObject object = json.getJSONObject(n);
			    String sourceCurrency = object.getString("source");
				String targetCurrency = object.getString("target");
				String baseCurrency = "USD";
				String id1 = baseCurrency.trim() + targetCurrency.trim();
				String id2 = baseCurrency.trim() + sourceCurrency.trim();
				CurrencyRateDao crd = new CurrencyRateDao();
			    crd.setFactory(factory);
			    
			    CurrencyRates baseTarget = crd.getCurrencyRate(id1);
			    CurrencyRates baseSource = crd.getCurrencyRate(id2);
			    
			    float btrate =  baseTarget.getRate();
			    String date = baseTarget.getDate(); 
			    datetime = baseTarget.getDatetime();
			    
			    System.out.println(date);
			    
			    float bsrate = baseSource.getRate();
			    
			    float rate = btrate/bsrate ; 
			    
			    JSONObject jsonArray = new JSONObject();
			    
			    jsonArray.putOpt("source",sourceCurrency);
			    jsonArray.putOpt("target",targetCurrency);
			    jsonArray.putOpt("rate",rate);
			    
			    
			    jsonparams.add(jsonArray);
			  
			}
			responsejson.accumulate("result", jsonparams);
		    metajson.putOpt("ts",datetime);
		    metajson.putOpt("status","true");
			responsejson.putOpt("datatype","obj");
			 
			result.putOpt("meta",metajson);
			result.putOpt("response",responsejson);
			result.putOpt("error", error);
			
			System.out.println(result.toString());
		}catch (AppDaoException e) {
			e.printStackTrace();
		}
		
		return result.toString();
	}

}
