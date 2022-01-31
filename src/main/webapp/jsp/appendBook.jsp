<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New book</title>
</head>
<body>
<c:import url="/jsp/navigation.jsp"/>
<main class="content-wrapper">
    <div class="container-fluid">
        <form method="POST" action="${pageContext.request.contextPath}/library-servlet">
            <input type="hidden" name="command" value="APPEND_BOOK"/>
            <p>Input the ISBN:</p>
            <input name="bookISBN" required>
            <p>Input the author name:</p>
            <input name="authorName" required>
            <p>Input the year of publishing:</p>
            <input name="yearOfPublishing" required>
            <p>Input the number of pages:</p>
            <input name="numberOfPages" required>
            <button type="submit">Append</button>
        </form>
        <br>
        ${result}
    </div>
</main>
</body>
</html>
