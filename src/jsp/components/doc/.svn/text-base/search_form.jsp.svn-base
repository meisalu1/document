<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<jsp:useBean id="searchForm" scope="request" type="documents.form.SearchForm" />
<form method="POST">
	<div class="row">
		<label for="id">Id:</label>
		<input type="text" name="id" value="<c:out value="${searchForm.id}"/>"/>
	</div>
	<div class="row">
		<label for="id">Nimi:</label>
		<input type="text" name="name" value="<c:out value="${searchForm.name}"/>"/>
	</div>
	<div class="row">
		<label for="id">Kirjeldus:</label>
		<input type="text" name="desc" value="<c:out value="${searchForm.desc}"/>"/>
	</div>
	<div class="row">
		<label for="id">Viimane muutja:</label>
		<input type="text" name="last_changer" value="<c:out value="${searchForm.last_modifier}"/>"/>
	</div>
	<div class="row">
		<label for="id">Kataloogi nimi:</label>
		<input type="text" name="cat_name" value="<c:out value="${searchForm.cat_name}"/>"/>
	</div>
	<div class="row">
		<label for="id">Subject:</label>
		<input type="text" name="subject" value="<c:out value="${searchForm.subject}"/>"/>
	</div>
	<div class="row">
		<label for="status">Dokumendi staatus:</label>
			<c:import url="components/doc/status.jsp">
				<c:param name="current_status" value="${searchForm.currentStatusID}"/>
			</c:import>
	</div>
	<c:choose>
		<c:when test="${empty requestScope.attributes}">
			<div class="row">
				<label for="attribute">Atribuudi väärtus:</label>
				<input type="text" name="attribute" value="<c:out value="${searchForm.attribute}"/>"/>
			</div>
		</c:when>
		<c:otherwise>
			<c:import url="components/doc/attributes.jsp"/>
		</c:otherwise>
	</c:choose>
	<div class="row">
		<label for="type">Dokumendi tüüp:</label>
		<c:import url="components/doc/types.jsp">
			 <c:param name="current_type" value="${searchForm.currentTypeID}"/>
		</c:import>
	</div>
	<div class="row">
		<input type="hidden" name="action" value="do_search"/>
		<input type="submit" name="submit" value="Otsi">
	</div>
</form>