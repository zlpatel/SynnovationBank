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

<h2> View your transactions here !!</h2>
<ul class="nav nav-tabs">
<li><a href="credit_debit">Credit Amount</a> </li>
<li><a href="debit">Debit Amount</a> </li>
<li><a href="transfer">Transfers</a></li>
<li><a href="viewTransactions">View Transactions</a></li>
<li><a href="customerNotifications">Notifications</a></li>
<li><a href="changeCustomerInfo">Change information</a></li>
<li><a href="techAccountAccess">Technical Account Access</a></li>
</ul>

<h4>Available balance in account is: ${balance}</h4>

<div id="disptran"><font color="red">${error}</font></div>
<form name ="cust_transactions" method = "post">
		<table id = "transaction" width="500" border="0" bordercolor="black" class ="table table-striped">
		<tr>
		  <td><b>Transaction Id</b></td>
		  <td><b>Transaction Amount</b></td>
          <td><b> Transaction Date</b></td>
          <td><b> Transaction Name</b></td>
          
          
         </tr>
      <c:forEach  items="${custAcc}" var="trans">
        <tr> 
          <td>${trans.transactionId}</td>
          <td>${trans.balance}</td>
          <td>${trans.transactionDate}</td>
          <td>${trans.transactionsName}</td>
         </tr>
      </c:forEach>
      </table>
      </form>






<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />	
			
			
</form>


<br><br>



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