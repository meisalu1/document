<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<c:import url="components/general/header.jsp"/>
<c:import url="components/cat/catlist.jsp"/>
<c:if test="${not empty requestScope.docs}">
	<c:import url="components/doc/table.jsp"/>
</c:if>
<c:import url="components/doc/cacheTable.jsp"/>
<c:import url="components/general/footer.jsp"/>