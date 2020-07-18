package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.UserNotRegisteredException;
import utils.DriverManagerConnectionPool;

public class GestoreDAO
{
	public static int getId(String username, String password) throws SQLException, UserNotRegisteredException
	{		
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT id FROM gestore WHERE LOWER(username) = '" + username.toLowerCase() + "' AND password = '" + password + "'";
	    
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	    
	    if(rs.next())
	    	return rs.getInt("id");
	    else
	    	throw new UserNotRegisteredException();  
	}
	
	public static boolean isRegistered(String username) throws SQLException
	{		
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT id FROM gestore WHERE LOWER(username) = '" + username.toLowerCase() + "'";
	    
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	    
	    if(rs.next())
	    	return true;
	    else
	    	return false;
	}
}