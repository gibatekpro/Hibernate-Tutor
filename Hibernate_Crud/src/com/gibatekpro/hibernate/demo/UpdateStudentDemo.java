package com.gibatekpro.hibernate.demo;

import java.text.ParseException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gibatekpro.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		//create Session
		Session session = factory.getCurrentSession();
		
		try {
			Date date = DateUtils.parseDate("02/06/1994");
			
			//create a student object
			System.out.println("Creating new student object...");
			Student tempStudent = new Student("Daffy", "Duck", date, "paul@gibatekpro.com");
			
			//start a transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving the student...");
			System.out.println(tempStudent);
			session.save(tempStudent);
			
			//commit transaction
			session.getTransaction().commit();
			
			//find out the student's id: primary key
			System.out.println("Saved student. Generated id: " + tempStudent.getId());
			
			//now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: " + tempStudent.getId());
			
			Student myStudent = session.get(Student.class, tempStudent.getId());
			
			System.out.println("Get complete: " + myStudent);
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			factory.close();
			System.out.println("Factory Closed!!!");
		}
		

	}

}
