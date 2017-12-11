package com.book.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;


public class QueryTest {

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

			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("bankPU");
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

	public static void populate() {
		EntityTransaction tx = null;
		try {

			
			EntityManagerFactory emf = Persistence
					.createEntityManagerFactory("bankPU");
			EntityManager em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Client c1 = new Client("PLATINI", "MICHEL", "Ex footeux");
			Address a1 = new Address("75000", "Champs elyse", "Paris");
			c1.setAddress(a1);

			c1.addExternalAccount(new ExternalAccount("010101", "Account A"));
			c1.addExternalAccount(new ExternalAccount("020202", "Account B"));

			InternalAccount ci1 = new InternalAccount("1", "desc1", 1000);
			InternalAccount ci2 = new InternalAccount("2", "desc2", 2000);

			ci1.addOperation(new Operation("Boucher", 10, new Date()));
			ci1.addOperation(new Operation("Charcutier", 12, new Date()));

			ci2.addOperation(new Operation("Plasma", 10000, new Date()));
			ci2.addOperation(new Operation("Ipod", 450, new Date()));
			ci2.addOperation(new Operation("Nounou", 700, new Date()));

			c1.addInternalAccount(ci1);
			c1.addInternalAccount(ci2);



			Book b = new Book();
			b.addClient(c1);
			b.addClient(c2);


			em.persist(b);
			
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

