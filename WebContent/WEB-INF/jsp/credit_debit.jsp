<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Credit Page</title>
<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</head>

<body>
<center><h1> SYNNOVATION </h1> </center>

<h2> Credit Page !!</h2>
<a href="credit_debit">Credit/Debit</a> <br>
<a href="transfer">Transfers</a><br>
<a href="viewTransactions">View Transactions</a><br>
<a href="customerNotifications">Notifications</a><br>
<a href="changeCustomerInfo">Change information</a><br>
<a href="techAccountAccess">Technical Account Access</a><br>

<br><br>
<form action="creditrequest" commandName="creditFormBean" method="get">
<input id="creditAmount" type="text" name="amount" placeholder="Enter the amount (in USD)" value="${creditFormBean.creditAmount}"/><br>
<input class="btn btn-lg btn-primary" type="submit" value="Submit"/>

</form>
</body>
</html>