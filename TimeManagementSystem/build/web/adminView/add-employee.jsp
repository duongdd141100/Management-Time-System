<%-- 
    Document   : add-employee
    Created on : Jun 15, 2021, 9:40:29 AM
    Author     : Do Duc Duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="employeeView/css/TimeKeppingHeader.css" rel="stylesheet" type="text/css"/>
        <link href="adminView/css/Edit.css" rel="stylesheet" type="text/css"/>
        <script src="employeeView/js/CheckConfirmPassword.js" type="text/javascript"></script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div id="detail">
            <div class="title">
                <h3>Information</h3>
            </div>
            <div id="table">
                <form action="#" method="POST">
                    <table>
                        <tr>
                            <th>Username:</th>
                            <td><input type="text" class="inputText" name="user"></td>
                        </tr>

                        <tr>
                            <th>Name:</th>
                            <td><input type="text" class="inputText" name="name"></td>
                        </tr>
                        <tr>
                            <th>Email:</th>
                            <td><input type="text" class="inputText" name="email"></td>
                        </tr>

                        <tr>
                            <th>Gender:</th>
                            <td style="display: flex;">
                                <input type="radio" value="true" name="gender" class="gender" id="radioMale">
                                <label for="radioMale">Male</label>
                                <input type="radio" value="false"  name="gender" class="gender" id="radioFemale">
                                <label for="radioFemale">Female</label>
                            </td>
                        </tr>
                        <tr>
                            <th>Date Of Birth:</th>
                            <td><input type="date" name="dob" class="inputText"></td>
                        </tr>
                        <tr>
                            <th>Time ID</th>
                            <td>
                                <select name="timeId" class="inputText">
                                    <c:forEach items="${listTime}" var="t">
                                        <option value="${t.id}"
                                                <c:if test="${t.id == timeId}">
                                                    checked
                                                </c:if>
                                                >
                                            ${t.id}, ${t.from} - ${t.to}</option>
                                        </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>Password:</th>
                            <td><input type="password" class="inputText" name="pass"></td>
                        </tr>
                        <tr>
                            <th>Confirm Password: </th>
                            <td><input type="password" class="inputText" name="cfPass"></td>
                        </tr>

                    </table>
                    <input type="hidden" value="${e.username}" name="username">
                    <div id="formButton">
                        <input type='button' value="Save" id="submit" onclick="">
                    </div>
                </form>
            </div>
        </div>
        <div id="changePassword" style="padding-top: 30px;">
            
        </div>
    </body>
</html>
<script>

    if (isValidPassCf("pass", "cfPass")) {

    }
</script>
