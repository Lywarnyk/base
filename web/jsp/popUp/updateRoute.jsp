<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="my" uri="/WEB-INF/custom.tld" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popUp.css">
</head>
<body>
<script type="text/javascript">

    function showRouteUpdate(state, id, departureDate, arrivalDate, departurePoint, destinationPoint, description) {

        document.getElementById('departureDate').value = departureDate;
        document.getElementById('arrivalDate').value = arrivalDate;
        document.getElementById('departurePoint').value = departurePoint;
        document.getElementById('destinationPoint').value = destinationPoint;
        document.getElementById('description').value = description;
        document.getElementById('routeId').value = id;

        document.getElementById('windowRouteUpdate').style.display = state;
        document.getElementById('wrapRouteUpdate').style.display = state;
    }

    function hideRouteUpdate(state) {

        document.getElementById('windowRouteUpdate').style.display = state;
        document.getElementById('wrapRouteUpdate').style.display = state;
    }

</script>

<div onclick="hideRouteUpdate('none')" id="wrapRouteUpdate"></div>

<div id="windowRouteUpdate">

    <img class="close" onclick="hideRouteUpdate('none')" src=${pageContext.request.contextPath}/img/close.png>
    <div style="text-align: center;">

        <form action="/controller" method="post">
            <table class="popUpTable">
                <tr>
                    <td><fmt:message key="route.label.departure-date"/></td>
                    <td><input type="date" name="departureDate" required min="<my:minDate/>"
                               id="departureDate"></td>
                </tr>
                <tr>
                    <td><fmt:message key="route.label.arrival-date"/></td>
                    <td><input type="date" name="arrivalDate" required min="<my:minDate/>"
                               id="arrivalDate"></td>
                </tr>
                <tr>
                    <td><fmt:message key="route.label.departure-point"/></td>
                    <td><input type="text" name="departurePoint" required pattern="[A-ZА-ЯЁ][\wА-Яа-яЁё_- ]{2,30}" id="departurePoint"></td>
                </tr>
                <tr>
                    <td><fmt:message key="route.label.destination-point"/></td>
                    <td><input type="text" name="destinationPoint" required pattern="[A-ZА-ЯЁ][\wА-Яа-яЁё_- ]{2,30}" id="destinationPoint"></td>
                </tr>
                <tr>
                    <td><fmt:message key="route.label.description"/></td>
                    <td><input type="text" name="description" required pattern="[\wА-Яа-яЁё_- ]{3,30}" id="description"></td>
                </tr>
                <tr>
                    <td><fmt:message key="route.label.status"/></td>
                    <td>
                        <select name="status" required="required">
                            <option value="Open">Open</option>
                            <option value="In progress">In progress</option>
                            <option value="Canceled">Canceled</option>
                            <option value="Completed">Completed</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="hidden" name="routeId" id="routeId">
                        <button class="myButton" name="command" value="updateRoute"><fmt:message key="button.update"/> </button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>

</body>
</html>