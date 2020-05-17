<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<p><strong><a href="admin">BACK</a></strong></p>
<c:if test="${msg != null}">
    <h1>${msg}</h1>
</c:if>
<%--<h2>Spring MVC Create, Read, Update and Delete (CRUD) Example</h2>--%>
<form action="../update" method="post">


    <c:set var="personal" value="${Personal}"/>
        <br><h1>Update personal with id ${personal.id}</h1>

                 <input type="hidden" name="id" value="${personal.id} "/>

        <br> LAST_NAME <input type="text" name="lastName" value="${personal.lastName}"/>

       BOSS_ID   <input type="number" name="bossId" value="${personal.bossID}"/>

       EXP       <input type="number" name="exp" value="${personal.exp}"/>

       SALARY    <input type="number" name="salary" value="${personal.salary}"/>

       JOB_ID    <input type="number" name="jobId" value="${personal.jobId}"/>

                 <input type="submit" value="update"/>

    <sec:csrfInput/>
</form>
</body>
</html>