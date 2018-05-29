<%-- 
    Document   : treatmentdetail
    Created on : 25.03.2018, 19:31:54
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
            <title><fmt:message key="doctor.treatment.treatment-details-title"/></title>
            <script>
                <%@include file="/WEB-INF/scripts/treatment-details.js" %>
            </script>
        </head>
        <body>
            <%@include file="/WEB-INF/jspf/header.jspf"%>
            <%@include file="/WEB-INF/jspf/logout.jspf"%>
            <%@include file="/WEB-INF/jspf/doctor-menu.jspf"%>
            <form style="width: 740px; margin: auto;" method="POST" action="${pageContext.request.contextPath}/HospitalServlet?command=edit-treatment&patientId=${patientId}">
                <input type="hidden" name="patientId" value="${patientId}">
                <input type="hidden" name="treatmentId" value="${treatment.id}">
                <input id="drugCount" type="hidden" name="drugCount">
                <h2 align="center"><fmt:message key="doctor.treatment.treatment-details-title"/></h2>
                <fmt:message key="doctor.treatment.complaints"/>
                <br/>
                <textarea id="complaint" rows="10" cols="99" name="complaint">${treatment.complaint}</textarea>
                <br/>
                <br/>
                <fmt:message key="doctor.treatment.diagnosis"/>
                <br/>
                <textarea id="diagnosis" rows="10" cols="99" name="diagnosis">${treatment.diagnosis}</textarea>
                <br/>
                <br/>
                <input id="addPrescription" type="button" value="<fmt:message key="doctor.treatment.add-prescription"/>" onclick="addRow()"/>
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
                    <c:forEach items="${prescriptions}" var="prescription">
                        <tr>
                            <td>
                                <script>
                                    document.write(rowNumber());
                                </script>
                                <input type="hidden" value="${prescription.id}">
                            </td>
                            <td>
                                <select class="select" id="select" style="width: 100px;" size="1">
                                    <c:set  var="selectedOption" scope="page" value="${prescription.drug.id}" />
                                    <option name="option" value="${prescription.drug.id}">${prescription.drug.drugName}</option>
                                    <c:forEach items="${drugs}" var="drug"> 
                                        <c:if test="${selectedOption != drug.id}">
                                            <option name="option" value="${drug.id}">${drug.drugName}</option>
                                        </c:if>
                                    </c:forEach>
                                </select> 
                            </td>
                            <td><input class="input" type="text" value="${prescription.dosage}"></td>
                            <td><input class="input" type="text" value="${prescription.count}"></td>
                            <td><textarea rows="2" cols="30">${prescription.comment}</textarea></td>
                        <script>
                            setElementsName();
                        </script>
                        </tr>
                    </c:forEach>
                </table>
                <br/>
                <input id="submitOk" type="submit" value="<fmt:message key="doctor.treatment.edit"/>"/>
                <input id="submitEndTreatment" style="margin-left: 20px;" type="submit" form="EndTreatment" value="<fmt:message key="doctor.treatment.end-treatment"/>"/>
                <input style="margin-left: 20px;" type="submit" form="Cancel" value="<fmt:message key="doctor.treatment.cancel"/>"/>
            </form>
            <form id="Cancel" method="POST" action="${pageContext.request.contextPath}/HospitalServlet?command=show-treatments&patientId=${patientId}"></form>
            <form id="Reopen" method="POST" action="${pageContext.request.contextPath}/HospitalServlet?command=reopen-treatment&treatmentId=${treatment.id}&patientId=${patientId}"></form>
            <form id="EndTreatment" method="POST" onsubmit="return confirmWindow('<fmt:message key="doctor.treatment.confirm"/>')" action="${pageContext.request.contextPath}/HospitalServlet?command=end-treatment&treatmentId=${treatment.id}&patientId=${patientId}"></form>
            <c:if test="${treatment.endDate != null}">
                <script>
                    closedTreatment('<fmt:message key="doctor.treatment.reopen"/>');
                </script>
            </c:if>
        </body>
    </html>
</fmt:bundle>
