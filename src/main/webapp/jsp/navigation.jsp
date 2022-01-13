<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/navStyle.css">

<nav class="navbar navbar-expand-lg navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="#">Home Library</a>
    <div class="collapse navbar-collapse">
        <ul class="navbar-nav sidenav">
            <li class="nav-item">
                <a class="nav-link nav-link-collapse"
                   href="#"
                   data-toggle="collapse"
                   data-target="#userCommands">User commands</a>
                <ul class="nav-second-level collapse" id="userCommands">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/appendAuthor.jsp">Append an
                            author</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/appendBook.jsp">Append a
                            book</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/appendBookmark.jsp">Append a
                            bookmark</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/deleteAuthor.jsp">Delete an
                            author</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/deleteBook.jsp">Delete a
                            book</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/deleteBookmark.jsp">Delete a
                            bookmark</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/searchBookByISBN.jsp">Search
                            for a book by ISBN</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/searchBooksByAuthor.jsp">Search
                            for books by part of the author's name</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/searchBooksByName.jsp">Search
                            for books by part of the book title</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/jsp/searchBooksByYearPagesName.jsp">Search for books
                            by year of publication, number of pages and part of the book title</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/searchBooksByYearRange.jsp">Search
                            for books by year range</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/library-servlet?command=TAKE_BOOKS_WITH_MY_BOOKMARKS">
                            Search for books with my bookmarks</a>
                    </li>
                </ul>
            <li class="nav-item">
                <a class="nav-link nav-link-collapse"
                   href="#"
                   data-toggle="collapse"
                   data-target="#adminCommands">For administrator</a>
                <ul class="nav-second-level collapse" id="adminCommands">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/createUser.jsp">
                            Create a new user
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/banUser.jsp">
                            Ban a user
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/showHistoryOperations.jsp">
                            Take a history
                        </a>
                    </li>
                </ul>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/library-servlet?command=SAVE_DATA">
                    Save data
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/library-servlet?command=LOG_OUT">
                    Log out
                </a>
            </li>
        </ul>
    </div>
</nav>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/js/bootstrap.min.js"></script>