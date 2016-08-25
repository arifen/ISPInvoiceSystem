<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: arifen
  Date: 8/25/16
  Time: 11:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>Invoice Creation</h1>
<ui>
    <li><a href="/downloadPDF">All Customer </a></li>
    <c:forEach var="packageValue" items="${Package}"><%--<a href="/downloadPDF/${listValue.customerId}">Invoice</a>--%>
        <%-- <option value="${packageValue.id}">${packageValue.packageName}</option>--%>
        <li><a href="/downloadPDFperCustomer/${packageValue.id}">${packageValue.packageName}</a></li>
    </c:forEach>
</ui>
</body>
</html>
