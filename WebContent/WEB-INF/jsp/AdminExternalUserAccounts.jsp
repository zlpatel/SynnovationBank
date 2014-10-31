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

<ul class="nav nav-tabs">
    <li><a href="home">Notifications</a></li>
    <li><a href="admininternaluseraccounts">Internal User Accounts</a></li>
    <li class="active"><a href="#">External User Accounts</a></li>
    <li><a href="adminpiirequests">PII Requests</a></li>
    <li><a href="admincriticaltransactions">Critical Transactions</a></li>
    <li><a href="adminsystemlog">System Log</a></li>   
    <li><a href="adminchangepassword">Change Password</a></li>
</ul>

<br><br><table class="table">
        <thead>
            <tr>
                <th>Account No.</th>            
                <th>First Name</th>
                <th>Last Name</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <tr>
            	<td>928401843</td>
                <td>Tim</td>
                <td>Johnson</td>
                <td>
                <button type="button" class="btn btn-primary">Modify</button>
                <button type="button" class="btn btn-danger">Delete</button>
                </td>
            </tr>
            <tr>
            	<td>539049175</td>            
                <td>Mark</td>
                <td>Brenton</td>
                <td>
                <button type="button" class="btn btn-primary">Modify</button>
                <button type="button" class="btn btn-danger">Delete</button>
                </td>
            </tr>
            <tr>
            	<td>602860272</td>
                <td>Jack</td>
                <td>Spensor</td>
                <td>
                <button type="button" class="btn btn-primary">Modify</button>
                <button type="button" class="btn btn-danger">Delete</button>
                </td>
            </tr>
        </tbody>
    </table>

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