package com.practice;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Cache.Connection;

public class StudentDao {
	private String driver;
	private String url;
	private String username;
	private String password;
	
	java.sql.Connection con;
	Statement stmt;
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		System.out.println("setting driver");
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		System.out.println("setting url");
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		System.out.println("setting username");
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		System.out.println("setting password");
		this.password = password;
	}
	
	//@PostConstruct
	public void createDatabaseConnection() throws ClassNotFoundException, SQLException {
		
		System.out.println("init method called");
		//load driver
				Class.forName(driver);
				System.out.println("making connection");
				//make connection
				 con= DriverManager.getConnection(url, username, password);
				
				//execute query
				 stmt= con.createStatement();
	}
	public void selectAllRows() throws ClassNotFoundException, SQLException {
		
		//createDatabaseConnection();
		ResultSet rs= stmt.executeQuery("SELECT * FROM employees.employee");
		
		while(rs.next()) 
		{
			int empID= rs.getInt(1);
			String emp1stName= rs.getString(2);
			String empLastName= rs.getString(3);
			int salary= rs.getInt(4);
			System.out.println("Reterieved records are:");
			System.out.println(empID+" "+ emp1stName+" "+ empLastName+" "+ salary );
		}
		closeConnnection();
		
	}
	public void deleteEmpRecord(int empID)throws ClassNotFoundException, SQLException  {
		
				//createDatabaseConnection();
			 stmt.executeUpdate("DELETE FROM employees.employee where id= "+ empID);
			 System.out.println("1 record has been deleted");
			 closeConnnection();
	}
	//@PreDestroy
	public void closeConnnection() throws SQLException {
	 con.close();
	 System.out.println("destroy method called");
	}
}

