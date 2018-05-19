<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new Car</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popUp.css">
</head>
<body>
<script type="text/javascript">
    function showCarCreate(state) {

        document.getElementById('windowCarCreate').style.display = state;
        document.getElementById('wrapCarCreate').style.display = state;
    }

</script>

<div onclick="showCarCreate('none')" id="wrapCarCreate"></div>

<div id="windowCarCreate">

    <img class="closeCar" onclick="showCarCreate('none')" src="${pageContext.request.contextPath}/img/close.png">

    <div style="text-align: center;">
        <form action="/controller" method="post">
            <table>
                <tr>
                    <td><fmt:message key="car.label.plate-number"/></td>
                    <td><input type="text" name="plateNumber" required pattern="^[A-Z]{0,2}[0-9]{4}[A-Z]{2}$"></td>
                </tr>
                <tr>
                    <td><fmt:message key="car.label.model"/></td>
                    <td><input type="text" name="model" required pattern="[A-ZА-ЯЁ][\dA-Za-zА-Яа-яЁё_\- ]{2,18}"></td>
                </tr>
                <tr>
                    <td><fmt:message key="car.label.capacity"/></td>
                    <td>
                        <select name="capacity" required>
                            <option value="2"><fmt:message key="car.label.capacity.up2t"/></option>
                            <option value="5"><fmt:message key="car.label.capacity.up5t"/></option>
                            <option value="10"><fmt:message key="car.label.capacity.up10t"/></option>
                            <option value="22"><fmt:message key="car.label.capacity.up22t"/></option>
                            <option value="100"><fmt:message key="car.label.capacity.up100t"/></option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><fmt:message key="car.label.condition"/></td>
                    <td>
                        <select name="condition" required>
                            <option value="new"><fmt:message key="car.label.condition.new"/></option>
                            <option value="good"><fmt:message key="car.label.condition.good"/></option>
                            <option value="need repair"><fmt:message key="car.label.condition.need-repair"/></option>
                        </select>
                    </td>
                </tr>
                <tr>
                     <td colspan="2" align="center">
                         <button class="myButton" name="command" value="createCar"><fmt:message key="button.create"/></button>
                     </td>
                 </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>