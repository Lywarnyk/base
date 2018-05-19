<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popUp.css">
</head>
<body>
<script type="text/javascript">

    function hideWindow(state) {
        document.getElementById('windowUserUpdate').style.display = state;
        document.getElementById('wrapUserUpdate').style.display = state;
    }

    function showUserUpdate(state, id, userLogin, firstName, lastName ) {

        document.getElementById('login').value = userLogin;
        document.getElementById('first').value = firstName;
        document.getElementById('last').value = lastName;
        document.getElementById('userId').value = id;

        document.getElementById('windowUserUpdate').style.display = state;
        document.getElementById('wrapUserUpdate').style.display = state;
    }

</script>
<div onclick="hideWindow('none')" id="wrapUserUpdate"></div>
<div id="windowUserUpdate">
    <img class="close" onclick="hideWindow('none')" src="${pageContext.request.contextPath}/img/close.png">

    <div style="text-align: center;">
        <form action="/controller" method="post">
            <table>
                <tr>
                    <td><fmt:message key="user.label.login"/></td>
                    <td><input type="text" name="login" required="required" id="login" pattern="[A-Za-zА-Яа-яЁё_\-0-9]{4,18}"></td>
                </tr>
                <tr>
                    <td><fmt:message key="user.label.password"/></td>
                    <td><input type="password" name="password" required="required" pattern="[A-Za-z0-9А-Яа-яЁё_\-#$%&*]{4,18}"></td>
                </tr>
                <tr>
                    <td><fmt:message key="users.label.user-firstname"/></td>
                    <td><input required="required" type="text" name="firstName" id="first" pattern="[A-ZА-Я][A-Za-zА-Яа-яЁё_\- ]{3,25}"></td>
                </tr>
                <tr>
                    <td><fmt:message key="users.label.user-lastname"/></td>
                    <td><input required="required" type="text" name="lastName" id="last" pattern="[A-ZА-Я][A-Za-zА-Яа-яЁё_\- ]{3,25}"></td>
                </tr>
                <tr>
                    <td><fmt:message key="users.label.user-role"/></td>
                    <td><input required="required" type="radio" name="role" value="dispatcher"><fmt:message key="users.label.user-role.disp"/><br>
                    <td><input required="required" type="radio" name="role" value="driver"><fmt:message key="users.label.user-role.driver"/></td>
                </tr>
                <tr><td colspan="2" align="center">
                    <input type="hidden" name="userID" value="" id="userId">
                    <button class="myButton" name="command" value="updateUser" id="button"><fmt:message key="button.update"/></button>
                </td></tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
