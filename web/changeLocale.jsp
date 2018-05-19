<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- set the locale --%>
<fmt:setLocale value="${param.locale}" scope="session"/>
<c:set scope="session" var="locale" value="${param.locale}"/>

<%-- load the bundle (by locale) --%>
<fmt:setBundle basename="resource"/>

<%-- goto back to the previous page--%>
<c:redirect url="${header.referer}"/>
