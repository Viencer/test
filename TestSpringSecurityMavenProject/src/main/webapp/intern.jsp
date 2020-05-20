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
        <c:set var="personal" value="${person}"/>
        <tr>
            <td>${personal.id}</td>
            <td>${personal.firstName}</td>
            <td>${personal.lastName}</td>
            <td>${personal.jobId}</td>
            <td>${personal.bossID}</td>
            <td>${personal.salary}</td>
            <td>${personal.premium}</td>
            <td>${personal.department_id}</td>
            <td>${personal.patient_id}</td>
        </tr>
    </table>
    <br>
    PATIENTS
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
        </tr>
        <c:forEach items="${listPatient}" var="personal">
            <tr>
                <td>${personal.id}</td>
                <td>${personal.firstName}</td>
                <td>${personal.lastName}</td>
                <td>${personal.position}</td>
                <td>${personal.phone}</td>
                <td>${personal.address}</td>
                <td>${personal.diagnosisId}</td>
                <td>${personal.medicineId}</td>
                <td><a href="updatePatient/<c:out value='${personal.id}'/>">Update</a></td>
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