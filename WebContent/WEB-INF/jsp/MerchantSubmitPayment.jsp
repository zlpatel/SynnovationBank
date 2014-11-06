<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Merchant Accept Notifications Page</title>
<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<h2>Merchant Page</h2>
<p>Only merchants have access to this page.</p><br>

<ul class="nav nav-tabs">
    <li><a href="../merchant/MerchantCredit">Credit Amount</a> </li>
<li><a href="../merchant/MerchantDebit">Debit Amount</a> </li>
<li><a href="../merchant/MerchantTransfer">Transfers</a></li>
<li><a href="../merchant/ViewMerchantTransactions">View Transactions</a></li>
<li><a href="../merchant/MerchantNotifications">Notifications</a></li>
<li><a href="../merchant/ChangeMerchantInfo">Change information</a></li>
<li><a href="../merchant/MerchantTechAccountAccess">Technical Account Access</a></li>
<li><a href="../merchant/MerchantAcceptNotification">Accept Customer Notifications</a><li>
<li class="active"><a href="#">Submit Payment</a></li>
</ul>

<br><br>



<div id="certificate-error"><font color="red">${error}</font></div>

<form:form action="submitpayment" modelAttribute="merchantPaymentFormBean" method="post" enctype="multipart/form-data">
		<h4>Notification ID <span class="label label-default">${merchantPaymentFormBean.notification_id}</span></h4>
        <h4>Amount<span class="label label-default">${merchantPaymentFormBean.amount}</span></h4>
		File to upload: <input type="file" name="file" class="btn btn-primary"><br /> 
		<input class = "btn btn-primary" type="submit" value="Upload"> Press here to upload the file!
		
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />	
			
				
    </form:form>
<input class="btn btn-lg btn-primary" type="submit" value="Submit"/>

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
<br> <a href="javascript:formSubmit()"> Logout</a>

!-- Latest compiled and minified JavaScript -->

<!-- <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> -->
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>



</body>
</html>