<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New author</title>
</head>
<body>
<c:import url="/jsp/navigation.jsp"/>
<main class="content-wrapper">
    <div class="container-fluid">
        <form method="POST" action="${pageContext.request.contextPath}/library-servlet">
            <input type="hidden" name="command" value="APPEND_AUTHOR"/>
            <p>Input the author's name:</p>
            <input name="authorName" required>
            <button type="submit">Append</button>
        </form>
        <br>
        ${result}
    </div>
</main>
</body>
</html>
