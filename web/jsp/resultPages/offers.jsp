<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Offers</title>
</head>
<body>
<jsp:include page="${requestScope.contextPath}/${sessionScope.startPage}"/>
<c:if test="${not empty requestScope.offers}">
    <table class="table" style="margin-left: auto; margin-right: auto">
        <th><fmt:message key="route.label.departure-date"/></th>
        <th><fmt:message key="route.label.arrival-date"/></th>
        <th><fmt:message key="route.label.departure-point"/></th>
        <th><fmt:message key="route.label.destination-point"/></th>
        <th><fmt:message key="route.label.description"/></th>
        <c:if test="${sessionScope.user.role!='driver'}">
            <th><fmt:message key="accepted-offers.label.driver-firstName"/></th>
            <th><fmt:message key="accepted-offers.label.driver-lastName"/></th>
        </c:if>
        <th><fmt:message key="offers.label.car-params"/></th>
        <c:if test="${sessionScope.user.role=='admin'||sessionScope.user.role=='dispatcher'}">
            <th><fmt:message key="chooseCar.button.choose"/></th>
            <c:if test="${sessionScope.user.role=='admin'}">
                <th><fmt:message key="button.delete"/></th>
            </c:if>
        </c:if>

        <c:forEach var="offer" items="${requestScope.offers}">
            <tr>
                <td>${offer.route.departureDate}</td>
                <td>${offer.route.arrivalDate}</td>
                <td>${offer.route.departurePoint}</td>
                <td>${offer.route.destinationPoint}</td>
                <td>${offer.route.description}</td>
                <c:if test="${sessionScope.user.role!='driver'}">
                    <td>${offer.user.firstName}</td>
                    <td>${offer.user.lastName}</td>
                </c:if>
                <td>${offer.carParams}</td>
                <c:if test="${sessionScope.user.role=='admin' or sessionScope.user.role=='dispatcher'}">
                    <td>
                        <form action="/controller" method="get">
                            <button class="myButton" type="submit" name="command" value="chooseCar"><fmt:message
                                    key="chooseCar.button.choose"/></button>
                            <input type="hidden" name="offerID" value="${offer.offerID}"/>
                            <input type="hidden" name="userID" value="${offer.user.id}"/>
                            <input type="hidden" name="userLastName" value="${offer.user.lastName}"/>
                            <input type="hidden" name="carParams" value="${offer.carParams}"/>
                        </form>
                    </td>
                    <c:if test="${sessionScope.user.role=='admin'}">
                        <td>
                            <form action="/controller" method="post">
                                <button class="myButton" name="command" value="deleteOffer"><fmt:message
                                        key="button.delete"/></button>
                                <input type="hidden" name="offerID" value="${offer.offerID}"/>
                            </form>
                        </td>
                    </c:if>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${empty requestScope.offers}">
    <h2 class="error-message"><fmt:message key="offers.label.empty"/></h2>
</c:if>
</body>
</html>