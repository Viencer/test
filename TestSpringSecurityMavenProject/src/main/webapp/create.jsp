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
<form action="createP" method="post">

<pre>
    FIRST_NAME: <input type="text" name="firstName"/>

    LAST_NAME: <input type="text" name="lastName"/>

    BOSS_ID: <input type="number" name="bossId"/>

    COMMISSION: <input type="number" name="com"/>

    SALARY: <input type="number" name="salary"/>

    JOB_ID: <input type="number" name="jobId"/>

    DEPARTMENT_ID: <input type="number" name="department"/>

    PATIENT_ID: <input type="number" name="patient" value="0"/>

    USERNAME: <input type="text" name="username"/>

    PASSWORD: <input type="text" name="password"/>

    <input type="submit" value="create"/>
</pre>
    <sec:csrfInput/>
</form>
</body>
</html>