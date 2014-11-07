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
</head>

<body>
<center><h1> SYNNOVATION </h1> </center>
<h2> NOTIFICATIONS PAGE</h2>
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
<form name ="customer_notifications" method = "post" commandName="custNotifFormBean">
		<table id = "transaction" width="500" border="0" bordercolor="black" class ="table table-striped">
		<tr>
		  <!--  td><b>First Name</b></td>
		  <td><b>Last Name</b></td>
		  <td><b>User Name</b-->
          <td><b>Notification from employee</b></td>
          
         
         </tr>
      <c:forEach  items="${custNotifFormBean}" var="custNotif">
        <tr> 
          <!--  td>${empNotif.firstName}</td>
          <td>${empNotif.lastName}</td>
          <td>${empNotif.userName}</td-->
          <td>${custNotif.notifications}</td>
          
        </tr>
      </c:forEach>
      </table>
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
