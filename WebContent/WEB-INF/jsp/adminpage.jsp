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
<h2>NOTIFICATIONS</h2>
<ul class="nav nav-tabs">
    <li class="active"><a href="#">Notifications</a></li>
    <li><a href="admininternaluseraccounts">Internal User Accounts</a></li>
    <li><a href="adminexternaluseraccounts">External User Accounts</a></li>
	<li><a href="adminpiirequests">PII Requests</a></li>
    <li><a href="admincriticaltransactions">Critical Transactions</a></li>
      
    
</ul>
<br>
<form action="admin" commandName="getadminnotifications" method="post" >
<table class="table" class ="table table-striped">
        <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Username</th>
                <th>Notification</th>
            </tr>
        </thead>
        <tbody>
         <c:forEach  items="${adminNotifFormBean}">
        <tr> 
          <td>${adminNotifFormBean.getFirstName}</td>
          <td>${adminNotifFormBean.getLastName}</td>
          <td>${adminNotifFormBean.getUserName}</td>
          <td>${adminNotifFormBean.getNotifications}</td>
          <!-- <td align="center"><input style="width:75px;" name="approve" type="submit" value= "Approve" class = "btn btn-primary" onclick="document.forms[0].method = 'post';document.forms[0].action = '/notificationAccepted';;return true;"/></td>  
          <td align="center"><input  style="width:75px;" name="deny" type="submit" value= "Deny" class = "btn btn-primary"  onclick="document.forms[0].method = 'post';document.forms[0].action = 'ExtToDoDenied.html?authUsername=${person.ID}'; ;return true;"/></td>--> 
        
        </tr>
      </c:forEach>
      </tbody>
      
      </table>
      <a class="btn btn-deafult" href="javascript:formSubmit()">Logout</a>
      </form>

<!-- <table class="table">
        <thead>
            <tr>
                <th>Sr. No.</th>
                <th>Title</th>
                <th>Date</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>1</td>
                <td>User request One.</td>
                <td>10/25/2014</td>
                <td>
                <button type="button" class="btn btn-primary">Accept</button>
                <button type="button" class="btn btn-danger">Reject</button>
                </td>
            </tr>
            <tr>
                <td>2</td>
                <td>User request Two.</td>
                <td>10/24/2014</td>
                <td>
                <button type="button" class="btn btn-primary">Accept</button>
                <button type="button" class="btn btn-danger">Reject</button>
                </td>
            </tr>
            <tr>
                <td>3</td>
                <td>User request Three.</td>
                <td>10/24/2014</td>
                <td>
                <button type="button" class="btn btn-primary">Accept</button>
                <button type="button" class="btn btn-danger">Reject</button>
                </td>
            </tr>
        </tbody>
    </table> -->

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
