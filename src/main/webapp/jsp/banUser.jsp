<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ban page</title>
</head>
<body>
<c:import url="/jsp/navigation.jsp"/>
<main class="content-wrapper">
    <div class="container-fluid">
        <form method="POST" action="library-servlet">
            <input type="hidden" name="command" value="BAN_USER"/>
            <p>Input the login of the banned user:</p>
            <input name="deleteLogin" required>
            <button type="submit">Ban</button>
        </form>
        <br>
        ${result}
    </div>
</main>
</body>
</html>
