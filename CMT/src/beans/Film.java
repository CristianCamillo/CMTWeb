package beans;

public class Film
{
	private int id;
	private String titolo;
	private short durata;
	private String genere;
	private String regista;
	private String attore1;
	private String attore2;
	private String descrizione;
	private String locandina;
	
	public Film(int id, String titolo, short durata, String genere, String regista, String attore1, String attore2, String descrizione, String locandina)
	{
		this.id = id;
		this.titolo = titolo;
		this.durata = durata;
		this.genere = genere;
		this.regista = regista;
		this.attore1 = attore1;
		this.attore2 = attore2;
		this.descrizione = descrizione;
		this.locandina = locandina;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getTitolo()
	{
		return titolo;
	}

	public void setTitolo(String titolo)
	{
		this.titolo = titolo;
	}

	public short getDurata()
	{
		return durata;
	}

	public void setDurata(short durata)
	{
		this.durata = durata;
	}

	public String getGenere()
	{
		return genere;
	}

	public void setGenere(String genere)
	{
		this.genere = genere;
	}

	public String getRegista()
	{
		return regista;
	}

	public void setRegista(String regista)
	{
		this.regista = regista;
	}

	public String getAttore1()
	{
		return attore1;
	}

	public void setAttore1(String attore1)
	{
		this.attore1 = attore1;
	}

	public String getAttore2()
	{
		return attore2;
	}

	public void setAttore2(String attore2)
	{
		this.attore2 = attore2;
	}

	public String getDescrizione()
	{
		return descrizione;
	}

	public void setDescrizione(String descrizione)
	{
		this.descrizione = descrizione;
	}

	public String getLocandina()
	{
		return locandina;
	}

	public void setLocandina(String locandina)
	{
		this.locandina = locandina;
	}
	
	// sistemato per javascript
	public String toString()
	{
		return "[\"" + id + "\", \"" + titolo + "\", \"" + durata + "\", \"" + genere + "\", \"" + regista + "\", \"" + attore1 + "\", \"" + attore2 + "\", \"" + descrizione + "\", \"" + locandina + "\"]";
	}
}