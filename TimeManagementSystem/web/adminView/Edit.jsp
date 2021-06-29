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
                <form action="edit-employee" method="POST" id="formEdit">
                    <c:set value="${employee}" var="e"/>
                    <table>
                        <tr>
                            <th>Name:</th>
                            <td><input type="text" value="${e.name}" class="inputText" name="name" id="name">
                                <p class="error" id="error-empty-name">*Name must not empty!</p>
                            </td>
                        </tr>
                        <tr>
                            <th>Email:</th>
                            <td><input type="text" value="${e.email}" class="inputText" name="email" id="email">
                                <p class="error" id="error-exist-email">*Email is exist!</p>
                                <p class="error" id="error-empty-email">*Email must not empty!</p>
                            </td>
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
                                <p class="error" id="error-empty-gender">*Choose gender!</p>
                            </td>
                        </tr>
                        <tr>
                            <th>Date Of Birth:</th>
                            <td><input type="date" value="${e.dob}" name="dob" class="inputText" id="dob">
                                <p class="error" id="error-empty-dob">*Birth Date must not empty!</p>
                            </td>
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
                        <input type="button" value="Save" id="buttonSave" onclick="validationValue()">
                        <input type="button" value="Reset" id="buttonReset" onclick="resetForm()">
                    </div>
                </form>
            </div>
        </div>
        <div id="changePassword" style="padding-top: 30px;">

        </div>
    </body>
</html>
<script>
        var listEmail = []
        <c:forEach items="${listAd}" var="l">
            listEmail.push('${l.email}')
        </c:forEach>
        <c:forEach items="${listEmp}" var="l">
            listEmail.push('${l.email}')
        </c:forEach>
    function validationValue() {
        var isValid = true
        var name = document.getElementById("name").value
        var email = document.getElementById("email").value
        var gender = document.getElementsByName("gender")
        var dob = document.getElementById("dob").value
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
        if (gender[0].checked == false && gender[1].checked == false) {
            document.getElementById("error-empty-gender").style.display = "block"
            isValid = false
        } else {
            document.getElementById("error-empty-gender").style.display = "none"
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
            alert("Edit Successfully!")
            document.getElementById("formEdit").submit()
        }
    }
    function resetForm() {
        var error = document.getElementsByClassName("error")
        for(var i = 0; i < error.length; i++) {
            error[i].style.display = "none"
        }
        document.getElementById("formEdit").reset()
    }
</script>
