package com.book.bookservice;

import java.util.Date;
import java.util.List;


import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.PersistenceContext;

import com.book.businessobject.Book;
import com.book.businessobject.Category;
import com.book.businessobject.Soldbook;
import com.book.businessobject.Writer;


@Stateless
@RolesAllowed(value = {"visitor", "renter", "admin"})
public class BookBean implements BookInterface {
	
		@PersistenceContext(name = "bookPU")
		private EntityManager em;
		
		@Resource 
		private SessionContext ctx;

		public Book getBookById(long id)
		{
			Query query = em.createQuery("FROM Book b WHERE b.id=:id");
			query.setParameter("id", id);
			
			return (Book) query.getSingleResult();
		}
		
		public List<Book> getAllBooks()
		{
			return (List<Book>) em.createQuery("SELECT b FROM Book b where b.isSold=false").getResultList();
		}
		
		public List<Book> getAllBooksFromCategory(Category category) {
			return (List<Book>) em.createQuery("SELECT b FROM Book b where b.category.id=:categoryid").setParameter("categoryid", category.getId()).getResultList();
		}
		public List<Book> getAllBooksFromAuthor(Writer author) {
			return (List<Book>) em.createQuery("SELECT b FROM Book b where b.author.id=:authorid").setParameter("authorid", author.getId()).getResultList();
		}
		@RolesAllowed(value = {"admin"})
		public List<Soldbook> getAllSoldBooks()
		{
			return (List<Soldbook>) em.createQuery("SELECT sb FROM Soldbook sb").getResultList();
		}
		
		public double getTotalSales()
		{
			return (double) em.createQuery("SELECT SUM(sb.book.price) FROM Soldbook sb").getSingleResult();
		}
		
		public long getTotalSoldbooks()
		{
			return (long) em.createQuery("SELECT COUNT(sb) FROM Soldbook sb").getSingleResult();
		}
		
		public double getAverageSales()
		{
			return (double) em.createQuery("SELECT AVG(sb.book.price) FROM Soldbook sb").getSingleResult();
		}
		
		@RolesAllowed(value = {"renter", "admin"})
		public String rent(Book book)
		{	
			String rentResult;
			
			if(!book.isRented())
			{
				book.setRented(true);
				em.merge(book);
				rentResult="You have rented this book!";
			}
			else
			{
				rentResult="We are sorry. The book is already rented!";
			}
			
			return rentResult;
		}
		
		@RolesAllowed(value = {"renter", "admin"})
		@TransactionAttribute(value=TransactionAttributeType.REQUIRED)
		public void buyBook(Book soldBook)
		{
			if(!soldBook.isSold())
			{
				soldBook.setSold(true);
				em.merge(soldBook);
				
				Soldbook s1 = new Soldbook(soldBook, new Date());
				em.merge(s1);
			}
		}
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
			em.createQuery("DELETE FROM Soldbook sb").executeUpdate();
			em.createQuery("DELETE FROM Book b").executeUpdate();
			em.createQuery("DELETE FROM Writer w").executeUpdate();
			em.createQuery("DELETE FROM Category c").executeUpdate();
			
			Category c1 = new Category("Fantasy");
			Writer w1 = new Writer("J.K", "Rowling");
			Book b1 = new Book("Harry Potter and the Philosopher's Stone", "I-1234567890", "Bloomsbury", "01-01-1997", 223, false, "https://upload.wikimedia.org/wikipedia/en/6/6b/Harry_Potter_and_the_Philosopher%27s_Stone_Book_Cover.jpg", 15.00, false);
			Book b6 = new Book("Harry Potter and the Chamber of Secrets", "I-1234567890", "Bloomsbury", "01-01-1998", 223, false, "https://upload.wikimedia.org/wikipedia/en/5/5c/Harry_Potter_and_the_Chamber_of_Secrets.jpg", 14.50, false);
			Book b7 = new Book("Harry Potter and the Prisoner of Azkaban", "I-1234567890", "Bloomsbury", "01-01-1999", 223, false, "https://upload.wikimedia.org/wikipedia/en/a/a0/Harry_Potter_and_the_Prisoner_of_Azkaban.jpg", 14.95, false);
			Book b8 = new Book("Harry Potter and the Goblet of Fire", "I-1234567890", "Bloomsbury", "01-01-2000", 223, false, "https://upload.wikimedia.org/wikipedia/en/c/c7/Harry_Potter_and_the_Goblet_of_Fire.jpg", 9.95, false);
			Book b9 = new Book("Harry Potter and the Order of the Phoenix", "I-1234567890", "Bloomsbury", "01-01-2003", 223, false, "https://upload.wikimedia.org/wikipedia/en/7/70/Harry_Potter_and_the_Order_of_the_Phoenix.jpg", 9.95, false);
			Book b10 = new Book("Harry Potter and the Half-Blood Prince", "I-1234567890", "Bloomsbury", "01-01-2005", 223, false, "https://upload.wikimedia.org/wikipedia/en/f/f0/Harry_Potter_and_the_Half-Blood_Prince.jpg", 15.00, false);
			Book b11 = new Book("Harry Potter and the Deathly Hallows", "I-1234567890", "Bloomsbury", "01-01-2007", 223, false, "https://upload.wikimedia.org/wikipedia/en/a/a9/Harry_Potter_and_the_Deathly_Hallows.jpg", 9.95, false);

			
			Category c2 = new Category("Romance");
			Writer w2 = new Writer("Margaret", "Mitchell");
			Book b2 = new Book("Gone with the Wind", "I-1234567", "Warner Books", "01-01-1936", 1037, true, "https://upload.wikimedia.org/wikipedia/en/6/6b/Gone_with_the_Wind_cover.jpg", 39.95, false);
			
			Category c3 = new Category("Fiction");
			Writer w3 = new Writer("George", "Orwell");
			Book b3 = new Book("Nineteen Eighty-Four", "I-1234567", "WSOY", "01-01-1949", 100, true, "https://upload.wikimedia.org/wikipedia/commons/6/6b/1984-Big-Brother.jpg", 27.75, false);
			Book b4 = new Book("Animal Farm", "I-1234567", "TAMMI", "01-01-1969", 126, false, "https://upload.wikimedia.org/wikipedia/commons/f/fb/Animal_Farm_-_1st_edition.jpg", 21.40, true);
			
			
			Category c4 = new Category("Mystery");
			Writer w4 = new Writer("Dan", "Brown");
			Book b5 = new Book("The Da Vinci Code", "I-1234567", "OTAVA", "10-04-2003", 454, false, "https://upload.wikimedia.org/wikipedia/en/6/6b/DaVinciCode.jpg", 50.40, true);
			
			
			Soldbook s1 = new Soldbook(b4, new Date());
			Soldbook s2 = new Soldbook(b5, new Date());
			
			
			//Harry potter series
			w1.writeBook(b1);
			w1.writeBook(b6);
			w1.writeBook(b7);
			w1.writeBook(b8);
			w1.writeBook(b9);
			w1.writeBook(b10);
			w1.writeBook(b11);
			c1.addBook(b1);
			c1.addBook(b6);
			c1.addBook(b7);
			c1.addBook(b8);
			c1.addBook(b9);
			c1.addBook(b10);
			c1.addBook(b11);
			
			//Gone with the wind
			w2.writeBook(b2);
			c2.addBook(b2);
			
			//george orwell books
			w3.writeBook(b3);
			w3.writeBook(b4);
			c3.addBook(b3);
			c3.addBook(b4);
			
			//dan brown
			w4.writeBook(b5);
			c4.addBook(b5);
			
			em.persist(c1);
			em.persist(c2);
			em.persist(c3);
			em.persist(c4);
			
			em.persist(w1);
			em.persist(w2);
			em.persist(w3);
			em.persist(w4);
			
			em.persist(s1);
			em.persist(s2);
			
			/*em.persist(b1);
			em.persist(b2);
			em.persist(b3);
			em.persist(b4);
			em.persist(b5);
			em.persist(b6);
			em.persist(b7);
			em.persist(b8);
			em.persist(b9);
			em.persist(b10);
			em.persist(b11);*/

			
			
		}
	}
