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
<h2> NOTIFICATIONS PAGE</h2>
<ul class="nav nav-tabs">
    <li class="active"><a href="#">Notifications</a></li>
    <li><a href="employeeuseraccounts">User Accounts</a></li>
    <li><a href="employeeviewmerchanttransactions">Merchant Requests</a></li>
    <li><a href="employeeviewtransactions">View Customer Transactions</a></li>
</ul>
<br>
<form name ="employee_notifications" method = "post" commandName="empNotifFormBean">
		<table id = "transaction" width="500" border="0" bordercolor="black" class ="table table-striped">
		<thead>
		<tr>
			
			
		  	<th>First Name</th>
		  	<th></th>
		  	<th>Last Name</th>
		  	<th></th>
		  	<th>User Name</th>
		  	<th></th>
          	<th>Notification</th>
         </tr>
        </thead>
        <tbody> 
      <c:forEach  items="${empNotifFormBean}" var="empNotif">
        <tr> 
          <td>${empNotif.firstName}</td>
          <td></td>
          <td>${empNotif.lastName}</td>
          <td></td>
          <td>${empNotif.userName}</td>
          <td></td>
          <td>${empNotif.notifications}</td>
          
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