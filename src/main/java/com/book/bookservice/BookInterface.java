package com.book.bookservice;

import java.util.List;

import javax.ejb.Local;

import com.book.businessobject.Book;
import com.book.businessobject.Category;
import com.book.businessobject.Writer;


@Local
public interface BookInterface {

	public Book getBook(String title, String authorLastname);
	
	public List<Book> getAllBooks();
	
	public List<Book> getAllBooksFromAuthor(String authorLastName);
	
	public void addData();

	//void transfer(Account compteSrc, Account compteDest, int montant) throws Exception;

	public List<Writer> getWriters();

	public Writer getWriter(long writerid);
	
	public List<Category> getCategories();

	public Category getCategory(long categoryid);
}