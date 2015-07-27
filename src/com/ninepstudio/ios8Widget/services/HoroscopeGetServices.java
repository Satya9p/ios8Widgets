package com.ninepstudio.ios8Widget.services;


import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ninepstudio.ios8Widget.dao.HoroscopeDao;
import com.ninepstudio.ios8Widget.data.Horoscope;
import com.ninepstudio.ios8Widget.data.SessionFactory;
import com.ninepstudio.ios8Widget.exceptions.AppDaoException;

public class HoroscopeGetServices {
	
	public String getSignDescription(JSONObject jsonData){
		
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
			
			String signname = json.getString("sign");
			HoroscopeDao hd = new HoroscopeDao();
			hd.setFactory(factory);
			Horoscope desc = hd.getSignDescription(signname);
			
			String des = desc.getDescription();
			String datetime = desc.getTimedate();
			jsonparams.putOpt("sign",signname);
			jsonparams.putOpt("desc",des);
			metajson.putOpt("ts",datetime);
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
	
	
	public String getAllSignDescription(JSONObject jsonData)
	{
		System.out.println(jsonData);
		
		SqlSessionFactory factory;
		factory=SessionFactory.sessionBuilder();
		
		JSONObject result = new JSONObject();
		JSONObject metajson = new JSONObject();
		JSONObject responsejson = new JSONObject();
		JSONObject error = new JSONObject();
		
		JSONArray jsonarr = new JSONArray();
		
		try 
		{
			HoroscopeDao hd = new HoroscopeDao();
			hd.setFactory(factory);
			List<Horoscope> hlist  = null;
			hlist= hd.getAllSignDescriptions();
			System.out.println(hlist.size());

			if(hlist != null)
			   {
					Iterator<Horoscope>  it = hlist.iterator();
					while(it.hasNext())
					{
						JSONObject json = new JSONObject();
						Horoscope obj = it.next(); 
						json.putOpt("sign",obj.getSign());
						json.putOpt("desc",obj.getDescription());
						metajson.putOpt("ts",obj.getTimedate());
						jsonarr.put(json); 
					}
			   }	

			metajson.putOpt("status","true");
			responsejson.putOpt("datatype","arr");
			responsejson.putOpt("result",jsonarr);
			
			result.putOpt("meta",metajson);
			result.putOpt("response",responsejson);
			result.putOpt("error", error);
			
		}catch (AppDaoException e) {
			e.printStackTrace();
		}
		return result.toString();
	}
	
}
