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
<security:authorize access="hasRole('DOCTOR') or hasRole('ADMIN')">
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
        </tr>
        <c:set var="personal" value="${person}"/>
        <tr>
            <td>${personal.id}</td>
            <td>${personal.firstName}</td>
            <td>${personal.lastName}</td>
            <td>${personal.jobId}</td>
            <td>${personal.bossID}</td>
            <td>${personal.salary}</td>
            <td>${personal.com}</td>
            <td>${personal.department_id}</td>
            <td>${personal.patient_id}</td>
        </tr>
    </table>
</security:authorize>
<security:csrfInput/>
</body>
</html>