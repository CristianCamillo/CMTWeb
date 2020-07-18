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
import exceptions.UserNotRegisteredException;
import utils.DataChecker;

@WebServlet("/login")
public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    public LoginServlet()
    {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(!DataChecker.checkLogin(response, username, password))
			return;
		
		try
		{
			int id;
			boolean isCliente;
			
			try
			{
				id = ClienteDAO.getId(username, password);
				isCliente = true;
			}
			catch(UserNotRegisteredException e)
			{
				try
				{
					id = GestoreDAO.getId(username, password);
				   	isCliente = false;
				}
				catch(UserNotRegisteredException e1)
				{
					writeErrorMessage(response, "Dati non associati ad alcun utente");
					return;
				}
			}
		    
		    HttpSession oldSession = request.getSession(false);
		    			
			// se esiste già una sessione per questo utente, viene invalidata
			if(oldSession != null)
				oldSession.invalidate();
			
			// crea una nuova sessione
			HttpSession currentSession = request.getSession();
			
			// attributo "id"
			currentSession.setAttribute("id", id);
			
			// attributo "tipo"
			currentSession.setAttribute("tipo", isCliente ? "cliente" : "gestore");
			
			// vengono impostati massimo 5 minuti di inattività prima che la sessione venga eliminata
			currentSession.setMaxInactiveInterval(5 * 60);
			
			// reindirizzamento a homepage
			response.sendRedirect("homepage.jsp");				
		}
		catch(SQLException e)
		{
			System.out.println("Login Servlet SQL Exception");
		}		
	}
	
	private void writeErrorMessage(HttpServletResponse response, String msg) throws IOException
	{
		response.setContentType("text/html");	
		response.getOutputStream().println("<script>alert(\"" + msg + "\");</script>" + 
										   "<meta http-equiv=\"refresh\" content=\"0;URL=login.jsp\">");
		response.getOutputStream().flush();
	}
}