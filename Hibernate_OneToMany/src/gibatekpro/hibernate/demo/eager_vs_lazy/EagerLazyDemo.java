package gibatekpro.hibernate.demo.eager_vs_lazy;

import gibatekpro.hibernate.demo.entity.Course;
import gibatekpro.hibernate.demo.entity.Instructor;
import gibatekpro.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyDemo {

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

            System.out.println("gibatek: Instructor: " + instructor);

            //get courses for the instructor
            System.out.println("gibatek: Courses: " + instructor.getCourses());

            //commit transaction
            session.getTransaction().commit();

            System.out.println("gibatek: Done!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            session.close();

            factory.close();
            System.out.println("Factory Closed!!!");
        }


    }

}
