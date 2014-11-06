<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
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
	 var newpassword = document.getElementById("newpassword").value;
	 var retypepassword = document.getElementById("retypepassword").value;

	  if (username === "" || newpassword === "" || retypepassword === "") 
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
	  	    
	
	  
	 }</script>
</head>
<body>
<div id="password-error"><font color="red">${error}</font></div>
<h1>Change Password</h1>
<form action="../otp/changepasswordsuccessful" commandName="changepasswordformbean" method="post" >

<p>	
	<input id="username" name="username" placeholder="username" type="text" value="${changepasswordformbean.username}"/>
</p>
<p>
	<input id="newpassword" name="newpassword" placeholder="new password" type="password" value="${changepasswordformbean.newpassword}"/>
</p>
<p>
	<input id="retypepassword" name="retypepassword" placeholder="retype password" type="password" value="${changepasswordformbean.retypepassword}"/>
</p>

<input class="btn btn-lg btn-primary" type="submit" value="Submit" onclick="return validateForm();"/>

<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />	
</form>

</body>
</html>