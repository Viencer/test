<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<sec:authorize access="hasRole('DOCTOR')">
    <p><strong><a href="${pageContext.request.contextPath}/user">BACK TO DOCTOR</a></strong></p>
</sec:authorize>
<sec:authorize access="hasRole('ADMIN')">
    <p><strong><a href="${pageContext.request.contextPath}/admin">BACK TO ADMIN</a></strong></p>
    <c:if test="${msg != null}">
        <h1>${msg}</h1>
        <c:redirect url="/admin"/>
    </c:if>
    <c:if test="${task == 1}">
        <form action="${pageContext.request.contextPath}/updateP" method="post">

<pre>
    <c:set var="personal" value="${Personal}"/>
    <br>
    <h1>Update personal with id ${personal.id}</h1>

    <input type="hidden" name="id" value="${personal.id} "/>

    <br> LAST_NAME <input type="text" name="lastName" value="${personal.lastName}"/>

    BOSS_ID <input type="number" name="bossId" value="${personal.bossID}"/>

    PREMIUM <input type="number" name="com" value="${personal.premium}"/>

    SALARY <input type="number" name="salary" value="${personal.salary}"/>

    JOB_ID <input type="number" name="jobId" value="${personal.jobId}"/>

    DEPARTMENT_ID: <input type="number" name="department" value="${personal.department_id}"/>

    PATIENT_ID: <input type="number" name="patient" value="${personal.patient_id}"/>

    <input type="submit" value="update"/>
</pre>
            <sec:csrfInput/>
        </form>
    </c:if>
</sec:authorize>
<sec:authorize access="hasAnyRole('ADMIN', 'DOCTOR')">
    <c:if test="${msg != null}">
        <h1>${msg}</h1>
        <c:redirect url="/user"/>
    </c:if>
    <c:if test="${task == 2}">
        <form action="${pageContext.request.contextPath}/updatePatient" method="post">

<pre>
    <c:set var="patient" value="${Patient}"/>
    <br>
    <h1>Update personal with id ${patient.id}</h1>

    <input type="hidden" name="id" value="${patient.id} "/>

    <br> FIRST_NAME: <input type="text" disabled name="firstName" value="${patient.firstName}"/>

    <br> LAST_NAME:  <input type="text" disabled name="lastName" value="${patient.lastName}"/>

    <br> POSITION:   <input type="text" disabled name="position" value="${patient.position}"/>

    <br> PHONE:      <input type="text" disabled name="phone" value="${patient.phone}"/>

    <br> ADDRESS:    <input type="text" disabled name="address" value="${patient.address}"/>

    DIAGNOSIS_ID:    <input type="number" name="diagnosisId" value="${patient.diagnosisId}"/>

    MEDICINE_ID:     <input type="number" name="medicineId" value="${patient.medicineId}"/>

    <input type="submit" value="update"/>
</pre>
            <sec:csrfInput/>
        </form>
    </c:if>
</sec:authorize>
</body>
</html>