<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page import="documents.model.data.Document"  %>
<jsp:useBean id="docs" scope="request" type="documents.model.data.Document[]" />

<table class="doctable">
	<thead>
		<tr>
			<th>Vali</th>
			<th>Id</th>
			<th>Nimi</th>
			<th>Kirjeldus</th>
			<th>Tegevused</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="doc" items="${docs}">
		<tr>
			<td>hiljem</td>
			<td><c:out value="${doc.id}"/></td>
			<td><c:out value="${doc.name}"/></td>
			<td><c:out value="${doc.desc}"/></td>
			<td>
				<form method="POST" class="ajaxdelete">
					<input type="submit" name="submit" value="Kustuta"/>
					<input type="hidden" name="id" value="<c:out value="${doc.id}"/>"/>
					<input type="hidden" name="action" value="ajax"/>
					<input type="hidden" name="ajax_action" value="delete"/>
				</form>
				<form method="POST" class="ajaxform">
					<input type="submit" name="submit" value="Muuda"/>
					<input type="hidden" name="id" value="<c:out value="${doc.id}"/>"/>
					<input type="hidden" name="action" value="ajax"/>
					<input type="hidden" name="ajax_action" value="edit"/>
				</form>
				<form method="POST" class="ajaxcache">
					<input type="submit" name="submit" value="<c:choose><c:when test="${not empty sessionScope.cache[doc.id]}">Eemalda puhvrist</c:when><c:otherwise>Lisa puhvrisse</c:otherwise></c:choose>"/>
					<input type="hidden" name="id" value="<c:out value="${doc.id}"/>"/>
					<input type="hidden" name="action" value="ajax"/>
					<input type="hidden" name="ajax_action" value="addCache"/>
				</form>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<form method="POST" class="hidden">
	<div class="row">
		<label for="id">Nimi:</label>
		<input type="text" name="name" value=""/>
	</div>
	<div class="row">
		<label for="id">Kirjeldus:</label>
		<input type="text" name="desc" value=""/>
	</div>
	<div class="row hidden" id="ajaxDocEdit">
	</div>
	<div class="row">
		<label for="status">Dokumendi staatus:</label>
		<c:import url="components/doc/status.jsp">
			<c:param name="current_status" value="0"/>
		</c:import>
	</div>
	<div class="row">
		<input type="hidden" name="id" value="0"/>
		<input type="hidden" name="type" value="0"/>
		<input type="hidden" name="action" value="ajax"/>
		<input type="hidden" name="ajax_action" value="editSubmit"/>
		<input type="submit" name="submit" value="Muuda"/>
	</div>
</form>