<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06.03.2018
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
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
        <c:if test="${isNotLogin}">
            <p class="warning">Invalid login or password!</p>
        </c:if>

    <form action="${pageContext.servletContext.contextPath}/login" method="POST">

        <div class="field">
            <label for="uName">Username:</label>
            <input type="text" id="uName" name="login" />
        </div>
        <div class="field">
            <label for="uPass">Password:</label>
            <input type="text" id="uPass" name="password" />
        </div>
        <div class="submit">
            <input type="submit" align="center" value="login"/>
        </div>


    </form>
    </div>

</div>
</body>
</html>
