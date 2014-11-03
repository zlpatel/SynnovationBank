<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
</head>
<body>

<div id="certificate-error"><font color="red">${error}</font></div>

<form:form action="uploadfile" modelAttribute="fileuploadformbean" method="post" enctype="multipart/form-data">
		File to upload: <input type="file" name="file" class="btn btn-primary"><br /> 
		<input class = "btn btn-primary" type="submit" value="Upload"> Press here to upload the file!
		
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />	
				
    </form:form>

</body>
</html>