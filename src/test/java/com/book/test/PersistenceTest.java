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

			Category c = new Category("Fiction");
			Category c2 = new Category("Thriller");
			Category c3 = new Category("Novel");
			Category c4 = new Category("Drama");
			Category c5 = new Category("Mystery");
			Category c6 = new Category("Adventure");
			Category c7 = new Category("Romance");
			Category c8 = new Category("Fantasy");
			
			Writer w = new Writer("George", "Orwell", "Sierre");
			Writer w1 = new Writer("Jake", "Pratt", "NYC");
			Writer w2 = new Writer("John", "Smith", "Sierre");
			Writer w3 = new Writer("Jake", "Pratt", "NYC");
			Writer w4 = new Writer("John", "Smith", "Sierre");
			Writer w5 = new Writer("Jake", "Pratt", "NYC");
			Writer w6 = new Writer("John", "Smith", "Sierre");
			Writer w7 = new Writer("Jake", "Pratt", "NYC");
			Writer w8 = new Writer("John", "Smith", "Sierre");
			Writer w9 = new Writer("Jake", "Pratt", "NYC");
			
			Book b = new Book("1 mile", "I-1234567890", "London House", "10-10-2016", 100, false, "Awesome");
			Book b2 = new Book("test 2", "I-1234567", "idk", "10-10-2006", 100, true, "good");
			Book b3 = new Book("test 3", "I-1234567", "idk", "10-10-2006", 100, true, "good");
			Book b4 = new Book("test 4", "I-1234567", "idk", "10-10-2006", 100, true, "good");
			Book b5 = new Book("test 5", "I-1234567", "idk", "10-10-2006", 100, true, "good");
			
			w.writeBook(b2);
			w.writeBook(b);
			w.writeBook(b3);
			w2.writeBook(b2);
			w2.writeBook(b4);
			w2.writeBook(b5);
			c.addBook(b);
			c2.addBook(b2);
			c2.addBook(b3);
			c.addBook(b4);
			c.addBook(b5);
			em.persist(c);
			em.persist(c2);
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
