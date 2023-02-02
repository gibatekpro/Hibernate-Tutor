package com.gibatekpro.hibernate.demo.bi;

import com.gibatekpro.hibernate.demo.entity.Instructor;
import com.gibatekpro.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {

	public static void main(String[] args) {
		
		//create session factory
		//Always add every annotated class
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
		
		//create Session
		Session session = factory.getCurrentSession();
		
		try {

			//create instructor object
			System.out.println("Creating new instructor object...");
			Instructor instructor = new Instructor("Tony", "Gibah", "broad@gibatekpro.com");

			//create instructor detail object
			System.out.println("Creating instructor detail object");
			InstructorDetail instructorDetail = new InstructorDetail(
			"https://www.gibatekpro.com",
			"Luv football!!!"
			);

			//associate the objects
			instructor.setInstructorDetail(instructorDetail);

			//start a transaction
			session.beginTransaction();

			/*
			* Save the instructor
			* Note: this will also save the details object because
			* of CascadeType.ALL
			*/
			System.out.println("Saving instructor: " + instructor);
			session.save(instructor);

			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			session.close();

			factory.close();
			System.out.println("Factory Closed!!!");
		}
		

	}

}
