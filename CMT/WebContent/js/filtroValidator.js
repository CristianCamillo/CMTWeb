function validateForm()
{
	var titolo = document.forms["form"]["titolo"].value;
	var genere = document.forms["form"]["genere"].value;
	var regista = document.forms["form"]["regista"].value;
	var attore = document.forms["form"]["attore"].value;
	
	if(titolo.length > 30)
	{
    	alert("Il titolo deve avere massimo 30 caratteri");
    	return false;
	}
	
	if(genere.length > 30)
	{
    	alert("Il genere deve avere massimo 30 caratteri");
    	return false;
	}
	
	if(regista.length > 30)
	{
    	alert("Il regista deve avere massimo 30 caratteri");
    	return false;
	}
	
	if(attore.length > 30)
	{
    	alert("L'attore deve avere massimo 30 caratteri");
    	return false;
	}
	
	return true;
}