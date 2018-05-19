<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<c:if test="${not empty sessionScope.startPage}">
    <jsp:include page="${requestScope.contextPath}/${sessionScope.startPage}"/>
</c:if>
<c:if test="${empty sessionScope.startPage}">
    <jsp:include page="${requestScope.contextPath}/index.jsp"/>
</c:if>
<h2 class="error-message">${sessionScope.errorMessage}</h2>
<img class="error-img" src="<c:url value="/img/error2.png"/>" align="center">
</body>
</html>
