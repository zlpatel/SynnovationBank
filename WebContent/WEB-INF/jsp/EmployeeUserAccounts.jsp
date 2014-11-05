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
<h2>Employee Page</h2>


<ul class="nav nav-tabs">
    <li><a href="home">Notifications</a></li>
    <li class="active"><a href="#">User Accounts</a></li>
    <li><a href="employeechangepassword">Change Password</a></li>
     <li><a href="employeeviewtransactions">View Customer Transactions</a></li>
</ul>
<p align="center">User Accounts Page</p>
<form action ="employeeuseraccounts" method = "post" >
		<table id = "transaction" width="500" border="0" bordercolor="black" class ="table table-striped">
		<tr>
		  <td><b>First Name</b></td>
		  <td></td>
		  <td><b>Last Name</b></td>
		  <td></td>
		  <td><b>User Name</b></td>
		  <td></td>
          <td><b>Account Number</b></td>
          <td></td>
          <td><b>Balance</b></td>
          
         
         </tr>
      <c:forEach  items="${empUserAcc}" var="empUser">
        <tr> 
          <td>${empUser.firstName}</td>
          <td></td>
          <td>${empUser.lastName}</td>
          <td></td>
          <td>${empUser.userId}</td>
          <td></td>
          <td>${empUser.getAccountNumber}</td>
          <td></td>
          <td>${empUser.getBalance}</td>
          
          
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
	<br><p><a href="javascript:formSubmit()"> Logout</a></p>

</body>
</html>