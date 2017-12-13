package com.book.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.book.bookservice.BookInterface;
import com.book.businessobject.Book;
import com.book.businessobject.Category;
import com.book.businessobject.Writer;

public class RentBean {
	
	private BookInterface bI;
	private List<Book> books;
	private Book book;
	private String rentResult = null;
	
	@PostConstruct
	public void initialize() throws NamingException 
	{	
	    // use JNDI to inject reference to book EJB
	    InitialContext ctx = new InitialContext();
		bI = (BookInterface) ctx.lookup("java:global/Java-EE-Project-0.0.1-SNAPSHOT/BookBean!com.book.bookservice.BookInterface");  
		
		if(bI != null)
		{
			bI.addData();
		}
	}
	public String getAllBooks()
	{	
		books = bI.getAllBooks();
		System.out.println("************************************");
		System.out.println("************************************");
		System.out.println(books.size());
		
		return "bookList?faces-redirect=true";
	}
	public List<Book> getBooks()
	{

		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public void performDataAdding()
	{
		bI.addData();
	}
	
	public String viewBook(long bookId)
	{
		book = bI.getBookById(bookId);
		return "bookInfo?faces-redirect=true";
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
	public String rentBook(Book rentedBook){
		
		this.rentResult = bI.rent(rentedBook);
		return "bookInfo?faces-redirect=true";
	}
	
	public String getRentResult() {
		return rentResult;
	}
	public void setRentResult(String rentResult) {
		this.rentResult = rentResult;
	}
	
	public String bookByAuthor(Writer author)
	{
		books = bI.getAllBooksFromAuthor(author);
		return "booksByAuthor?faces-redirect=true";
	}
	
	public String bookByCategory(Category category)
	{
		books = bI.getAllBooksFromCategory(category);
		return "booksByCategory?faces-redirect=true";
	}

}
