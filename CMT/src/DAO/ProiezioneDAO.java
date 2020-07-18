package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Proiezione;
import utils.DriverManagerConnectionPool;

public class ProiezioneDAO
{
	public static ArrayList<Proiezione> getProiezioni(int idFilm, boolean includePast) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();
		
		String query = "SELECT * FROM proiezione WHERE proiezione.idfilm = " + idFilm +
					   (includePast ? " " : " AND STRCMP(proiezione.data, DATE_FORMAT(SYSDATE(), '%Y%m%d')) >= 0 ") +
					   "ORDER BY proiezione.data, proiezione.orario";
	    
	    ResultSet rs = con.createStatement().executeQuery(query);
	    
	    DriverManagerConnectionPool.releaseConnection(con);
	    
	    ArrayList<Proiezione> list = new ArrayList<Proiezione>();
		
		while(rs.next())	
			list.add(new Proiezione(rs.getInt("id"), rs.getInt("data"), rs.getShort("orario"), rs.getFloat("costo"), rs.getInt("idsala"), rs.getInt("idfilm")));

		return list;
	}
}