<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<noscript>
  <META HTTP-EQUIV="Refresh" CONTENT="0;URL=../admin/enablejavascript">
</noscript>
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
<center><h3>INTERNAL USER ACCOUNTS PAGE</h3></center>

<p>${message} </p>
<ul class="nav nav-tabs">
    <!-- <li><a href="home">Notifications</a></li> -->
    <li class="active"><a href="#">Internal User Accounts</a></li>
    <li><a href="adminexternaluseraccounts">External User Accounts</a></li>
    <li><a href="adminpiirequests">PII Requests</a></li>
    <li><a href="admincriticaltransactions">Critical Transactions</a></li>
     
   
</ul>
<br>
<form action="admininternaluseraccounts" commandName="getinternaluseraccounts" method="post" >
<table class ="table table-striped">
        <thead>
            <tr>
                <th>Username</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th></th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
         <c:forEach  items="${internaluserslist}" var="internalUser">
        <tr> 
          <td>${internalUser.username}</td>
          <td>${internalUser.fname}</td>
          <td>${internalUser.lname}</td>
          <!-- <td><button type="button" class="btn btn-primary">Modify</button>
                <button type="button" class="btn btn-danger">Delete</button></td> -->
          <td align="center"><input style="width:100px;" name="Modify" type="submit" value= "View/Modify" class = "btn btn-primary" onclick="document.forms[0].method = 'post';document.forms[0].action = 'adminmodifyinternaluser?userId=${internalUser.username}'; ;return true;"/></td>  
          <td align="center"><input  style="width:75px;" name="Delete" type="submit" value= "Delete" class = "btn btn-danger"  onclick="document.forms[0].method = 'post';document.forms[0].action = 'admindeleteinternaluser?userId=${internalUser.username}'; ;return true;"/></td> 
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
<a class="btn btn-default" href="adminaddinternaluser">Add New Internal User</a>
<a class="btn btn-default" href="javascript:formSubmit()">Logout</a>
</body>
</html>