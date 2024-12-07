package com.jfsd_lab.exp3.p2;

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

		Car car = new Car();
		car.setVehicleId(101);
		car.setModel("Toyota Corolla");
		car.setSeats(5);
		car.setFuelType("Petrol");
		session.save(car);

		Bike bike = new Bike();
		bike.setVehicleId(102);
		bike.setModel("Harley Davidson");
		bike.setType("Cruiser");
		bike.setEngineCapacity(1500);
		session.save(bike);

		tx.commit();
		session.close();
		sf.close();
	}
}
