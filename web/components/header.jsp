<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="servletPath" value="${pageContext.request.servletPath}" />

<link rel="stylesheet" href="${contextPath}/css/bootstrap.min.css">
<script src="${contextPath}/js/bootstrap.min.js"></script>
<c:choose>
    <c:when test="${(servletPath == '/login.jsp' || servletPath == '/login') && (sessionScope.id != null && sessionScope.id > 0)}">
        <c:redirect url="/" />
    </c:when>
    <c:when test="${(servletPath != '/login.jsp' && servletPath != '/login') && sessionScope.id == null}">
        <c:redirect url="/login" />
    </c:when>
</c:choose>