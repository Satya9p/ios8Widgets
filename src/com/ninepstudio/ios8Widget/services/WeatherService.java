package com.ninepstudio.ios8Widget.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.util.TimerTask;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

public class WeatherService extends TimerTask {

	@Override
	public void run() {
		int insertedRows = 0;
		String yahoourl ="https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%3D2502265&format=json&diagnostics=true&callback=";
		try 
		{
			String json ="";
	 		HttpsURLConnection  connection = (HttpsURLConnection) new URL(yahoourl).openConnection();
			InputStream res = connection.getInputStream();
			
			BufferedReader br = new BufferedReader(new  InputStreamReader(res));
			 if(br != null)
			 {
		 	        json = br.readLine();
			 }
			JSONObject jsonData = new JSONObject(json);
			
			System.out.println(jsonData.toString());
			
		}catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		 System.out.println(insertedRows);
	}
	
	

}


