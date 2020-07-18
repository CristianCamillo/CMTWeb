package exceptions;

public class UserNotRegisteredException extends RuntimeException
{
	// EC 3
	
	private static final long serialVersionUID = 13143769754901513L;
	
	public UserNotRegisteredException()
	{
		super("I dati inseriti non sono associati a nessun utente.");
	}
}
