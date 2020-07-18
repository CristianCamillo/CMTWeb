package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import beans.Sala;
import utils.DriverManagerConnectionPool;

public class SalaDAO
{
	public static Sala getSala(int id) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT * FROM sala WHERE id = " + id;
	    
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
		
		if(rs.next())
			return new Sala(rs.getInt("id"), rs.getByte("numerofile"), rs.getByte("postifila"));
		else
			return null;
	}
}
