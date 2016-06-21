<%--
  Created by IntelliJ IDEA.
  User: arifen
  Date: 6/20/16
  Time: 3:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title></title>
</head>
<body>
<div>
<form:form  action="fileupload" modelAttribute="fileBucket" method="post" enctype="multipart/form-data">
  <table border="2" style="margin-left:50px">
    <tr>
      <td>
        File Upload
      </td>
      <td>
        <form:input type="file" path="file"/>
        <form:errors path="file" cssStyle="color: red;"/>
      </td>
    </tr>
    <tr>
      <td>
        <input type="submit" value="Submit">
      </td>
    </tr>
  </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="hidden" name="userId" value="${userId}" />
</form:form>
</div>
</body>
</html>
