<%-- 
    Document   : header
    Created on : Jun 15, 2021, 8:48:49 AM
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
                <a id="fpt" href="manage-employee">Employees</a>
                <a id="timeReport" href="time-work">Time Work</a>
                <a id="addEmployee" href="add-employee">Add Employee</a>
                <a id="password" onclick="loadChangPass()">Change Password</a>
            </div>

            <div class="optionRight">
                <a id="request" href="view-request">View Request</a>
                <a id="logout" href="logout">Logout<img src="employeeView/icons/logout.png"></a>
            </div>
        </div>
    </body>
</html>
