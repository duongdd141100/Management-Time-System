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
        <link href="adminView/css/add-employee.css" rel="stylesheet" type="text/css"/>
        <script src="employeeView/js/CheckConfirmPassword.js" type="text/javascript"></script>
    </head>
    <body>
        <c:if test="${iserted != null}">
            <script>
                alert("Add Successfully")
            </script>
            
        </c:if>
        <script>
            var listUser = []
            var listEmail = []
            <c:forEach items="${listAcc}" var="l">
            listUser.push('${l.username}')
            </c:forEach>
            <c:forEach items="${listAd}" var="l">
            listEmail.push('${l.email}')
            </c:forEach>
            <c:forEach items="${listEmp}" var="l">
            listEmail.push('${l.email}')
            </c:forEach>
        </script>
        <%@include file="header.jsp" %>
        <div id="detail">
            <div class="title">
                <h3>Information</h3>
            </div>
            <div id="table">
                <form action="add-employee" method="POST" id="formAdd">
                    <table>
                        <tr>
                            <th>Username:</th>
                            <td><input type="text" class="inputText" name="user" id="user">
                                <p class="error" id="error-exist-user">*Username is exist!</p>
                                <p class="error" id="error-empty-user">*Username must not empty!</p>
                            </td>
                        </tr>

                        <tr>
                            <th>Name:</th>
                            <td><input type="text" class="inputText" name="name" id="name">
                                <p class="error" id="error-empty-name">*Name must not empty!</p>
                            </td>
                        </tr>
                        <tr>
                            <th>Email:</th>
                            <td><input type="text" class="inputText" name="email" id="email">
                                <p class="error" id="error-exist-email">*Email is exist!</p>
                                <p class="error" id="error-empty-email">*Email must not empty!</p>
                            </td>
                        </tr>

                        <tr>
                            <th>Gender:</th>
                            <td>
                                <input type="radio" value="true" name="gender" class="gender" id="radioMale">
                                <label for="radioMale">Male</label>
                                <input type="radio" value="false"  name="gender" class="gender" id="radioFemale">
                                <label for="radioFemale">Female</label>
                                <p class="error" id="error-empty-gender">*Choose gender!</p>
                            </td>
                        </tr>
                        <tr>
                            <th>Date Of Birth:</th>
                            <td><input type="date" name="dob" class="inputText" id="dob">
                                <p class="error" id="error-empty-dob">*Birth Date must not empty!</p>
                            </td>
                        </tr>
                        <th>Type of user:</th>
                        <td>
                            <select class="inputText" id="userType" name="userType" onchange="chageUserType()">
                                <c:forEach items="${listGroup}" var="l">
                                    <option value="${l.name}">${l.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <tr id="trTime" style="display: none;">
                            <th>Time ID</th>
                            <td>
                                <select name="timeId" class="inputText">
                                    <c:forEach items="${listTime}" var="t">
                                        <option value="${t.id}">${t.id}, ${t.from} - ${t.to}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>Password:</th>
                            <td><input type="password" class="inputText" name="pass" id="pass">
                                <p class="error" id="error-empty-pass">*Password Date must not empty!</p>
                            </td>
                        </tr>
                        <tr>
                            <th>Confirm Password: </th>
                            <td><input type="password" class="inputText" name="cfPass" id="cfPass">
                                <p class="error" id="error-cf-pass">*Password is not duplicate!</p>
                            </td>
                        </tr>

                    </table>
                    <div id="formButton">
                        <input type='button' value="Add" id="add" onclick="validationValue()">
                    </div>
                </form>
            </div>
        </div>
        <div id="changePassword" style="padding-top: 30px;">

        </div>
    </body>
</html>
<script>
    function validationValue() {
        var isValid = true
        var user = document.getElementById("user").value
        var name = document.getElementById("name").value
        var email = document.getElementById("email").value
        var gender = document.getElementsByName("gender")
        var dob = document.getElementById("dob").value
        var pass = document.getElementById("pass").value
        var cfPass = document.getElementById("cfPass").value
        if (user === "") {
            document.getElementById("error-empty-user").style.display = "block"
            isValid = false;
        } else {
            document.getElementById("error-empty-user").style.display = "none"
        }
        if (name === "") {
            document.getElementById("error-empty-name").style.display = "block"
            isValid = false;
        } else {
            document.getElementById("error-empty-name").style.display = "none"
        }
        if (email === "") {
            document.getElementById("error-empty-email").style.display = "block"
            isValid = false;
        } else {
            document.getElementById("error-empty-email").style.display = "none"
        }
        if (dob === "") {
            document.getElementById("error-empty-dob").style.display = "block"
            isValid = false;
        } else {
            document.getElementById("error-empty-dob").style.display = "none"
        }
        if (pass === "") {
            document.getElementById("error-empty-pass").style.display = "block"
            isValid = false;
        } else {
            document.getElementById("error-empty-pass").style.display = "none"
            if (cfPass == pass) {
                document.getElementById("error-cf-pass").style.display = "none"
            } else {
                document.getElementById("error-cf-pass").style.display = "block"
                isValid = false
            }
        }

        if (gender[0].checked == false && gender[1].checked == false) {
            document.getElementById("error-empty-gender").style.display = "block"
            isValid = false
        } else {
            document.getElementById("error-empty-gender").style.display = "none"
        }
        var isExistUser = false;
        for (var i = 0; i < listUser.length; i++) {
            if (listUser[i] === user) {
                document.getElementById("error-exist-user").style.display = "block"
                isExistUser = true
                isValid = false
            }
        }
        if (!isExistUser) {
                document.getElementById("error-exist-user").style.display = "none"
        }
        var isExistEmail = false;
        for (var i = 0; i < listEmail.length; i++) {
            if (listEmail[i] === email) {
                document.getElementById("error-exist-email").style.display = "block"
                isExistEmail = true
                isValid = false
            }
        }
        if (!isExistEmail) {
                document.getElementById("error-exist-email").style.display = "none"
        }
        if(isValid) {
            alert("Add Successfully!")
            document.getElementById("formAdd").submit()
        }

    }
    function chageUserType() {
        if (document.getElementById("userType").value == "Admin") {
            document.getElementById("trTime").style.display = "none"
        } else {
            document.getElementById("trTime").style.display = "contents"
        }
    }
</script>
