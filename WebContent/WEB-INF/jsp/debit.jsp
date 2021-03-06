<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<noscript>
  <META HTTP-EQUIV="Refresh" CONTENT="0;URL=../customer/enablejavascript">
</noscript>
<title>Debit Page</title>
<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	
	function validateForm()
	{

	 var debitAmount = document.getElementById("debitAmount").value;

	  if (debitAmount == "") 
	  {
	  	alert("Please fill all fields.");
	  	return false;
	  }
	  
	  if(debitAmount==0)
	  {
		  alert("Please Enter a value greater than 0");
		  return false;
		  
	  }
		
		var regex2 = new RegExp("^[0-9]+$");
		if(!regex2.test(debitAmount))
		{
				alert('Please remove special characters from Credit Amount Field');
				return false;
		}
		
	
	  
	 }</script>
</head>

<body>
<center><h1> SYNNOVATION </h1> </center>
<center><h3>DEBIT PAGE</h3></center>>

<div id="otp-error"><font color="red">${error}</font></div>
<ul class="nav nav-tabs">
<li><a href="credit_debit">Credit Amount</a> </li>
<li class="active"><a href="#">Debit Amount</a> </li>
<li><a href="transfer">Transfers</a></li>
<li><a href="viewTransactions">View Transactions</a></li>
<li><a href="customerNotifications">Notifications</a></li>
<li><a href="changeCustomerInfo">Change information</a></li>
<li><a href="techAccountAccess">Technical Account Access</a></li>
<li><a href="payMerchant">Pay Merchant</a></li>
<li><a href="criticalTransfer">Critical Transfers (greater than $500)</a></li>
</ul>
<br>
<form  commandName="debitFormBean" method="post">
<input id="debitAmount" type="text" name="debitAmount" maxlength= "4" class = "form-control" placeholder="Enter the amount to be debitted(in USD)" style="width:500px;" value="${debitFormBean.debitAmount}"/>
<br>
<input class="btn btn-lg btn-primary" type="submit" value="Submit" onclick="document.forms[0].action = 'debitrequest' ;return validateForm();"/>
<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />	
<a class="btn btn-default" href="javascript:formSubmit()">Logout</a>
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
