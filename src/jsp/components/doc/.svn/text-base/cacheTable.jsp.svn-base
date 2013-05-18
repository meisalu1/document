<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page import="documents.model.data.Document"  %>

<table class="cachetable">
	<thead>
		<tr>
			<th>Id</th>
			<th>Nimi</th>
			<th>Kirjeldus</th>
			<th>		
				<form method="POST" class="ajaxemptycache">
					<input type="submit" name="submit" value="Eemalda puhvrist koik"/>
					<input type="hidden" name="action" value="ajax"/>
					<input type="hidden" name="ajax_action" value="emptyCache"/>
				</form></th>
			<th>
				<form method="POST" class="ajaxselectedcache">
					<input type="submit" name="submit" value="Eemalda puhvrist valitud"/>
					<input type="hidden" name="action" value="ajax"/>
					<input type="hidden" name="ajax_action" value="removeSelectedCache"/>
				</form>
			</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${not empty sessionScope.cache}">
			<c:forEach var="entry" items="${sessionScope.cache}">
				<tr>
					<td><c:out value="${entry.value.id}"/></td>
					<td><c:out value="${entry.value.name}"/></td>
					<td><c:out value="${entry.value.desc}"/></td>
					<td>
						<form method="POST" class="ajaxcacheremove">
							<input type="submit" name="submit" value="Eemalda puhvrist"/>
							<input type="hidden" name="id" value="<c:out value="${entry.value.id}"/>"/>
							<input type="hidden" name="action" value="ajax"/>
							<input type="hidden" name="ajax_action" value="removeCache"/>
						</form>
					</td>
					<td>
						<input type="checkbox" name="doc_<c:out value="${entry.value.id}"/>" value="<c:out value="${entry.value.id}"/>"/>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<tr id="empty" class="<c:if test="${not empty sessionScope.cache}">hidden</c:if>">
			<td colspan="5">
				Puhver on hetkel tyhi
			</td>
		</tr>
		<tr class="hidden example">
			<td class="id">id</td>
			<td class="name">name</td>
			<td class="desc">desc</td>
			<td>
				<form method="POST" class="ajaxcacheremove">
					<input type="submit" name="submit" value="Eemalda puhvrist"/>
					<input type="hidden" name="id" value="0"/>
					<input type="hidden" name="action" value="ajax"/>
					<input type="hidden" name="ajax_action" value="removeCache"/>
				</form>
			</td>
			<td>
				<input type="checkbox" name="doc_0" value="0"/>
			</td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="5">
				<form method="POST" class="move">
					<input type="submit" name="submit" value="Tosta siia kataloogi"/>
					<input type="hidden" name="action" value="move"/>
				</form>
			</td>
		</tr>
	</tfoot>
</table>