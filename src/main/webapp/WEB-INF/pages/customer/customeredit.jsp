<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: arifen
  Date: 7/24/16
  Time: 1:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <%-- need this tag to get csfr token--%>
    <sec:csrfMetaTags/>
    <link rel="stylesheet" href="../resources/css/style.css" type="text/css"/>
    <script type="text/javascript">

        $(document).ready(function () {

            $('#editCustomerForm').submit(function (event) {
                try {
                    var token = $("meta[name='_csrf']").attr("content");
                    var header = $("meta[name='_csrf_header']").attr("content");

                    var name = $('#name').val();
                    var emailAddress = $('#emailAddress').val();
                    var customerId = $('#customerId').val();
                    var password = $('#password').val();
                    var address = $('#address').val();
                    var mobileNumber = $('#mobileNumber').val();
                    var status = $('#status').val();
                    var selectPackId = $('#aPackage').val();
                    var packageId = "1";
                    var packageName = "PKG-1";
                    var amount = "500";
                    var getId = "";
                    var i;
                    var length = ${Package.size()};
                    var temp;
                    console.log("length " + length);
                    /*
                     * java List cannot be converted to a javascript list directly by doing var people = "
                    ${peopleList}", instead loop through List and create manually
                     * */
                    var packageList = [];
                    <c:forEach items="${Package}" var="ppl">
                    //var people = new Object();
                    temp = '${ppl.id}';
                    if (selectPackId == temp) {
                        packageId = '${ppl.id}';
                        packageName = '${ppl.packageName}';
                        amount = '${ppl.amount}';
                    }
                    console.log("id " + packageId + " name " + packageName + " amoumt " + amount);
                    //peopleList.push(people);
                    </c:forEach>


                    var packageJson = {
                        "id": packageId,
                        "packageName": packageName,
                        "amount": amount
                    };
                    var json = {
                        "name": name,
                        "emailAddress": emailAddress,
                        "customerId": customerId,
                        "password": password,
                        "address": address,
                        "mobileNumber": mobileNumber,
                        "status": status,
                        "aPackage": packageJson
                    };

                    $.ajax({
                        url: $("#editCustomerForm").attr("action"),
                        data: JSON.stringify(json),
                        dataType: 'json',
                        type: "POST",
                        beforeSend: function (xhr) {
                            xhr.setRequestHeader("Accept", "application/json");
                            xhr.setRequestHeader("Content-Type", "application/json");
                            xhr.setRequestHeader(header, token);
                        },
                        success: function (customer) {
                            var respContent = "this is arifen";
                            //alert("customer " + customer);
                            respContent += "<span class='success'>Customer edited successfully: [";
                            respContent += customer.customerId + " : ";
                            respContent += customer.aPackage.packageName + "]</span>";
                            $("#editcustomerFromResponse").html(respContent);
                        },
                        error: function (xhr, status, error) {
                            alert(" xhr.responseText: " + xhr.responseText + " //status: " + status + " //Error: " + error);

                        }
                    });

                    event.preventDefault();
                } catch (err) {
                    alert("err " + err);
                }
            });

        });
    </script>
</head>
<body>
<div id="editcustomerFromResponse"></div>
<h1>Customer Edit</h1>
<form:form id="editCustomerForm" commandName="customer" action="/customer/editajax/${customer.id}" method="POST">

    <table border="2" style="margin-left:50px">
        <tr>
            <td>
                Name:
            </td>
            <td>
                <form:input path="name"/>
                    <%--<form:errors path="name" cssStyle="color: red;"/>--%>
            </td>
        </tr>
        <tr>
            <td>
                Email Address:
            </td>
            <td>
                <form:input path="emailAddress"/>
                    <%--<form:errors path="emailAddress" cssStyle="color: red;"/>--%>
            </td>
        </tr>
        <tr>
            <td>
                UserId:
            </td>
            <td>
                <form:input path="customerId"/>
                    <%--<form:errors path="customerId" cssStyle="color: red;"/>--%>
            </td>
        </tr>
        <tr>
            <td>
                Password:
            </td>
            <td>
                <form:password path="password"/>
                    <%--<form:errors path="password" cssStyle="color: red;"/>--%>
            </td>
        </tr>
        <tr>
            <td>
                Addresss
            </td>
            <td>
                <form:input path="address"/>
                    <%-- <form:errors path="address" cssStyle="color: red;"/>--%>
            </td>
        </tr>
        <tr>
            <td>
                Package Name
            </td>
            <td>
                <form:select path="aPackage">
                    <form:options items="${Package}" itemValue="id" itemLabel="packageName"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td>
                Mobile Number
            </td>
            <td>
                <form:input path="mobileNumber"/>
                    <%--<form:errors path="mobileNumber" cssStyle="color: red;"/>--%>
            </td>
        </tr>
        <form:hidden path="status" value="active"/>
        <tr>
            <td>
                <input type="submit" value="Submit">
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
