package com.ninepstudio.ios8Widget.services;

import java.util.Calendar;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;



/**
 * Application Lifecycle Listener implementation class WidgetsListener
 *
 */
@WebListener
public class WidgetsListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public WidgetsListener() {
        
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
    	
    	Timer timer ;
    	 timer = new Timer();
    	 Calendar date = Calendar.getInstance();
 	    date.set(Calendar.AM_PM, Calendar.PM);
 	    date.set(Calendar.HOUR, 6);
 	    date.set(Calendar.MINUTE,30);
 	    date.set(Calendar.SECOND, 0);
 	    date.set(Calendar.MILLISECOND, 0);
    	 
    	 timer.schedule(new CurrencyTimer(),0,1000 * 60*60*1);
 	     timer.schedule(new HoroscopeTimer(),date.getTime(),1000 *60*60*24);
 	     timer.schedule(new QuotesTimer(),date.getTime(),1000 *60*60*24);
    	
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        
    }
	
}
