<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<title>Cars</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main/main.css">
<jsp:include page="/jsp/startPages/adminPage.jsp"/>

<c:if test="${empty requestScope.cars}">
    <h2 class="error-message"><fmt:message key="cars.label.empty"/></h2>
</c:if>
<c:if test="${not empty requestScope.cars}">
    <p>
    <table class="table">
        <th><fmt:message key="car.label.plate-number"/></th>
        <th><fmt:message key="car.label.model"/></th>
        <th><fmt:message key="car.label.capacity"/></th>
        <th><fmt:message key="car.label.condition"/></th>
        <th><fmt:message key="button.update"/></th>
        <th><fmt:message key="button.delete"/></th>
        <th>Find routes</th>
        <c:forEach var="car" items="${requestScope.cars}">
            <tr>
                <td>${car.plateNumber}</td>
                <td>${car.model}</td>
                <td>${car.loadCapacity}</td>
                <td>${car.condition}</td>
                <td>
                    <button class="myButton" onclick="showCarUpdate('block', '${car.id}', '${car.model}', '${car.plateNumber}')"><fmt:message key="button.update"/></button>
                </td>
                <td>
                    <form action="/controller" method="post">
                        <button class="myButton" name="command" value="deleteCarById"><fmt:message key="button.delete"/></button>
                        <input type="hidden" name="carId" value="${car.id}"/>
                    </form>
                </td>

                <%--TASK--%>
                <td>
                    <form action="/controller">
                        <button class="myButton" name="command" value="getRoutesByCarId">Find routes</button>
                        <input type="hidden" name="carId" value="${car.id}"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    </p>
    <jsp:include page="${pageContext.request.contextPath}/jsp/popUp/updateCar.jsp"/>
</c:if>
