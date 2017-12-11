package com.book.businessobject;

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
	@Column(name="address")
	private String address;
	
	@OneToMany(mappedBy="author", cascade = CascadeType.ALL)
	private Set<Book> books;
	
	public Writer(){}
	
	public Writer(String firstname, String lastname, String address)
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
	}
	
}
