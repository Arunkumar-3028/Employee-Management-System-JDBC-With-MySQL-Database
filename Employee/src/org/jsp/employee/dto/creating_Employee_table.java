package org.jsp.employee.dto;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class creating_Employee_table {
	public static void main(String[] args) {
		
	
	Properties p=new Properties();
    FileInputStream fin= null;
    Connection con=null;
    Statement st=null;
    
    
    
    String qry="create table Employee(id int,name varchar(25) not null,designation varchar(25) not null,salary decimal not null,email varchar(40) unique not null,phone bigint unique not null,password varchar(20) not null,primary key(id))";
    		try {
				fin=new FileInputStream("C:\\Users\\AK\\Desktop\\test\\Employee\\lib\\jdbc.properties");
				p.load(fin);
				Class.forName(p.getProperty("DriverClass"));
				con=DriverManager.getConnection(p.getProperty("url"), p);
				st=con.createStatement();
				 st.execute(qry);
				
				
			} catch (IOException |SQLException | ClassNotFoundException e) {
				
				e.printStackTrace();
			}
    		
    
	
	}
}
