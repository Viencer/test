<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<p><strong><a href="${pageContext.request.contextPath}/admin">BACK</a></strong></p>
<c:if test="${msg != null}">
    <h1>${msg}</h1>
    <c:redirect url="/admin"/>
</c:if>
<form action="${pageContext.request.contextPath}/updateP" method="post">

<pre>
    <c:set var="personal" value="${Personal}"/>
    <br>
    <h1>Update personal with id ${personal.id}</h1>

    <input type="hidden" name="id" value="${personal.id} "/>

    <br> LAST_NAME <input type="text" name="lastName" value="${personal.lastName}"/>

    BOSS_ID <input type="number" name="bossId" value="${personal.bossID}"/>

    COMMISSION <input type="number" name="com" value="${personal.com}"/>

    SALARY <input type="number" name="salary" value="${personal.salary}"/>

    JOB_ID <input type="number" name="jobId" value="${personal.jobId}"/>

    DEPARTMENT_ID: <input type="number" name="department" value="${personal.department_id}"/>

    PATIENT_ID: <input type="number" name="patient" value="${personal.patient_id}"/>

    <input type="submit" value="update"/>
</pre>
    <sec:csrfInput/>
</form>
</body>
</html>