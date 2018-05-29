<%-- 
    Document   : editpatient
    Created on : 07.03.2018, 12:19:32
    Author     : АРТЁМ
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${currentLocale}"/>
<fmt:bundle basename="localization.messages">
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><fmt:message key="doctor.edit.title"/></title>
            <script>
                <%@include file="/WEB-INF/scripts/edit-patient.js"%>
            </script>
            <style>
                <%@include file="/WEB-INF/styles/doctor-edit-patient.css"%>
            </style>
        </head>
        <body>
            <%@include file="/WEB-INF/jspf/header.jspf"%>
            <%@include file="/WEB-INF/jspf/logout.jspf"%>
            <%@include file="/WEB-INF/jspf/doctor-menu.jspf"%>
            <div style="width: 200px; margin: auto;">
                <h2 align="center"><fmt:message key="doctor.edit.title"/></h2>
                <form id="form" method="POST" action="${pageContext.request.contextPath}/HospitalServlet?command=edit-patient">
                    <input type="hidden" name="patientId" value="${patient.id}">
                    <fmt:message key="doctor.edit.name"/>
                    <br/>
                    <input type="text" name="name" value="${patient.name}"/>
                    <br/>
                    <br/>
                    <fmt:message key="doctor.edit.patronymic"/>
                    <br/>
                    <input type="text" name="patronymic" value="${patient.patronymic}"/>
                    <br/>
                    <br/>
                    <fmt:message key="doctor.edit.surname"/>
                    <br/>
                    <input type="text" name="surname" value="${patient.surname}"/>
                    <br/>
                    <br/>
                    <fmt:message key="doctor.edit.date-of-birth"/>
                    <br/>
                    <input type="text" name="dateOfBirth" value="<fmt:formatDate pattern = "dd.MM.yyyy" type="date" value="${patient.dateOfBirth}"/>"/>
                    <br/>
                    <br/>
                    <input type="submit" onClick="return formCheck('<fmt:message key="doctor.edit.name-fill"/>','<fmt:message key="doctor.edit.patronymic-fill"/>','<fmt:message key="doctor.edit.surname-fill"/>','<fmt:message key="doctor.edit.date-of-birth-fill"/>')" value="<fmt:message key="doctor.edit.edit"/>"/>
                    <input style="margin-left: 20px;" type="submit" form="Cancel" value="<fmt:message key="doctor.edit.cancel"/>"/>
                </form>
                <form id="Cancel" method="POST" action="${pageContext.request.contextPath}/HospitalServlet?command=view-doctor-main-page"></form>    
            </div>
        </body>
    </html>
</fmt:bundle>