<%-- 
    Document   : LoginForm
    Created on : Mar 13, 2021, 7:36:32 AM
    Author     : Do Duc Duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="employeeView/css/login.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="timeKeeping">
            <h4>Company TimeKeeping</h4>
        </div>
        <div class="login">
            <div class="title">
                <h3>Login</h3>
            </div>
            <div id="loginForm">
                <form action="login" method="POST">
                    <p for="">Username:</p>
                    <input type="text" name="username">
                    <p for="">Password:</p>
                    <input type="password" name="password"><br>
                    <c:if test="${requestScope.isWrong}">
                        <label id="labelErr">*Username or Password is invalid!</label><br>
                        <script>alert('Username or Password is invalid!')</script>
                    </c:if>
                    <button type="submit">Login</button>
                    <a href="forget-password">Forget password?</a>
                </form>
            </div>
        </div>
    </body>
</html>
