package exceptions;

public class UsernameTakenException extends RuntimeException
{
	// EC 2
	
	private static final long serialVersionUID = -8676871720762712544L;

	public UsernameTakenException(String username)
	{
		super("L'username \"" + username + "\" è già in uso.");
	}
}
