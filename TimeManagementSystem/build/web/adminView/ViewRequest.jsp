<%-- 
    Document   : ViewRequest
    Created on : Mar 25, 2021, 8:53:48 AM
    Author     : Do Duc Duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="employeeView/css/TimeKeppingHeader.css" rel="stylesheet" type="text/css"/>
        <script src="employeeView/js/LoadChagePassword.js" type="text/javascript"></script>
        <script src="employeeView/js/CheckConfirmPassword.js" type="text/javascript"></script>
        <link href="adminView/css/ViewRequest.css" rel="stylesheet" type="text/css"/>
        <script src="employeeView/js/RederPager.js" type="text/javascript"></script>
        <title>View Request</title>
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

        <div id="report">
            <div id="title">
                <div>
                    <p>Request:</p>
                </div>

                <div>
                    <form action="view-request" method="GET" id="formSearch">
                        
                    
                        <select name="username" onchange="loadRequestOfUsername()">
                        <option value="all">All</option>
                        <c:forEach items="${listEmployee}" var="l">
                            <option value="${l.username}"
                                    <c:if test="${l.username == username}">
                                        selected
                                    </c:if>
                                    >${l.name}</option>
                        </c:forEach>
                    </select>
                    </form>
                </div>
            </div>


            <div id="detail">
                <table>
                    <tr>
                        <th>Name</th>
                        <th>Date</th>
                        <th>Request</th>
                        <th>Reason</th>
                        <th>Status</th>
                        <th>Action</th>
                        <th>Accepter</th>
                    </tr>
                    <c:set var="index" value='0'/>
                    <c:forEach items="${listAbsent}" var="la">
                        <tr>
                            <c:forEach items="${listEmployee}" var="lE">
                                <c:if test="${la.username == lE.username}">
                                    <td>${lE.name}</td>
                                </c:if>
                            </c:forEach>
                            <td>${la.date}</td>
                            <td>${la.request.name}</td>
                            <td>${la.reason}</td>
                            <c:if test="${la.acceptType == 1}">
                                <td id="accepted">Accepted</td>
                                <td></td>
                                <c:forEach items="${listAdmin}" var="l">
                                    <c:if test="${l.username == la.accepter}">
                                        <td>${l.name}</td>
                                    </c:if>
                                </c:forEach>
                                
                            </c:if>
                            <c:if test="${la.acceptType == 0}">
                                <td id="processing">Processing</td>
                                <td>
                                    <c:set var="index" value="${index + 1}"/>
                                    <form action="view-request" method="post" id="form${index}">
                                        <input type="hidden" name="username" value="${la.username}">
                                        <input type="hidden" name="date" value="${la.date}">
                                        <input type="hidden" name="reason" value="${la.reason}">
                                        <input type="hidden" name="requestId" value="${la.request.id}">
                                        <input type="hidden" name="acceptType" id="acceptType${index}">
                                        <input type="hidden" name="accepter" value="${usernameAdmin}">
                                    </form>
                                    <button id="buttonAccept" onclick="accept('form${index}', 'acceptType${index}')">Accept</button>
                                    <button id="buttonReject" onclick="reject('form${index}', 'acceptType${index}')">Reject</button>
                                </td>
                                <td></td>
                            </c:if>

                            <c:if test="${la.acceptType == -1}">
                                <td id="reject">Reject</td>
                                <td></td>
                                <c:forEach items="${listAdmin}" var="l">
                                    <c:if test="${l.username == la.accepter}">
                                        <td>${l.name}</td>
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
    RenderPager('pager', ${requestScope.pageIndex}, ${totalPage}, 2, '${requestScope.url}')
    function accept(form, acceptType) {
        document.getElementById(acceptType).value = '1'
        document.getElementById(form).submit()
    }
    function reject(form, acceptType) {
        document.getElementById(acceptType).value = '-1'
        document.getElementById(form).submit()
    }
    function loadRequestOfUsername() {
        document.getElementById("formSearch").submit()
    }
</script>
