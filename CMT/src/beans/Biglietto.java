package beans;

public class Biglietto
{
	private int id;
	private short posto;
	private int idCliente;
	private int idProiezione;
	
	public Biglietto(int id, short posto, int idCliente, int idProiezione)
	{
		this.id = id;
		this.posto = posto;
		this.idCliente = idCliente;
		this.idProiezione = idProiezione;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public short getPosto()
	{
		return posto;
	}
	
	public void setPosto(short posto)
	{
		this.posto = posto;
	}
	
	public int getIdCliente()
	{
		return idCliente;
	}
	
	public void setIdCliente(int idCliente)
	{
		this.idCliente = idCliente;
	}
	
	public int getIdProiezione()
	{
		return idProiezione;
	}
	
	public void setIdProiezione(int idProiezione)
	{
		this.idProiezione = idProiezione;
	}
}