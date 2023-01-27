package com.gibatekpro.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gibatekpro.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create Session
		Session session = factory.getCurrentSession();

		try {
			int studentId = 1;

			// start a transaction
			session.beginTransaction();
			
			//retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("Updating student...");
			myStudent.setFirstName("Scooby");

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
			
			
			//NEW CODE
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//update email for all students
			System.out.println("Update email for all students");
			
			session.createQuery("update Student set email = 'foo@gmail.com'")
					.executeUpdate();
			
			//commit the transaction
			session.getTransaction().commit();
			

		} finally {
			factory.close();
			System.out.println("Factory Closed!!!");
		}

	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
