<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="my" uri="/WEB-INF/custom.tld" %>
<html>
<head>
    <title>Create new Route</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popUp.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main/main.css">
</head>
<body>
<script type="text/javascript">

    function showRouteCreate(state) {

        document.getElementById('windowRouteCreate').style.display = state;
        document.getElementById('wrapRouteCreate').style.display = state;
    }

</script>
<div onclick="showRouteCreate('none')" id="wrapRouteCreate"></div>

<div id="windowRouteCreate">

    <img class="close" onclick="showRouteCreate('none')" src="${pageContext.request.contextPath}/img/close.png">

    <div style="text-align: center;">
        <table>
        <form action="/controller" method="post">
                <tr>
                    <td><fmt:message key="route.label.departure-date"/></td>
                    <td><input type="date" name="departureDate" required min="<my:minDate/>"></td>
                </tr>
                <tr>
                    <td><fmt:message key="route.label.arrival-date"/></td>
                    <td><input type="date" name="arrivalDate" required min="<my:minDate/>"></td>
                </tr>
                <tr>
                    <td><fmt:message key="route.label.departure-point"/></td>
                    <td><input type="text" name="departurePoint" required pattern="[A-ZА-ЯЁ][\wА-Яа-яЁё_- ]{2,30}"></td>
                </tr>
                <tr>
                    <td><fmt:message key="route.label.destination-point"/></td>
                    <td><input type="text" name="destinationPoint" required pattern="[A-ZА-ЯЁ][\wА-Яа-яЁё_- ]{2,30}"></td>
                </tr>
                <tr>
                    <td><fmt:message key="route.label.description"/></td>
                    <td><input type="text" name="description" required pattern="[\wА-Яа-яЁё_- ]{3,30}"></td>
                </tr>
                <tr><td colspan="2" align="center">
                    <button class="myButton" name="command" value="createNewRoute"><fmt:message key="route.button.create"/></button>
                </td></tr>

        </form>
        <form action="/controller?command=uploadFile" method="post" enctype="multipart/form-data">
           <tr><td><p>Or choose file</p></td></tr>
            <tr><td colspan="2"><input type="file" name="file" accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"/>
            <input class="myButton" type="submit" value="Submit"/></td></tr>
        </form>
        </table>
    </div>
</div>
</body>
</html>

