package com.book.businessobject;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Writer")
public class Writer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="firstname")
	private String firstname;
	@Column(name="lastname")
	private String lastname;

	
	@OneToMany(mappedBy="author", cascade = CascadeType.ALL)
	private Set<Book> books;
	
	public Writer(){
		this.books = new HashSet<Book>();
	}
	
	public Writer(String firstname, String lastname)
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.books = new HashSet<Book>();
	}

	public Long getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}



	public Set<Book> getBooks() {
		return books;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	public void writeBook(Book book)
	{
		books.add(book);
		book.setAuthors(this);
	}
}
