<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Owner
  Date: 05.02.2018
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task</title>
</head>
<body>
<jsp:include page="/jsp/startPages/adminPage.jsp"/>

<p>Routes of car ${requestScope.car.plateNumber} ${requestScope.car.model}</p>

<c:if test="${empty requestScope.routes}">
    <p>There is no Route</p>
</c:if>
<c:if test="${not empty requestScope.routes}">
<table class="table">
    <th><fmt:message key="route.label.id"/></th>
    <th><fmt:message key="route.label.departure-date"/></th>
    <th><fmt:message key="route.label.arrival-date"/></th>
    <th><fmt:message key="route.label.departure-point"/></th>
    <th><fmt:message key="route.label.destination-point"/></th>
    <th><fmt:message key="route.label.description"/></th>
    <th><fmt:message key="route.label.status"/></th>

    <c:forEach var="route" items="${requestScope.routes}">
    <tr>
        <td>${route.id}</td>
        <td>${route.departureDate}</td>
        <td>${route.arrivalDate}</td>
        <td>${route.departurePoint}</td>
        <td>${route.destinationPoint}</td>
        <td>${route.description}</td>
        <td>${route.status}</td>

    </tr>
    </c:forEach>
</table>
</c:if>


</body>
</html>
