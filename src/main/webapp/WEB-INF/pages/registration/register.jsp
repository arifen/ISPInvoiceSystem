<%--
  Created by IntelliJ IDEA.
  User: arifen
  Date: 5/25/16
  Time: 6:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
    <c:if test="${not empty msg}">
      <div class="msg">${msg}</div>
    </c:if>
    <div >
      <h1>User Creation</h1>
      <form modelAttribute="user" action="userregister" method="post" >

        <table border="2" style="margin-left:50px">
          <tr>
            <td>
              User Name:
            </td>
            <td>
              <input type="text" name="name" value="">
            </td>
          </tr>
          <tr>
            <td>
              Email Address:
            </td>
            <td>
              <input type="text" name="emailAddress" value="">
            </td>
          </tr>
          <tr>
            <td>
              LoginId:
            </td>
            <td>
              <input type="text" name="loginId" value="">
            </td>
          </tr>
          <tr>
            <td>
              Password:
            </td>
            <td>
              <input type="text" name="password" value="">
            </td>
          </tr>
          <tr>
            <td>
              Role:
            </td>
            <td>
              <select name="role" multiple>
                <option value="ADMIN">Admin</option>
                <option value="GUEST">Guest</option>
                <option value="USER">user</option>
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
    </div>
</body>
</html>
