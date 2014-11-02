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
    <li class="active"><a href="#">Internal User Accounts</a></li>
    <li><a href="adminexternaluseraccounts">External User Accounts</a></li>
    <li><a href="adminpiirequests">PII Requests</a></li>
    <li><a href="admincriticaltransactions">Critical Transactions</a></li>
    <li><a href="adminsystemlog">System Log</a></li>   
    <li><a href="adminchangepassword">Change Password</a></li>
</ul>

<br>
<h3>Modify User Account Details</h3>
<br>

<form action="adminmodifiedinternaluseraccounts" commandName="modifyinternaluserformbean" method="post" >

<input name="fname"  class = "form-control" style="width:200px;" placeholder = "First Name" maxlength="15" value="${modifyinternaluserformbean.fname}"/>
<br>
<input name="lname"  type="text" class = "form-control" style="width:200px;" placeholder = "Last Name" maxlength="14" value="${modifyinternaluserformbean.lname}"/>
<br>
<input name="address"  type="text" class = "form-control" style="width:200px;" placeholder = "Address" maxlength="50" value="${modifyinternaluserformbean.address}"/>
<br>
<%-- <input name="city"  type="text" class = "form-control" style="width:200px;" placeholder = "City" maxlength="15" value="${addexternaluserformbean.city}"/>
<br><input name="state"  type="text" class = "form-control" style="width:200px;" placeholder = "State" maxlength="15" value="${addexternaluserformbean.state}"/>
<br>
<input name="zipcode"  type="text" class = "form-control" style="width:200px;" placeholder = "Zip Code" maxlength="5" value="${addexternaluserformbean.zipcode}"/>
<br>
 <input name="phone"  type="text" class = "form-control" style="width:200px;" placeholder = "Phone" maxlength="10" value="${addexternaluserformbean.phone}"/>
<br><input name="cell"  type="text" class = "form-control" style="width:200px;" placeholder = "Cell" maxlength="10" value="${addexternaluserformbean.cell}"/>
<br>
--%><input name="email"  type="text" class = "form-control" style="width:200px;" placeholder = "Email" maxlength="30" value="${modifyinternaluserformbean.email}"/>
<br><input name="username"  class = "form-control" style="width:200px;" placeholder = "Username" maxlength="15" value="${modifyinternaluserformbean.username}" readonly/>
<br>
<input class="btn btn-lg btn-primary" type="submit" value="Submit"/>
<!-- <a class="btn btn-primary" href="adminaddedexternaluseraccounts" type="submit">Save</a> -->
<a class="btn btn-deafult" href="admininternaluseraccounts">Cancel</a>
<a class="btn btn-deafult" href="javascript:formSubmit()">Logout</a>

<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />	

</form>
<!--<input name="AddExtuser" type="submit" value="Add" style="position:absolute;width:200px;left:450px;top:955px;z-index:4;" class = "btn btn-primary" onclick="document.forms[0].action = 'ExternalUserAddedSuccessfully.html';return validateForm();"/>
<input name="ExtCancel" type="submit" value="Cancel" style="position:absolute;width:200px;left:670px;top:955px;z-index:4;" class = "btn btn-primary" onclick="document.forms[0].action = 'CancelInternal.html';return true;"/> -->


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
 
</body>
</html>