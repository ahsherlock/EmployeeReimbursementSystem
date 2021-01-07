package com.reimbursement.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static final String URL = "jdbc:postgresql://reimbursementdb.cwtzuvrcq59p.us-west-1.rds.amazonaws.com/postgres";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "holywater1";
	
	private static Connection conn;
	
	public static Connection getConnection() {
		
		
		try {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
