<%-- 
    Document   : edit-profile
    Created on : Jul 11, 2021, 9:39:15 AM
    Author     : Do Duc Duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="employeeView/css/TimeKeppingHeader.css" rel="stylesheet" type="text/css"/>
        <link href="employeeView/css/edit-profile.css" rel="stylesheet" type="text/css"/>
        <script src="employeeView/js/LoadChagePassword.js" type="text/javascript"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${group == 'Admin'}">
            <%@include file="../adminView/admin-header.jsp" %>
        </c:if>
        <c:if test="${group == 'Employee'}">
            <%@include file="emp-header.jsp" %>
        </c:if>
        
        <div id="body">
            <div id="title">
                <h3>Profile</h3>
            </div>
            <div id="content">
                <form action="edit-profile" method="post" id="formUpdate">
                    <table>
                        <tr>
                            <th>Username</th>
                            <td><input type="text" name="username" value="${info.username}" readonly></td>
                        </tr>
                        <tr>
                            <th>Full Name</th>
                            <td><input type="text" name="name" value="${info.name}"></td>
                        </tr>
                        <tr>
                            <th>Gender</th>
                            <td id="gender">
                                <span>Male</span><input type="radio" name="gender" value="true" 
                                       <c:if test="${info.gender}">checked</c:if>
                                       >
                                <span>Female</span><input type="radio" name="gender" value="false" 
                                       <c:if test="${!info.gender}">checked</c:if>
                                       >
                            </td>
                        </tr>
                        <tr>
                            <th>Date Of Birth</th>
                            <td><input type="date" name="dob" value="${info.dob}"></td>
                        </tr>
                        <tr>
                            <th>Email</th>
                            <td><input type="text" name="email" value="${info.email}"></td>
                        </tr>
                    </table>
                        <input type="button" value="Save" id="buttonSave" onclick="saveUpdate()">
                </form>
            </div>
        </div>
        <div id="changePassword" style="padding-top: 30px;">

        </div>
    </body>
</html>
<script>
    function saveUpdate() {
        alert("Update Successfully!")
        document.getElementById("formUpdate").submit()
    }
</script>