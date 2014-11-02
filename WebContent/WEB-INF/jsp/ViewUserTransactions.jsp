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


<ul class="nav nav-tabs">
    <li><a href="employee">Notifications</a></li>
    <li><a href="employeeuseraccounts">User Accounts</a></li>
    <li class="active"><a href="#">Change Password</a></li>
    <li><a href="employeeviewtransactions">View Customer Transactions</a></li>
    </ul>
    <p align="center">View Customer Transactions</p>
    <form name ="user_transactions" method = "post">
		<table id = "transaction" width="500" border="0" bordercolor="black" class ="table table-striped">
		<tr>
		  <td><b>Transaction Id</b></td>
		  <td><b>Balance</b></td>
          <td><b> Transaction Date</b></td>
          <td><b> Transaction Name</b></td>
          
          <td colspan="2" align="center"><b>Action</b></td>
         </tr>
      <c:forEach  items="${userTransaction}" var="trans">
        <tr> 
          <td>${trans.transactionId}</td>
          <td>${trans.balance}</td>
          <td>${trans.transactionDate}</td>
          <td>${trans.transactionsName}</td>
         </tr>
      </c:forEach>
      </table>
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