package gibatekpro.hibernate.one_to_many_uni;

import gibatekpro.hibernate.one_to_many_uni.entity.Course;
import gibatekpro.hibernate.one_to_many_uni.entity.Instructor;
import gibatekpro.hibernate.one_to_many_uni.entity.InstructorDetail;
import gibatekpro.hibernate.one_to_many_uni.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCourseAndReviewsDemo {

    public static void main(String[] args) {

        //create session factory
        //Always add every annotated class
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        //create Session
        Session session = factory.getCurrentSession();

        try {
            //start a transaction
            session.beginTransaction();

            int id = 10;

            Course course = session.get(Course.class, id);

            System.out.println(course.getReviews());

            session.save(course);

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
