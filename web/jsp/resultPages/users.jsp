<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>All Users</title>
</head>
<body>
<jsp:include page="/jsp/startPages/adminPage.jsp"/>

<c:if test="${not empty requestScope.users}">
    <table class="table">
        <th><fmt:message key="users.label.user-role"/></th>
        <th><fmt:message key="users.label.user-id"/></th>
        <th><fmt:message key="users.label.user-firstname"/></th>
        <th><fmt:message key="users.label.user-lastname"/></th>
        <th><fmt:message key="users.user-update"/></th>
        <th><fmt:message key="users.user-delete"/></th>
        <c:forEach var="user" items="${requestScope.users}">
            <tr>
                <td>${user.role}</td>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>
                    <button class="myButton"
                            onclick="showUserUpdate('block','${user.id}', '${user.login}', '${user.firstName}', '${user.lastName}')">
                        <fmt:message key="users.user-update"/></button>
                </td>

                <td>
                    <form action="/controller" method="post">
                        <button class="myButton" name="command" value="deleteUserById"><fmt:message
                                key="users.user-delete"/></button>
                        <input type="hidden" name="userID" value="${user.id}"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <jsp:include page="${pageContext.request.contextPath}/jsp/popUp/updateUser.jsp"/>
</c:if>
</body>
</html>
