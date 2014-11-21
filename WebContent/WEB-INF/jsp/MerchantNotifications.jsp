<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<noscript>
  <META HTTP-EQUIV="Refresh" CONTENT="0;URL=../merchant/enablejavascript">
</noscript>
<title>Merchant Notifictions Page</title>
<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</head>




<body>
<center><h1> SYNNOVATION </h1> </center>
<h2>NOTIFICATIONS</h2>
<ul class="nav nav-tabs">
<li><a href="MerchantCredit">Credit Amount</a> </li>
<li><a href="MerchantDebit">Debit Amount</a> </li>
<li><a href="MerchantTransfer">Transfers</a></li>
<li><a href="ViewMerchantTransactions">View Transactions</a></li>
<li class="active"><a href="#">Notifications</a></li>
<li><a href="ChangeMerchantInfo">Change information</a></li>
<li><a href="MerchantTechAccountAccess">Technical Account Access</a></li>
<li><a href="MerchantAcceptNotification">Accept Customer Notifications</a><li>
<!-- <li><a href="MerchantSubmitPayment">Submit Payment</a></li> -->
</ul>

<p align="center">Merchant Notifications</p>
<form name ="merchant_notifications" method = "post" commandName="custNotifFormBean">
		<table id = "transaction" width="500" border="0" bordercolor="black" class ="table table-striped">
		<tr>
		  <!--  td><b>First Name</b></td>
		  <td><b>Last Name</b></td>
		  <td><b>User Name</b-->
          <td><b>Notification from employee</b></td>
         </tr>
      <c:forEach  items="${merchantNotifFormBean}" var="merchantNotif">
        <tr> 
          <!--  td>${empNotif.firstName}</td>
          <td>${empNotif.lastName}</td>
          <td>${empNotif.userName}</td-->
          <td>${merchantNotif.notifications}</td>
          
        </tr>
      </c:forEach>
      </table>
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
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

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