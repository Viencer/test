<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<sec:authorize access="hasRole('ADMIN')">
    <p><strong><a href="${pageContext.request.contextPath}/admin">BACK TO ADMIN</a></strong></p>
</sec:authorize/>
<sec:authorize access="hasAnyRole('DOCTOR', 'ADMIN')">
    <p><strong><a href="${pageContext.request.contextPath}/user">BACK TO USER</a></strong></p>
</sec:authorize/>
<br>
<br>
<form action="findPatByName" method="post">
    LAST_NAME: <input type="text" name="lastName"/>
    <input type="submit" value="find"/>
    <sec:csrfInput/>
</form>

<form action="findPatById" method="post">
    ID: <input type="number" name="id"/>
    <input type="submit" value="find"/>
    <sec:csrfInput/>
</form>
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
        <sec:authorize access="hasRole('ADMIN')">
            <th>Delete</th>
        </sec:authorize/>

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
            <td><a href="update/<c:out value='${patient.id}'/>">Update</a></td>
            <sec:authorize access="hasRole('ADMIN')">
                <td><a href="delete/<c:out value='${patient.id}'/>">Delete</a></td>
            </sec:authorize/>
        </tr>
    </c:forEach>
</table>
<sec:csrfInput/>
</body>
</html>
