package com.lee.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lee.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									 .configure("hibernate.cfg.xml")
									 .addAnnotatedClass(Student.class)
									 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			// display the students
			displayStudents(theStudents);
			
			// query students: lastName= 'Doe'
			
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			
			
			System.out.println("\n\nStudents who have last name of Doe");
			displayStudents(theStudents);	
			
			theStudents = 
					session.createQuery("from Student s where"
							+ " s.lastName='Doe' or s.firstName='Daffy'").getResultList();
			
			System.out.println("\n\nStudents who have last name of Doe or first name Daffy");
			displayStudents(theStudents);
			
			//query students where emali Like '%email.com'
			theStudents = 
					session.createQuery("from Student s where"
							+ " s.email LIKE '%email.com'").getResultList();			
			
			System.out.println("\n\nStudents who have emails end with email.com");
			displayStudents(theStudents);
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
		
		
		
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent: theStudents) {
			System.out.println(tempStudent);
		}
	}

}
