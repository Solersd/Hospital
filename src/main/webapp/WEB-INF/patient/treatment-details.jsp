<%-- 
    Document   : treatment-details
    Created on : 07.04.2018, 22:27:40
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
            <title><fmt:message key="patient.treatment.treatment-details-title"/></title>
            <script>
                <%@include file="/WEB-INF/scripts/increment.js" %>
            </script>
        </head>
        <body>
            <%@include file="/WEB-INF/jspf/header.jspf"%>
            <%@include file="/WEB-INF/jspf/logout.jspf"%>
            <%@include file="/WEB-INF/jspf/patient-menu.jspf"%>
            <form style="width: 740px; margin: auto;" method="POST" action="${pageContext.request.contextPath}/HospitalServlet?command=show-patient-main-page">
                <h2 align="center"><fmt:message key="patient.treatment.treatment-details-title"/></h2>
                <fmt:message key="patient.treatment.complaints"/>:
                <br/>
                <textarea rows="10" cols="99" name="complaint" disabled="">${treatment.complaint}</textarea>
                <br/>
                <br/>
                <fmt:message key="patient.treatment.diagnosis"/>:
                <br/>
                <textarea rows="10" cols="99" name="diagnosis" disabled="">${treatment.diagnosis}</textarea>
                <br/>
                <br/>
                <table border="1" cellpadding="5" cellspacing="1" >
                    <tr>    
                        <th>№</th>
                        <th><fmt:message key="patient.treatment.drug"/></th>
                        <th><fmt:message key="patient.treatment.dosage"/></th>
                        <th><fmt:message key="patient.treatment.count"/></th>
                        <th><fmt:message key="patient.treatment.comment"/></th>
                    </tr>
                    <c:forEach items="${prescriptions}" var="prescription">
                        <tr>
                            <td>
                                <script>
                                    document.write(rowNumber());
                                </script>
                                <input type="hidden" value="${prescription.id}">
                            </td>
                            <td>
                                <select style="width: 100px;" size="1" disabled="">
                                    <c:set  var="selectedOption" scope="page" value="${prescription.drug.id}" />
                                    <option name="option" value="${prescription.drug.id}">${prescription.drug.drugName}</option>
                                    <c:forEach items="${drugs}" var="drug"> 
                                        <c:if test="${selectedOption != drug.id}">
                                            <option name="option" value="${drug.id}">${drug.drugName}</option>
                                        </c:if>
                                    </c:forEach>
                                </select> 
                            </td>
                            <td><input type="text" value="${prescription.dosage}" disabled=""></td>
                            <td><input type="text" value="${prescription.count}" disabled=""></td>
                            <td><textarea rows="2" cols="30" disabled="">${prescription.comment}</textarea></td>
                        </tr>
                    </c:forEach>
                </table>
                <br/>
                <input type="submit" value="<fmt:message key="patient.treatment.ok"/>"/>
            </form>
        </body>
    </html>
</fmt:bundle>
