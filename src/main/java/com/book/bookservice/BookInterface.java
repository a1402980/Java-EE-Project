package com.book.bookservice;

import java.util.List;

import javax.ejb.Local;

import com.book.businessobject.Book;
import com.book.businessobject.Category;
import com.book.businessobject.Writer;


@Local
public interface BookInterface {

	Book getBook(String title, String authorLastname);
	
	public List<Book> getAllBooksFromAuthor(String authorLastName);

	//void transfer(Account compteSrc, Account compteDest, int montant) throws Exception;

	List<Writer> getWriters();

	Writer getWriter(long writerid);
	
	List<Category> getCategories();

	Category getCategory(long categoryid);
}