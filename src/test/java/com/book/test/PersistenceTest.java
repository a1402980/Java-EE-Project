package com.book.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

import com.book.businessobject.Book;
import com.book.businessobject.Category;
import com.book.businessobject.Writer;


public class PersistenceTest {

	@Test
	public void test() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String cmd;
			while (true) {
				System.out
						.println("Write a query [or 'populate' or 'quit']: ");
				cmd = reader.readLine();

				if ("populate".equals(cmd)) {
					populate();
				} else if ("quit".equals(cmd)) {
					System.out.println("The End");
					break;
				} else {
					executeRequest(cmd);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void executeRequest(String cmd) {
		List result = null;
		EntityTransaction tx = null;
		try {

			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookPU");
			EntityManager em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			result = em.createQuery(cmd).getResultList();
			Iterator it = result.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
			tx.commit();

		} catch (Exception e) {
			System.err.println(e.getMessage());
			try {
				tx.rollback();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} 
		}
	}

	public void populate() {
		EntityTransaction tx = null;
		try {

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookPU");
			EntityManager em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			
			Category c1 = new Category("Fantasy");
			Writer w1 = new Writer("J.K", "Rowling");
			Book b1 = new Book("Harry Potter and the Philosopher's Stone", "I-1234567890", "Bloomsbury", "01-01-1997", 223, false, "https://upload.wikimedia.org/wikipedia/en/6/6b/Harry_Potter_and_the_Philosopher%27s_Stone_Book_Cover.jpg");
			
			Category c2 = new Category("Romance");
			Writer w2 = new Writer("Margaret", "Mitchell");
			Book b2 = new Book("Gone with the Wind", "I-1234567", "Warner Books", "01-01-1936", 1037, true, "https://upload.wikimedia.org/wikipedia/en/6/6b/Gone_with_the_Wind_cover.jpg");
			
			Category c3 = new Category("Fiction");
			Writer w3 = new Writer("George", "Orwell");
			Book b3 = new Book("Nineteen Eighty-Four", "I-1234567", "WSOY", "01-01-1949", 100, true, "https://upload.wikimedia.org/wikipedia/commons/6/6b/1984-Big-Brother.jpg");
			Book b4 = new Book("Animal Farm", "I-1234567", "TAMMI", "01-01-1969", 126, false, "https://upload.wikimedia.org/wikipedia/commons/f/fb/Animal_Farm_-_1st_edition.jpg");
			
			
			Category c4 = new Category("Mystery");
			Writer w4 = new Writer("Dan", "Brown");
			Book b5 = new Book("The Da Vinci Code", "I-1234567", "OTAVA", "10-04-2003", 454, false, "https://upload.wikimedia.org/wikipedia/en/6/6b/DaVinciCode.jpg");
			
			
			w1.writeBook(b1);
			c1.addBook(b1);
			
			w2.writeBook(b2);
			c2.addBook(b2);
			
			w3.writeBook(b3);
			w3.writeBook(b4);
			c3.addBook(b3);
			c3.addBook(b4);
			
			w4.writeBook(b5);
			c4.addBook(b5);
			
			em.persist(c1);
			em.persist(c2);
			em.persist(c3);
			em.persist(c4);
			
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			/*
			 * try { tx.rollback(); } catch (IllegalStateException e1) {
			 * e1.printStackTrace(); } catch (SecurityException e1) {
			 * e1.printStackTrace(); } catch (SystemException e1) {
			 * e1.printStackTrace(); }
			 */
		}

	}
}
