<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete author</title>
</head>
<body>
<c:import url="/jsp/navigation.jsp"/>
<main class="content-wrapper">
    <div class="container-fluid">
        <form method="POST" action="${pageContext.request.contextPath}/library-servlet">
            <input type="hidden" name="command" value="DELETE_AUTHOR"/>
            <p>Input the author's name:</p>
            <input name="authorName" required>
            <button type="submit">Delete</button>
        </form>
        <br>
        ${result}
    </div>
</main>
</body>
</html>
