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
	
		@PersistenceContext(name = "bookPU", type=PersistenceContextType.EXTENDED)
		private EntityManager em;

		public Book getBook(String title, String authorLastName) {
			Query query = em.createQuery("FROM Book b WHERE b.title=:title AND b.author.lastname=:authorLastName");
			query.setParameter("title", title);
			query.setParameter("authorLastName", authorLastName);
			
			return (Book) query.getSingleResult();
		}
		public List<Book> getAllBooks()
		{
			return (List<Book>) em.createQuery("SELECT b FROM Book b").getResultList();
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
		public void addData()
		{
			em.createQuery("DELETE FROM Book b").executeUpdate();
			em.createQuery("DELETE FROM Writer w").executeUpdate();
			em.createQuery("DELETE FROM Category c").executeUpdate();
			
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
			
		}
	}
