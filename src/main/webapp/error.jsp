<%-- 
    Document   : errorpage
    Created on : 04.03.2018, 16:21:20
    Author     : АРТЁМ
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${currentLocale}" />
<fmt:bundle basename="localization.messages">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="error.error"/></title>
    </head>
    <body>
    <%@include file="/WEB-INF/jspf/header.jspf" %>
</fmt:bundle>
<fmt:bundle basename="localization.errors">
        <h1 align="center"><fmt:message key="${exception.error}"/></h1>
</fmt:bundle>
    </body>
</html>
