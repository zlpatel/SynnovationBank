<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Page</title>
<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<center><h1>SYNNOVATION</h1></center>
<center><h3>PII REQUESTS PAGE</h3></center>

<ul class="nav nav-tabs">
<!--     <li><a href="home">Notifications</a></li>
 -->    <li><a href="admininternaluseraccounts">Internal User Accounts</a></li>
    <li><a href="adminexternaluseraccounts">External User Accounts</a></li>
    <li class="active"><a href="#">PII Requests</a></li>
    <li><a href="admincriticaltransactions">Critical Transactions</a></li>
    
</ul>
<br>
<form action="adminpiirequests" method="post">
<table class ="table table-striped">
        <thead>
            <tr>
                <th>Username</th>
                <th>Account Number</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Date of Birth</th>
                <th>SSN</th>
            </tr>
        </thead>
        <tbody>
         <c:forEach  items="${piirequestslist}" var="personandpii">
        <tr> 
          <td>${personandpii.userName}</td>
          <td>${personandpii.accountNumber}</td>
          <td>${personandpii.firstName}</td>
          <td>${personandpii.lastName}</td>
          <td>${personandpii.dateOfBirth}</td>
          <td>${personandpii.ssn}</td>                              
        </tr>
      </c:forEach>
      </tbody>
      </table>
      
      <input type="hidden" 
		name="${_csrf.parameterName}"
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
<br> <!-- <a href="javascript:formSubmit()"> Logout</a> -->

</body>
</html>