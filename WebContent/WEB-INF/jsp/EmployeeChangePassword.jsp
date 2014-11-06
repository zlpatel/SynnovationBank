<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee Page</title>
<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<center><h1> SYNNOVATION </h1> </center>
<h2> MERCHANT REQUESTS PAGE</h2>
<ul class="nav nav-tabs">
    <li><a href="../employee/home">Notifications</a></li>
    <li><a href="../employee/employeeuseraccounts">User Accounts</a></li>
    <li class="active"><a href="#">Merchant Requests</a></li>
    <li><a href="../employee/employeeviewtransactions">View Customer Transactions</a></li>
</ul>
<br>
<form method="post" >
<table class="table" class ="table table-striped">
<thead>
            <tr>
                <th>User Name</th>
                <th></th>
                <th>Account Number</th>
                <th></th>
                <th>Notification</th>
                <th></th>
                <th>Amount</th>
                <th></th>                                                
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach  items="${adminCriticalNotifFormBean}" var="notification">
        <tr> 
          <td>${notification.userName}</td>
          <td></td>
          <td>${notification.accountNumber}</td>
          <td></td>
          <td>${notification.notifications}</td>
          <td></td>
          <td>${notification.transactionAmount}</td>
          
           <td align="center"><input style="width:75px;" name="Modify" type="submit" value= "Accept" class = "btn btn-primary" onclick="document.forms[0].method = 'post';document.forms[0].action = 'employeetransactionaccepted/${notification.userName}/${notification.transactionId}/${notification.notificationId}'; ;return true;"/></td>  
          <td align="center"><input  style="width:75px;" name="Delete" type="submit" value= "Decline" class = "btn btn-danger"  onclick="document.forms[0].method = 'post';document.forms[0].action = 'employeetransactiondeclined/${notification.userName}/${notification.transactionId}/${notification.notificationId}'; ;return true;"/></td> 
          
        </tr>
      </c:forEach>
        </tbody>
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
	<br><p><a href="javascript:formSubmit()"> Logout</a></p>

</body>
</html>