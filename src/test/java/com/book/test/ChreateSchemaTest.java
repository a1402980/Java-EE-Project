package com.book.test;

import org.hibernate.cfg.Configuration;
import org.hibernate.internal.CoreMessageLogger;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.jboss.logging.Logger;
import org.junit.Test;

import junit.framework.TestCase;

public class ChreateSchemaTest extends TestCase {

	@Test
	public void test() {

		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass(com.book.businessobject.Category.class);
		cfg.addAnnotatedClass(com.book.businessobject.Writer.class);
		cfg.addAnnotatedClass(com.book.businessobject.Book.class);

		cfg.setProperty("hibernate.dialect",
				"org.hibernate.dialect.HSQLDialect");
		cfg.setProperty("hibernate.connection.driver_class",
				"org.hsqldb.jdbcDriver");
		cfg.setProperty("hibernate.connection.driver_class",
				"org.hsqldb.jdbcDriver");
		cfg.setProperty("hibernate.connection.url",
				"jdbc:hsqldb:hsql://localhost/DB");
		cfg.setProperty("hibernate.connection.username", "sa");
		
		new SchemaExport(cfg).setOutputFile("schema.ddl").create(false, true);
	}
}
