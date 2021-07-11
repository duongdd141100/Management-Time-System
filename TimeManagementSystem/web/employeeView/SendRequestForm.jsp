<%-- 
    Document   : SendRequestForm
    Created on : Mar 18, 2021, 8:44:22 AM
    Author     : Do Duc Duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="employeeView/css/TimeKeppingHeader.css" rel="stylesheet" type="text/css"/>
        <link href="employeeView/css/SendRequest.css" rel="stylesheet" type="text/css"/>
        <script src="employeeView/js/LoadChagePassword.js" type="text/javascript"></script>
        <script src="employeeView/js/CheckConfirmPassword.js" type="text/javascript"></script>
        <title>Send Request</title>
    </head>
    <body>
        <%@include file="emp-header.jsp" %>
        

        <div class="request">
            <div class="title">
                <h3>Request</h3>
            </div>
            <div class="detail">
                <form action="send-request" method="POST">
                    <div>
                        <p>Request:</p>
                        <select name="requestId" id="requestChoice">
                            <c:forEach items="${listRequest}" var="l">
                                <option value="${l.id}">${l.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div>
                        <p>Date</p>
                        <input id="dateInput" type="date" required name="date">
                    </div>
                    <div>
                        <p>Reason</p>
                        <textarea name="reason" id="" cols="" rows="4" required></textarea>
                    </div>

                    <input type="submit" value="Submit" id="submit">

                </form>
                <c:if test="${isSubmit}">
                    <script>alert('Send request successfully!')</script>
                </c:if>

            </div>

        </div>
        <div id="changePassword" style="padding-top: 30px;">

        </div>
    </body>
</html>
