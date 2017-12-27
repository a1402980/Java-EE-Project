package com.book.businessobject;

import java.util.HashSet;



import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy="category")
	private Set<Book> books;
	
	public Category(){
		this.books = new HashSet<Book>();
	}	
	
	public Category(String name)
	{
		this.name = name;
		this.books = new HashSet<Book>();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	public void addBook(Book book) {
		books.add(book);
		book.setCategory(this);
	}
}
