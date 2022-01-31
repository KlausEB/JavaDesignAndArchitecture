<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search books</title>
</head>
<body>
<c:import url="/jsp/navigation.jsp"/>
<main class="content-wrapper">
    <div class="container-fluid">
        <form method="GET" action="${pageContext.request.contextPath}/library-servlet">
            <input type="hidden" name="command" value="SEARCH_BOOKS_BY_PART_AUTHOR_NAME"/>
            <p>Input the part of the author's name:</p>
            <input name="authorName" required>
            <button type="submit">Search</button>
        </form>
    </div>
    <c:import url="/jsp/showBooks.jsp"/>
</main>
</body>
</html>
