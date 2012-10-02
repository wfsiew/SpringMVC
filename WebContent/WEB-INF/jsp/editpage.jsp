<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Car</title>
<link rel="Stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />" />
</head>
<body>

	<h1>Edit Car</h1>

	<c:url var="saveUrl" value="/main/cars/edit/${carAttribute.id}" />
	<form:form modelAttribute="carAttribute" method="POST"
		action="${saveUrl}">
		<table>
			<tr>
				<td><form:label path="id">Id:</form:label></td>
				<td><form:input path="id" disabled="true" cssClass="text" /></td>
			</tr>

			<tr>
				<td><form:label path="make">Make:</form:label></td>
				<td><form:input path="make" cssClass="text" /></td>
				<td><form:errors path="make" cssClass="form_error" /></td>
			</tr>

			<tr>
				<td><form:label path="model">Model:</form:label></td>
				<td><form:input path="model" cssClass="text" /></td>
				<td><form:errors path="model" cssClass="form_error" /></td>
			</tr>

			<tr>
				<td><form:label path="year">Year:</form:label></td>
				<td><form:input path="year" cssClass="text" /></td>
				<td><form:errors path="year" cssClass="form_error" /></td>
			</tr>

			<tr>
				<td><form:label path="doors">Doors:</form:label></td>
				<td>
					<form:select path="doors" cssClass="select">
						<form:option value="2">2</form:option>
		            	<form:option value="3">3</form:option>
		            	<form:option value="4">4</form:option>
		            	<form:option value="5">5</form:option>
					</form:select>
				</td>
				<td><form:errors path="doors" cssClass="form_error" /></td>
			</tr>

			<tr>
				<td><form:label path="colour">Colour:</form:label></td>
				<td><form:input path="colour" cssClass="text" /></td>
				<td><form:errors path="colour" cssClass="form_error" /></td>
			</tr>

			<tr>
				<td><form:label path="price">Price:</form:label></td>
				<td><form:input path="price" cssClass="text" /></td>
				<td><form:errors path="price" cssClass="form_error" /></td>
			</tr>
		</table>

		<input type="submit" value="Save" />
	</form:form>

</body>
</html>