<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Create new Route</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popUp.css">
</head>
<body>
<script type="text/javascript">

    function hideCarUpdate(state) {

        document.getElementById('windowCarUpdate').style.display = state;
        document.getElementById('wrapCarUpdate').style.display = state;
    }
    function showCarUpdate(state, id, carModel, plateNumber) {

        document.getElementById('windowCarUpdate').style.display = state;
        document.getElementById('wrapCarUpdate').style.display = state;

        document.getElementById('model').value = carModel;
        document.getElementById('plate').value = plateNumber;
        document.getElementById('carId').value = id;
    }

</script>
<div onclick="hideCarUpdate('none')" id="wrapCarUpdate"></div>
<div id="windowCarUpdate">
    <img class="closeCar" onclick="hideCarUpdate('none')" src="../../img/close.png">
    <div style="text-align: center;">
        <form action="/controller" method="post">
            <table>
                <tr>
                    <td><fmt:message key="car.label.plate-number"/></td>
                    <td><input type="text" name="plateNumber" required pattern="^[A-Z]{0,2}[0-9]{4}[A-Z]{2}$" id="plate"/></td></tr>
                <tr>
                    <td><fmt:message key="car.label.model"/></td>
                    <td><input type="text" name="model" required pattern="[А-ЯЁA-Z][\dA-Za-zА-Яа-яЁё_\- ]{2,18}" id="model"></td>
                </tr>
                <tr>
                    <td><fmt:message key="car.label.capacity"/></td>
                    <td>
                        <select name="capacity" required="required">
                            <option value="2"><fmt:message key="car.label.capacity.up2t"/></option>
                            <option value="5"><fmt:message key="car.label.capacity.up5t"/></option>
                            <option value="10"><fmt:message key="car.label.capacity.up10t"/></option>
                            <option value="22"><fmt:message key="car.label.capacity.up22t"/></option>
                            <option value="100"><fmt:message key="car.label.capacity.up100t"/></option>
                            <option></option>
                        </select></td>
                </tr>
                <tr>
                    <td><fmt:message key="car.label.condition"/></td>
                    <td><select name="condition" required="required">
                        <option  value="new"><fmt:message key="car.label.condition.new"/></option>
                        <option value="good"><fmt:message key="car.label.condition.good"/></option>
                        <option value="need repair"><fmt:message key="car.label.condition.need-repair"/></option>
                    </select></td>

                </tr>
                <tr>
                    <td align="center">
                        <input type="hidden" name="carId" id="carId"/>
                        <button class="myButton" name="command" value="updateCar"><fmt:message key="button.update"/></button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>

</body>
</html>