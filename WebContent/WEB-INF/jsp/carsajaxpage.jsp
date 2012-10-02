<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Car CRUD using jQuery</title>
<link rel="Stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />" />
<link rel="Stylesheet" type="text/css" href="<c:url value="/resources/css/ui-darkness/jquery-ui-1.8.21.custom.css" />" />
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.7.2.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui-1.8.21.custom.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/ejs.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/utils.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/nav_list.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/car.js" />"></script>
</head>
<body>
	<h1>Car CRUD using jQuery</h1>
	<div id="table_container">
		<div class="page_title ui-widget-header ui-widget">Cars</div>
		<table class="page_container">
			<tbody>
				<tr>
					<td valign="top">
						<div id="id_add" class="hover ui-state-default ui-corner-all">
							<span>Add new car ...</span>
						</div>
					</td>
					<td valign="top">
						<%@ include file="search_form.jsp"%>
					</td>
					<td valign="top">
						<%@ include file="navigate_form.jsp"%>
					</td>
				</tr>
			</tbody>
		</table>
		<table class="page_container">
			<tr>
				<td id="left_box" valign="top" style="display: none"></td>
				<td id="right_box" valign="top"><%@ include file="carlist.jsp"%></td>
			</tr>
		</table>
	</div>
	<div id="dialog-message" title="Add Car">
		<div id="dialog_msg"></div>
	</div>
	<div id="error-dialog" title="Internal Server Error">
		<div id="error_dialog"></div>
	</div>
</body>
</html>