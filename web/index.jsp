<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <title>Login</title>
    <meta content="text/html" charset="UTF-8" >
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main/main.css">
</head>
<body>
<div class="menu">
    <ul class="nav">
        <li class="nav__link">
            <form action="${pageContext.request.contextPath}/changeLocale.jsp">
                <h:language/>
            </form>
        </li>
    </ul>
</div>


<form action="${pageContext.request.contextPath}/login" method="POST">
    <table align="center">
        <tr>
            <td><label for="login"><fmt:message key="user.label.login"/></label></td>
            <td><input id="login" type="text" name="login" required="required" pattern="[A-Za-zА-Яа-яЁё_\-0-9]{4,18}" autofocus/></td>
        </tr>

        <tr>
            <td><label for="pass"><fmt:message key="user.label.password"/></label></td>
            <td><input id="pass" type="password" name="password" required="required" pattern="[A-Za-z0-9А-Яа-яЁё_\-#$%&*]{4,18}"></td>
        </tr>
        <tr>
            <td><button class="myButton" name="command" value="login"><fmt:message key="login"/></button></td>
        </tr>
    </table>
</form>
</body>
</html>