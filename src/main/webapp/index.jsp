<%-- 
    Document   : index
    Created on : 25.02.2018, 16:01:18
    Author     : АРТЁМ
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <body>
    <c:if test="${role == null}">
        <jsp:forward page="login.jsp"/>
    </c:if>
    <jsp:useBean id="roleBean" class="xyz.prodes.hospital.constants.Role" scope="page"></jsp:useBean>
    <c:set var="doctor" scope="page" value="${roleBean.doctor}"></c:set>
    <c:set var="patient" scope="page" value="${roleBean.patient}"></c:set>
    <c:if test="${role == doctor}">
        <jsp:forward page="/HospitalServlet?command=view-doctor-main-page"/>
    </c:if>
    <c:if test="${role == patient}">
        <jsp:forward page="/HospitalServlet?command=show-patient-main-page"/>
    </c:if>
    </body>
</html>
