<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: arifen
  Date: 8/1/16
  Time: 4:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:if test="${not empty msg}">
    <div>
            ${msg}
    </div>
</c:if>

<form action="packagecustomer" method="post" style="margin-left: -49px;">
    <table border="2" style="margin-left:50px">
        <tr>
            <td>
                Package Name
            </td>
            <td>
                <select name="selectpackage">
                    <c:forEach var="packageValue" items="${Package}">
                        <option value="${packageValue.id}">${packageValue.packageName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Submit">
            </td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>
