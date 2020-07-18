package utils;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public class DataChecker
{
	private enum Field
	{
		USERNAME,
		PASSWORD,
		SALDO,
		TITOLO,
		GENERE,
		REGISTA,
		ATTORE
	}
	
	private enum Page
	{
		HOMEPAGE,
		REGISTRAZIONE,
		LOGIN
	}
	
	private static final Map<Field, String> errorMsgMap;
    static
    {
        Map<Field, String> map = new HashMap<Field, String>();
        map.put(Field.USERNAME,	"L'username deve avere tra 6 e 20 caratteri");
        map.put(Field.PASSWORD, "La password deve avere tra 6 e 20 caratteri");
        map.put(Field.SALDO, 	"Il saldo deve essere un numero maggiore o uguale a 0");
        map.put(Field.TITOLO,	"Il titolo deve avere massimo 30 caratteri");
        map.put(Field.GENERE,	"Il genere deve avere massimo 30 caratteri");
        map.put(Field.REGISTA,	"Il regista deve avere massimo 30 caratteri");
        map.put(Field.ATTORE,	"L'attore deve avere massimo 30 caratteri");
        errorMsgMap = Collections.unmodifiableMap(map);
    }
    
    private static final Map<Page, String> pageMap;
    static
    {
        Map<Page, String> map = new HashMap<Page, String>();
        map.put(Page.HOMEPAGE, 		"homepage.jsp");
        map.put(Page.REGISTRAZIONE, "registrazione.jsp");
        map.put(Page.LOGIN, 		"login.jsp");
        pageMap = Collections.unmodifiableMap(map);
    }
	
	private static void writeErrorMessage(HttpServletResponse response, String msg, String page) throws IOException
	{
		response.setContentType("text/html");	
		response.getOutputStream().println("<script>alert(\"" + msg + "\");</script>" + 
										   "<meta http-equiv=\"refresh\" content=\"0;URL=" + page + "\">");
		response.getOutputStream().flush();
	}
	
	private static boolean checkBetween(String string, int min, int max)
	{
		return min <= string.length() && string.length() <= max;
	}
	
	private static boolean checkMax(String string, int max)
	{
		return string.length() <= max;
	}
	
	private static boolean checkIsNumber(String string)
	{
		try
		{
			Double.parseDouble(string);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
	
	public static boolean checkFiltro(HttpServletResponse response, String titolo, String genere, String regista, String attore) throws IOException
	{
		if(!checkMax(titolo, 30))
		{
			writeErrorMessage(response, errorMsgMap.get(Field.TITOLO), pageMap.get(Page.HOMEPAGE));
			return false;
		}
		
		if(!checkMax(genere, 30))
		{
			writeErrorMessage(response, errorMsgMap.get(Field.GENERE), pageMap.get(Page.HOMEPAGE));
			return false;
		}
		
		if(!checkMax(regista, 30))
		{
			writeErrorMessage(response, errorMsgMap.get(Field.REGISTA), pageMap.get(Page.HOMEPAGE));
			return false;
		}
		
		if(!checkMax(attore, 30))
		{
			writeErrorMessage(response, errorMsgMap.get(Field.ATTORE), pageMap.get(Page.HOMEPAGE));
			return false;
		}
		
		return true;
	}
	
	public static boolean checkRegistrazione(HttpServletResponse response, String username, String password, String saldo) throws IOException
	{
		if(!checkBetween(username, 6, 20))
		{
			writeErrorMessage(response, errorMsgMap.get(Field.USERNAME), pageMap.get(Page.REGISTRAZIONE));
			return false;
		}
		
		if(!checkBetween(password, 6, 20))
		{
			writeErrorMessage(response, errorMsgMap.get(Field.PASSWORD), pageMap.get(Page.REGISTRAZIONE));
			return false;
		}
		
		if(!checkIsNumber(saldo))
		{
			writeErrorMessage(response, errorMsgMap.get(Field.SALDO), pageMap.get(Page.REGISTRAZIONE));
			return false;
		}
		
		return true;
	}
	
	public static boolean checkLogin(HttpServletResponse response, String username, String password) throws IOException
	{
		if(!checkBetween(username, 6, 20))
		{
			writeErrorMessage(response, errorMsgMap.get(Field.USERNAME), pageMap.get(Page.REGISTRAZIONE));
			return false;
		}
		
		if(!checkBetween(password, 6, 20))
		{
			writeErrorMessage(response, errorMsgMap.get(Field.PASSWORD), pageMap.get(Page.REGISTRAZIONE));
			return false;
		}
		
		return true;
	}
}