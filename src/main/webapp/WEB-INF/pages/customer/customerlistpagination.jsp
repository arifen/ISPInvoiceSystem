<%--
  Created by IntelliJ IDEA.
  User: arifen
  Date: 7/5/16
  Time: 12:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title></title>
    <%-- need this tag to get csfr token--%>
    <%--<link rel="stylesheet" href="../resources/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="../resources/bootstrap-3.3.7-dist/css/bootstrap.css" type="text/css"/>--%>
    <sec:csrfMetaTags/>
    <script type="text/javascript">

        $(document).ready(function () {
            var deleteLink = $("a:contains('Delete')");
            $(deleteLink).click(function (event) {
                try {
                    var token = $("meta[name='_csrf']").attr("content");
                    var header = $("meta[name='_csrf_header']").attr("content");
                    alert("header " + header + " token " + token);
                    $.ajax({
                        url: $(event.target).attr("href"),
                        type: "DELETE",

                        beforeSend: function (xhr) {
                            xhr.setRequestHeader("Accept", "application/json");
                            xhr.setRequestHeader("Content-Type", "application/json");
                            xhr.setRequestHeader(header, token);
                        },

                        success: function (customer) {
                            var respContent = "";
                            var rowToDelete = $(event.target).closest("tr");

                            rowToDelete.remove();

                            respContent += "<span class='success'>Customer was deleted: [";
                            respContent += customer.customerId + " : ";
                            respContent += customer.aPackage.packageName + "]</span>";

                            $("#customerFromResponse").html(respContent);
                        },
                        error: function (xhr, status, error) {
                            alert(" xhr.responseText: " + xhr.responseText + " //status: " + status + " //Error: " + error);

                        }
                    });
                } catch (err) {
                    alert(err);
                }

                event.preventDefault();
            });

        });
    </script>
</head>
<body>
<c:url var="firstUrl" value="/customerlist/1"/>
<c:url var="lastUrl" value="/customerlist/${endIndex}"/>
<c:url var="prevUrl" value="/customerlist/${currentIndex - 1}"/>
<c:url var="nextUrl" value="/customerlist/${currentIndex + 1}"/>
<div id="customerFromResponse"></div>
<c:if test="${not empty msg}">
    <div>
            ${msg}
    </div>
    <div>
        Number of Customer : ${customerlists.size()}
    </div>
</c:if>
<c:if test="${not empty customerlists}">
    <div>
        <table border="2">
            <thead style="background-color: #31708f">
            <td>
                Name
            </td>
            <td>
                User Id
            </td>
            <td>
                Password
            </td>
            <td>
                Mobile Number
            </td>
            <td>
                Status
            </td>
            <td>
                Package Name
            </td>
            <td>
                Amount
            </td>
            <td>
                Address
            </td>
            <td>
                Email Address
            </td>
            <td>
                Action
            </td>
            </thead>
            <tbody>
            <c:forEach var="listValue" items="${customerlists}">
                <tr>
                    <td>
                            ${listValue.name}
                    </td>
                    <td>
                            ${listValue.customerId}
                    </td>
                    <td>
                            ${listValue.password}
                    </td>
                    <td>
                            ${listValue.mobileNumber}
                    </td>
                    <td>
                            ${listValue.status}
                    </td>
                    <td>
                            ${listValue.aPackage.packageName}
                    </td>
                    <td>
                            ${listValue.aPackage.amount}
                    </td>
                    <td>
                            ${listValue.address}
                    </td>
                    <td>
                            ${listValue.emailAddress}
                    </td>
                    <td>
                        <a href="customeredit/${listValue.id}">Edit</a>
                        <a href="customer/delete/${listValue.id}">Delete</a>
                        <a href="/downloadPDF/${listValue.customerId}">Invoice</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="pagination">
        <ul>
            <c:choose>
                <c:when test="${currentIndex == 1}">
                    <span class="disabled"><a href="#">first</a></span>
                    <span class="disabled"><a href="#">prev</a></span>
                </c:when>
                <c:otherwise>
                    <span><a href="${firstUrl}">first</a></span>
                    <span><a href="${prevUrl}">prev</a></span>
                </c:otherwise>
            </c:choose>
            <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
                <c:url var="pageUrl" value="/customerlist/${i}"/>
                <c:choose>
                    <c:when test="${i == currentIndex}">
                        <span class="activespan"><a href="${pageUrl}"><c:out value="${i}"/></a></span>
                    </c:when>
                    <c:otherwise>
                        <span><a href="${pageUrl}"><c:out value="${i}"/></a></span>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:choose>
                <c:when test="${currentIndex == endIndex}">
                    <span class="disabled"><a href="#">next</a></span>
                    <span class="disabled"><a href="#">last</a></span>
                </c:when>
                <c:otherwise>
                    <span><a href="${nextUrl}">next</a></span>
                    <span><a href="${lastUrl}">last</a></span>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</c:if>
</body>
</html>
