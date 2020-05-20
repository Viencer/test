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
    <p><strong><a href="${pageContext.request.contextPath}/admin">BACK TO ADMIN</a></strong></p>
</security:authorize>
<security:authorize access="hasAnyRole('DOCTOR', 'ADMIN',)">
    <p><strong><a href="${pageContext.request.contextPath}/admin">BACK TO ADMIN</a></strong></p>
</security:authorize>
<security:authorize access="hasRole('INTERN')">
    LINK
</security:authorize>
<security:authorize access="hasAnyRole('DOCTOR', 'ADMIN', 'INTERN')">
    <br>
    <br>
    <c:if test="${task == 1}">
        <p><strong><h1>You BOSS of the BOSSES</h1></strong></p>
    </c:if>
    <c:if test="${task == 2}">
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
        <c:forEach items="${listPerson}" var="personal">
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
        </c:forEach>
    </table>
    </c:if>
</security:authorize>
<security:csrfInput/>
</body>
</html>