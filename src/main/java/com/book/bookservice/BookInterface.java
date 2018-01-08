package com.book.bookservice;

import java.util.List;

import javax.ejb.Local;

import com.book.businessobject.Book;
import com.book.businessobject.Category;
import com.book.businessobject.Soldbook;
import com.book.businessobject.Writer;


@Local
public interface BookInterface {

	public Book getBookById(long id);
	
	public List<Book> getAllBooks();
	
	public List<Book> getAllBooksFromCategory(Category category);
	
	public List<Book> getAllBooksFromAuthor(Writer author);
	
	public List<Soldbook> getAllSoldBooks();
	
	public double getTotalSales();
	
	public long getTotalSoldbooks();
	
	public double getAverageSales();
	
	public void addData();

	//void transfer(Account compteSrc, Account compteDest, int montant) throws Exception;

	public List<Writer> getWriters();

	public Writer getWriter(long writerid);
	
	public List<Category> getCategories();

	public Category getCategory(long categoryid);
	
	public String rent(Book book);
	
	public String buyBook(Book book);
}