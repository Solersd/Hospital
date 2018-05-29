<%-- 
    Document   : main
    Created on : 25.02.2018, 12:50:59
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
            <title><fmt:message key="doctor.main.title"/></title>
            <style>
                <%@include file="/WEB-INF/styles/doctor-main.css"%>
            </style>
        </head>
        <body>
            <%@include file="/WEB-INF/jspf/header.jspf"%>
            <%@include file="/WEB-INF/jspf/logout.jspf"%>
            <%@include file="/WEB-INF/jspf/doctor-menu.jspf"%>
            <div style="margin: auto; width: 800px;">
                <form method="POST" action="${pageContext.request.contextPath}/HospitalServlet?command=filter-patient">
                    <div style="float: left; width: 120px;">
                        <fmt:message key="doctor.main.name"/>
                        <input class="input_width" type="text" name="name">
                    </div>
                    <div class="div_style">
                        <fmt:message key="doctor.main.patronymic"/>
                        <input class="input_width" type="text" name="patronymic">
                    </div>
                    <div class="div_style">
                        <fmt:message key="doctor.main.surname"/>
                        <input class="input_width" type="text" name="surname">
                    </div>
                    <div class="div_style">
                        <fmt:message key="doctor.main.date-of-birth"/>
                        <input class="input_width" type="text" name="dateOfBirth" placeholder="<fmt:message key="doctor.main.date-of-birth.hint"/>">
                    </div>
                    <br/>
                    <br/>
                    <br/>
                    <input type="submit" value="<fmt:message key="doctor.main.filter"/>"/>
                </form>
                <br/>
                <table width="800" border="1" cellpadding="5" cellspacing="1" >
                    <tr>
                        <th><fmt:message key="doctor.main.name"/></th>    
                        <th><fmt:message key="doctor.main.patronymic"/></th>
                        <th><fmt:message key="doctor.main.surname"/></th>
                        <th><fmt:message key="doctor.main.date-of-birth"/></th>
                        <th><fmt:message key="doctor.main.doctor"/></th>
                        <th colspan="2"></th>
                    </tr>
                    <c:forEach items="${patients}" var="patient" >
                        <tr>
                            <td>${patient.name}</td>  
                            <td>${patient.patronymic}</td>
                            <td>${patient.surname}</td>                
                            <td><fmt:formatDate pattern = "dd.MM.yyyy" type="date" value="${patient.dateOfBirth}"/></td>
                            <td>${patient.doctor.surname} ${patient.doctor.name}</td>
                            <td><a href="${pageContext.request.contextPath}/HospitalServlet?command=show-edit-patient-page&patientId=${patient.id}"><fmt:message key="doctor.main.edit"/></a></td>
                            <td><a href="${pageContext.request.contextPath}/HospitalServlet?command=show-treatments&patientId=${patient.id}"><fmt:message key="doctor.main.view-treatments"/></a></td>
                        </tr>            
                    </c:forEach>
                </table>
            </div>
        </body>
    </html>
</fmt:bundle>
