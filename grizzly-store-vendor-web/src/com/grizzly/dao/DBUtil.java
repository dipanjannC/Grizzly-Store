package com.grizzly.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.grizzly.validation.WebsiteException;

public class DBUtil {

	static Connection connection;
	static
//	Loading the Driver
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	
	}
	//	MAKING CONNECTION
	static Connection makeConnection() throws WebsiteException
	{
		if (connection==null)
		{
			try{
				connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/grizzly_storeDB", "root", "root");
			}
			catch (SQLException e) {
				throw new WebsiteException("Server Down. Please try after sometime");
			}
		}		
		return connection;
   
	}
	
//	CLOSING CONNECTION
	static void closeConnection() throws WebsiteException
	{
		try {
			connection.close();
		}
		catch (SQLException e) {
			throw new WebsiteException("Error while Closing.Please Try Again");
		}
	}

	
	
}
