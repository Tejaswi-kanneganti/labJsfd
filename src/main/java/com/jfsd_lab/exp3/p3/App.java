package com.jfsd_lab.exp3.p3;

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

		Electronics electronics = new Electronics();
		electronics.setProductId(201);
		electronics.setName("Smart TV");
		electronics.setWarranty("2 years");
		electronics.setBrand("Samsung");
		session.save(electronics);

		Clothing clothing = new Clothing();
		clothing.setProductId(202);
		clothing.setName("Jacket");
		clothing.setSize("L");
		clothing.setMaterial("Leather");
		session.save(clothing);

		tx.commit();
		session.close();
		sf.close();
	}
}
