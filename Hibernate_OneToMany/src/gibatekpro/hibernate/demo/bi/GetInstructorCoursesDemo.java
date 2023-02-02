package gibatekpro.hibernate.demo.bi;

import gibatekpro.hibernate.demo.entity.Course;
import gibatekpro.hibernate.demo.entity.Instructor;
import gibatekpro.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorCoursesDemo {

    public static void main(String[] args) {

        //create session factory
        //Always add every annotated class
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        //create Session
        Session session = factory.getCurrentSession();

        try {

            //start a transaction
            session.beginTransaction();

            //get the instructor detail object
            int id = 1;
            Instructor instructor = session.get(Instructor.class, id);

            //get courses for the instructor
            System.out.println("Courses: " + instructor.getCourses());

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
