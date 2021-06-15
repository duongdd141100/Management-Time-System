<%-- 
    Document   : header.jsp
    Created on : Jun 15, 2021, 9:08:40 AM
    Author     : Do Duc Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
    </body>
</html>
