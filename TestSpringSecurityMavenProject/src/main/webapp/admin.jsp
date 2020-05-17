<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <th>EXP</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach  items="${listPersonal}" var="personal">
                <tr>
                    <td>${personal.id}</td>
                    <td>${personal.lastName}</td>
                    <td>${personal.lastName}</td>
                    <td>${personal.jobId}</td>
                    <td>${personal.bossID}</td>
                    <td>${personal.salary}</td>
                    <td>${personal.exp}</td>
                    <td><a href="update/<c:out value='${personal.id}'/>">Update</a></td>
                    <td><a href="delete/<c:out value='${personal.id}'/>">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    <a href="create">Create</a>
</security:authorize>
<security:csrfInput/>
</body>
</html>