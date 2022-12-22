package com.eshopify.connectiondb;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {
	
	
	static Connection connection = null;
	
	
	public static Connection connectDb() {
		try {	
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root", "root");
			} catch (Exception e)
			{
			e.printStackTrace();
			}
			return connection;
			}
	}
	
	
	
	


