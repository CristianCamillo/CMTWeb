package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Cliente;
import exceptions.CannotPurchaseException;
import exceptions.UserNotRegisteredException;
import exceptions.UsernameTakenException;
import utils.DriverManagerConnectionPool;

public class ClienteDAO
{	
	public static int getId(String username, String password) throws SQLException, UserNotRegisteredException
	{		
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT id FROM cliente WHERE LOWER(username) = '" + username.toLowerCase() + "' AND password = '" + password + "'";
	    
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
		
		String query = "SELECT id FROM cliente WHERE LOWER(username) = '" + username.toLowerCase() + "'";
	    
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	    
	    if(rs.next())
	    	return true;
	    else
	    	return false;
	}
	
	public static int getLastId() throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT id FROM cliente ORDER BY id DESC LIMIT 1";
		
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);

	    if(rs.next())    
	    	return rs.getInt("id");
	    else
	    	return -1;
	}
	
	public static void addCliente(Cliente cliente) throws SQLException, UsernameTakenException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT id FROM cliente WHERE LOWER(username) = '" + cliente.getUsername().toLowerCase() + "'";
		
		ResultSet rs = con.createStatement().executeQuery(query);
		
		if(rs.next())
			throw new UsernameTakenException(cliente.getUsername());
		
		String insert = "INSERT INTO cliente VALUES ('" + cliente.getId() + "', '" + cliente.getUsername() + "', '" + cliente.getPassword() + "', '" + cliente.getSaldo() + "')";
		
	    con.createStatement().executeUpdate(insert);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	}
	
	public static void spend(int id, float ammontare) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT saldo FROM cliente WHERE id = " + id;
	    
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
		
		if(rs.next())
		{
			float saldo = rs.getFloat("saldo");
			
			if(saldo < ammontare)
			{
				DriverManagerConnectionPool.releaseConnection(con);
				throw new CannotPurchaseException();
			}
			
			String update = "UPDATE cliente SET saldo = " + (saldo - ammontare) + " WHERE id = " + id;
			
		    con.createStatement().executeUpdate(update);
		}
		
		DriverManagerConnectionPool.releaseConnection(con);
	}
}