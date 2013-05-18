<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page import="documents.model.data.Catalog"  %>
<ul>
	<c:forEach var="cat" items="${requestScope.catalogs}">
		<c:if test="${cat.level eq 1}">
			<li <c:if test="${cat.id eq param.current_cat}"><c:out value="class='active'"/></c:if>>
				<a href="?mode=categories&submode=cat_<c:out value="${cat.id}"/>"><c:out value="${cat.name}"/></a>
				<c:if test="${not empty cat.subCats}">
					<ul>
						<c:forEach var="child" items="${cat.subCats}">
							<a href="?mode=categories&submode=cat_<c:out value="${child.id}"/>"><c:out value="${child.name}"/></a>
						</c:forEach>
					</ul>
				</c:if>
			</li>
		</c:if>
	</c:forEach>
</ul>