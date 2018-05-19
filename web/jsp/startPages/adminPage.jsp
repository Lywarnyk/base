<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="my" uri="/WEB-INF/custom.tld" %>
<%@taglib prefix="h" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Admin Start Page</title>
    <meta content="text/html" charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main/main.css">
</head>
<body>
<div class="menu">
        <ul class="nav">
            <form action="/controller">
                <li class="nav__link"><button class="button" name="command" value="getAllUsers"><fmt:message key="adminPage.users"/></button></li>
                <li class="nav__link"><button class="button" name="command" value="getAllCars"><fmt:message key="adminPage.cars"/></button></li></li>
                <li class="nav__link"><button class="button" name="command" value="getAllRoutes"><fmt:message key="adminPage.routes"/></button></li>
                <li class="nav__link"><button class="button" name="command" value="getAllOffers"><fmt:message key="adminPage.offers"/></button></li>
                <li class="nav__link"><button class="button" name="command" value="getAllAcceptedOffers"><fmt:message key="label.accepted-offers"/></button></li>
                <li class="nav__link"><a onclick="showUserCreate('block')"><fmt:message key="create-user"/></a></li>
                <li class="nav__link"><a onclick="showCarCreate('block')"><fmt:message key="button.create-car"/></a></li>
                <li class="nav__link"><a onclick="showRouteCreate('block')"><fmt:message key="create-route"/></a></li>
                <li class="nav__link">
                   <h:language/>
                </li>
                <li class="nav__link"><button class="button" name="command" value="exit"><fmt:message key="exit"/></button></li>
            </form>
            <li><my:signed/></li>
        </ul>
</div>


<jsp:include page="${pageContext.request.contextPath}/jsp/popUp/createCar.jsp"/>
<jsp:include page="${pageContext.request.contextPath}/jsp/popUp/createUser.jsp"/>
<jsp:include page="${pageContext.request.contextPath}/jsp/popUp/createRoute.jsp"/>
</body>
</html>
