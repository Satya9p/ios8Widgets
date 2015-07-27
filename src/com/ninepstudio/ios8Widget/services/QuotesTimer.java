package com.ninepstudio.ios8Widget.services;

import java.util.Date;
import java.util.TimerTask;

public class QuotesTimer  extends TimerTask {

	@Override
	public void run() {
		Date d = new Date();
	    System.out.println("Quotes Last updated at::: "+ d.toString());
		String re="";
		QuotesGetService qgs = new QuotesGetService();
		 re = qgs.setQuoteRandom();
		 
		 System.out.println(re);
		
	}

}
