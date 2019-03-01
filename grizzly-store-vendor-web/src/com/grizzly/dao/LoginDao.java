package com.grizzly.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.grizzly.pojo.LoginPojo;

public class LoginDao {
	
	static Connection connect=null;
	static Statement statement=null;
	
	public static int lockAccount(String username)
	{
		connect=DBUtil.makeConnection();
		String status="inactive";
		int update=0;
		
		try {
			
			statement=connect.createStatement();
			String query="update user_details set user_status='"+status+"' where user_name='"+username+"'";
			 update=statement.executeUpdate(query);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return update;
	}
	
	public	static LoginPojo  loginValidation(LoginPojo pojo)
    {
		Connection connection = DBUtil.makeConnection();
		java.sql.Statement statement = null;
		ResultSet resultSet ;
		LoginPojo newpojo = new LoginPojo();

		try {
			statement = connection.createStatement();
			String query = "select * from user_details where user_name='"+ pojo.getUsername() + "'";
			resultSet = statement.executeQuery(query);

			if (resultSet.next()) {

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
			e.printStackTrace();
		}
		return newpojo;

	}

}
