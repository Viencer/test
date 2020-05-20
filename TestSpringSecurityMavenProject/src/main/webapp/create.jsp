<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Update</title>
</head>
<body>

<sec:authorize access="hasRole('DOCTOR')">
    <p><strong><a href="${pageContext.request.contextPath}/user">BACK TO USER</a></strong></p>
    <c:if test="${msg != null}">
        <h1>${msg}</h1>
        <c:redirect url="/user"/>
    </c:if>
</sec:authorize>

<sec:authorize access="hasRole('ADMIN')">
    <p><strong><a href="${pageContext.request.contextPath}/admin">BACK TO ADMIN</a></strong></p>
    <p><strong><a href="${pageContext.request.contextPath}/user">BACK TO USER</a></strong></p>
    <c:if test="${msg != null}">
        <h1>${msg}</h1>
        <c:redirect url="/admin"/>
    </c:if>
    <c:if test="${task == 1}">
        <form action="${pageContext.request.contextPath}/createPersonal" method="post">

<pre>
    FIRST_NAME: <input type="text" name="firstName" required placeholder="firstName"/>

    LAST_NAME: <input type="text" name="lastName" required placeholder="lastName"/>

    BOSS_ID: <input type="number" name="bossId" value="0"/>

    PREMIUM: <input type="number" name="com" value="0"/>

    SALARY: <input type="number" name="salary" required placeholder="salary"/>

    JOB_ID: <input type="number" name="jobId" required placeholder="jobId"/>

    DEPARTMENT_ID: <input type="number" name="department" required placeholder="department"/>

    PATIENT_ID: <input type="number" name="patient" value="0"/>

    USERNAME: <input type="text" name="username" required placeholder="username"/>

    PASSWORD: <input type="text" name="password" required placeholder="password"/>

    <input type="submit" value="create"/>
</pre>
            <sec:csrfInput/>
        </form>
    </c:if>
</sec:authorize>

<c:if test="${task == 2}">
    <c:if test="${msg != null}">
        <h1>${msg}</h1>
        <c:redirect url="/admin"/>
    </c:if>

    <form action="createPatient" method="post">
<pre>
    <br>

    <br> FIRST_NAME: <input type="text" name="firstName" required placeholder="firstName"/>

    <br> LAST_NAME: <input type="text" name="lastName" required placeholder="lastName"/>

    <br> POSITION: <input type="text" name="position" required placeholder="position"/>

    <br> PHONE: <input type="number" name="phone" required placeholder="phone"/>

    <br> ADDRESS: <input type="text" name="address" required placeholder="address"/>

    DIAGNOSIS_ID: <input type="number" name="diagnosisId" required placeholder="diagnosisId"/>

    MEDICINE_ID: <input type="number" name="medicineId" required placeholder="medicineId"/>

    <input type="submit" value="update"/>
</pre>
        <sec:csrfInput/>
    </form>
</c:if>
</body>
</html>