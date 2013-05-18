<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page import="documents.model.data.Catalog"  %>
<div class="row">
	<label for="cat_id">Kategooria</label>
	<select name="cat_id">
		<c:forEach var="cat" items="${requestScope.catalogs}">
			<c:if test="${cat.level eq 1}">
				<option value="<c:out value="${cat.id}"/>" <c:if test="${cat.id eq param.current_cat}"><c:out value="selected='selected'"/></c:if>><c:out value="${cat.name}"/></option>
					<c:if test="${not empty cat.subCats}">
						<c:forEach var="child" items="${cat.subCats}">
							<option value="<c:out value="${child.id}"/>" <c:if test="${child.id eq param.current_cat}"><c:out value="selected='selected'"/></c:if>>...<c:out value="${child.name}"/></option>
						</c:forEach>
					</c:if>
			</c:if>
		</c:forEach>
	</select>
</div>