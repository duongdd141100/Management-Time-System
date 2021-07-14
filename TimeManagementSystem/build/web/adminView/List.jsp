<%-- 
    Document   : List
    Created on : Mar 20, 2021, 9:35:04 AM
    Author     : Do Duc Duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="adminView/css/List.css" rel="stylesheet" type="text/css"/>
        <link href="employeeView/css/TimeKeppingHeader.css" rel="stylesheet" type="text/css"/>
        
        <script src="employeeView/js/LoadChagePassword.js" type="text/javascript"></script>
        <script src="employeeView/js/CheckConfirmPassword.js" type="text/javascript"></script>
        
        <title>List Employee</title>
    </head>
    <body>
        <%@include file="admin-header.jsp" %>

        <div id="detail">
            <div class="title">
                <h3>Information</h3>
            </div>
            <div id="table">
                <table>
                    <tr>
                        <th>Name</th>
                        <th>Gender</th>
                        <th>DOB</th>
                        <th>Email</th>
                        <th>From</th>
                        <th>To</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach items="${listEmployee}" var="l">
                        <tr>
                            <td>${l.name}</td>   
                            <c:if test="${l.gender}">
                                <td>Male</td>  
                            </c:if>
                            <c:if test="${!l.gender}">
                                <td>Female</td>  
                            </c:if>                  
                            <td>${l.dob}</td>                        
                            <td>${l.email}</td>                        
                            <td>${l.from}</td>                        
                            <td>${l.to}</td>   
                            <td><a id='edit' href="edit-employee?username=${l.username}">Edit</a> | 
                                <a id='delete' onclick="confirmDelete('${l.username}')">Delete</a> | 
                                <a id='view' href="view-work-time-employee?username=${l.username}">View Work Time report</a> | 
                                <a id='sendEmail' href="send-email?username=${l.username}">Send Email</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>

        <div id="changePassword" style="padding-top: 30px;">

        </div>
    </body>
</html>

<script>
    function confirmDelete(username) {
        if (confirm("Are you sure want to delete?")) {
            window.location.href = "delete-employee?username=" + username + "";
        }
    }
    
</script>