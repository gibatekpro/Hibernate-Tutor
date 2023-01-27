package com.gibatekpro.hibernate.demo;

import java.text.ParseException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gibatekpro.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

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
			
			//create 3 student objects
			System.out.println("Creating new student object...");
			Student tempStudent1 = new Student("John", "Gibah", date, "john@gibatekpro.com");
			Student tempStudent2 = new Student("Frank", "Okosun", date, "frank@gibatekpro.com");
			Student tempStudent3 = new Student("Fred", "Oyemike", date, "fred@gibatekpro.com");
			
			//start a transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving the student...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			
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
