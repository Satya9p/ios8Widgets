package com.ninepstudio.ios8Widget.services;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;

import com.ninepstudio.ios8Widget.dao.QuotesDao;
import com.ninepstudio.ios8Widget.data.Quotes;
import com.ninepstudio.ios8Widget.data.SessionFactory;
import com.ninepstudio.ios8Widget.exceptions.AppDaoException;

/**
 * Servlet implementation class InspiringQuotes
 */
@WebServlet("/IQS")
public class InspiringQuotesService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InspiringQuotesService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/json");
		PrintWriter pw = response.getWriter();
		SqlSessionFactory factory;
		factory=SessionFactory.sessionBuilder();
		int count  = 0;
		try 
		{
		String author = request.getParameter("author");
		String quote = request.getParameter("quote");
		
		System.out.println(author);
		System.out.println(quote);
		
		Quotes qs = new Quotes();
		qs.setQuote(quote);
		qs.setQuoteAuthor(author);
		
		QuotesDao qd = new QuotesDao();
		qd.setFactory(factory);
		
		count  = qd.insertQuotesDetails(qs);
		
		}catch (AppDaoException ae) {
			ae.printStackTrace();
		}
		
		pw.println(count);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
