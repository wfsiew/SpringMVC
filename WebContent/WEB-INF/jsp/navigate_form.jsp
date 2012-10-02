<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="nav_table">
	<tr>
		<td>
			<span id="id_prev" class="page_button ui-state-default ui-corner-all">&lt;&lt;</span>
		</td>
		<td>
			<span id="id_next" class="page_button ui-state-default ui-corner-all">&gt;&gt;</span>
		</td>
	</tr>
	<tr>
		<td>
			<label for="id_display">Display</label>
			<select id="id_display" class="display_options">
				<option value="0">All</option>
				<option value="10">10</option>
				<option value="25">25</option>
				<option value="50">50</option>
				<option value="100">100</option>
			</select>
		</td>
		<td><span class="item_display"></span></td>
	</tr>
</table>