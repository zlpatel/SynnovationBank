<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Synnovation Bank</title>
<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	
	<!-- Optional theme -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js"></script>
	<link type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery.keypad.css" rel="stylesheet"> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.plugin.js"> </script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.keypad.js"> </script>
	<script type="text/javascript">
	  $(function() {
		$('#otpKeypad').keypad(); 
	  });
		  
	  $('#viewKeypad').click(function() { 
		  alert('The current value is: ' + $('#otpKeypad').val()); 
	  }); 
		 
	  $('#removeKeypad').toggle(function() { 
	       $(this).text('Re-attach'); 
	       $('#otpKeypad').keypad('destroy'); 
      }, 
	    function() { 
	        $(this).text('Remove'); 
	        $('#otpKeypad').keypad(); 
	    } 
   	);
	</script>
</head>
<body>

<div id="otp-error"><font color="red">${error}</font></div>

<h1>One Time Password</h1>
<form action="../otp/changepassword" commandName="otpformbean" method="post" >

<p>	
	<input id="otpKeypad" name="otp" placeholder="one time password" type="password" readonly="readonly" value="${otpformbean.otp}"/>
	<!-- <button id="removeKeypad" type="button" style="display: none;"></button> -->
</p>

<input class="btn btn-lg btn-primary" type="submit" value="Submit"/>

<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />	
</form>

</body>
</html>