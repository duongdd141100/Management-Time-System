<%-- 
    Document   : WorkTimeReportForm
    Created on : Mar 13, 2021, 11:15:17 AM
    Author     : Do Duc Duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="employeeView/css/WorkTimeReport.css" rel="stylesheet" type="text/css"/>
        <link href="employeeView/css/TimeKeppingHeader.css" rel="stylesheet" type="text/css"/>
        <script src="employeeView/js/RederPager.js" type="text/javascript"></script>
        <script src="employeeView/js/CalTimeSum.js" type="text/javascript"></script>
        <script src="employeeView/js/LoadChagePassword.js" type="text/javascript"></script>
        <script src="employeeView/js/CheckConfirmPassword.js" type="text/javascript"></script>
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


        <div class="report">
            <div id="seach">
                <form action="work-time-report" method="GET" id="dateFind">
                    <label for="dateFrom">From</label>
                    <input class="inputDate" type="date" id="dateFrom" name="dateFrom">
                    <label for="dateTo">To</label>
                    <input class="inputDate" type="date" id="dateTo" name="dateTo">
                    <input type="submit" value="Find" id="buttonFind">
                </form>
                <div id="monthFind">
                    <a href="work-time-report?thisMonth=true">This Month</a>
                    <a href="work-time-report?thisMonth=false">Last Month</a>
                </div>
            </div>
            <div class="overview">
                <div>
                    <p>Report</p>
                </div>

                <div class="parameters">
                    <span id="timeAccepted">Time Accepted: ${requestScope.timeAccepted}</span>
                    <span id="timNotAccepted">Time Not Accepted Yet: ${requestScope.timeNotAccepted}</span>
                    <span id="timeReject">Time Reject: ${requestScope.timeReject}</span>
                </div>            
            </div>
            <div>
                <table>
                    <tr>
                        <th>Date</th>
                        <th>Time Start</th>
                        <th>Time Finish</th>
                        <th>Total</th>
                        <th>Status</th>
                    </tr>
                    <c:forEach items="${requestScope.listReport}" var="r">
                        <tr>
                            <td>${r.date}</td>
                            <td>${r.from}</td>
                            <td>${r.to}</td>
                            <td class="time">${r.total}</td>
                            <c:if test="${r.acceptType == 1}">
                                <td id="accepted">Accepted</td>
                            </c:if>
                            <c:if test="${r.acceptType == 0}">
                                <td id="processing">Processing</td>
                            </c:if>
                            <c:if test="${r.acceptType == -1}">
                                <td id="reject">Reject</td>
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
//    document.querySelector('#totalOfHours').innerHTML = calTimeSum('.time');
    RenderPager('pager', ${requestScope.pageIndex}, ${totalPage}, 2, '${requestScope.url}')
</script>
