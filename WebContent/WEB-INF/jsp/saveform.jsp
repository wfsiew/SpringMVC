<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<div class="form_title ui-widget-header ui-widget ui-corner-all">
	<div class="title"><c:out value="${formTitle}"></c:out></div>
	<div class="close hover ui-corner-all ui-state-default">
		<div class="ui-icon ui-icon-closethick"></div>
	</div>
</div>
<div class="ui-widget-content ui-widget ui-corner-all">
    <form:form id="save-form" modelAttribute="carAttribute" action="." method="POST">
        <div class="row">
            <form:label path="make" for="id_make">Make</form:label>
            <form:input path="make" id="id_make" cssClass="text" />
        </div>
        <div class="row">
            <form:label path="model" for="id_model">Model</form:label>
            <form:input path="model" id="id_model" cssClass="text" />
        </div>
        <div class="row">
            <form:label path="year" for="id_year">Year</form:label>
            <form:input path="year" id="id_year" cssClass="text" />
        </div>
        <div class="row">
            <form:label path="doors" for="id_doors">Doors</form:label>
            <form:select path="doors" id="id_doors" cssClass="select">
            	<form:option value="2">2</form:option>
            	<form:option value="3">3</form:option>
            	<form:option value="4">4</form:option>
            	<form:option value="5">5</form:option>
            </form:select>
        </div>
        <div class="row">
            <form:label path="colour" for="id_colour">Colour</form:label>
            <form:input path="colour" id="id_colour" cssClass="text" />
        </div>
        <div class="row">
            <form:label path="price" for="id_price">Price</form:label>
            <form:input path="price" id="id_price" cssClass="text" />
        </div>
        <div class="row_button">
            <span class="save_button save hover ui-corner-all ui-state-default">
                <span>Save</span>
            </span>
            <span class="save_button cancel hover ui-corner-all ui-state-default">
                <span>Cancel</span>
            </span>
        </div>
    </form:form>
</div>