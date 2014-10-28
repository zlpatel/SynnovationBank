<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Synnovation Bank</title>
<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
</head>
<body>

<h1>Change Password</h1>
<form action="../otp/changepasswordsuccessful" commandName="changepasswordformbean" method="post" >

<p>	
	<input id="username" name="username" placeholder="please enter you username" type="text" value="${changepasswordformbean.username}"/>
</p>
<p>
	<input id="newpassword" name="newpassword" placeholder="please enter your new password" type="text" value="${changepasswordformbean.newpassword}"/>
</p>

<input class="btn btn-lg btn-primary" type="submit" value="Submit"/>

<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />	
</form>

</body>
</html>