package beans;

public class Sala
{
	private int id;
	private byte numeroFile;
	private byte postiFila;
	
	public Sala(int id, byte numeroFile, byte postiFila)
	{
		this.id = id;
		this.numeroFile = numeroFile;
		this.postiFila = postiFila;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public byte getNumeroFile()
	{
		return numeroFile;
	}

	public void setNumeroFile(byte numeroFile)
	{
		this.numeroFile = numeroFile;
	}

	public byte getPostiFila()
	{
		return postiFila;
	}

	public void setPostiFila(byte postiFila)
	{
		this.postiFila = postiFila;
	}	
}