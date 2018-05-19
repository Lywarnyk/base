<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Routes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main/main.css">
</head>
<body>
<jsp:include page="${requestScope.contextPath}/${sessionScope.startPage}"/>
<c:if test="${empty sessionScope.routes}">
    <h2 class="error-message"><fmt:message key="route.label.empty"/></h2>
</c:if>
<c:if test="${not empty sessionScope.routes}">

    <table class="table">
        <th><a class="sortRef" href="/controller?command=sortRoutesById"><fmt:message key="route.label.id"/><img class="sort" src="${pageContext.request.contextPath}/img/sorting.png"></a></th>
        <th><a class="sortRef" href="/controller?command=sortRoutesByDepartureDate"><fmt:message key="route.label.departure-date"/><img class="sort" src="${pageContext.request.contextPath}/img/sorting.png"></a></th>
        <th><fmt:message key="route.label.arrival-date"/></th>
        <th><fmt:message key="route.label.departure-point"/></th>
        <th><fmt:message key="route.label.destination-point"/></th>
        <th><fmt:message key="route.label.description"/></th>
        <th><a class="sortRef" href="/controller?command=sortRoutesByStatus"><fmt:message key="route.label.status"/><img class="sort" src="${pageContext.request.contextPath}/img/sorting.png"></a></th>
        <c:if test="${sessionScope.user.role=='admin'}">
            <th><fmt:message key="button.update"/></th>
            <th><fmt:message key="button.delete"/></th>
        </c:if>
        <c:if test="${sessionScope.user.role=='driver'}">
            <th><fmt:message key="route.label.required-capacity"/></th>
            <th><fmt:message key="route.label.offer-route"/></th>
        </c:if>

        <c:forEach var="route" items="${sessionScope.routes}">
            <tr>
                <td>${route.id}</td>
                <td>${route.departureDate}</td>
                <td>${route.arrivalDate}</td>
                <td>${route.departurePoint}</td>
                <td>${route.destinationPoint}</td>
                <td>${route.description}</td>
                <td id="status">${route.status}</td>
                <input type="hidden" id="creationDate" value="${route.creationDate}">
                <td hidden="hidden" id="creationDate">${route.creationDate}</td>
                    <%--ADMIN'S PART--%>
                <c:if test="${sessionScope.user.role=='admin'}">
                    <td>
                        <button class="myButton" onclick="showRouteUpdate('block','${route.id}','${route.departureDate}', '${route.arrivalDate}',
                            '${route.departurePoint}', '${route.destinationPoint}','${route.description}')"><fmt:message key="button.update"/></button>
                    </td>
                    <td>
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <button class="myButton" name="command" value="deleteRoute"><fmt:message key="button.delete"/></button>
                            <input type="hidden" name="routeID" value="${route.id}"/>
                        </form>
                    </td>
                </c:if>
                    <%--DRIVER'S PART--%>
                <c:if test="${sessionScope.user.role=='driver'}">
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <td><select name="carParams" required="required">
                            <option value="2"><fmt:message key="car.label.capacity.up2t"/></option>
                            <option value="5"><fmt:message key="car.label.capacity.up5t"/></option>
                            <option value="10"><fmt:message key="car.label.capacity.up10t"/></option>
                            <option value="22"><fmt:message key="car.label.capacity.up22t"/></option>
                            <option value="100"><fmt:message key="car.label.capacity.up100t"/></option>
                            <option></option>
                        </select></td>
                        </td>
                        <td>
                            <input type="hidden" name="routeId" value="${route.id}">
                            <button class="myButton" value="createNewOfferByRouteId" name="command"><fmt:message key="route.button.offer-route"/></button>
                        </td>
                    </form>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <div class="sidebar-left">
        <fmt:message key="route.label.sort"/>
    <form action="/controller" align="center" class="sidebar-left" method="post">
        <button class="myButton" name="command" value="sortRoutesById"><fmt:message key="route.button.sort.id"/></button><br>
        <button class="myButton" name="command" value="sortRoutesByCreationDate"><fmt:message key="route.button.sort.creation-date"/></button><br>
        <button class="myButton" name="command" value="sortRoutesByDepartureDate"><fmt:message key="route.button.sort.departure-date"/></button><br>
        <button class="myButton" name="command" value="sortRoutesByStatus"><fmt:message key="route.button.sort.status"/></button><br>
    </form>

    </div>

</c:if>
<c:if test="${sessionScope.user.role=='admin'}">
    <jsp:include page="${pageContext.request.contextPath}/jsp/popUp/updateRoute.jsp"/>
</c:if>
</body>
</html>
