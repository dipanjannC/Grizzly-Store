package com.grizzly.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.grizzly.pojo.LoginPojo;
import com.grizzly.validation.WebsiteException;

public class LoginDao {
	
	static Connection connect=null;
	static Statement statement=null;
	
	public static int lockAccount(String username) throws WebsiteException
	{
		//Establishing connection with the database
		connect=DBUtil.makeConnection();
		String status="inactive";
		int update=0;
		
		try {
			
			statement=connect.createStatement();
			// Executing query to lock the account in user_details
			String query="update user_details set user_status='"+status+"' where user_name='"+username+"'";
			update=statement.executeUpdate(query);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new WebsiteException("Failed to lock account");
		}
		
		return update;
	}
	
	public	static LoginPojo  loginValidation(LoginPojo pojo) throws WebsiteException
    {
		Connection connection = DBUtil.makeConnection();
		java.sql.Statement statement = null;
		ResultSet resultSet ;
		LoginPojo newpojo = new LoginPojo();

		try {
			statement = connection.createStatement();
			//Executing query to fetch the user details from the database
			String query = "select * from user_details where user_name='"+ pojo.getUsername() + "'";
			resultSet = statement.executeQuery(query);

			if (resultSet.next()) {
				//setting data into the pojo
				
				newpojo.setUsername(resultSet.getString(1));
				newpojo.setPassword(resultSet.getString(2));
				newpojo.setRole(resultSet.getString(3));
				newpojo.setStatus(resultSet.getString(4));

			}

			if (resultSet != null) {
				resultSet.close();

			}
			statement.close();

		} catch (SQLException e) {
			throw new WebsiteException("Can't fetch user_details from the User Database");
		}
		return newpojo;

	}

}
