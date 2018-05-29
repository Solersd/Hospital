<%-- 
    Document   : main
    Created on : 25.02.2018, 12:56:22
    Author     : АРТЁМ
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${currentLocale}"/>
<fmt:bundle basename="localization.messages">
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><fmt:message key="patient.treatments.title"/></title>
            <script>
                <%@include file="/WEB-INF/scripts/increment.js" %>
            </script>
        </head>
        <body>
            <%@include file="/WEB-INF/jspf/header.jspf" %>
            <%@include file="/WEB-INF/jspf/logout.jspf" %>
            <%@include file="/WEB-INF/jspf/patient-menu.jspf" %>
            <div style="margin: auto; width: 400px;">
                <h2 align="center"><fmt:message key="patient.treatments.title"/></h2>
                <br>
                <table width="400" border="1" cellpadding="5" cellspacing="1" >
                    <tr>  
                        <th>№</th>
                        <th><fmt:message key="patient.treatments.start-date"/></th>
                        <th><fmt:message key="patient.treatments.end-date"/></th>
                        <th></th>
                    </tr>
                    <c:forEach items="${treatments}" var="treatment"> 
                        <tr>
                            <th>
                                <script>
                                    document.write(rowNumber());
                                </script>
                            </th>
                            <th><fmt:formatDate pattern = "dd.MM.yyyy" type="date" value="${treatment.startDate}"/></th>
                            <th><fmt:formatDate pattern = "dd.MM.yyyy" type="date" value="${treatment.endDate}"/></th>
                            <th><a href="${pageContext.request.contextPath}/HospitalServlet?command=show-treatment-details-for-patient&treatmentId=${treatment.id}"><fmt:message key="patient.treatments.details"/></a></th>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </body>
    </html>
</fmt:bundle>
