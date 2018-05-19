<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="label.accepted-offers"/></title>
</head>
<body>
<jsp:include page="${sessionScope.contextPath}/${sessionScope.startPage}"/>
<c:if test="${empty sessionScope.acceptedOffers}">
    <h2 class="error-message"><fmt:message key="accepted-offers.label.empty"/></h2>
</c:if>
<c:if test="${not empty sessionScope.acceptedOffers}">
    <form action="/controller">
        <div class="sidebar-left">
        <button class="myButton" style="margin-left: 2px" name="command" value="downloadPDF"><fmt:message key="button.download-report"/> </button>
        </div>
    </form>
    <table class="table">
        <th><fmt:message key="route.label.departure-date"/></th>
        <th><fmt:message key="route.label.arrival-date"/></th>
        <th><fmt:message key="route.label.departure-point"/></th>
        <th><fmt:message key="route.label.destination-point"/></th>
        <th><fmt:message key="route.label.description"/></th>
        <c:if test="${sessionScope.user.role=='admin' || sessionScope.user.role=='dispatcher'}">
            <th><fmt:message key="accepted-offers.label.driver-firstName"/></th>
            <th><fmt:message key="accepted-offers.label.driver-lastName"/></th>
        </c:if>
        <th><fmt:message key="car.label.plate-number"/></th>
        <th><fmt:message key="car.label.model"/></th>
        <th><fmt:message key="route.label.status"/></th>

        <c:if test="${sessionScope.user.role=='admin'}">
            <th><fmt:message key="button.delete"/></th>
        </c:if>
        <c:if test="${sessionScope.user.role=='driver'}">
            <th><fmt:message key="accepted-offers.label.select-car-condition"/></th>
            <th><fmt:message key="button.complete"/></th>
        </c:if>
        <c:forEach var="acceptedOffer" items="${sessionScope.acceptedOffers}">
            <tr>
                <td>${acceptedOffer.route.departureDate}</td>
                <td>${acceptedOffer.route.arrivalDate}</td>
                <td>${acceptedOffer.route.departurePoint}</td>
                <td>${acceptedOffer.route.destinationPoint}</td>
                <td>${acceptedOffer.route.description}</td>
                <c:if test="${sessionScope.user.role=='admin' || sessionScope.user.role=='dispatcher'}">
                    <td>${acceptedOffer.user.firstName}</td>
                    <td>${acceptedOffer.user.lastName}</td>
                </c:if>
                <td>${acceptedOffer.car.plateNumber}</td>
                <td>${acceptedOffer.car.model}</td>
                <td>${acceptedOffer.route.status}</td>
                <c:if test="${sessionScope.user.role=='admin'}">
                    <td>
                        <form action="/controller" method="post">
                            <button name="command" class="myButton" value="deleteAcceptedOffer"><fmt:message key="button.delete"/></button>
                            <input type="hidden" name="routeID" value="${acceptedOffer.route.id}"/>
                        </form>
                    </td>
                </c:if>
                <c:if test="${sessionScope.user.role=='driver'}">
                    <form action="/controller" method="post">
                        <td><select name="carCondition" required="required">
                            <option value="new"><fmt:message key="car.label.condition.new"/></option>
                            <option value="good"><fmt:message key="car.label.condition.good"/></option>
                            <option value="need repair"><fmt:message key="car.label.condition.need-repair"/></option>
                        </select></td>

                        <td><c:if test="${acceptedOffer.route.status =='In progress'}">
                            <button name="command" class="myButton" value="completeAcceptedOffer"><fmt:message key="button.complete"/></button>
                            <input type="hidden" name="routeID" value="${acceptedOffer.route.id}"/></c:if>
                        </td>

                    </form>
                </c:if>

                <%--TASK--%>
                <td>
                    <form action="/controller">
                        <button class="myButton" name="command" value="getRoutesByCarId">Find routes</button>
                        <input type="hidden" name="carId" value="${acceptedOffer.car.id}"/>
                    </form>
                </td>
            </tr>

        </c:forEach>
    </table>
</c:if>
</body>
</html>
