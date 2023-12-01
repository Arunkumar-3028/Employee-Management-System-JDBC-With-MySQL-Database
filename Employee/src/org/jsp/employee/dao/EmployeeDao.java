package org.jsp.employee.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.jsp.employee.dto.Employee;

public class EmployeeDao {

Connection con=null;
PreparedStatement pst=null;
ResultSet rs=null;
{
	Properties p =new Properties();
	FileInputStream fin=null;

	try {
		fin=new FileInputStream("C:\\Users\\AK\\Eclipse-Workspace 2\\JDBCconnect\\lib\\jdbc.properties");
		p.load(fin);
		con=DriverManager.getConnection(p.getProperty("url"),p);

	} catch(IOException |SQLException e) {
		e.printStackTrace();
	}
	
	finally {
		if(fin!=null) {
			try {
				fin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
public String SaveEmployee(Employee e) {
	String qry="insert into employee values(?,?,?,?,?,?,?)";
	try {
		pst=con.prepareStatement(qry);
		pst.setInt(1, e.getId());
		pst.setString(2, e.getName());
		pst.setString(3, e.getDesignation());
		pst.setDouble(4, e.getSalary());
		pst.setString(5, e.getEmail());
		pst.setLong(6, e.getPhone());
		pst.setString(7, e.getPassword());
		pst.executeUpdate();
		return "Employee saved";
	} catch (SQLException x) {
		return "unable to save the Employee";

	}
}
public String UpdateEmployee(Employee e) {
	String qry="update employee set name=?,designation=?,salary=?,email=?,phone=?,password=? where id=?";
	try {
		pst=con.prepareStatement(qry);
		pst.setInt(7, e.getId());
		pst.setString(1, e.getName());
		pst.setString(2, e.getDesignation());
		pst.setDouble(3, e.getSalary());
		pst.setString(4, e.getEmail());
		pst.setLong(5, e.getPhone());
		pst.setString(6, e.getPassword());
		pst.executeUpdate();
		return "PEmployee updated";
	} catch (SQLException x) {
		return "unable to update the Employee";

	}
}
public Employee findEmployeeById(int id) {
	String sql = "select * from employee where id=?";
	try {
		pst = con.prepareStatement(sql);
		pst.setInt(1, id);
		rs = pst.executeQuery();
		Employee e = new Employee();
		while (rs.next()) {
			e.setId(rs.getInt(1));
			e.setName(rs.getString(2));
			e.setDesignation(rs.getString(3));
			e.setSalary(rs.getDouble(4));
			e.setEmail(rs.getString(5));
			e.setPhone(rs.getLong(6));
			e.setPassword(rs.getString(7));
			return e;
		}
		return null;
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
}
public Employee verifyEmployeebyphone(long phone,String password) {
	String qry="select * from employee where email=? and password=?";
	try {
		pst=con.prepareStatement(qry);
		pst.setLong(1, phone);
		pst.setString(2, password);
		rs=pst.executeQuery();
		Employee e=new Employee();
		if(rs.next()) {
			e.setId(rs.getInt(1));
			e.setName(rs.getString(2));
			e.setDesignation(rs.getString(3));
			e.setSalary(rs.getDouble(4));
			e.setEmail(rs.getString(5));
			e.setPhone(rs.getLong(6));
			e.setPassword(rs.getString(7));
			return e;
		}
		return null;
	} catch (SQLException e) {
		
		e.printStackTrace();
		return null;
	}
	
	
}
public Employee verifyEmployeebyEmail(String email,String password) {
	String qry="select * from employee where email=? and password=?";
	try {
		pst=con.prepareStatement(qry);
		pst.setString(1, email);
		pst.setString(2, password);
		rs=pst.executeQuery();
		Employee e=new Employee();
		if(rs.next()) {
			e.setId(rs.getInt(1));
			e.setName(rs.getString(2));
			e.setDesignation(rs.getString(3));
			e.setSalary(rs.getDouble(4));
			e.setEmail(rs.getString(5));
			e.setPhone(rs.getLong(6));
			e.setPassword(rs.getString(7));
			return e;
		}
		return null;
	} catch (SQLException e) {
		
		e.printStackTrace();
		return null;
	}
	
	
}

public Employee findProductBydesignation(String designation) {
	String sql = "select * from employee where designation=?";
	try {
		pst = con.prepareStatement(sql);
		pst.setString(1, designation);
		rs = pst.executeQuery();
		Employee e = new Employee();
		while (rs.next()) {
			e.setId(rs.getInt(1));
			e.setName(rs.getString(2));
			e.setDesignation(rs.getString(3));
			e.setSalary(rs.getDouble(4));
			e.setEmail(rs.getString(5));
			e.setPhone(rs.getLong(6));
			e.setPassword(rs.getString(7));
			return e;
		}
		return null;
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
}

public List<Employee> FilterBySalary(){
	List<Employee> employees=new ArrayList<Employee>();
	String qry="select * from employee order by cost desc";


	try {
		pst=con.prepareStatement(qry);
		rs=pst.executeQuery();
		while (rs.next()) {
			Employee e =new Employee();
			e.setId(rs.getInt(1));
			e.setName(rs.getString(2));
			e.setDesignation(rs.getString(3));
			e.setSalary(rs.getDouble(4));
			e.setEmail(rs.getString(5));
			e.setPhone(rs.getLong(6));
			e.setPassword(rs.getString(7));
			employees.add(e);
		}
		return employees;
	}
	catch (SQLException e) 
	{
		e.printStackTrace();
		return employees;
	}

}	
public String exit()  {
	
		try {
			if (con != null)
			con.close();
			if (pst != null)
				pst.close();
			if (rs != null)
				rs.close();
			return "Application Closed!!!";
			
		} catch (SQLException e) {
			e.printStackTrace();
			return "Application Not Closed!!!";
		}
	
}
}
