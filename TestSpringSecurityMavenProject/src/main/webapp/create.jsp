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
<form action="createP" method="post">

<pre>
    FIRST_NAME: <input type="text" name="firstName"/>

    LAST_NAME: <input type="text" name="lastName"/>

    BOSS_ID: <input type="number" name="bossId"/>

    EXP: <input type="number" name="exp"/>

    SALARY: <input type="number" name="salary"/>

    JOB_ID: <input type="number" name="jobId"/>

    USERNAME: <input type="text" name="username"/>

    PASSWORD: <input type="text" name="password"/>

    <input type="submit" value="create"/>
</pre>
    <sec:csrfInput/>
</form>
</body>
</html>