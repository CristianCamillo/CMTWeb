package beans;

public class Cliente
{	
	private int id;
	private String username;
	private String password;
	private float saldo;
	
	public Cliente(int id, String username, String password, float saldo)
	{
		this.id = id;
		this.username = username;
		this.password = password;
		this.saldo = saldo;
	}
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public float getSaldo()
	{
		return saldo;
	}
	
	public void setSaldo(float saldo)
	{
		this.saldo = saldo;
	}
}