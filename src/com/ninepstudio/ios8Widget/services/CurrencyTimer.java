package com.ninepstudio.ios8Widget.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.TimerTask;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

public class CurrencyTimer extends TimerTask {

	@Override
	public void run() {
		
		Date d = new Date();
	    System.out.println("Currency Rates Last updated at::: "+ d.toString());
		
		int insertedRows = 0;
		String yahoourl = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in('USDAUD'%2C'USDALL'%2C'USDDZD'%2C'USDXAL'%2C'USDARS'%2C'USDAWG'%2C'USDGBP'%2C'USDBSD'%2C'USDBHD'%2C'USDBDT'%2C'USDBBD'%2C'USDBYR'%2C'USDBZD'%2C'USDBMD'%2C'USDBTN'%2C'USDBOB'%2C'USDBWP'%2C'USDBRL'%2C'USDBND'%2C'USDBGN'%2C'USDBIF'%2C'USDCAD'%2C'USDCNY'%2C'USDKHR'%2C'USDCVE'%2C'USDKYD'%2C'USDXOF'%2C'USDXAF'%2C'USDCLP'%2C'USDCOP'%2C'USDKMF'%2C'USDXCP'%2C'USDCRC'%2C'USDHRK'%2C'USDCUP'%2C'USDCZK'%2C'USDEUR'%2C'USDDKK'%2C'USDDJF'%2C'USDDOP'%2C'USDXCD'%2C'USDECS'%2C'USDEGP'%2C'USDSVC'%2C'USDERN'%2C'USDEEK'%2C'USDETB'%2C'USDFKP'%2C'USDFJD'%2C'USDHKD'%2C'USDIDR'%2C'USDINR'%2C'USDGMD'%2C'USDGHC'%2C'USDGIP'%2C'USDXAU'%2C'USDGTQ'%2C'USDGNF'%2C'USDGYD'%2C'USDHTG'%2C'USDHNL'%2C'USDHUF'%2C'USDISK'%2C'USDIRR'%2C'USDIQD'%2C'USDILS'%2C'USDJPY'%2C'USDJMD'%2C'USDJOD'%2C'USDKZT'%2C'USDKES'%2C'USDKWD'%2C'USDLAK'%2C'USDLVL'%2C'USDLBP'%2C'USDLSL'%2C'USDLRD'%2C'USDLYD'%2C'USDLTL'%2C'USDMOP'%2C'USDMKD'%2C'USDMWK'%2C'USDMYR'%2C'USDMVR'%2C'USDMTL'%2C'USDMRO'%2C'USDMUR'%2C'USDMXN'%2C'USDMDL'%2C'USDMNT'%2C'USDMAD'%2C'USDMMK'%2C'USDNAD'%2C'USDNPR'%2C'USDANG'%2C'USDNZD'%2C'USDNIO'%2C'USDNGN'%2C'USDKPW'%2C'USDNOK'%2C'USDOMR'%2C'USDXPF'%2C'USDPKR'%2C'USDXPD'%2C'USDPAB'%2C'USDPGK'%2C'USDPYG'%2C'USDPEN'%2C'USDPHP'%2C'USDXPT'%2C'USDPLN'%2C'USDQAR'%2C'USDRON'%2C'USDRUB'%2C'USDRWF'%2C'USDCHF'%2C'USDKRW'%2C'USDWST'%2C'USDSTD'%2C'USDSAR'%2C'USDSCR'%2C'USDSLL'%2C'USDXAG'%2C'USDSGD'%2C'USDSKK'%2C'USDSIT'%2C'USDSBD'%2C'USDSOS'%2C'USDZAR'%2C'USDLKR'%2C'USDSHP'%2C'USDSDG'%2C'USDSZL'%2C'USDSEK'%2C'USDSYP'%2C'USDUSD'%2C'USDTHB'%2C'USDTRY'%2C'USDTWD'%2C'USDTZS'%2C'USDTOP'%2C'USDTTD'%2C'USDTND'%2C'USDAED'%2C'USDUGX'%2C'USDUAH'%2C'USDUYU'%2C'USDVUV'%2C'USDVEF'%2C'USDVND'%2C'USDYER'%2C'USDZMK'%2C'USDZWD')&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
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
			
			CurrencyRateService crs = new CurrencyRateService();
			insertedRows = crs.insertCurrencyRatestoDb(jsonData);
			
		}catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		 System.out.println(insertedRows);
		
	}

}
