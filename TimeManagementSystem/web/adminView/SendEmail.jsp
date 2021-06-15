<%-- 
    Document   : SendEmail
    Created on : Mar 22, 2021, 4:25:27 PM
    Author     : Do Duc Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="employeeView/css/TimeKeppingHeader.css" rel="stylesheet" type="text/css"/>
        <link href="adminView/css/SendEmail.css" rel="stylesheet" type="text/css"/>
        <script src="employeeView/js/LoadChagePassword.js" type="text/javascript"></script>
        <script src="employeeView/js/CheckConfirmPassword.js" type="text/javascript"></script>
        <title>Send Email</title>
    </head>
    <body>
        <%@include file="header.jsp" %>

        <div id="detail">
            <div class="title">
                <h3>New Message</h3>
            </div>
            <div id="message">
                <form action="send-email" method="POST" id="form">
                    <div id="divTo">
                        <span>To</span> <input id="email" value="${employee.email}" name="email" readonly>

                    </div>
                    <hr>
                    <div id="divSubject">
                        <span>Subject</span> <input id="subject" name="subject">

                    </div>
                    <hr>
                    <textarea name="message" id="text"></textarea>
                    <input onclick="sendEmail('subject', 'text', 'form')" type="submit" value="Send" id='submit'>
                </form>
            </div>
        </div>
        <div id="changePassword" style="padding-top: 30px;">

        </div>
    </body>
</html>
<script>
    function sendEmail(idSubject, idMessage, form) {
        var subject = document.getElementById(idSubject)
        var message = document.getElementById(idMessage)
        if (subject.value.trim().length == 0 || message.value.trim().length == 0) {
            if (confirm('Send this message without a subject or text in the body?')) {
                document.getElementById(form).submit()
            }
        } else {
            console.log(form)
            document.getElementById(form).submit()
        }
    }
</script>