package beans;

public class Proiezione
{
	private int id;
	private int data;
	private short orario;
	private float costo;
	private int idSala;
	private int idFilm;
	
	public Proiezione(int id, int data, short orario, float costo, int idSala, int idFilm)
	{
		this.id = id;
		this.data = data;
		this.orario = orario;
		this.costo = costo;
		this.idSala = idSala;
		this.idFilm = idFilm;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getData()
	{
		return data;
	}

	public void setData(int data)
	{
		this.data = data;
	}
	
	public short getOrario()
	{
		return orario;
	}

	public void setOrario(short orario)
	{
		this.orario = orario;
	}

	public float getCosto()
	{
		return costo;
	}

	public void setCosto(float costo)
	{
		this.costo = costo;
	}

	public int getIdSala()
	{
		return idSala;
	}

	public void setIdSala(int idSala)
	{
		this.idSala = idSala;
	}

	public int getIdFilm()
	{
		return idFilm;
	}

	public void setIdFilm(int idFilm)
	{
		this.idFilm = idFilm;
	}
	
	// sistemato per javascript
	public String toString()
	{
		return "[\"" + id + "\", \"" + data + "\", \"" + orario + "\", \"" + costo + "\", \"" + idSala + "\", \"" + idFilm + "\"]";
	}
}