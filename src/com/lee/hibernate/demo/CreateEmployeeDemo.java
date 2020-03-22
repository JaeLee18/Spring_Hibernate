package com.lee.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lee.hibernate.demo.entity.Employee;
import com.lee.hibernate.demo.entity.Student;

public class CreateEmployeeDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									 .configure("hibernate_employee.cfg.xml")
									 .addAnnotatedClass(Employee.class)
									 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			
			
			// start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving the Employee ...");
			
			// commit the transaction
			session.getTransaction().commit();
			
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("Get all employees");
			List<Employee> employees = session.createQuery("from Employee").getResultList();
			for(Employee e: employees) {
				System.out.println(e);
			}
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
		
		
		
	}

}
