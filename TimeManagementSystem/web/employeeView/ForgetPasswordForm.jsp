<%-- 
    Document   : ForgetPasswordForm
    Created on : Mar 18, 2021, 2:28:44 PM
    Author     : Do Duc Duong
--%>

<%@page import="employeeCotroller.JavaMailUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="employeeView/css/ForgetPassword.css" rel="stylesheet" type="text/css"/>
        <script src="employeeView/js/CheckEmail.js" type="text/javascript"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.0.min.js"></script>
        <title>Forget Password</title>
        <script>
            var listEmails = [];
          <c:forEach items="${emails}" var="e">
              listEmails.push('${e}')
          </c:forEach>
        </script>
    </head>
    <body>
        <div class="timeKeeping">
            <h4>Company TimeKeeping</h4>
        </div>
        <div class="login">
            <div class="title">
                <h3>Forget Password</h3>
            </div>
            <div id="fogetPassForm">
                <form action="forget-password" method="POST" id="form">
                    <p for="">Email:</p>
                    <input type="text" name="email" id="email"> 
                    <!--<div id='divSendEmail'></div>-->
                    <button type='button' id='sendCode' onclick="isValidEmail('email', listEmails)">Send</button>
                    

                </form>
            </div>
        </div>
    </body>
</html>

<script>
    $("#form").keypress(
  function(event){
    if (event.which == '13') {
      if(!isValidEmail('email', listEmails)) {
          event.preventDefault();
      }
    }
});
//    var listEmails = [];
//    <c:forEach items="${emails}" var="e">
//    listEmails.push('${e}')
//    </c:forEach>
//    document.getElementById("divSendEmail").innerHTML = "<button type='button' id='sendCode' onclick='"+isValidEmail('email', listEmails )+"'>Send</button>";
    function isValidEmail(id, emails) {
        var email = document.getElementById(id).value
        for (var i = 0; i < emails.length; i++) {
            if (email == emails[i]) {
                document.getElementById(id).readOnly = true;
                document.getElementById("form").submit()
                return true
            }
        }
        alert('Email Invalid')
        return false

    }
</script>