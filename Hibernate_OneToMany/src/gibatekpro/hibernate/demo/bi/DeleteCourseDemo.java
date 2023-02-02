package gibatekpro.hibernate.demo.bi;

import gibatekpro.hibernate.demo.entity.Course;
import gibatekpro.hibernate.demo.entity.Instructor;
import gibatekpro.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseDemo {

    public static void main(String[] args) {

        //create session factory
        //Always add every annotated class
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        //create Session
        Session session = factory.getCurrentSession();

        try {

            //start a transaction
            session.beginTransaction();

            //get the course object
            int id = 10;
            Course course = session.get(Course.class, id);

            //get the Course
            System.out.println("Course: " + course);

            //Delete Course
            System.out.println("Deleting Course..." + course);
            session.delete(course);

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
