<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
	<script type="text/javascript">
		function validate_fileupload()
		{
			var fup = document.getElementById('ufile');
		    var fileName = fup.value;
		    var allowed_extensions = "cer";
		    var file_extension = fileName.split('.').pop(); 
		    // split function will split the filename by dot(.), and pop function will pop the last element from the array which will give you the extension as well. If there will be no extension then it will return the filename.
		
		    if(allowed_extensions !=file_extension)
		    {
		    	alert("Please Select .cer file"); // valid file extension
		    	return false;
		    }
		  
		    return true;
		}
</script>
</head>
<body>
<center><h1>SYNNOVATION</h1></center>
<h2>SUBMIT PAYMENT PAGE</h2>
<ul class="nav nav-tabs">
    <li><a href="../../../MerchantCredit">Credit Amount</a> </li>
<li><a href="../../../MerchantDebit">Debit Amount</a> </li>
<li><a href="../../../MerchantTransfer">Transfers</a></li>
<li><a href="../../../ViewMerchantTransactions">View Transactions</a></li>
<li><a href="../merchant/MerchantNotifications">Notifications</a></li>
<li><a href="../../../ChangeMerchantInfo">Change information</a></li>
<li><a href="../merchant/MerchantTechAccountAccess">Technical Account Access</a></li>
<li><a href="../../../MerchantAcceptNotification">Accept Customer Notifications</a><li>
<!-- <li class="active"><a href="#">Submit Payment</a></li> -->
</ul>
<br>
<div id="certificate-error"><font color="red">${error}</font></div>

<form:form  modelAttribute="merchantPaymentFormBean" method="post" enctype="multipart/form-data" onsubmit="return validate_fileupload(this);">
		<h4>Notification ID <span class="label label-default">${merchantPaymentFormBean.notification_id}</span></h4>
        <h4>Amount<span class="label label-default">${merchantPaymentFormBean.amount}</span></h4>
		File to upload: <input type="file" id="ufile" name="file" class="btn btn-primary"><br /> 
		<input class = "btn btn-primary" type="submit" value="Upload" onclick="document.forms[0].method='post';document.forms[0].action='../../../../merchant/submitpayment/${merchantPaymentFormBean.notification_id}'; ;return true;"> Press here to upload the file!
		
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />	
			
		<a class="btn btn-default" href="javascript:formSubmit()">Logout</a>		
    </form:form>
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




<!-- <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> -->
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>



</body>
</html>