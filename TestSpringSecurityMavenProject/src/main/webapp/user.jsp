<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home Page</title>
</head>
<body>
<a href="logout">logout</a> <br><br>
<security:authorize access="hasRole('ADMIN')">
    <a href="${pageContext.request.contextPath}/admin">Admin mode</a>
</security:authorize>
<br><br>
<security:authorize access="hasAnyRole('DOCTOR', 'ADMIN')">
    <table class="tg" border='1' cellpadding='2' width='100%'>
        <tr>
            <th>ID</th>
            <th>FIRST_NAME</th>
            <th>LAST_NAME</th>
            <th>JOB_ID</th>
            <th>BOSS_ID</th>
            <th>SALARY</th>
            <th>PREMIUM</th>
            <th>DEPARTMENT_ID</th>
            <th>PATIENT_ID</th>
        </tr>
        <c:set var="patient" value="${person}"/>
        <tr>
            <td>${patient.id}</td>
            <td>${patient.firstName}</td>
            <td>${patient.lastName}</td>
            <td>${patient.jobId}</td>
            <td>${patient.bossID}</td>
            <td>${patient.salary}</td>
            <td>${patient.premium}</td>
            <td>${patient.department_id}</td>
            <td>${patient.patient_id}</td>
        </tr>
    </table>
    <br>
    YOUR PATIENT
    <br>
    <table class="tg" border='1' cellpadding='2' width='100%'>
        <tr>
            <th>ID</th>
            <th>FIRST_NAME</th>
            <th>LAST_NAME</th>
            <th>POSITION</th>
            <th>PHONE</th>
            <th>ADDRESS</th>
            <th>DIAGNOSIS_ID</th>
            <th>MEDICINE_ID</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${listPatient}" var="patient">
            <tr>
                <td>${patient.id}</td>
                <td>${patient.firstName}</td>
                <td>${patient.lastName}</td>
                <td>${patient.position}</td>
                <td>${patient.phone}</td>
                <td>${patient.address}</td>
                <td>${patient.diagnosisId}</td>
                <td>${patient.medicineId}</td>
                <td><a href="updatePatient/<c:out value='${patient.id}'/>">Update</a></td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/createNewPatient">Create</a>
    <a href="${pageContext.request.contextPath}/findPatient">Find by...</a>
</security:authorize>
<security:csrfInput/>
</body>
</html>