package org.jsp.employee.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.employee.dao.EmployeeDao;
import org.jsp.employee.dto.Employee;

public class EmployeeController {
public static void main(String[] args) {
	Scanner s = new Scanner(System.in);
	EmployeeDao dao = new EmployeeDao();
	System.out.println("Enter Your Choice");
	boolean flag = true;
	while (flag) {
		System.out.println("1.Save Employee");
		System.out.println("2.Update Employee");
		System.out.println("5.Find Employee By Id");
		System.out.println("4.Verify Employee by phone and password");
		System.out.println("5.Verify Employee by email and password");
		System.out.println("6.Find Employee by designation");
		System.out.println("7.Exit");
		System.out.println("8.Filter by Employee salary");
		switch (s.nextInt()) {
		case 1: {
			System.out.println("Enter the employee id,name,designation,salary,email,phone and password to save employee");
			int id = s.nextInt();
			String name = s.next();
			String designation=s.next();
			double salary=s.nextDouble();	
			String email = s.next();
			long phone = s.nextLong();
			String password = s.next();
			Employee e=new Employee(id, name, designation, salary, email, phone, password);
			String message = dao.SaveEmployee(e);
			System.out.println(message);
			break;
		}
		case 2:{
			System.out.println("Enter the employee id,name,designation,salary,email,phone and password to update employee");
			int id = s.nextInt();
			String name = s.next();
			String designation=s.next();
			double salary=s.nextDouble();	
			String email = s.next();
			long phone = s.nextLong();
			String password = s.next();
			Employee e=new Employee(id, name, designation, salary, email, phone, password);
			String message = dao.UpdateEmployee(e);
			System.out.println(message);
			break;
		}case 4: {
			System.out.println("Enter the phone Number and password to verify employee");
			long phone = s.nextLong();
			String password = s.next();
			Employee e = dao.verifyEmployeebyphone(phone, password);
			if (e != null) {
				System.out.println("Employee Verified Succesfully");
				System.out.println("Employee Id:" + e.getId());
				System.out.println("Employee Name:" + e.getName());
				System.out.println("Employee desigantion:"+e.getDesignation());
				System.out.println("Employee Salary:"+e.getSalary());
				System.out.println("Employee Email Id:" + e.getEmail());
				System.out.println("Employee Phone Number:" + e.getPhone());
			} else {
				System.err.println("Invalid Phone Number or Password");
			}
			break;
		}
		case 5: {
			System.out.println("Enter the Email Id and password to verify user");
			String email = s.next();
			String password = s.next();
			Employee e = dao.verifyEmployeebyEmail(email, password);
			if (e != null) {
				System.out.println("Employee Verified Succesfully");
				System.out.println("Employee Id:" + e.getId());
				System.out.println("Employee Name:" + e.getName());
				System.out.println("Employee desigantion:"+e.getDesignation());
				System.out.println("Employee Salary:"+e.getSalary());
				System.out.println("Employee Email Id:" + e.getEmail());
				System.out.println("Employee Phone Number:" + e.getPhone());
			} else {
				System.err.println("Invalid Email Id or Password");
			}
			break;
		}
		case 3: {
			System.out.println("Enter the Employee id to find the user");
			int id = s.nextInt();
			Employee e = dao.findEmployeeById(id);
			if (e != null) {
				System.out.println("Employee Id:" + e.getId());
				System.out.println("Employee Name:" + e.getName());
				System.out.println("Employee desigantion:"+e.getDesignation());
				System.out.println("Employee Salary:"+e.getSalary());
				System.out.println("Employee Email Id:" + e.getEmail());
				System.out.println("Employee Phone Number:" + e.getPhone());
			} else {
				System.err.println("Invalid Id");
			}
			break;
		}
		case 6: {
			System.out.println("Enter the Employee designation to find the user");
			String designation = s.next();
			Employee e = dao.findProductBydesignation(designation);
			if (e != null) {
				System.out.println("Employee Id:" + e.getId());
				System.out.println("Employee Name:" + e.getName());
				System.out.println("Employee desigantion:"+e.getDesignation());
				System.out.println("Employee Salary:"+e.getSalary());
				System.out.println("Employee Email Id:" + e.getEmail());
				System.out.println("Employee Phone Number:" + e.getPhone());
			} else {
				System.err.println("Invalid Designation");
			}
			break;
		}
		case 7: {
			flag = false;
			System.out.println(dao.exit());
			s.close();
		}
		case 8:{
			List<Employee> employees=dao.FilterBySalary();
			if(employees.size()>0) {
				for(Employee e:employees) {
					System.out.println("Employee Id:" + e.getId());
					System.out.println("Employee Name:" + e.getName());
					System.out.println("Employee desigantion:"+e.getDesignation());
					System.out.println("Employee Salary:"+e.getSalary());
					System.out.println("Employee Email Id:" + e.getEmail());
					System.out.println("Employee Phone Number:" + e.getPhone());
					System.out.println("================================");
				}
			}
			else {
				System.err.println("no Employee Found");
			}
		}
			
		}
	}
	
}
}
