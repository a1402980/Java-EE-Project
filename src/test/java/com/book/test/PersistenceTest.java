package com.book.test;

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
		EntityTransaction tx = null;
		try {

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookPU");
			EntityManager em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Category c = new Category("Fiction!");
			Category c2 = new Category("Thriller");
			Category c3 = new Category("Novel");
			
			Writer w = new Writer("John", "Smith", "Sierre");
			Writer w2 = new Writer("Jake", "Pratt", "NYC");
			
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
