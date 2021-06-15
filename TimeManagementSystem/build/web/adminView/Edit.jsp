<%-- 
    Document   : Edit
    Created on : Mar 20, 2021, 1:44:43 PM
    Author     : Do Duc Duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="employeeView/css/TimeKeppingHeader.css" rel="stylesheet" type="text/css"/>
        <link href="adminView/css/Edit.css" rel="stylesheet" type="text/css"/>
        <script src="employeeView/js/LoadChagePassword.js" type="text/javascript"></script>
        <script src="employeeView/js/CheckConfirmPassword.js" type="text/javascript"></script>
        <title>Edit Employee</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        

        <div id="detail">
            <div class="title">
                <h3>Information</h3>
            </div>
            <div id="table">
                <form action="edit-employee" method="POST">
                    <c:set value="${employee}" var="e"/>
                    <table>
                        <tr>
                            <th>Name:</th>
                            <td><input type="text" value="${e.name}" class="inputText" name="name"></td>
                        </tr>
                        <tr>
                            <th>Email:</th>
                            <td><input type="text" value="${e.email}" class="inputText" name="email"></td>
                        </tr>

                        <tr>
                            <th>Gender:</th>
                            <td>
                                Male<input
                                    type="radio" 
                                    value="true" 
                                    name="gender" 
                                    <c:if test="${e.gender}">
                                        checked
                                    </c:if>
                                    class="gender">

                                Female<input
                                    type="radio" 
                                    value="false" 
                                    name="gender" 
                                    <c:if test="${!e.gender}">
                                        checked
                                    </c:if>
                                    class="gender"
                                    >
                            </td>
                        </tr>
                        <tr>
                            <th>Date Of Birth:</th>
                            <td><input type="date" value="${e.dob}" name="dob" class="inputText"></td>
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

                    </table>
                    <input type="hidden" value="${e.username}" name="username">
                    <div id="formButton">
                        <input type="submit" value="Save" id="submit">
                        <input type="reset" value="Reset" id="reset">
                    </div>
                </form>
            </div>
        </div>
        <div id="changePassword" style="padding-top: 30px;">

        </div>
    </body>
</html>
