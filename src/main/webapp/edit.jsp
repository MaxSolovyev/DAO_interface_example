<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02.03.2018
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
    <style>
        .field {
            clear: both;
            text-align: right;
            line-height: 25px;
        }
        .submit {
            margin-top: 5px;
        }
        label {
            float: left;
            padding-right: 10px;
        }
        .main {
            width: 253px;
            margin: 0 auto;
        }
        .warning {
            color: red;
            background-color: khaki;
        }
    </style>
</head>
<body>
<div align="center">
    <div class="main">
        <c:if test="${isNotValid}">
            <p class="warning">Invalid name or password!</p>
        </c:if>
    <form action="${pageContext.servletContext.contextPath}/admin/edit" method="POST">
        <input type="hidden" name="id" value="${user.id}">

        <div class="field">
            <label for="uName">Username:</label>
            <input type="text" id="uName" name="name" value="${user.name}" />
        </div>
        <div class="field">
            <label for="uPass">Password:</label>
            <input type="text" id="uPass" name="password" value="${user.password}" />
        </div>
        <div class="field">
            <label for="uRole">Role:</label>
            <input type="text" id="uRole" name="role" value="${user.role}" />
        </div>
        <div class="submit">
            <input type="submit" align="center" value="Submit"/>
        </div>

    </form>
    </div>
</div>

</body>
</html>
