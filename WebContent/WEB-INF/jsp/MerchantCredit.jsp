<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Merchant Credit Page</title>
<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	
	function validateForm()
	{

	 var creditAmount = document.getElementById("creditAmount").value;

	  if (creditAmount == "") 
	  {
	  	alert("Please fill all fields.");
	  	return false;
	  }
	  
	  if(creditAmount==0)
	  {
		  alert("Please Enter a value greater than 0");
		  return false;
		  
	  }
		
		var regex2 = new RegExp("^[0-9]+$");
		if(!regex2.test(creditAmount))
		{
				alert('Please remove special characters from Credit Amount Field');
				return false;
		}
		
	
	  
	 }</script>
	
</head>

<body>
<center><h1> SYNNOVATION </h1> </center>

<h2> CREDIT PAGE</h2>


<div id="otp-error"><font color="red">${error}</font></div>

<ul class="nav nav-tabs">
<li class="active"><a href="#">Credit Amount</a> </li>
<li><a href="MerchantDebit">Debit Amount</a> </li>
<li><a href="MerchantTransfer">Transfers</a></li>
<li><a href="ViewMerchantTransactions">View Transactions</a></li>
<li><a href="MerchantNotifications">Notifications</a></li>
<li><a href="ChangeMerchantInfo">Change information</a></li>
<li><a href="MerchantTechAccountAccess">Technical Account Access</a></li>
<li><a href="MerchantAcceptNotification">Accept Customer Notifications</a><li>
<li><a href="MerchantSubmitPayment">Submit Payment</a></li>

</ul>


<br><br>
<form  commandName="creditFormBean" method="post">
<input id="creditAmount" type="text" name="creditAmount" maxlength="4" placeholder="Enter the amount to be credited (in USD)" style="width:500px;" value="${creditFormBean.creditAmount}"/><br>
<input class="btn btn-lg btn-primary" type="submit" value="Submit" onclick="document.forms[0].action = 'merchantcreditrequest' ;return validateForm();"/>
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
		/* This code detects the tab/window close of the browser and we are performing logout function on it */
		/* window.onbeforeunload = function(){ formSubmit(); } */
	
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
<br><a href="javascript:formSubmit()"> Logout</a>




</body>
</html>