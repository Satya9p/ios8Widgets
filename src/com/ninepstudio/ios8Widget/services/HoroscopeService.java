package com.ninepstudio.ios8Widget.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;
import org.json.JSONObject;



import com.ninepstudio.ios8Widget.dao.HoroscopeDao;
import com.ninepstudio.ios8Widget.data.Horoscope;
import com.ninepstudio.ios8Widget.data.SessionFactory;
import com.ninepstudio.ios8Widget.exceptions.AppDaoException;

/**
 * Servlet implementation class Horoscope
 */
@WebServlet("/hsreset")
public class HoroscopeService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HoroscopeService() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/json");
		PrintWriter pw = response.getWriter();
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
			
			for (String var : horoscopeList)
			{
				System.out.println(var);
			}
			
			
			HoroscopeDao hd = new HoroscopeDao();
			hd.setFactory(factory);
			int deletedrows = hd.deletehoroscopeData();
			System.out.println(deletedrows);
			int t= 1;
			for (String var : horoscopeList)
			{	
				System.out.println(var);
				String json ="";
				String horoscopeurl = "http://widgets.fabulously40.com/horoscope.json?sign="+var;
				System.out.println(horoscopeurl);
				
				HttpURLConnection connection  = (HttpURLConnection) new URL(horoscopeurl).openConnection();
				InputStream res = connection.getInputStream();
				
				BufferedReader br = new BufferedReader(new  InputStreamReader(res));
				 if(br != null)
				 {
			 	        json = br.readLine();
				        System.out.println(json);
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
		}
		
		pw.println(insertedrows);
	}

}
