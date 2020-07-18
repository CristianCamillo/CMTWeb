package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BigliettoDAO;
import DAO.FilmDAO;
import DAO.ProiezioneDAO;
import DAO.SalaDAO;
import beans.Film;
import beans.Proiezione;
import beans.Sala;
import utils.DataChecker;

@WebServlet("/filtro")
public class FiltroServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    public FiltroServlet()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String titolo = request.getParameter("titolo");
		String genere = request.getParameter("genere");
		String regista = request.getParameter("regista");
		String attore = request.getParameter("attore");
		
		if(!DataChecker.checkFiltro(response, titolo, genere, regista, attore))
			return;
		
		try
		{
			ArrayList<Film> films = FilmDAO.findFilm(titolo, genere, regista, attore);
			
			request.getSession().setAttribute("films", films);
			
			ArrayList<ArrayList<Proiezione>> proiezioni = new ArrayList<ArrayList<Proiezione>>();			
			ArrayList<ArrayList<ArrayList<String>>> statoSale = new ArrayList<ArrayList<ArrayList<String>>>(); // IL MALE
			
			for(int i = 0; i < films.size(); i++)
			{
				ArrayList<Proiezione> ps = ProiezioneDAO.getProiezioni(films.get(i).getId(), false);
				ArrayList<ArrayList<String>> ss = new ArrayList<ArrayList<String>>();
				
				for(Proiezione p : ps)
				{
					Sala sala = SalaDAO.getSala(p.getIdSala());
					
					ArrayList<Short> posti = BigliettoDAO.getPosti(p.getId());
					
					ArrayList<String> stato = new ArrayList<String>();
					
					stato.add("\"" + sala.getPostiFila() + "-" + sala.getNumeroFile() + "\"");
					
					for(Short posto : posti)
					{						
						int x = posto / 100;
						int y = posto % 100;
						
						stato.add("\"" + x + "-" + y + "\"");
					}
							
					ss.add(stato);
				}
				
				proiezioni.add(ps);
				statoSale.add(ss);
			}
			
			request.getSession().setAttribute("proiezioni", proiezioni);
			request.getSession().setAttribute("statoSale", statoSale);
			
			request.getRequestDispatcher("homepage.jsp").forward(request, response);
		}
		catch(SQLException e)
		{
			System.out.println("Filtro Servlet SQL Exception");
		}
	}
}