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

	 var receiverID = document.getElementById("receiverID").value;
	 var transferAmount = document.getElementById("transferAmount").value;
	

	  if (receiverID === "")
	  {
	  	alert("Please fill userid.");
	  	return false;
	  }
	  var regex1 = new RegExp("^[a-zA-Z]+$");	  
	  if(!regex1.test(receiverID))  
		{
			alert('Only Alphabetic characters are allowed for userid.');
			return false;
		}
	

		  if (transferAmount == "") 
		  {
		  	alert("Please fill all fields.");
		  	return false;
		  }
		  
		  if(transferAmount==0)
		  {
			  alert("Please Enter a value greater than 0");
			  return false;
			  
		  }
			
			var regex2 = new RegExp("^[0-9]+$");
			if(!regex2.test(transferAmount))
			{
					alert('Please remove special characters from Credit Amount Field');
					return false;
			}
			
	    
	  
	 }</script>
</head>




<body>
<center><h1> SYNNOVATION </h1> </center>
<h2> TRANSFER AMOUNT PAGE</h2>
<div id="otp-error"><font color="red">${error}</font></div>
<ul class="nav nav-tabs">
<li><a href="credit_debit">Credit Amount</a> </li>
<li><a href="debit">Debit Amount</a> </li>
<li class="active"><a href="#">Transfers</a></li>
<li><a href="viewTransactions">View Transactions</a></li>
<li><a href="customerNotifications">Notifications</a></li>
<li><a href="changeCustomerInfo">Change information</a></li>
<li><a href="techAccountAccess">Technical Account Access</a></li>
<li><a href="payMerchant">Pay Merchant</a></li>
</ul>
<br>
<form  commandName="transferFormBean" method="post">
<input id="receiverID" type="text" name="receiverID" maxlength= "15" class = "form-control" placeholder="Enter the User ID to which you want to tranfer the amount" style="width:800px;" value="${transferFormBean.receiverID}"/>
<br>
<input id="transferAmount" type="text" name="transferAmount" maxlength= "4" class = "form-control" placeholder="Enter the amount which you want to tranfer" style="width:800px;" value="${transferFormBean.transferAmount}"/><br>
<br>



<input class="btn btn-lg btn-primary" type="submit" value="Submit" onclick="document.forms[0].action = 'transferrequest1' ;return validateForm();"/>
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
