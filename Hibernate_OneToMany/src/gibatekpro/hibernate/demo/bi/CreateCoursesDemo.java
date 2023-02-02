package gibatekpro.hibernate.demo.bi;

import gibatekpro.hibernate.demo.entity.Course;
import gibatekpro.hibernate.demo.entity.Instructor;
import gibatekpro.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {

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

            //get the instructor from db
            int id = 1;
            Instructor instructor = session.get(Instructor.class, 1);

            //Create some courses
            Course course = new Course("Air Guitar - Ultimate Guide");
            Course course1 = new Course("Bass Guitar - Ultimate Guide");

            //add courses to instructor
            instructor.add(course);
            instructor.add(course1);

            session.save(course);
            session.save(course1);

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
