package com.book.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.book.businessobject.Book;
import com.book.businessobject.Category;
import com.book.businessobject.Writer;


public class RentBean {
	
	private Book book;
	private List<Book> books;
	private List<Writer> writers;
	private List<Category> categories;
	
	@PostConstruct
	public void initialize() throws NamingException 
	{	
	    // use JNDI to inject reference to bank EJB
	    InitialContext ctx = new InitialContext();
		book = (Book) ctx.lookup("java:global/Java-EE-Project/BookBean!com.book.bookservice.Book");  
				
	}
	    

}
