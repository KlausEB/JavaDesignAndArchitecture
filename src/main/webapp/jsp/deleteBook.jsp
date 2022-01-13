<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete book</title>
</head>
<body>
<c:import url="/jsp/navigation.jsp"/>
<main class="content-wrapper">
    <div class="container-fluid">
        <form method="POST" action="library-servlet">
            <input type="hidden" name="command" value="DELETE_BOOK"/>
            <p>Input the ISBN:</p>
            <input name="bookISBN" required>
            <button type="submit">Delete</button>
        </form>
        <br>
        ${result}
    </div>
</main>
</body>
</html>
