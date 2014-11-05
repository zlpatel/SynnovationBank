<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Employee Page</h2>
<p>Only employees have access to this page.</p><br>

<ul class="nav nav-tabs">
    <li><a href="employee">Notifications</a></li>
    <li><a href="employeeuseraccounts">User Accounts</a></li>
    <li class="active"><a href="#">Change Password</a></li>
    <li><a href="employeeviewtransactions">View Customer Transactions</a></li>
    
    <form name ="user_transactions" method = "post">
		<table id = "transaction" width="500" border="0" bordercolor="black" class ="table table-striped">
		<tr>
		  <td><b>First Name</b></td>
		  <td><b>Last Name</b></td>
          <td><b>Notifications</b></td>
          
          <td colspan="2" align="center"><b>Action</b></td>
         </tr>
      <c:forEach  items="${userTransaction}">
        <tr> 
          <td>${userTransaction.getFirstName}</td>
          <td>${userTransaction.getLastName}</td>
          <td>${userTransaction.getNotifications}</td>
         </tr>
      </c:forEach>
      </table>
      </form>
    
</ul>


</body>
</html>