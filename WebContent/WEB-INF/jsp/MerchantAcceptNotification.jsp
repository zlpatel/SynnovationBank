<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Merchant Accept Notifications Page</title>
<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<h2>Merchant Page</h2>
<p>Only merchants have access to this page.</p><br>

<ul class="nav nav-tabs">
    <li><a href="MerchantCredit">Credit Amount</a> </li>
<li><a href="MerchantDebit">Debit Amount</a> </li>
<li><a href="MerchantTransfer">Transfers</a></li>
<li><a href="ViewMerchantTransactions">View Transactions</a></li>
<li><a href="MerchantNotifications">Notifications</a></li>
<li><a href="ChangeMerchantInfo">Change information</a></li>
<li><a href="MerchantTechAccountAccess">Technical Account Access</a></li>
<li class="active"><a href="MerchantAcceptNotification">Accept Customer Notifications</a><li>

</ul>

<br><br>
<form commandName="merchantNotifAcceptRejectBean" method="post" >
<table class="table" class ="table table-striped">
<thead>
            <tr>
                <th>Notification ID </th>
                <th>Amount</th>
<!--                 <th>Notification</th> -->
<!--                 <th>Amount</th>                                                 -->
<!--                 <th>Action</th> -->
            </tr>
        </thead>
        <tbody>
            <c:forEach  items="${merchantNotifFormBean}" var="notification">
        <tr> 
          <td>${notification.notification_id}</td>
          <td>${notification.amount}</td>
          <td>${notification.notifications}</td>

           <td align="center"><input style="width:75px;" id="accept" type="submit" value= "Accept" class = "btn btn-primary" onclick="document.forms[0].method = 'post';document.forms[0].action = 'merchantacceptexternaluser?notifId=${notification.notification_id}'; ;return true;"/></td>  
          <td align="center"><input  style="width:75px;" id="delete" type="submit" value= "Reject" class = "btn btn-danger"  onclick="document.forms[0].method = 'post';document.forms[0].action = 'merchantrejectexternaluser?notifId=${notification.notification_id}'; ;return true;"/></td> 
          <!-- <td align="center"><input style="width:75px;" name="approve" type="submit" value= "Approve" class = "btn btn-primary" onclick="document.forms[0].method = 'post';document.forms[0].action = '/notificationAccepted';;return true;"/></td>  
          <td align="center"><input  style="width:75px;" name="deny" type="submit" value= "Deny" class = "btn btn-primary"  onclick="document.forms[0].method = 'post';document.forms[0].action = 'ExtToDoDenied.html?authUsername=${person.ID}'; ;return true;"/></td>--> 
        </tr>
      </c:forEach>
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
<br> <a href="javascript:formSubmit()"> Logout</a>

<!-- <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> -->
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>



</body>
</html>