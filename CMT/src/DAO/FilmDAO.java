package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Film;
import utils.DriverManagerConnectionPool;

public class FilmDAO
{
	public static ArrayList<Film> findFilm(String titolo, String genere, String regista, String attore) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getConnection();

		String query = "SELECT distinct(film.id) as id, film.titolo, film.durata, film.genere, film.regista, film.attore1, film.attore2, film.descrizione, film.locandina FROM film INNER JOIN proiezione ON film.id = proiezione.idfilm WHERE strcmp(proiezione.data, DATE_FORMAT(SYSDATE(), '%Y%m%d')) >= 0";
		
		String toAdd = "";
		
		if(!titolo.equals(""))
			toAdd += " AND LOWER(titolo) = '" + titolo.toLowerCase() + "'";
		
		if(!genere.equals(""))
			toAdd += " AND LOWER(genere) = '" + genere.toLowerCase() + "'";
		
		if(!regista.equals(""))
			toAdd += " AND LOWER(regista) = '" + regista.toLowerCase() + "'";
		
		if(!attore.equals(""))
		{
			String al = attore.toLowerCase();
			
			toAdd += " AND (LOWER(attore1) = '" + al + "' or LOWER(attore2) = '" + al + "')";
		}
		
		ResultSet rs = con.createStatement().executeQuery(query + toAdd);

		DriverManagerConnectionPool.releaseConnection(con);

		ArrayList<Film> list = new ArrayList<Film>();
		
		while(rs.next())	
			list.add(new Film(rs.getInt("id"), rs.getString("titolo"), rs.getShort("durata"),
							  rs.getString("genere"), rs.getString("regista"), rs.getString("attore1"),
							  rs.getString("attore2"), rs.getString("descrizione"), rs.getString("locandina")));

		return list;
	}
}