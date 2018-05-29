<%-- 
    Document   : treatments
    Created on : 24.03.2018, 23:50:29
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
            <title><fmt:message key="doctor.treatments.title"/></title>
            <script>
                <%@include file="/WEB-INF/scripts/increment.js" %>
            </script>
        </head>
        <body>
            <%@include file="/WEB-INF/jspf/header.jspf"%>
            <%@include file="/WEB-INF/jspf/logout.jspf"%>
            <%@include file="/WEB-INF/jspf/doctor-menu.jspf"%>
            <div style="margin: auto; width: 400px;">
                <form method="POST" action="${pageContext.request.contextPath}/HospitalServlet?command=show-new-treatment">
                    <span style="font-size: 24px; font-weight: bold;"><fmt:message key="doctor.treatments.title"/></span> <input Style="float: right; width: 110px;" type="submit" value="<fmt:message key="doctor.treatments.new"/>"/>
                    <input type="hidden" name="patientId" value="${patientId}">
                </form>
                <br/>
                <table width="400" border="1" cellpadding="5" cellspacing="1">
                    <tr>  
                        <th>№</th>
                        <th><fmt:message key="doctor.treatments.start-date"/></th>
                        <th><fmt:message key="doctor.treatments.end-date"/></th>
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
                            <th><a href="${pageContext.request.contextPath}/HospitalServlet?command=show-treatment-details&treatmentId=${treatment.id}&patientId=${patientId}"><fmt:message key="doctor.treatments.details"/></a></th>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </body>
    </html>
</fmt:bundle>
