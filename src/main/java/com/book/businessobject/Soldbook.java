package com.book.businessobject;

import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Soldbook")
public class Soldbook {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="price")
	private double price;
	
	@Column(name="date")
	private Timestamp solddate;

	public Soldbook()
	{
		
	}
	
	public Soldbook(String title, double price)
	{
		this.title = title;
		this.price = price;
	}
	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	

}
