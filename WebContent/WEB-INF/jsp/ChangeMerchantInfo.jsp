<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Merchant Page</title>
<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
	<script>
	function validateForm()
	{

	 var fname = document.getElementById("fname").value;
	 var lname = document.getElementById("lname").value;
	 var address = document.getElementById("address").value;
	 var email = document.getElementById("email").value;
     
	  if (fname === "" || lname === "" || address === "" || email === "") 
	  {
	  	alert("Please fill all fields.");
	  	return false;
	  }
	
	  var regex1 = new RegExp("^[a-zA-Z]+$");	  
	  if(!regex1.test(fname) || !regex1.test(lname))  
		{
			alert('Please enter alphabetic characters only for First Name and Last Name.');
			return false;
		}
		
		var regex2 = new RegExp("^[a-zA-Z0-9_ ]+$");
		if(!regex2.test(address))
		{
				alert('Please remove special characters from Address');
				return false;
		}
		
	  	    
	    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	    if(!re.test(email))
	    {
	    	alert("Please enter a valid e-mail address.");
	    	return false;
	    }
	  
	 }</script>
	
</head>








<body>
<center><h1> SYNNOVATION </h1> </center>
<h2>UPDATE INFORMATION PAGE</h2>
<br>
<ul class="nav nav-tabs">
<li><a href="MerchantCredit">Credit Amount</a> </li>
<li><a href="MerchantDebit">Debit Amount</a> </li>
<li><a href="MerchantTransfer">Transfers</a></li>
<li><a href="ViewMerchantTransactions">View Transactions</a></li>
<li><a href="MerchantNotifications">Notifications</a></li>
<li class="active"><a href="#">Change information</a></li>
<!-- <li><a href="MerchantTechAccountAccess">Technical Account Access</a></li> -->
<li><a href="MerchantAcceptNotification">Accept Customer Notifications</a><li>
<li><a href="MerchantSubmitPayment">Submit Payment</a></li>
</ul>
<br>
<form commandName="customerInfoChangeFormBean" method="post">
<input id="fname" type="text" name="firstName" maxlength="15" placeholder="Enter the first name to change" style="width:500px;" value="${customerInfoChangeFormBean.firstName}"/>
<br>
<input id="lname" type="text" name="lastName" maxlength="15" placeholder="Enter the last name to change" style="width:500px;" value="${customerInfoChangeFormBean.lastName}"/>
<br>
<input id="address" type="text" name="address" maxlength="50" placeholder="Enter the address to change" style="width:900px;" value="${customerInfoChangeFormBean.address}"/>
<br>
<input id="email" type="text" name="email" maxlength="15" placeholder="Enter the E-mail ID to change" style="width:500px;" value="${customerInfoChangeFormBean.email}"/>
<br>
<input class="btn btn-lg btn-primary" type="submit" value="Submit" onclick="document.forms[0].action = 'changemerchantinforequest' ;return validateForm();"/>
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