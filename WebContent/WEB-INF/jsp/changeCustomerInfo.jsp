<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Customer Page</title>
<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</head>








<body>
<center><h1> SYNNOVATION </h1> </center>

<h2> Change/update customer information !!</h2>
<ul class="nav nav-tabs">
<li><a href="credit_debit">Credit Amount</a> </li>
<li><a href="debit">Debit Amount</a> </li>
<li><a href="transfer">Transfers</a></li>
<li><a href="viewTransactions">View Transactions</a></li>
<li><a href="customerNotifications">Notifications</a></li>
<li><a href="changeCustomerInfo">Change information</a></li>
<li><a href="techAccountAccess">Technical Account Access</a></li>
</ul>

<br><br>

<form action="changecustomerinforequest" commandName="customerInfoChangeFormBean" method="get">
<input id="firstName" type="text" name="firstName" placeholder="Enter the first name to change" style="width:500px;" value="${customerInfoChangeFormBean.firstName}"/><br>
<input id="lastName" type="text" name="lastName" placeholder="Enter the last name to change" style="width:500px;" value="${customerInfoChangeFormBean.lastName}"/><br>
<input id="email" type="text" name="email" placeholder="Enter the E-mail ID to change" style="width:500px;" value="${customerInfoChangeFormBean.email}"/><br>
<input class="btn btn-lg btn-primary" type="submit" value="Submit"/>

</form>







<form name="input" action="welcomeUser" method="get">
First name: <input type="text" name="firstname"><br>
Last name: <input type="text" name="lastname"> <br>
E mail ID: <input type="text" name="email"> <br>
<a href="changePassword">Click here to change your password (using OTP)</a><br>

<input type="submit" value="Submit">
</form>
</body>
</html>