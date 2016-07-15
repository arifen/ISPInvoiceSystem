<%--
  Created by IntelliJ IDEA.
  User: arifen
  Date: 5/13/16
  Time: 6:06 PM
  To change this template use File | Settings | File Templates.
--%>
<script>
    function formSubmit() {
        document.getElementById("logoutForm").submit();
    }
</script>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/j_spring_security_logout" var="logoutUrl"/>

<form action="${logoutUrl}" method="post" id="logoutForm">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>


<ul>
    <sec:authorize access="hasRole('ADMIN')">
        <li><a href="/userlist">Pending User List</a></li>
    </sec:authorize>
    <li><a href="/customerhome">Customer</a></li>
    <li><a href="javascript:formSubmit()">exit</a></li>
</ul>
