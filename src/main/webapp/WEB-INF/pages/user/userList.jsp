<%--
  Created by IntelliJ IDEA.
  User: arifen
  Date: 6/22/16
  Time: 8:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title></title>
</head>
<body>
    <c:if test="${not empty userlist}">
        <div>
            <table border="2" style="margin-left:50px">
                <thead style="background-color: #31708f">
                    <td>
                        Name
                    </td>
                    <td>
                        Email Address
                    </td>
                    <td>
                        Registration Status
                    </td>
                    <td>
                        Action:
                    </td>
                </thead>
                    <c:forEach var="listValue" items="${userlist}">
                <tr>
                    <td>
                            ${listValue.name}
                    </td>
                    <td>
                            ${listValue.emailAddress}
                    </td>
                    <td>
                            ${listValue.status}
                    </td>
                </tr>
                    </c:forEach>
            </table>
        </div>
    </c:if>
</body>
</html>
