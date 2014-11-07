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
<h2> PAY MERCHANT </h2>
<div id="otp-error"><font color="red">${error}</font></div>
<ul class="nav nav-tabs">
<li><a href="credit_debit">Credit Amount</a> </li>
<li><a href="debit">Debit Amount</a> </li>
<li><a href="transfer">Transfers</a></li>
<li><a href="viewTransactions">View Transactions</a></li>
<li><a href="customerNotifications">Notifications</a></li>
<li><a href="changeCustomerInfo">Change information</a></li>
<li><a href="techAccountAccess">Technical Account Access</a></li>
<li class="active"><a href="#">Pay Merchant</a></li>
</ul>
<br>
<form action="merchanttransferrequest" commandName="merchanttransferFormBean" method="get">
<input id="receiverID" type="text" name="receiverID" placeholder="Enter the Merchant ID to whom you want to pay" style="width:800px;" value="${transferFormBean.receiverID}"/><br>
<input id="transferAmount" type="text" name="transferAmount" placeholder="Enter the amount which you want to pay" style="width:800px;" value="${transferFormBean.transferAmount}"/><br>

<input class="btn btn-lg btn-primary" type="submit" value="Submit"/>
<a class="btn btn-deafult" href="javascript:formSubmit()">Logout</a>
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




</body>
</html>