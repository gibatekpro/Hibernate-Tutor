package gibatekpro.hibernate.demo.uni;

import com.gibatekpro.hibernate.demo.entity.Instructor;
import com.gibatekpro.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {

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

			//get instructor by primary key (id)
			int instructorId = 1;
			Instructor instructor = session.get(Instructor.class, instructorId);

			System.out.println("Found instructor: " + instructor);

			//delete instructor
			if (instructor != null) {

				System.out.println("Deleting: " + instructor);

				session.delete(instructor);

			}

			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {

			session.close();

			factory.close();
			System.out.println("Factory Closed!!!");
		}

	}

}
