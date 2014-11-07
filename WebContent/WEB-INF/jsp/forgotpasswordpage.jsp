<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<noscript>
  <META HTTP-EQUIV="Refresh" CONTENT="0;URL=../otp/enablejavascript">
</noscript>
<title>Synnovation Bank</title>
<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
	function validateForm()
	{

	 var username = document.getElementById("username").value;		
	 var email = document.getElementById("email").value;
	 
	 
	  if (email === "" || username === "") 
	  {
	  	alert("Please fill all fields.");
	  	return false;
	  }
	  
		var regex3 = new RegExp("^[a-zA-Z0-9]+$");
		 if(!regex3.test(username))
		{
			alert('Please remove special characters and spaces from Username');
			return false; 
		}
	  	    
	    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	    if(!re.test(email))
	    {
	    	alert("Please enter a valid e-mail address.");
	    	return false;
	    }
	  
	 }
	 </script>
	
</head>
<body>

<div id="validation-error"><font color="red">${error}</font></div>

<h1>Forgot Password</h1>
<form action="../otp/otprequest" commandName="forgotpasswordformbean" method="post" >

<p>	
	<input id="username" name="username" placeholder="username" type="text" value="${forgotpasswordformbean.username}"/>
</p>
<p>
	<input id="email" name="email" placeholder="email" type="text" value="${forgotpasswordformbean.email}"/>
</p>

<input class="btn btn-lg btn-primary" type="submit" value="Submit" onclick="return validateForm();"/>

<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />	
</form>

</body>
</html>