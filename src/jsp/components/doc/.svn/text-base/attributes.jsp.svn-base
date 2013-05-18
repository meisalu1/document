<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page import="documents.model.data.Attribute"  %>
<jsp:useBean id="attributes" scope="request" type="documents.model.data.Attribute[]" />
<c:forEach var="attribute" items="${attributes}">
	<c:set var="name" value="attr_${attribute.attribute_type}"/> 
	<div class="row">
		<label for="attr_<c:out value="${attribute.attribute_type}"/>"><c:out value="${attribute.name}"/></label>
		<c:if test="${attribute.data_type != 4}">
			<input type="text" name="attr_<c:out value="${attribute.attribute_type}"/>" value="<c:out value="${attribute.defaultValue}"/>"/>
		</c:if>
		<c:if test="${attribute.data_type == 4}">
			<select name="attr_<c:out value="${attribute.attribute_type}"/>">
				<c:forEach var="option" items="${attribute.selections}">
					<option value="<c:out value="${option.id}"/>" <c:if test="${attribute.defaultValue eq option.id}"><c:out value="selected='selected'"/></c:if>><c:out value="${option.name}" /></option>
				</c:forEach>
			</select>
		</c:if>
		<c:if test="${not empty requestScope.errors[name]}">
			<c:out value="${requestScope.errors[name]}"/>
		</c:if>
	</div>
</c:forEach>