<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="${requestScope.contextPath}/${sessionScope.startPage}"/>
<img src="${pageContext.request.contextPath}/img/Welcome.png" class="welcome-img"/>

