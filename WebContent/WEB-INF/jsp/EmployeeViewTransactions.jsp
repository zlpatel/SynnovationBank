<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee Page</title>
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
	 

	  if (username === "" ) 
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
<h2>Employee Page</h2>


<ul class="nav nav-tabs">
    <li><a href="home">Notifications</a></li>
    <li><a href="employeeuseraccounts">User Accounts</a></li>
     <li><a href="employeeviewmerchanttransactions">Merchant Requests</a></li>
    <li class="active"><a href="#">View Customer Transactions</a></li>
</ul>
<p align="center">View Customer Transactions</p>
<div id="error"><font color="red">${error}</font></div>
<form action ="viewtransactions" method="post" commandName="usertransactionformbean">


<input name="userName"  id="username" class = "form-control" style="width:200px;" placeholder = "User Name" type="text" maxlength="15" value="${usertransactionformbean.userName}">
<input class="btn btn-lg btn-primary" type="submit" value="Submit" onclick="document.forms[0].action = 'viewtransactions' ;return validateForm();"/>
<!-- <input class="btn btn-lg btn-primary" type="submit" value="Submit"/>-->

<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />	
			
			
</form>
<c:url value="/j_spring_security_logout" var="logoutUrl" />
 
	<!-- csrf for log out-->
	<form action="${logoutUrl}" method="post" id="logoutForm">
	  <input type="hidden" 
		name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	</form>
	
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
	<br><p><a href="javascript:formSubmit()"> Logout</a></p>
</body>
</html>