<%-- 
    Document   : ChangePasswordForm
    Created on : Mar 15, 2021, 2:46:25 PM
    Author     : Do Duc Duong
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="employeeView/css/TimeKeppingHeader.css" rel="stylesheet" type="text/css"/>
        <link href="employeeView/css/ChangePass.css" rel="stylesheet" type="text/css"/>
        <!--<script src="employeeView/js/CheckConfirmPassword.js" type="text/javascript"></script>-->
        <title>Change Password</title>
    </head>
    <body>
        
<!--        <div class="option">
            <div class="optionLeft">
                <a id="fpt" href="time-keeping">AZPro</a>
                <a id="timeReport" href="work-time-report">Work Time Report</a>
                <a id="absent" href="absent-history">Absent History</a>
                <a id="password" href="change-password">Change Password</a>
            </div>


            <div class="optionRight">
                <a id="request" href="send-request">Send Request<img src="employeeView/icons/sendRequest.png"></a>
                <a id="logout" href="logout">Logout<img src="employeeView/icons/logout.png"></a>
            </div>
        </div>-->

        <div>
            <div class="titleChangePass">
                <h3>Change Password</h3>
            </div>
            <div class="changePassForm">
                <form action="change-password" id="changePassForm" method="POST">
                    <div>
                        <p>Username</p>
                        <input id="email" type="text" value="${account.username}" readonly >
                    </div>

                    <div>
                        <p>Password</p>
                        <input type="password" name="password" id="passwordId" required>
                    </div>
                    <div>
                        <p>New Password</p>
                        <input type="password" name="newPass" id="newPass" required>
                    </div>
                    <div>
                        <p>New Password Confirm</p>
                        <input type="password" id="newCfPass" required>
                    </div>
                        <input type="button" value="Save Change" onclick="checkValidPass('newPass', 'newCfPass', 'changePassForm', '${account.password}', 'passwordId')" id="save">
                </form>
            </div>
        </div>
    </body>
</html>

