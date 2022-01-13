<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New user</title>
</head>
<body>
<c:import url="/jsp/navigation.jsp"/>
<main class="content-wrapper">
    <div class="container-fluid">
        <form method="POST" action="library-servlet">
            <input type="hidden" name="command" value="CREATE_NEW_USER"/>
            <p>login</p>
            <input name="login" required>
            <p>password</p>
            <input type="password" name="password" required>
            <button type="submit">Create user</button>
        </form>
        <br>
        ${result}
    </div>
</main>
</body>
</html>
