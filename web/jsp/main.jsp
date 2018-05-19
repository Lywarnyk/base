<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Owner
  Date: 09.01.2018
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Start Page</title>
    <meta content="text/html" charset="UTF-8">
</head>
<body>
<div class="header">

    <div class="menu">
        <ul class="nav">
            <c:if test="${empty sessionScope.user}">
                <li class="nav__link"><a href="${requestScope.contextPath}/index.jsp">log in</a></li>
            </c:if>
            <c:if test="${not empty sessionScope.user}">
                <li class="nav__link"><a href="${requestScope.contextPath}/jsp/welcome.jsp">Main</a></li>
            </c:if>
            <li class="nav__link">
                <form action="${pageContext.request.contextPath}/changeLocale.jsp">
                <a href="${pageContext.request.contextPath}/changeLocale.jsp?locale=ru">Рус</a>
                <a href="${pageContext.request.contextPath}/changeLocale.jsp?locale=en">Eng</a>
                </form>
            </li>
            <c:if test="${not empty sessionScope.user}">
                <li class="nav__link"><a href="${pageContext.request.contextPath}/controller?command=exit">Exit</a></li>
            </c:if>
        </ul>
        <c:if test="${not empty sessionScope.user}">
            Signed in as ${sessionScope.user.role} ${sessionScope.user.firstName} ${sessionScope.user.lastName}
        </c:if>
    </div>
</div>
</body>
</html>
