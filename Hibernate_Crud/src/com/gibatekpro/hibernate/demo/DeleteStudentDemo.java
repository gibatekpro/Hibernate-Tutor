package com.gibatekpro.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gibatekpro.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		//create Session
		Session session = factory.getCurrentSession();
		
		try {
			
			int studentId = 1;
			
			//now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			//delete the student id = 2
			System.out.println("Deleting Student" + myStudent);
			session.createQuery("delete from Student where id=2")
					.executeUpdate();
			

			//delete the student
			//System.out.println("Deleting Student" + myStudent);
			//session.delete(myStudent);
			
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}finally {
			factory.close();
			System.out.println("Factory Closed!!!");
		}
		

	}

}
