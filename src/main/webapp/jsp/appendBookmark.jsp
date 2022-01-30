<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New bookmark</title>
</head>
<body>
<c:import url="/jsp/navigation.jsp"/>
<main class="content-wrapper">
    <div class="container-fluid">
        <form method="POST" action="${pageContext.request.contextPath}/library-servlet">
            <input type="hidden" name="command" value="APPEND_BOOKMARK"/>
            <p>Input the ISBN:</p>
            <input name="bookISBN" required>
            <p>Input the page number:</p>
            <input name="pageNumber" required>
            <button type="submit">Append</button>
        </form>
        <br>
        ${result}
    </div>
</main>
</body>
</html>
