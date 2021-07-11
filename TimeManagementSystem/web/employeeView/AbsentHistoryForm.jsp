<%-- 
    Document   : AbsentHistoryView
    Created on : Mar 16, 2021, 8:02:58 AM
    Author     : Do Duc Duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="employeeView/css/TimeKeppingHeader.css" rel="stylesheet" type="text/css"/>
        <link href="employeeView/css/AbsentHistory.css" rel="stylesheet" type="text/css"/>
        <script src="employeeView/js/RederPager.js" type="text/javascript"></script>
        <script src="employeeView/js/LoadChagePassword.js" type="text/javascript"></script>
        <script src="employeeView/js/CheckConfirmPassword.js" type="text/javascript"></script>
        <title>Absent History</title>
    </head>
    <body>
        <%@include file="emp-header.jsp" %>
        

        <div class="history">
            <div id="seach">
                <form action="absent-history" method="GET" id="dateFind">
                    <label for="dateFrom">From</label>
                    <input class="inputDate" type="date" id="dateFrom" name="dateFrom">
                    <label for="dateTo">To</label>
                    <input class="inputDate" type="date" id="dateTo" name="dateTo">
                    <input type="submit" value="Find" id="buttonFind">
                </form>
                <div id="monthFind">
                    <a href="absent-history?thisMonth=true">This Month</a>
                    <a href="absent-history?thisMonth=false">Last Month</a>
                </div>
            </div>
            <div class="title">
                <p>Report</p>
            </div>
            <div>
                <table>
                    <tr>
                        <th>Request</th>
                        <th>Date</th>
                        <th>Reason</th>
                        <th>Status</th>
                    </tr>

                    <c:if test="${listAbsent != null}">
                        <c:forEach items="${requestScope.listAbsent}" var="l">
                            <tr>
                                <td>${l.request.name}</td>
                                <td>${l.date}</td>
                                <td>${l.reason}</td>
                                <c:if test="${l.acceptType == 1}">
                                    <td id="accepted">Accepted</td>
                                </c:if>
                                <c:if test="${l.acceptType == 0}">
                                    <td id="processing">Processing</td>
                                </c:if>
                                <c:if test="${l.acceptType == -1}">
                                    <td id="reject">Reject</td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </c:if>
                </table>
                <c:if test="${listAbsent.size() == 0}">
                    <h3>Have no any request</h3>
                </c:if>
                <div id="pager"></div>
            </div>

        </div>
        <div id="changePassword" style="padding-top: 30px;">

        </div>

    </body>
</html>
<script>
    RenderPager('pager', ${requestScope.pageIndex}, ${totalPage}, 2, '${requestScope.url}')
</script>