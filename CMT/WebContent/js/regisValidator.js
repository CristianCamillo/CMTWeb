function validateForm()
{
	var username = document.forms["form"]["username"].value;
	var password = document.forms["form"]["password"].value;
	var confPassword = document.forms["form"]["confPassword"].value;
	var saldo = document.forms["form"]["saldo"].value;
	var termini = document.forms["form"]["termini"].checked;
	
	if(username.length < 6 || username.length > 20)
	{
    	alert("L'username deve avere tra 6 e 20 caratteri");
    	return false;
	}
	
	if(password.length < 6 || password.length > 20)
	{
    	alert("La password deve avere tra 6 e 20 caratteri");
    	return false;
	}
	
	if(confPassword.length < 6 || confPassword.length > 20)
	{
    	alert("La conferma password deve avere tra 6 e 20 caratteri");
    	return false;
	}
	
	if(saldo == "" || isNaN(saldo) || saldo < 0)
	{
    	alert("Il saldo deve essere un numero maggiore o uguale a 0");
    	return false;
	}
	
	if(!termini)
	{
		alert("Bisogna accettare il trattamento dei propri dati personali");
		return false;
	}
	
	if(password != confPassword)
	{
		alert("La password e la conferma password devono combaciare");
		return false;
	}
	
	return true;
}