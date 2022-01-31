<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log In</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/loginForm.css">
</head>
<body class="text-center">
<form class="form-group" method="GET" action="${pageContext.request.contextPath}/library-servlet">
    <input type="hidden" name="command" value="login"/>
    <div class="form-group mx-sm-3 mb-2">
        <label for="login">Login</label>
        <input class="form-control" id="login" name="login" required>
    </div>
    <div class="form-group mx-sm-3 mb-2">
        <label for="password">Password</label>
        <input class="form-control" id="password" type="password" name="password" required>
    </div>
    <button type="submit" class="btn">Log In</button>
    <br>
    <a href="${pageContext.request.contextPath}/jsp/signup.jsp">Sign Up</a>
    <br>
    ${incorrectLoginPassword}
</form>
</body>
</html>
