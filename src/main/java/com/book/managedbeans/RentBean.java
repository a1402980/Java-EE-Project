package com.book.managedbeans;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.book.bookservice.BookInterface;
import com.book.businessobject.Book;

public class RentBean {
	
	private BookInterface bI;
	private List<Book> books;
	
	@PostConstruct
	public void initialize() throws NamingException 
	{	
	    // use JNDI to inject reference to bank EJB
	    InitialContext ctx = new InitialContext();
		bI = (BookInterface) ctx.lookup("java:global/Java-EE-Project-0.0.1-SNAPSHOT/BookBean!com.book.bookservice.BookInterface");  
		
		
	}
	public List<Book> getBooks()
	{
		books = bI.getAllBooks();
		System.out.println("************************************");
		System.out.println("************************************");
		System.out.println(books.size());
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public void performDataAdding()
	{
		bI.addData();
	}
	
	public String viewBook(int bookId)
	{
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		
		return "bookInfo";
	}
	    

}
