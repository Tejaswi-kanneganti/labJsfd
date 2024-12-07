package com.jfsd_lab.exp2;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.jfsd_lab.exp1.Student;

public class App {

    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();

        //1. HQL Query to Display all student records with all columns.
        List<Student> studs = s.createQuery("from Student", Student.class).list();
        for (Student stu : studs) {
            System.out.println(stu);
        }

        //2. HQL Query to Display all student records with specific columns.
        List<Object[]> cols = s.createQuery("select id, name, cgpa from Student").list();
        for (Object[] row : cols) {
            System.out.println("ID: " + row[0] + ", Name: " + row[1] + ", CGPA: " + row[2]);
        }

        //3. HQL Query to Display only names of all students whose CGPA is greater than 7.
        List<String> cgpaAbove7 = s.createQuery("select name from Student where cgpa > 7").list();
        for (String name : cgpaAbove7) {
            System.out.println(name);
        }

        //4. HQL Query to Delete a Student whose Student ID is 30101 using input parameter.
        Query<Student> delStud = s.createQuery("delete from Student where id = :studentId");
        delStud.setParameter("studentId", 30101);
        int result = delStud.executeUpdate();
        System.out.println("Delete Result: " + result);

        //5. HQL Query to Update few details of a student whose Student ID is 30102 using input parameter.
        Query<Student> updStud = s.createQuery("update Student set cgpa = :newCgpa where id = :studentId");
        updStud.setParameter("newCgpa", 8.5);
        updStud.setParameter("studentId", 30102);
        int updateResult = updStud.executeUpdate();
        System.out.println("Update Result: " + updateResult);

        //6. HQL Query to Perform aggregate functions on CGPA column.
        Object[] arr = (Object[]) s.createQuery("select count(*), avg(cgpa), sum(cgpa), min(cgpa), max(cgpa) from Student").uniqueResult();
        System.out.println("Count: " + arr[0] + ", Avg: " + arr[1] + ", Sum: " + arr[2] + ", Min: " + arr[3] + ", Max: " + arr[4]);

        //7. HCQL Query to Display specific columns from set of student records.
        List<Object[]> name_cgpa = s.createNativeQuery("select name, cgpa from Student").list();
        for (Object[] row : name_cgpa) {
            System.out.println("Name: " + row[0] + ", CGPA: " + row[1]);
        }

        //8. HCQL Query to Get 5th to 10th records.
        List<Student> subStuds = s.createNativeQuery("select * from Student", Student.class)
                .setFirstResult(4).setMaxResults(6).list();
        for (Student stu : subStuds) {
            System.out.println(stu);
        }

        //9. HCQL Query to Apply various conditions on CGPA column.
        List<Student> conditionStudents = s.createNativeQuery("select * from Student where cgpa > 7 and cgpa <= 9", Student.class).list();
        for (Student stu : conditionStudents) {
            System.out.println(stu);
        }

        //10. HCQL Query to Get records in ascending and descending order based on Student Name.
        List<Student> studentsAsc = s.createNativeQuery("select * from Student order by name asc", Student.class).list();
        for (Student stu : studentsAsc) {
            System.out.println(stu);
        }
        
        List<Student> studentsDesc = s.createNativeQuery("select * from Student order by name desc", Student.class).list();
        for (Student stu : studentsDesc) {
            System.out.println(stu);
        }

        tx.commit();
        s.close();
        sf.close();
    }
}
