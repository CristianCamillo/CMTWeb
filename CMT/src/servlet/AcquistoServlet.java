package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.BigliettoDAO;
import DAO.ClienteDAO;
import beans.Biglietto;
import exceptions.CannotPurchaseException;

@WebServlet("/acquisto")
public class AcquistoServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    public AcquistoServlet()
    {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int idCliente = (Integer)request.getSession().getAttribute("id");	
		float totale = Float.parseFloat(request.getParameter("totale"));
		
		try
		{
			ClienteDAO.spend(idCliente, totale);
		}
		catch(CannotPurchaseException e)
		{
			writeMessage(response, "Saldo non sufficiente per effettuare l'acquisto");
		}
		catch(SQLException e)
		{
			System.out.println("Acquisto Servlet SQL Exception");
		}		
		
		String postiString = request.getParameter("posti");
		int idProiezione = Integer.parseInt(request.getParameter("idProiezione"));
		
		String[] aPosti = postiString.split("/");
		
		try
		{
			int lastId = BigliettoDAO.getLastId();
			int counter = 0;
			
			for(String sPosto : aPosti)
			{
				counter++;
				String[] aPosto = sPosto.split("-");
				
				short posto = (short)((Integer.parseInt(aPosto[0]) + 1) * 100 + Integer.parseInt(aPosto[1]) + 1);
				
				BigliettoDAO.addBiglietto(new Biglietto(lastId + counter, posto, idCliente, idProiezione));
			}
			
			 HttpSession oldSession = request.getSession(false);
 			
			// se esiste già una sessione per questo utente, viene invalidata
			if(oldSession != null)
				oldSession.invalidate();
			
			// crea una nuova sessione
			HttpSession currentSession = request.getSession();
			
			// attributo "id"
			currentSession.setAttribute("id", idCliente);
			
			// attributo "tipo"
			currentSession.setAttribute("tipo", "cliente");
			
			// vengono impostati massimo 5 minuti di inattività prima che la sessione venga eliminata
			currentSession.setMaxInactiveInterval(5 * 60);
			
			// reindirizzamento a homepage
			response.sendRedirect("homepage.jsp");
		}
		catch(CannotPurchaseException e)
		{
			writeMessage(response, "Almeno uno dei biglietti è stato acquistato");
		}
		catch(SQLException e)
		{
			System.out.println("Acquisto Servlet SQL Exception");
		}
	}
	
	private void writeMessage(HttpServletResponse response, String msg) throws IOException
	{
		response.setContentType("text/html");	
		response.getOutputStream().println("<script>alert(\"" + msg + "\");</script>" + 
										   "<meta http-equiv=\"refresh\" content=\"0;URL=homepage.jsp\">");
		response.getOutputStream().flush();
	}
}