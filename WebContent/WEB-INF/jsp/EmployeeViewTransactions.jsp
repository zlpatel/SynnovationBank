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
</head>
<body>
<h2>Employee Page</h2>
<p>Only employees have access to this page.</p><br>

<ul class="nav nav-tabs">
    <li><a href="employee">Notifications</a></li>
    <li><a href="employeeuseraccounts">User Accounts</a></li>
    <li class="active"><a href="#">Change Password</a></li>
    <li><a href="employeeviewtransactions">View Customer Transactions</a></li>
</ul>

<form name ="addexternaluser" method="post" commandName="usertransactionFormBean">


<input name="accNum"  class = "form-control" style="width:200px;" placeholder = "Account Number" maxlength="15">
</form>
<input name="AddExtuser" type="submit" value="Add" style="position:absolute;width:200px;left:450px;top:955px;z-index:4;" class = "btn btn-primary" onclick="document.forms[0].action = '/viewTransaction';"/>

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