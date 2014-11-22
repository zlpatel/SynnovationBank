<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<noscript>
  <META HTTP-EQUIV="Refresh" CONTENT="0;URL=../customer/enablejavascript">
</noscript>
<title>Customer Page</title>
<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	
	function validateForm()
	{

	 var fname = document.getElementById("firstName").value;
	 var lname = document.getElementById("lastName").value;
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
<h2> UPDATE INFORMATION PAGE</h2>
<div id="otp-error"><font color="red">${error}</font></div>
<ul class="nav nav-tabs">
<li><a href="credit_debit">Credit Amount</a> </li>
<li><a href="debit">Debit Amount</a> </li>
<li><a href="transfer">Transfers</a></li>
<li><a href="viewTransactions">View Transactions</a></li>
<li><a href="customerNotifications">Notifications</a></li>
<li><a href="changeCustomerInfo">Change information</a></li>
<li><a href="techAccountAccess">Technical Account Access</a></li>
<li><a href="payMerchant">Pay Merchant</a></li>
<li><a href="criticalTransfer">Critical Transfers (greater than $500)</a></li>
</ul>
<br>
<form commandName="customerInfoChangeFormBean" method="post">
<input id="firstName" type="text" name="firstName" class="form-control" maxlength= "15" placeholder="First Name" style="width:200px;" value="${customerInfoChangeFormBean.firstName}"/>
<br>
<input id="lastName" type="text" name="lastName" class="form-control" maxlength= "15" placeholder="Last Name" style="width:200px;" value="${customerInfoChangeFormBean.lastName}"/>
<br>
<input id="address" type="text" name="address" class="form-control" maxlength= "50" placeholder="Address" style="width:200px;" value="${customerInfoChangeFormBean.address}"/>
<br>
<input id="email" type="text" name="email" class="form-control" placeholder="Email Id" maxlength= "30" style="width:200px;" value="${customerInfoChangeFormBean.email}"/>
<br>
<input class="btn btn-lg btn-primary" type="submit" value="Submit" onclick="document.forms[0].action = 'changecustomerinforequest' ;return validateForm();"/>
<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />	
<a class="btn btn-default" href="javascript:formSubmit()">Logout</a>
</form>
<c:url value="/j_spring_security_logout" var="logoutUrl" />
 
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
