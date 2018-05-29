<%-- 
    Document   : prescribetreatment
    Created on : 07.03.2018, 16:31:29
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
            <title><fmt:message key="doctor.treatment.new-treatment-title"/></title>
            <script>
                <%@include file="/WEB-INF/scripts/new-treatment.js"%>
            </script>
        </head>
        <body>
            <%@include file="/WEB-INF/jspf/header.jspf"%>
            <%@include file="/WEB-INF/jspf/logout.jspf"%>
            <%@include file="/WEB-INF/jspf/doctor-menu.jspf"%>
            <form style="width: 740px; margin: auto;" method="POST" action="${pageContext.request.contextPath}/HospitalServlet?command=prescribe-treatment">
                <input type="hidden" name="patientId" value="${patientId}">
                <input id="drugCount" type="hidden" name="drugCount" value="0">
                <h2 align="center"><fmt:message key="doctor.treatment.new-treatment-title"/></h2>
                <fmt:message key="doctor.treatment.complaints"/>
                <br/>
                <textarea rows="10" cols="99" name="complaint"></textarea>
                <br/>
                <br/>
                <fmt:message key="doctor.treatment.diagnosis"/>
                <br/>
                <textarea rows="10" cols="99" name="diagnosis"></textarea>
                <br/>
                <br/>
                <input type="button" value="<fmt:message key="doctor.treatment.add-prescription"/>" onclick="addRow()"/>
                <br/>
                <br/>
                <table  id="prescriptionTable" border="1" cellpadding="5" cellspacing="1" >
                    <tr>    
                        <th>№</th>
                        <th><fmt:message key="doctor.treatment.drug"/></th>
                        <th><fmt:message key="doctor.treatment.dosage"/></th>
                        <th><fmt:message key="doctor.treatment.count"/></th>
                        <th><fmt:message key="doctor.treatment.comment"/></th>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>
                            <select id="select" style="width: 100px;" name="drug0" size="1">
                                <c:forEach items="${drugs}" var="drug"> 
                                    <option name="option" value="${drug.id}">${drug.drugName}</option>
                                </c:forEach>
                            </select> 
                        </td>
                        <td><input type="text" name="dosage0"></td>
                        <td><input type="text" name="count0"></td>
                        <td><textarea rows="2" cols="30" name="comment0"></textarea></td>
                    </tr>
                </table>
                <br/>
                <input type="submit" value="<fmt:message key="doctor.treatment.prescribe-treatment"/>" onclick="return formCheck(this.form)"/>
                <input style="margin-left: 20px;" type="submit" form="Cancel" value="<fmt:message key="doctor.treatment.cancel"/>"/>
            </form>
            <form id="Cancel" method="POST" action="${pageContext.request.contextPath}/HospitalServlet?command=show-treatments&patientId=${patientId}"></form>
        </body>
    </html>
</fmt:bundle>
