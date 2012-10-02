<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="list_table">
	<tbody>
		<c:forEach items="${cars}" var="car" varStatus="loop">
			<c:set var="rowCss" value="ui-state-default" />
			<c:if test="${loop.count % 2 == 0}">
				<c:set var="rowCss" value="ui-state-active" />
			</c:if>
			<tr class="${rowCss} list_row">
				<td id="${car.id}" width="60px">
					<div class="list_button edit hover ui-corner-all ui-state-default">
                    	<span>Edit</span>
                	</div>
                	<div class="list_button delete hover ui-corner-all ui-state-default">
                    	<span>Delete</span>
                	</div>
				</td>
				<td valign="top">
					<div>${car.make} ${car.model} (Year ${car.year}) ${car.doors} Doors ${car.colour}</div>
                	<div>RM ${car.price}</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<input id="id_pg" type="hidden" value="${dic.hasprev},${dic.hasnext},${dic.prevpage},${dic.nextpage},${dic.item_msg}" />