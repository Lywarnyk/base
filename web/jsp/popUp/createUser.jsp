<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new User</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popUp.css">
</head>
<body>
<script type="text/javascript">

    function showUserCreate(state) {

        document.getElementById('windowUserCreate').style.display = state;
        document.getElementById('wrapUserCreate').style.display = state;
    }
</script>

<div onclick="showUserCreate('none')" id="wrapUserCreate"></div>
<div id="windowUserCreate">
    <img class="close" onclick="showUserCreate('none')" src="${pageContext.request.contextPath}/img/close.png">

    <div style="text-align: center;">
        <form action="/controller" method="post" name="user-form">
            <table>
                <tr>
                    <td><fmt:message key="user.label.login"/></td>
                    <td><input type="text" name="login" required="required" pattern="[A-Za-zА-Яа-яЁё_\-0-9]{4,18}"></td>
                </tr>
                <tr>
                    <td><fmt:message key="user.label.password"/></td>
                    <td><input type="password" name="password" required="required" pattern="[A-Za-z0-9А-Яа-яЁё_\-#$%&*]{4,18}"></td>
                </tr>
                <tr>
                    <td><fmt:message key="users.label.user-firstname"/></td>
                    <td><input required="required" type="text" name="firstName" pattern="[A-ZА-Я][A-Za-zА-Яа-яЁё_\- ]{3,25}"></td>
                </tr>
                <tr>
                    <td><fmt:message key="users.label.user-lastname"/></td>
                    <td><input required="required" type="text" name="lastName" pattern="[A-ZА-Я][A-Za-zА-Яа-яЁё_\- ]{3,25}"></td>
                </tr>
                <tr>
                    <td><fmt:message key="users.label.user-role"/></td>
                    <td><input required="required" type="radio" name="role" value="dispatcher"><fmt:message key="users.label.role.daspatcher"/><br>
                    <td><input required="required" type="radio" name="role" value="driver"><fmt:message key="users.label.role.driver"/></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <button class="myButton" name="command" value="createNewUser"><fmt:message key="button.create"/></button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
