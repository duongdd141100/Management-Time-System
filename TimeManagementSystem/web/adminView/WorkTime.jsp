<%-- 
    Document   : WorkTime
    Created on : Mar 23, 2021, 1:39:29 PM
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
        <link href="adminView/css/WorkTime.css" rel="stylesheet" type="text/css"/>
        <script src="employeeView/js/LoadChagePassword.js" type="text/javascript"></script>
        <script src="employeeView/js/CheckConfirmPassword.js" type="text/javascript"></script>
        <title>Work Time</title>
    </head>
    <body>
        <c:if test="${isAdd != null}">
            <script>
                alert('Slot is exist!')
            </script>
        </c:if>
        <%@include file="header.jsp" %>
        

        <div id="detail">
            <div class="title">
                <h3>Work Time</h3>
            </div>
            <div id="table">
                <table>
                    <tr>
                        <th>Slot</th>
                        <th>From</th>
                        <th>To</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach items="${listWorkTime}" var="l">
                        <tr>
                            <td><input type="text" value="${l.id}" name="id${l.id}" readonly></td>
                            <td><input type="time" value="${l.from}" name="from${l.id}" readonly id="from${l.id}"></td>
                            <td><input type="time" value="${l.to}" name="to${l.id}" readonly id="to${l.id}"></td>
                            <td class="action">
                                <form method="POST" action="time-work" id="formEdit${l.id}">
                                    <input type="hidden" value="${l.id}" name="id">
                                    <input type="hidden" name="from" id="fromInForm${l.id}">
                                    <input type="hidden" name="to" id="toInForm${l.id}">
                                </form>
                                <form method="POST" action="time-work" id="formDelete${l.id}">
                                    <input type="hidden" value="${l.id}" name="id">
                                </form>
                                <button class="buttonEdit" id="buttonEdit${l.id}" onclick="edit('from${l.id}', 'to${l.id}', 'buttonSave${l.id}', 'buttonEdit${l.id}', 'buttonDelete${l.id}')">Edit</button>
                                <button class="buttonDelete" id="buttonDelete${l.id}" onclick="deleteSubmit('formDelete${l.id}')">Delete</button>
                                <button class="buttonSave" id="buttonSave${l.id}" onclick="saveSubmit('formEdit${l.id}', 'from${l.id}', 'to${l.id}', 'fromInForm${l.id}', 'toInForm${l.id}')">Save</button>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr id="trAdd">
                        <td><input type="number" id="idAdd" name="id"></td>
                        <td><input type="time" id="fromAdd" name="from"></td>
                        <td><input type="time" id="toAdd" name="to"></td>
                        <td>
                            <form action="time-work" method="post" id="formAdd">
                                <input type="hidden" id="idAddInForm" name="id">
                                <input type="hidden" id="fromAddInForm" name="from">
                                <input type="hidden" id="toAddInForm" name="to">
                                <input type="hidden" value="true" name="isAdd">
                                <input type="button" value="Save" id="saveAdd" onclick="testValueAdd('idAdd', 'fromAdd', 'toAdd', 'idAddInForm', 'fromAddInForm', 'toAddInForm', 'formAdd')">
                            </form>
                            
                        </td>
                    </tr>
                </table>
                <div id="divAdd">
                    <button id="buttonAdd" onclick="addRecord('trAdd', 'idAdd', 'fromAdd', 'toAdd', 'saveAdd')"></button>
                </div>


            </div>
        </div>
            <div id="changePassword" style="padding-top: 30px;">

        </div>
    </body>
</html>

<script>
    function edit(from, to, buttonSave, buttonEdit, buttonDelete) {
        var classSave = document.getElementsByClassName("buttonSave")
        var classDelete = document.getElementsByClassName("buttonDelete")
        var classEdit = document.getElementsByClassName("buttonEdit")
        var classInput = document.getElementsByTagName("input")
        for(var i = 0; i < classInput.length; i++) {
            classInput[i].style.border = 'none'
        }
        for(var i = 0; i < classSave.length; i++) {
            classSave[i].style.display = 'none'
            classEdit[i].style.display = 'block'
            classDelete[i].style.display = 'block'
        }
        document.getElementById("trAdd").style.display = 'none'
        var inputFrom = document.getElementById(from)
        var inputTo = document.getElementById(to)
        inputFrom.readOnly = false
        inputTo.readOnly = false
        inputFrom.style.border = '1px solid #ccc'
        inputTo.style.border = '1px solid #ccc'

        document.getElementById(buttonSave).style.display = 'block'
        document.getElementById(buttonEdit).style.display = 'none'
        document.getElementById(buttonDelete).style.display = 'none'

    }
    function deleteSubmit(form) {
        document.getElementById(form).submit()
    }

    function saveSubmit(form, from, to, fromInform, toInfrom) {
        document.getElementById(fromInform).value = document.getElementById(from).value
        document.getElementById(toInfrom).value = document.getElementById(to).value
        document.getElementById(form).submit()
    }
    function addRecord(trAdd, id, from, to, save) {
        var classSave = document.getElementsByClassName("buttonSave")
        var classDelete = document.getElementsByClassName("buttonDelete")
        var classEdit = document.getElementsByClassName("buttonEdit")
        var classInput = document.getElementsByTagName("input")
        for(var i = 0; i < classInput.length; i++) {
            classInput[i].style.border = 'none'
        }
        for(var i = 0; i < classSave.length; i++) {
            classSave[i].style.display = 'none'
            classEdit[i].style.display = 'block'
            classDelete[i].style.display = 'block'
        }
        document.getElementById(trAdd).style.display = 'contents'
        document.getElementById(id).style.border = '1px solid #ccc'
        document.getElementById(from).style.border = '1px solid #ccc'
        document.getElementById(to).style.border = '1px solid #ccc'
    }
    function testValueAdd(idAdd, fromAdd, toAdd, idAddInForm,fromAddInForm, toAddInForm, form) {
        var id = document.getElementById(idAdd)
        var from = document.getElementById(fromAdd)
        var to = document.getElementById(toAdd)
        if(id.value.trim().length == 0 || from.value.trim().length == 0 || to.value.trim().length == 0) {
            alert('Please enter all value to add!')
        } else {
            document.getElementById(idAddInForm).value = id.value
            document.getElementById(fromAddInForm).value = from.value
            document.getElementById(toAddInForm).value = to.value
            document.getElementById(form).submit()
        }
        
    }
</script>