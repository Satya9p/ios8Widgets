package com.ninepstudio.ios8Widget.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;

import org.apache.ibatis.session.SqlSessionFactory;
import org.json.JSONObject;

import com.ninepstudio.ios8Widget.dao.HoroscopeDao;
import com.ninepstudio.ios8Widget.data.Horoscope;
import com.ninepstudio.ios8Widget.data.SessionFactory;
import com.ninepstudio.ios8Widget.exceptions.AppDaoException;

public class HoroscopeTimer extends TimerTask {

	@Override
	public void run() {
		
		Date d = new Date();
	    System.out.println("Heroscope Last updated at::: "+ d.toString());
		
		SqlSessionFactory factory;
		factory=SessionFactory.sessionBuilder();
		int count =0;
		int insertedrows =0;
		try{
			ArrayList<String> horoscopeList = new ArrayList<String>();
			horoscopeList.add("Aries");
			horoscopeList.add("Taurus");
			horoscopeList.add("Gemini");
			horoscopeList.add("Cancer");
			horoscopeList.add("Leo");
			horoscopeList.add("Virgo");
			horoscopeList.add("Libra");
			horoscopeList.add("Scorpio");
			horoscopeList.add("Sagittarius");
			horoscopeList.add("Capricorn");
			horoscopeList.add("Aquarius");
			horoscopeList.add("Pisces");
			
//			for (String var : horoscopeList)
//			{
//				System.out.println(var);
//			} 
//			
			
			HoroscopeDao hd = new HoroscopeDao();
			hd.setFactory(factory);
			int deletedrows = hd.deletehoroscopeData();
			System.out.println(deletedrows);
			int t= 1;
			for (String var : horoscopeList)
			{	
//				System.out.println(var);
				String json ="";
				String horoscopeurl = "http://widgets.fabulously40.com/horoscope.json?sign="+var;
//				System.out.println(horoscopeurl);
				
				HttpURLConnection connection  = (HttpURLConnection) new URL(horoscopeurl).openConnection();
				InputStream res = connection.getInputStream();
				
				BufferedReader br = new BufferedReader(new  InputStreamReader(res));
				 if(br != null)
				 {
			 	        json = br.readLine();
//				        System.out.println(json);
				        connection.disconnect();
				 }
				JSONObject jsonData = new JSONObject(json);
				JSONObject horoscopejson = jsonData.getJSONObject("horoscope");
				String sign = horoscopejson.getString("sign");
				String horoscopeDesc = horoscopejson.getString("horoscope");
				Horoscope hs = new Horoscope();
				hs.setSign(sign);
				hs.setId(t);
				hs.setDescription(horoscopeDesc);
				 count = hd.insertHoroscopeDetails(hs);
				t++;
				if(count ==1){
					insertedrows ++;
				}
			}	
				
			System.out.println(insertedrows);
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (AppDaoException ae) {
			ae.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		System.out.println(insertedrows);
		
		
		
		
		
	} 

}
