<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page import="documents.model.data.DocumentType"  %>
<jsp:useBean id="types" scope="request" type="documents.model.data.DocumentType[]" />
<select name="type">
	<c:if test="${not empty requestScope.addEmpty}">
		<option value="0">Vali...</option>
	</c:if>
	<c:forEach var="type" items="${types}">
		<option value="<c:out value="${type.id}"/>" <c:if test="${type.id eq param.current_type}"><c:out value="selected='selected'"/></c:if>><c:out value="${type.name}"/></option>
		<c:if test="${not empty type.types}">
			<c:forEach var="child" items="${type.types}">
				<option value="<c:out value="${child.id}"/>" <c:if test="${child.id eq param.current_type}"><c:out value="selected='selected'"/></c:if>><c:out value="${child.name}"/></option>
			</c:forEach>
		</c:if>
	</c:forEach>
</select>