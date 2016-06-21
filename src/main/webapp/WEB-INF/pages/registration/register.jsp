<%--
  Created by IntelliJ IDEA.
  User: arifen
  Date: 5/25/16
  Time: 6:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<body>
<c:if test="${not empty msg}">
    <div class="msg">${msg}</div>
</c:if>
<h1>User Creation</h1>
<form:form commandName="user" action="userregister" method="post">

    <table border="2" style="margin-left:50px">
        <tr>
            <td>
                Name:
            </td>
            <td>
                <form:input path="name"/>
                <form:errors path="name" cssStyle="color: red;"/>
                    <%--<input type="text" name="name" value="">--%>
            </td>
        </tr>
        <tr>
            <td>
                Email Address:
            </td>
            <td>
                <form:input path="emailAddress"/>
                <form:errors path="emailAddress" cssStyle="color: red;"/>
                    <%--<input type="text" name="emailAddress" value="">--%>
            </td>
        </tr>
        <tr>
            <td>
                LoginId:
            </td>
            <td>
                <form:input path="loginId"/>
                <form:errors path="loginId" cssStyle="color: red;"/>
                    <%--<input type="text" name="loginId" value="">--%>
            </td>
        </tr>
        <tr>
            <td>
                Password:
            </td>
            <td>
                <form:password path="password"/>
                <form:errors path="password" cssStyle="color: red;"/>
                    <%--<input type="text" name="password" value="">--%>
            </td>
        </tr>
        <tr>
            <td>
                Role
            </td>
            <td>
                <form:select path="role" >
                    <form:options items="${Role}" itemValue="roleName" itemLabel="roleName"/>
                </form:select>
                <form:errors path="role" cssStyle="color: red;"/>
            </td>
        </tr>
        <form:hidden path="status" value="pre-reg"/>
            <%--<tr>
              <td>
                Role:
              </td>
              <td>
                <select name="role" >
                  &lt;%&ndash;<option value="ADMIN">Admin</option>
                  <option value="GUEST">Guest</option>
                  <option value="USER">user</option>&ndash;%&gt;
                    <c:forEach  items="${Role}" var="role">
                      <option value="<c:out value="${role.roleName}"/>"><c:out value="${role.roleName}"/></option>
                    </c:forEach>
                </select>
              </td>
            </tr>--%>
        <tr>
            <td>
                <input type="submit" value="Submit">
            </td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form:form>
</div>
</body>
</html>
