package com.gibatekpro.hibernate.crud;

import com.gibatekpro.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentDemo {

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

            //Create a course
            Course course = new Course("Air Guitar - Ultimate Guide");

            //save the course
            System.out.println("\nSaving the course ...");
            session.save(course);
            System.out.println("\nSaved the course: " + course);

            //create the student
            Student student = new Student("Mary", "Osunide", "gibatekpro@gmail.com");
            Student student1 = new Student("Joseph", "Gibah", "devappliance@gmail.com");
            Student student2 = new Student("Henry", "Osunide", "gibatekpro@gmail.com");

            //add students to course
            course.addStudent(student);
            course.addStudent(student1);
            course.addStudent(student2);

            //save the students
            System.out.println("\nSaving the students ...");
            session.save(student);
            session.save(student1);
            session.save(student2);
            System.out.println("\nSaved the students: " + course);

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
