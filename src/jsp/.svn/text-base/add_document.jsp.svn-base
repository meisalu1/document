<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<c:import url="components/general/header.jsp"/>
<c:import url="components/doc/docs_menu.jsp"/>
<form method="POST">
	<label for="type">Dokumendi tüüp:</label>
	<c:import url="components/doc/types.jsp">
		<c:param name="current_type" value="${requestScope.type_id}"/>
	</c:import>
	<input type="submit" name="submit" value="Lisa uus dokument">
	<input type="hidden" name="action" value="add_doc_form"/>
</form>
<c:if test="${not empty requestScope.showform}">
	<c:import url="components/doc/doc_form.jsp"/>
</c:if>
<c:import url="components/general/footer.jsp"/>