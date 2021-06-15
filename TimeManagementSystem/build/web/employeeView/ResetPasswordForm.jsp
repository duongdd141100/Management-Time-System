<%-- 
    Document   : ResetPasswordForm
    Created on : Mar 19, 2021, 1:59:41 AM
    Author     : Do Duc Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<link href="employeeView/css/ResetPassword.css" rel="stylesheet" type="text/css"/>-->
        <link href="employeeView/css/ForgetPassword.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="timeKeeping">
            <h4>Company TimeKeeping</h4>
        </div>
            <div id="fogetPassForm">
                <form action="forget-password" method="POST" id="form">
                <p for="">Code Confirm:</p>
                <input type="number" name="code" id="code"><br>

                <p for="">New Password:</p>
                <input type="password" id="pass" name="pass"><br>

                <p for="">Password Confirm:</p>
                <input type="password" id="passCf" name="passCf"><br>
                <input type="hidden" value="${email}" name="email">
                <button type="button" id="buttonSubmit" onclick="checkCodeAndPass()">Submit</button>
                
            </form>
        </div>
    </body>
</html>


<script>
    function checkCodeAndPass() {
        var codeCf = document.getElementById("code").value
        var pass = document.getElementById("pass").value
        var passCf = document.getElementById("passCf").value
        console.log(${code})
        if(codeCf != ${code}) {
            alert('Code invalid')
            return
        }
        if(pass != passCf) {
            alert('Password Confirm invalid')
            return
        }
        alert("Reset Password Successfully!")
        document.getElementById("form").submit()
    }
</script>
