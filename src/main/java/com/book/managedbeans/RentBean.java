package com.book.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.book.bookservice.BookInterface;
import com.book.businessobject.Book;
import com.book.businessobject.Category;
import com.book.businessobject.Soldbook;
import com.book.businessobject.Writer;

public class RentBean {
	
	private BookInterface bI;
	private List<Book> books;
	private List<Book> authorbooks;
	private List<Book> categorybooks;
	private List<Soldbook> soldBooks;
	private Book book;
	private String rentResult = null;
	private double totalSales;
	private long totalCount;
	private double averageSales;
	
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
	

	public List<Book> getBooks()
	{	
		books = bI.getAllBooks();
		return books;
	}

	public List<Book> getAuthorbooks() {
		return authorbooks;
	}


	public List<Book> getCategorybooks() {
		return categorybooks;
	}


	public List<Soldbook> getSoldBooks(){
		
		soldBooks = bI.getAllSoldBooks();
		return soldBooks;
	}
	
	public void setSoldBooks(List<Soldbook> soldBooks)
	{
		this.soldBooks = soldBooks;
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
		
		if(!rentedBook.isSold())
		{
			this.rentResult = bI.rent(rentedBook);
		}
		return "bookInfo?faces-redirect=true";
	}
	
	public String buyBook(Book soldBook){
		
		if(!soldBook.isRented())
		{
			bI.buyBook(soldBook);
		}
		return "bookInfo?faces-redirect=true";
	}
	
	public void setTotalSales(double totalSales) {
		this.totalSales = totalSales;
	}

	public double getTotalSales()
	{
		totalSales = bI.getTotalSales();
		return totalSales;
	}
	public long getTotalCount() {
		
		totalCount = bI.getTotalSoldbooks();
		return totalCount;
	}

	public double getAverageSales() {
		
		averageSales = bI.getAverageSales();
		return averageSales;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public void setAverageSales(double averageSales) {
		this.averageSales = averageSales;
	}

	public String getRentResult() {
		return rentResult;
	}
	public void setRentResult(String rentResult) {
		this.rentResult = rentResult;
	}
	
	public String bookByAuthor(Writer author)
	{
		authorbooks = bI.getAllBooksFromAuthor(author);
		return "booksByAuthor?faces-redirect=true";
	}
	
	public String bookByCategory(Category category)
	{
		categorybooks = bI.getAllBooksFromCategory(category);
		return "booksByCategory?faces-redirect=true";
	}

}
