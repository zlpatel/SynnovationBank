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
<h2>Admin Page</h2>
<p>Only admins have access to this page.</p><br>

<p>${message} </p>

<ul class="nav nav-tabs">
    <li><a href="home">Notifications</a></li>
    <li><a href="admininternaluseraccounts">Internal User Accounts</a></li>
    <li class="active"><a href="#">External User Accounts</a></li>
    <li><a href="adminpiirequests">PII Requests</a></li>
    <li><a href="admincriticaltransactions">Critical Transactions</a></li>
    <li><a href="adminsystemlog">System Log</a></li>   
    <li><a href="adminchangepassword">Change Password</a></li>
</ul>

<br><br>

<form action="adminexternaluseraccounts" commandName="getexternaluseraccounts" method="post" >
<table class ="table table-striped">
        <thead>
            <tr>
                <th>Username</th>
                <th>Account Number</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Balance</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
         <c:forEach  items="${externaluserslist}" var="externalUser">
        <tr> 
          <td>${externalUser.username}</td>
          <td>${externalUser.accountNumber}</td>
          <td>${externalUser.fname}</td>
          <td>${externalUser.lname}</td>
          <td>${externalUser.accountBalance}</td>          
          <!-- <td><button type="button" class="btn btn-primary">Modify</button>
                <button type="button" class="btn btn-danger">Delete</button></td> -->
          <td align="center"><input style="width:100px;" name="Modify" type="submit" value= "View/Modify" class = "btn btn-primary" onclick="document.forms[0].method = 'post';document.forms[0].action = 'adminmodifyexternaluser?userId=${externalUser.username}'; ;return true;"/></td>  
          <td align="center"><input  style="width:75px;" name="Delete" type="submit" value= "Delete" class = "btn btn-danger"  onclick="document.forms[0].method = 'post';document.forms[0].action = 'admindeleteexternaluser?userId=${externalUser.username}'; ;return true;"/></td> 
        </tr>
      </c:forEach>
      </tbody>
      </table>
      
      <input type="hidden" 
		name="${_csrf.parameterName}"
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
<br>
<a class="btn btn-default" href="adminaddexternaluser">Add New External User</a>
<a class="btn btn-deafult" href="javascript:formSubmit()"> Logout</a>

</body>
</html>