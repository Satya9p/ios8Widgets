package com.ninepstudio.ios8Widget.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/api")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
       
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/json");
		PrintWriter pw = response.getWriter();
		String json ="";
		String re="";
		try {
			BufferedReader br = new BufferedReader(new  InputStreamReader(request.getInputStream()));
			 if(br != null)
			 {
		 	        json = br.readLine();
			        System.out.println(json);
			 }
			 JSONObject jsonData = new JSONObject(json);
			 System.out.println(jsonData.toString());
			 
			 JSONObject req = jsonData.getJSONObject("request");
			 String api = ((String)req.get("api"));
			 if(api.equals("Cc.getRate"))
			 {
				 System.out.println("Forwarad to currencyconverter url"); 
				 CurrencyRateService crs = new CurrencyRateService();
				 re  = crs.getCurrencyRate(jsonData);
			 }
			 if(api.equals("Cc.getRates"))
			 {
				 System.out.println("Forwarad to currencyconverter url"); 
				 CurrencyRateService crs = new CurrencyRateService();
				 re  = crs.getArrayOfCurrencyRates(jsonData);
			 }else if(api.equals("Hs.getSign")){
				 System.out.println("Forwarad to horoscopedesc url");
				 HoroscopeGetServices hgs = new HoroscopeGetServices();
				 re = hgs.getSignDescription(jsonData); 
			}else if(api.equals("Hs.getAll")){
				 System.out.println("Forwarad to AllSign url");
				 HoroscopeGetServices hgs = new HoroscopeGetServices();
				 re = hgs.getAllSignDescription(jsonData); 
			}
			else if(api.equals("Iq.getRandomQuote")){
				 System.out.println("Forwarad to getRandomQuote url");
				 QuotesGetService qgs = new QuotesGetService();
				 re = qgs.setQuoteRandom();
			}else if(api.equals("Iq.getQuote")){
				 System.out.println("Forwarad to getQuote url");
				 QuotesGetService qgs = new QuotesGetService();
				 re = qgs.getDailyQuote(jsonData);
			}
			
		}catch (ParseException e){
			e.printStackTrace();
		}
		
		pw.println(re);
	}

}
