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

<h1>One Time Password</h1>
<form action="../otp/changepassword" commandName="otpformbean" method="post" >

<p>	
	<input id="otp" name="otp" placeholder="please enter the One time password" type="text" value="${otpformbean.otp}"/>
</p>

<input class="btn btn-lg btn-primary" type="submit" value="Submit"/>

<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />	
</form>

</body>
</html>