package com.ninepstudio.ios8Widget.data;

public class DailyQuoteSingletone {
	
	private static DailyQuoteSingletone dailyquote = null;
	private String quote;
	private String quoteAuthor;
	
	/**
     * Create private constructor
     */
	private DailyQuoteSingletone(){
        
    }
	
	/**
     * Create a static method to get instance.
     */
    public static DailyQuoteSingletone getInstance(){
        if(dailyquote == null){
        	dailyquote = new DailyQuoteSingletone();
        }
        return dailyquote;
    }
    
    
    public String getDailyQuote() {
		return quote;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	
	public String getQuoteAuthor() {
		return quoteAuthor;
	}
	public void setQuoteAuthor(String quoteAuthor) {
		this.quoteAuthor = quoteAuthor;
	}

}
