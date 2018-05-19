<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="my" uri="/WEB-INF/custom.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <title>Dispatcher Start Page</title>
    <meta content="text/html" charset="UTF-8" >
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main/main.css">
</head>
<body>
<div class="menu">
    <ul class="nav">
        <form action="/controller">
            <li class="nav__link"><a onclick="showRouteCreate('block')"><fmt:message key="route.button.create"/></a></li>
            <li class="nav__link"><button class="button" name="command" value="getAllRoutes"><fmt:message key="adminPage.routes"/></button></li>
            <li class="nav__link"><button class="button" name="command" value="getAllOffers"><fmt:message key="adminPage.offers"/></button></li>
            <li class="nav__link"><button class="button" name="command" value="getAllAcceptedOffers"><fmt:message key="label.accepted-offers"/></button></li>
            <li class="nav__link">
                <h:language/>
            </li>
            <li class="nav__link"><button class="button" name="command" value="exit"><fmt:message key="exit"/></button></li>
        </form>
        <li><my:signed/></li>
    </ul>
</div>

<jsp:include page="${pageContext.request.contextPath}/jsp/popUp/createRoute.jsp"/>
</body>
</html>
