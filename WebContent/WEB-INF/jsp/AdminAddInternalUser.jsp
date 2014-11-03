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
	
	<script type="text/javascript">
	
	function validateForm()
	{

	 var fname = document.getElementById("fname").value;
	 var lname = document.getElementById("lname").value;
	 var address = document.getElementById("address").value;
	 var email = document.getElementById("email").value;
	 var username = document.getElementById("username").value;
	 var password = document.getElementById("password").value;

	  if (fname === "" || lname === "" || address === "" || email === "" || username === "" || password === "") 
	  {
	  	alert("Please fill all fields.");
	  	return false;
	  }
	  var regex1 = new RegExp("^[a-zA-Z]+$");	  
	  if(!regex1.test(fname) || !regex1.test(lname))  
		{
			alert('Please enter alphabetic characters only for First Name and Last Name.');
			return false;
		}
		
		var regex2 = new RegExp("^[a-zA-Z0-9_ ]+$");
		if(!regex2.test(address))
		{
				alert('Please remove special characters from Address');
				return false;
		}
		
		var regex3 = new RegExp("^[a-zA-Z0-9]+$");
		 if(!regex3.test(username))
		{
			alert('Please remove special characters and spaces from Username');
			return false; 
		}
	  	    
	    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	    if(!re.test(email))
	    {
	    	alert("Please enter a valid e-mail address.");
	    	return false;
	    }
	  
	 }</script>
	
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

<br><br>

<form action ="adminaddedinternaluseraccounts" commandName="addinternaluserformbean" method="post" >

<input name="fname" id="fname"  class = "form-control" style="width:200px;" placeholder = "First Name" maxlength="15" value="${addinternaluserformbean.fname}"/>
<br>
<input name="lname" id="lname"  type="text" class = "form-control" style="width:200px;" placeholder = "Last Name" maxlength="14" value="${addinternaluserformbean.lname}"/>
<br>
<input name="address" id="address"  type="text" class = "form-control" style="width:200px;" placeholder = "Address" maxlength="50" value="${addinternaluserformbean.address}"/>
<br>
<%-- <input name="city"  type="text" class = "form-control" style="width:200px;" placeholder = "City" maxlength="15" value="${addinternaluserformbean.city}"/>
<br><input name="state"  type="text" class = "form-control" style="width:200px;" placeholder = "State" maxlength="15" value="${addinternaluserformbean.state}"/>
<br>
<input name="zipcode"  type="text" class = "form-control" style="width:200px;" placeholder = "Zip Code" maxlength="5" value="${addinternaluserformbean.zipcode}"/>
<br>
 <input name="phone"  type="text" class = "form-control" style="width:200px;" placeholder = "Phone" maxlength="10" value="${addinternaluserformbean.phone}"/>
<br><input name="cell"  type="text" class = "form-control" style="width:200px;" placeholder = "Cell" maxlength="10" value="${addinternaluserformbean.cell}"/>
<br>
--%><input name="email" id="email"  type="text" class = "form-control" style="width:200px;" placeholder = "Email" maxlength="30" value="${addinternaluserformbean.email}"/>
<br><input name="username" id="username"  class = "form-control" style="width:200px;" placeholder = "Username" maxlength="15" value="${addinternaluserformbean.username}"/>
<br><input name="password" id="password" type="password" class="form-control" style="width:200px;" placeholder="Password" value="${addinternaluserformbean.password}"/>
<br>

<input class="btn btn-lg btn-primary" type="submit" value="Add User" onclick="document.forms[0].action = 'adminaddedinternaluseraccounts' ;return validateForm();"/>
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
 
</body>
</html>