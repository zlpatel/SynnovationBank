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
    <li><a href="employeechangepassword"> Change Password</a></li>
    <li class="active"><a href="#">View Customer Transactions</a></li>
</ul>
<p align="center">View Customer Transactions</p>

    <form name ="user_transactions" method = "post">
		<table  width="500" border="0" bordercolor="black" class ="table table-striped">
		<tr>
		  <td><b>Transaction Id</b></td>
		  <td></td>
		  <td><b>Balance</b></td>
          <td></td>
          <td><b>Transaction Date</b></td>
          <td></td>
          <td><b>Transaction Name</b></td>
          
          
         </tr>
      <c:forEach  items="${userTransaction}" var="trans">
        <tr> 
          <td>${trans.transactionId}</td>
          <td></td>
          <td>${trans.balance}</td>
          <td></td>
          <td>${trans.transactionDate}</td>
          <td></td>
          <td>${trans.transactionsName}</td>
         </tr>
      </c:forEach>
      </table>
      <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />	
  <input class="btn btn-lg btn-primary" type="submit" value="Back" onclick="document.forms[0].action = 'employeeviewtransactions' ;"/>    
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


