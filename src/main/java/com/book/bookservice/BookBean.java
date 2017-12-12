package com.book.bookservice;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.book.businessobject.Book;
import com.book.businessobject.Category;
import com.book.businessobject.Writer;

@Stateful
public class BookBean implements BookInterface {
	
		@PersistenceContext(name = "BankPU", type=PersistenceContextType.EXTENDED)
		private EntityManager em;

		public Book getBook(String title, String authorFirstname) {
			Query query = em.createQuery("FROM Book b WHERE b.title=:title AND b.author.firstname=:authorFirstname");
			query.setParameter("title", title);
			query.setParameter("authorFirstname", authorFirstname);
			
			return (Book) query.getSingleResult();
		}
		
		public List<Book> getAllBooksFromAuthor(String authorLastName) {
			return (List<Book>) em.createQuery("SELECT w.books FROM Writer w where w.lastname=:authorLastName").setParameter("authorLastName", authorLastName).getResultList();
		}

		/*public void rent(Person srcAccount, Account destAccount, int amount) {
			
			srcAccount = em.persist(srcAccount);
			destAccount = em.persist(destAccount);
			srcAccount.debit(amount);
			destAccount.credit(amount);
		}*/

		public List<Writer> getWriters() {
			return em.createQuery("FROM Writer").getResultList();
		}
		
		public Writer getWriter(long writerid) {
			return (Writer) em.createQuery("FROM Writer w where w.id=:id").setParameter("id", writerid).getSingleResult();
		}
		public List<Category> getCategories() {
			return em.createQuery("FROM Writer").getResultList();
		}
		
		public Category getCategory(long categoryid) {
			return (Category) em.createQuery("FROM Category c where c.id=:id").setParameter("id", categoryid).getSingleResult();
		}

	}
