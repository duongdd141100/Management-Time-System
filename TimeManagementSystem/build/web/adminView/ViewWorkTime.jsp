<%-- 
    Document   : ViewWorkTime
    Created on : Mar 22, 2021, 8:01:13 AM
    Author     : Do Duc Duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="employeeView/css/TimeKeppingHeader.css" rel="stylesheet" type="text/css"/>
        <link href="adminView/css/ViewWorkTime.css" rel="stylesheet" type="text/css"/>
        <script src="employeeView/js/RederPager.js" type="text/javascript"></script>
        <script src="employeeView/js/LoadChagePassword.js" type="text/javascript"></script>
        <script src="employeeView/js/CheckConfirmPassword.js" type="text/javascript"></script>
        <title>Work Time Employee</title>
    </head>
    <body>
        <div class="option">
            <div class="optionLeft">
                <a id="fpt" href="manage-employee">Employees</a>
                <a id="timeReport" href="time-work">Time Work</a>
                <a id="password" onclick="loadChangPass()">Change Password</a>
            </div>

            <div class="optionRight">
                <a id="request" href="view-request">View Request</a>
                <a id="logout" href="logout">Logout<img src="employeeView/icons/logout.png"></a>
            </div>
        </div>

        <div id="detail">
            <div class="title">
                <h3>Report: ${employee.name}</h3>
            </div>
            <div id="table">
                <table>
                    <tr>
                        <th>Date</th>
                        <th>From</th>
                        <th>To</th>
                        <th>Total</th>
                        <th>Status</th>
                        <th>Action</th>
                        <th>Accepter</th>
                    </tr>
                    <c:set var="index" value='0'/>
                    <c:forEach items="${listReport}" var="l">

                        <tr>
                            <td>${l.date}</td>                  
                            <td>${l.from}</td>                        
                            <td>${l.to}</td>    
                            <td>${l.total}</td>
                            <c:if test="${l.acceptType == 1}">
                                <td id="accepted">Accepted</td>
                                <td></td>
                                <c:forEach items="${listAdmin}" var="la">
                                    <c:if test="${la.username == l.accepter}">
                                        <td>${la.name}</td>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                            <c:if test="${l.acceptType == 0}">
                                <td id="processing">Processing</td>
                                <td>
                                    <c:set var="index" value="${index + 1}"/>
                                    <form action="view-work-time-employee" method="POST" id="form${index}">
                                        <input type="hidden" value="${employee.username}" name="username">
                                        <input type="hidden" value="${l.date}" name="date">
                                        <input type="hidden" value="${l.from}" name="from">
                                        <input type="hidden" value="${l.to}" name="to">
                                        <input type="hidden" name="acceptType" id="acceptType${index}">
                                        <input type="hidden" name="accepter" value="${requestScope.usernameAdmin}">
                                    </form>
                                    <button id="buttonAccept" onclick="accept('form${index}', 'acceptType${index}')">Accept</button>
                                    <button id="buttonReject" onclick="reject('form${index}', 'acceptType${index}')">Reject</button>
                                </td>
                                <td>${l.accepter}</td>
                            </c:if>
                            <c:if test="${l.acceptType == -1}">
                                <td id="reject">Reject</td>
                                <td></td>
                                <c:forEach items="${listAdmin}" var="la">
                                    <c:if test="${la.username == l.accepter}">
                                        <td>${la.name}</td>
                                    </c:if>
                                </c:forEach>
                            </c:if>

                        </tr>
                    </c:forEach>
                </table>
                <div id="pager"></div>
            </div>
        </div>
        <div id="changePassword" style="padding-top: 30px;">

        </div>
    </body>
</html>
<script>
    RenderPagerWithUser('pager', ${requestScope.pageIndex}, ${requestScope.totalPage}, 2, '${requestScope.url}', '${employee.username}')
    function accept(form, acceptType) {
        document.getElementById(acceptType).value = '1'
        document.getElementById(form).submit()
    }
    function reject(form, acceptType) {
        document.getElementById(acceptType).value = '-1'
        document.getElementById(form).submit()
    }
</script>