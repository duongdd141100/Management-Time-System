<%-- 
    Document   : TimeKeepingForm
    Created on : Mar 13, 2021, 9:43:14 AM
    Author     : Do Duc Duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="employeeView/css/TimeKeeping.css" rel="stylesheet" type="text/css"/>
        <link href="employeeView/css/TimeKeppingHeader.css" rel="stylesheet" type="text/css"/>
        <script src="employeeView/js/LoadChagePassword.js" type="text/javascript"></script>
        <script src="employeeView/js/CheckConfirmPassword.js" type="text/javascript"></script>
        <title>Time Keeping</title>
    </head>
    <body>
        <div class="option">
            <div class="optionLeft">
                <a id="fpt" href="time-keeping">AZPro</a>
                <a id="timeReport" href="work-time-report">Work Time Report</a>
                <a id="absent" href="absent-history">Absent History</a>
                <a id="password" onclick="loadChangPass()">Change Password</a>
            </div>


            <div class="optionRight">
                <a id="request" href="send-request">Send Request<img src="employeeView/icons/sendRequest.png"></a>
                <a id="logout" href="logout">Logout<img src="employeeView/icons/logout.png"></a>
            </div>


        </div>
        <div>
            <c:if test="${requestScope.inWorkingTime}">
                <h4 id="inWorkingTime">Note: You are in Work Time</h4>
            </c:if>
            <c:if test="${!requestScope.inWorkingTime}">
                <h4 id="notInWorkingTime">Note: You are not in Work Time</h4>
            </c:if>
            <hr>
        </div>

        <div class="message">
            <div class="title">
                <h3>Message</h3>
            </div>
            <div class="detail">
                <p>Hello ${requestScope.employeeName}</p>
            </div>
        </div>
        <div id="changePassword" style="padding-top: 30px;">

        </div>
    </body>
</html>
