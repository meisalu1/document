<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page import="documents.model.data.Status"  %>
<jsp:useBean id="statuses" scope="request" type="documents.model.data.Status[]" />
<select name="status">
	<c:if test="${not empty requestScope.addEmpty}">
		<option value="0">Vali...</option>
	</c:if>
	<c:forEach var="status" items="${statuses}">
		<option value='<c:out value="${status.id}"/>' <c:if test="${status.id eq param.current_status}"><c:out value="selected='selected'"/></c:if>><c:out value="${status.name}"/></option>
	</c:forEach>
</select>