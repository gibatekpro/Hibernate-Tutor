package gibatekpro.hibernate.demo.bi;

import gibatekpro.hibernate.demo.entity.Instructor;
import gibatekpro.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {

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

			//start a transaction
			session.beginTransaction();

			//get the instructor detail object
			int id = 6;
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

			//get the instructor detail
			System.out.println("Instructor Detail: " + instructorDetail);

			//print associated Instructor
			System.out.println("The associated Instructor: " + instructorDetail.getInstructor());

			//remove deleted object from associations
			instructorDetail.getInstructor().setInstructorDetail(null);

			//Delete Instructor Detail
			System.out.println("Deleting Instructor Detail..." + instructorDetail);
			session.delete(instructorDetail);

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
