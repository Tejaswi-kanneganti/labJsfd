package com.jfsd_lab.exp3.p1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();

		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		Book book = new Book();
		book.setItemId(1);
		book.setTitle("Effective Java");
		book.setAuthor("Joshua Bloch");
		book.setIsbn(978_013_4685_991L);

		session.save(book);

		DVD dvd = new DVD();
		dvd.setItemId(2);
		dvd.setDirector("Inception");
		dvd.setRegionCode("4455333");
		dvd.setDirector("Christopher Nolan");

		session.save(dvd);

		tx.commit();
		session.close();
		sf.close();
	}
}
