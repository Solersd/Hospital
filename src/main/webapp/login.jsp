<%-- 
    Document   : login
    Created on : 25.02.2018, 12:37:45
    Author     : АРТЁМ
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${currentLocale}" />
<fmt:bundle basename="localization.messages">
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><fmt:message key="login.title"/></title>
        </head>
        <body>
            <%@include file="/WEB-INF/jspf/header.jspf" %>
            <br/>
        <center>
            <h3><fmt:message key="login.entry-form"/></h3>        
            <form name="SignIn" method="POST" action="<c:url value="/HospitalServlet?command=login"/>">
                <fmt:message key="login.enter-login"/>
                <br/>
                <input type="text" name="login"/>
                <br/>
                <fmt:message key="login.enter-password"/>
                <br/>
                <input type="password" name="password"/>
                <br/>
                <br/>
                <input type="submit" value="<fmt:message key="login.enter"/>"/>
            </form>
        </center>
    </body>
</html>
</fmt:bundle>