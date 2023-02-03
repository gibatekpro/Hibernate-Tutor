package com.gibatekpro.hibernate.crud;

import com.gibatekpro.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateCoursesForStudentDemo {

    public static void main(String[] args) {

        //create session factory
        //Always add every annotated class
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        //create Session
        Session session = factory.getCurrentSession();

        try {
            //start a transaction
            session.beginTransaction();

            //get student
            int id =1;

            Student student = session.get(Student.class, id);

            System.out.println("The student information: " + student);
            System.out.println("The student's current courses: " + student.getCourses());

            Course course = new Course("Fluid Mechanics");
            Course course1 = new Course("Algebra and Calculus");
            Course course2 = new Course("Engineering Mathematics");

            student.addCourse(course);
            student.addCourse(course1);
            student.addCourse(course2 );

            session.save(course);
            session.save(course1);
            session.save(course2);

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
