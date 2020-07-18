package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Biglietto;
import exceptions.CannotPurchaseException;
import utils.DriverManagerConnectionPool;

public class BigliettoDAO
{
	public static ArrayList<Short> getPosti(int idProiezione) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT posto FROM biglietto WHERE idProiezione = " + idProiezione;
	    
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	    
	    ArrayList<Short> list = new ArrayList<Short>();
	    
	    while(rs.next())
	    	list.add(rs.getShort("posto"));
	    
	    return list;
	}
	
	public static int getLastId() throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT id FROM biglietto ORDER BY id DESC LIMIT 1";
		
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);

	    if(rs.next())    
	    	return rs.getInt("id");
	    else
	    	return -1;
	}
	
	public static void addBiglietto(Biglietto biglietto) throws SQLException, CannotPurchaseException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT id FROM biglietto WHERE posto = " + biglietto.getPosto() + " AND idproiezione = " + biglietto.getIdProiezione();
	    
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    if(rs.next())
	    {
	    	DriverManagerConnectionPool.releaseConnection(con);
	    	throw new CannotPurchaseException();
	    }
	    
	    String insert = "INSERT INTO biglietto VALUES (" + biglietto.getId() + ", " + biglietto.getPosto() + ", " + biglietto.getIdCliente() + ", " + biglietto.getIdProiezione() + ")";
	   
	    con.createStatement().executeUpdate(insert);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	}
}
