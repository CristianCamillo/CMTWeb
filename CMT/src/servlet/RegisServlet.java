package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ClienteDAO;
import DAO.GestoreDAO;
import beans.Cliente;
import utils.DataChecker;

@WebServlet("/registrazione")
public class RegisServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
	public RegisServlet()
    {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String saldo = request.getParameter("saldo");
		
		if(!DataChecker.checkRegistrazione(response, username, password, saldo))
			return;
		
		try
		{					
			if(ClienteDAO.isRegistered(username) || GestoreDAO.isRegistered(username))
			{
				writeErrorMessage(response, "Questo utente è già registrato");
				return;
			}

			Cliente nuovoCliente = new Cliente(ClienteDAO.getLastId() + 1, username, password, Float.parseFloat(saldo));

			ClienteDAO.addCliente(nuovoCliente);

			HttpSession oldSession = request.getSession(false);
			
			// se esiste già una sessione per questo utente, viene invalidata
			if(oldSession != null)
				oldSession.invalidate();
			
			// crea una nuova sessione
			HttpSession currentSession = request.getSession();
			
			// attributo "id"
			currentSession.setAttribute("id", nuovoCliente.getId());
			
			// attributo "tipo"
			currentSession.setAttribute("tipo", "cliente");
			
			// vengono impostati massimo 5 minuti di inattività prima che la sessione venga eliminata
			currentSession.setMaxInactiveInterval(5 * 60);
			
			// reindirizzamento a homepage
			response.sendRedirect("homepage.jsp");
		}
		catch(SQLException e)
		{
			System.out.println("Regis Servlet SQL Exception");
		}		
	}
	
	private void writeErrorMessage(HttpServletResponse response, String msg) throws IOException
	{
		response.setContentType("text/html");	
		response.getOutputStream().println("<script>alert(\"" + msg + "\");</script>" + 
										   "<meta http-equiv=\"refresh\" content=\"0;URL=registrazione.jsp\">");
		response.getOutputStream().flush();
	}
}