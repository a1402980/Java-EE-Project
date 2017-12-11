package com.book.businessobject;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="Book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="title")
	private String title;
	@Column(name="isbn")
	private String isbn;
	@Column(name="publisher")
	private String publisher;
	@Column(name="publishing_date")
	private Date publishingDate;
	@Column(name="pages")
	private int numberOfPages;
	@Column(name="isRented")
	private boolean isRented;
	@Column(name="cover")
	private String cover;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Category category;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Writer author;
	
	public Book(){

	}
	
	public Book(String title, String isbn, String publisher, Date publishingDate, int numberOfPages, boolean isRented, String cover)
	{
		this.title = title;
		this.isbn = isbn;
		this.publisher = publisher;
		this.publishingDate = publishingDate;
		this.numberOfPages = numberOfPages;
		this.isRented = isRented;
		this.cover = cover;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getPublisher() {
		return publisher;
	}

	public Date getPublishingDate() {
		return publishingDate;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public boolean isRented() {
		return isRented;
	}

	public String getCover() {
		return cover;
	}

	public Category getCategory() {
		return category;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public void setRented(boolean isRented) {
		this.isRented = isRented;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Writer getAuthor() {
		return author;
	}

	public void setAuthors(Writer author) {
		this.author = author;
	}
}
