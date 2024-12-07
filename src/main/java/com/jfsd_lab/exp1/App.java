package com.jfsd_lab.exp1;

import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {

	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();

		Scanner sc = new Scanner(System.in);
		int choice;
		do {
			System.out.println("===== MENU =====");
			System.out.println("1. Add Student");
			System.out.println("2. Update Student");
			System.out.println("3. Delete Student");
			System.out.println("4. View Student");
			System.out.println("5. View All Students");
			System.out.println("6. Exit");
			System.out.println("Enter your choice: ");
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				addStudent(sf, sc);
				break;
			case 2:
				updateStudent(sf, sc);
				break;
			case 3:
				deleteStudent(sf, sc);
				break;
			case 4:
				viewStudent(sf, sc);
				break;
			case 5:
				viewAllStudents(sf);
				break;
			case 6:
				System.out.println("Exiting...");
				break;
			default:
				System.out.println("Invalid choice, try again.");
			}
		} while (choice != 6);

		sf.close();
		sc.close();
	}

	private static void addStudent(SessionFactory sf, Scanner sc) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		Student student = new Student();
		System.out.println("Enter Student ID: ");
		student.setId(sc.nextInt());
		System.out.println("Enter Student Name: ");
		student.setName(sc.next());
		System.out.println("Enter Student Gender: ");
		student.setGender(sc.next());
		System.out.println("Enter Student Department: ");
		student.setDept(sc.next());
		System.out.println("Enter Student Program: ");
		student.setProg(sc.next());

		System.out.println("Enter Student DOB (yyyy-MM-dd): ");
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dob = sdf.parse(sc.next());
			student.setDob(dob);
		} catch (Exception e) {
			System.out.println("Invalid date format.");
			return;
		}

		System.out.println("Enter Student Phone Number: ");
		student.setPhone(sc.nextLong());
		System.out.println("Is the student graduated? (true/false): ");
		student.setGradStatus(sc.nextBoolean());
		System.out.println("Enter Student CGPA: ");
		student.setCgpa(sc.nextDouble());
		System.out.println("Enter Number of Backlogs: ");
		student.setBacklogs(sc.nextInt());

		session.save(student);
		tx.commit();
		session.close();
		System.out.println("Student added successfully!");
	}

	private static void updateStudent(SessionFactory sf, Scanner sc) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		System.out.println("Enter Student ID to update: ");
		int id = sc.nextInt();
		Student student = session.get(Student.class, id);
		if (student != null) {
			System.out.println("Enter new Student Name: ");
			sc.nextLine();
			student.setName(sc.nextLine());
			System.out.println("Enter new Student Gender: ");
			student.setGender(sc.next());
			System.out.println("Enter new Student Department: ");
			student.setDept(sc.next());
			System.out.println("Enter new Student Program: ");
			student.setProg(sc.next());

			System.out.println("Enter new Student DOB (yyyy-MM-dd): ");
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date dob = sdf.parse(sc.next());
				student.setDob(dob);
			} catch (Exception e) {
				System.out.println("Invalid date format.");
				return;
			}

			System.out.println("Enter new Student Phone Number: ");
			student.setPhone(sc.nextLong());
			System.out.println("Is the student graduated? (true/false): ");
			student.setGradStatus(sc.nextBoolean());
			System.out.println("Enter new Student CGPA: ");
			student.setCgpa(sc.nextDouble());
			System.out.println("Enter new Number of Backlogs: ");
			student.setBacklogs(sc.nextInt());

			session.update(student);
			tx.commit();
			System.out.println("Student updated successfully!");
		} else {
			System.out.println("Student not found.");
		}

		session.close();
	}

	private static void deleteStudent(SessionFactory sf, Scanner sc) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		System.out.println("Enter Student ID to delete: ");
		int id = sc.nextInt();
		Student student = session.get(Student.class, id);
		if (student != null) {
			session.delete(student);
			tx.commit();
			System.out.println("Student deleted successfully!");
		} else {
			System.out.println("Student not found.");
		}

		session.close();
	}

	private static void viewStudent(SessionFactory sf, Scanner sc) {
		Session session = sf.openSession();

		System.out.println("Enter Student ID to view: ");
		int id = sc.nextInt();
		Student student = session.get(Student.class, id);
		if (student != null) {
			System.out.println("Student Details:");
			System.out.println("ID: " + student.getId());
			System.out.println("Name: " + student.getName());
			System.out.println("Gender: " + student.getGender());
			System.out.println("Department: " + student.getDept());
			System.out.println("Program: " + student.getProg());
			System.out.println("DOB: " + student.getDob());
			System.out.println("Phone: " + student.getPhone());
			System.out.println("Graduation Status: " + student.isGradStatus());
			System.out.println("CGPA: " + student.getCgpa());
			System.out.println("Backlogs: " + student.getBacklogs());
		} else {
			System.out.println("Student not found.");
		}

		session.close();
	}

	private static void viewAllStudents(SessionFactory sf) {
		System.out.println("==========================================");
		System.out.println();
		Session session = sf.openSession();

		try {
			session.beginTransaction();

			List<Student> students = session.createQuery("from Student", Student.class).list();

			if (!students.isEmpty()) {
				System.out.println("Student Details:");
				for (Student student : students) {
					System.out.println("ID: " + student.getId());
					System.out.println("Name: " + student.getName());
					System.out.println("Gender: " + student.getGender());
					System.out.println("Department: " + student.getDept());
					System.out.println("Program: " + student.getProg());
					System.out.println("DOB: " + student.getDob());
					System.out.println("Phone: " + student.getPhone());
					System.out.println("Graduation Status: " + student.isGradStatus());
					System.out.println("CGPA: " + student.getCgpa());
					System.out.println("Backlogs: " + student.getBacklogs());
				}
			} else {
				System.out.println("No students found.");
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		System.out.println();
		System.out.println("==========================================");
	}
}
