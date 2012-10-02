<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<select id="id_selection" class="search_option">
<c:forEach items="${searchoptions}" var="o">
	<option value="${o.key}">${o.value}</option>
</c:forEach>
</select>
<div class="space">
	<input id="id_query" type="text" size="27" />
	<span id="id_find" class="hover ui-corner-all ui-state-default">
		<span>Find</span>
	</span>
</div>