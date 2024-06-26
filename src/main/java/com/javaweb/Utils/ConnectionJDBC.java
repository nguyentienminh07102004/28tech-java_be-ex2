package com.javaweb.Utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionJDBC {
	private static String url = "jdbc:mysql://localhost:3306/estatebasic?allowPublicKeyRetrieval=true&useSSL=false";
  	private static String user = "root";
  	private static String password = "Minhnguyen12345!";

	public static Connection getConnection() {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
    	return DriverManager.getConnection(url, user, password);
	} catch (Exception e) {
		System.out.println(e.getMessage());
		return null;
	}
    
  }
}
