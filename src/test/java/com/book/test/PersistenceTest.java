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
						.println("Write a query or or 'quit': ");
				cmd = reader.readLine();
				if ("quit".equals(cmd)) {
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

}
