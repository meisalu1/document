<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<jsp:useBean id="doc" scope="request" type="documents.model.data.Document" />
<form method="POST">
	<c:if test="${not empty requestScope.errors}">
		<div class="row error">
			Vormi salvestamine ebaonnestus !
		</div>
	</c:if>
	<div class="row">
		<label for="name">Nimi:</label>
		<input type="text" name="name" value="<c:out value="${doc.name}"/>"/>
		<c:if test="${not empty requestScope.errors['name']}">
			<c:out value="${requestScope.errors['name']}"/>
		</c:if>
	</div>
	<div class="row">
		<label for="desc">Kirjeldus:</label>
		<input type="text" name="desc" value="<c:out value="${doc.desc}"/>"/>
		<c:if test="${not empty requestScope.errors['desc']}">
			<c:out value="${requestScope.errors['desc']}"/>
		</c:if>
	</div>
	<c:if test="${not empty requestScope.attributes}">
		<c:import url="components/doc/attributes.jsp"/>
	</c:if>
	<c:if test="${not empty requestScope.catalogs}">
		<c:import url="components/cat/catselect.jsp">
			<c:param name="current_cat" value="${doc.cat_id}"/>
		</c:import>
	</c:if>
	<div class="row">
		<input type="hidden" name="action" value="add_new"/>
		<input type="hidden" name="type_id" value="<c:out value="${doc.type}"/>"/>
		<input type="hidden" name="created_by" value="<c:out value="${doc.created_by}"/>"/>
		<input type="hidden" name="status_id" value="3"/>
		<input type="submit" name="submit" value="Lisa uus"/>
	</div>
</form>