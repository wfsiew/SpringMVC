<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cars Page</title>
<link rel="Stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />" />
</head>
<body>
	<h1>Cars</h1>

	<p>${dic.item_msg}</p>
	<c:url var="addUrl" value="/main/cars/add" />
	<table style="border: 1px solid; width: 500px; text-align: center">
		<thead style="background: #fcf">
			<tr>
				<th>Make</th>
				<th>Model</th>
				<th>Year</th>
				<th>Doors</th>
				<th>Colour</th>
				<th>Price</th>
				<th colspan="3"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${cars}" var="car">
				<c:url var="editUrl" value="/main/cars/edit/${car.id}" />
				<c:url var="deleteUrl" value="/main/cars/delete/${car.id}" />
				<tr>
					<td>${car.make}</td>
					<td>${car.model}</td>
					<td>${car.year}</td>
					<td>${car.doors}</td>
					<td>${car.colour}</td>
					<td>${car.price}</td>
					<td><a href="${editUrl}">Edit</a></td>
					<td><a href="${deleteUrl}">Delete</a></td>
					<td><a href="${addUrl}">Add</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<c:if test="${empty cars}">
	There are currently no cars in the list. <a href="${addUrl}">Add</a> a car.
</c:if>

</body>
</html>