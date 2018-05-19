<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Choose the car</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main/main.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/${sessionScope.startPage}"/>
<form action="/controller">
    <input type="hidden" name="offerID" value="${requestScope.offerId}"/>
    <input type="hidden" name="userID" value="${requestScope.userID}"/>
    <input type="hidden" name="userLastName" value="${requestScope.userLastName}"/>
    <input type="hidden" name="carParams" value="${requestScope.carParams}"/>
    <c:if test="${empty requestScope.freeCars}">
        <h2 class="error-message"><fmt:message key="chooseCar.label.empty"/></h2>
    </c:if>
    <c:if test="${not empty requestScope.freeCars}">
        <table class="table">
            <th><fmt:message key="car.label.plate-number"/></th>
            <th><fmt:message key="car.label.model"/></th>
            <th><fmt:message key="car.label.capacity"/></th>
            <th><fmt:message key="car.label.condition"/></th>
            <th><fmt:message key="chooseCar.label.choose"/></th>
            <c:forEach var="car" items="${requestScope.freeCars}">
            <tr>
                <td>${car.plateNumber}</td>
                <td>${car.model}</td>
                <td>${car.loadCapacity}</td>
                <td>${car.condition}</td>
                <td>
                    <form action="/controller" method="post">
                        <input type="hidden" name="carId" value="${car.id}">
                        <input type="hidden" name="offerId" value="${requestScope.offerId}">
                        <button class="myButton" name="command" value="acceptOffer"><fmt:message key="chooseCar.label.accept"/></button>
                    </form>
                </td>
                </c:forEach>
        </table>
    </c:if>
</form>
</body>
</html>
