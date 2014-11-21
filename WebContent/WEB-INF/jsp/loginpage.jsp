<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- <script type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</script> -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
</head>

<script type="text/javascript">

function validateForm()
	{

	 var username = document.getElementById("j_username").value;
	 var password = document.getElementById("j_password").value;
	 
	  if (username === "" || password === "") 
	  {
	  	alert("Please enter username and password.");
	  	return false;
	  }
		
		 var regex3 = new RegExp("^[a-zA-Z]+$");	  
		  
		 if(!regex3.test(username))
		{
			alert('Please insert only alphabets for username');
			return false; 
		}
	  	    
	  
	 }</script>

<body>

<h1>Login</h1>

<div id="login-error"><font color="red">${error}</font></div>

<%-- <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
      <font color="red">
        Your login attempt was not successful due to <br/><br/>
        <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
      </font>
    </c:if> --%>

<form method="post" >

<p>	
	<input id="j_username" name="j_username" placeholder="username" type="text" style="text-transform: lowercase;"/>
</p>

<p>
	<input id="j_password" name="j_password" placeholder="password" type="password" />
</p>

<input class="btn btn-lg btn-primary" type="submit" value="Login" onclick="document.forms[0].action = '../../j_spring_security_check' ;return validateForm();"/>


<a class="btn btn-default" href="../otp/forgotpassword">Change/ Forgot Password</a>


<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />	
</form>

</body>
</html>

