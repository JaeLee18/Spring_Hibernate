package com.lee.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lee.hibernate.demo.entity.Employee;
import com.lee.hibernate.demo.entity.Student;

public class UpdateEmployeeDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									 .configure("hibernate_employee.cfg.xml")
									 .addAnnotatedClass(Employee.class)
									 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			
			// use the session object to save Java Object
			
			int employeeId = 1;
			// start a transaction
			session.beginTransaction();
			
			System.out.println("Update id=1's company to Samsung");
			Employee employee = session.get(Employee.class, employeeId);
			employee.setCompany("Samsung");
			
			// commit the transaction
			session.getTransaction().commit();
			
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("Get all employees");
			List<Employee> employees = session.createQuery("from Employee").getResultList();
			getAllEmployees(employees);
			
			// commit the transaction
			session.getTransaction().commit();
			
			
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			System.out.println("Delete an employee whose company is Google");
			session.createQuery("delete from Employee where company='Google'").executeUpdate();
			
			
			System.out.println("Get all employees");
			employees = session.createQuery("from Employee").getResultList();
			getAllEmployees(employees);
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
		
		
		
	}

	private static void getAllEmployees(List<Employee> employees) {
		
		for(Employee e: employees) {
			System.out.println(e);
		}
	}

}
