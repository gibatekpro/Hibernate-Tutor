package com.gibatekpro.hibernate.demo;

import java.text.ParseException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gibatekpro.hibernate.demo.entity.Student;

public class CreateStudentDemo {

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
			//Student tempStudent = new Student("Paula", "Wale", date, "pasa@gibatekpro.com");
			
			//start a transaction
			session.beginTransaction();
			
			String dates = DateUtils.formatDate(date);
			
			//save the student object
			System.out.println("Saving the student...");
			//Student student = session.get(Student.class, 3); 
			//student.setDateOfBirth(date);
			session.createQuery("update Student set dateOfBirth = :date").setParameter("date", date).executeUpdate();
			
			//commit transaction
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
