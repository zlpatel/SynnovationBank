<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Critical Transaction Page</title>
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
<center><h1> SYNNOVATION </h1> </center>
<h2> CRITICAL TRANSACTION </h2>
<div id="certificate-error"><font color="red">${error}</font></div>
<ul class="nav nav-tabs">
<li><a href="credit_debit">Credit Amount</a> </li>
<li><a href="debit">Debit Amount</a> </li>
<li><a href="transfer">Transfers</a></li>
<li><a href="viewTransactions">View Transactions</a></li>
<li><a href="customerNotifications">Notifications</a></li>
<li><a href="changeCustomerInfo">Change information</a></li>
<li><a href="techAccountAccess">Technical Account Access</a></li>
<li><a href="payMerchant">Pay Merchant</a></li>
<li class="active"><a href="#">Critical Transfers (greater than $500)</a></li>
</ul>
<br>



<form:form action="uploadfile1" modelAttribute="fileuploadformbean" method="post" enctype="multipart/form-data" onsubmit="return validate_fileupload(this);">
		Certificate to upload: <input type="file" id="ufile" name="file"><br /> 
		<input class = "btn btn-primary" type="submit" value="Upload"> Press here to upload the certificate!
		
		<%-- <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" /> --%>		
			
    </form:form>

</body>
</html>