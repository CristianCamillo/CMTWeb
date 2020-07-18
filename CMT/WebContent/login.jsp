<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>CMT - Login</title>
		<link rel="stylesheet" type="text/css" href="css/body.css">
		<link rel="stylesheet" type="text/css" href="css/hpButton.css">
		<script src="js/loginValidator.js"></script>
	</head>
	<body>
		<div>
			<table style="width: 100%">
				<tbody>
					<tr>
						<td><button class="homepageButton" onclick="location.href='homepage.jsp'"><b>CASTLE MOVIE THEATER</b></button></td>
					</tr>
				</tbody>
			</table>
		</div>
		<hr>
		<div>
			<form name="form" onsubmit="return validateForm()" action="${pageContext.request.contextPath}/login" method="POST">
				<table>
					<tbody>
						<tr>														
							<td>Username:</td><td><input type="text" name="username"></td>
						</tr>
						<tr>
							<td>Password:</td><td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td><input type="submit" value="Accedi"></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</body>
</html>