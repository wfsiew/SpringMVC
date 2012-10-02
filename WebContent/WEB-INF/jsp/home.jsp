<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<link rel="Stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />" />
</head>
<body>
	<div id="tabs">
		<ul>
			<li><a href="<c:url value="/main/cars" />">Main Car CRUD Operation</a></li>
			<li><a href="<c:url value="/ajax/cars" />">Main Car CRUD Operation with jQuery Ajax</a></li>
		</ul>
	</div>
</body>
</html>