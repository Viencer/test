<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home Page</title>
</head>
<body>
<a href="logout">logout</a> <br><br>
<security:authorize access="hasRole('ADMIN')">
    <table class="tg" border='1' cellpadding='2' width='100%'>
        <tr>
            <th>ID</th>
            <th>FIRST_NAME</th>
            <th>LAST_NAME</th>
            <th>JOB_ID</th>
            <th>BOSS_ID</th>
            <th>SALARY</th>
            <th>COMMISSION</th>
            <th>DEPARTMENT_ID</th>
            <th>PATIENT_ID</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${listPersonal}" var="patient">
            <tr>
                <td>${patient.id}</td>
                <td>${patient.firstName}</td>
                <td>${patient.lastName}</td>
                <td>${patient.jobId}</td>
                <td>${patient.bossID}</td>
                <td>${patient.salary}</td>
                <td>${patient.com}</td>
                <td>${patient.department_id}</td>
                <td>${patient.patient_id}</td>
                <td><a href="update/<c:out value='${patient.id}'/>">Update</a></td>
                <td><a href="delete/<c:out value='${patient.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/create">Create</a>
    <a href="${pageContext.request.contextPath}/findP">Find by...</a>
    <br>
    <br>
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
                <td><a href="deletePatient/<c:out value='${patient.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/createNewPatient">Create</a>
    <br>
    <a href="${pageContext.request.contextPath}/findPatient">Find by...</a>
    <br>
    <br>
    <a href="${pageContext.request.contextPath}/user">My cabinet</a>
</security:authorize>
<security:csrfInput/>
</body>
</html>