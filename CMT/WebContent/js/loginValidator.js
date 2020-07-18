function validateForm()
{
	var username = document.forms["form"]["username"].value;
	var password = document.forms["form"]["password"].value;
	
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
	
	return true;
}