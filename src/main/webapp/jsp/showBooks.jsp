<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class=" container table-responsive">
    <table class="table">
        <thead class="bg-light">
        <tr>
            <th scope="col">ISBN</th>
            <th scope="col">Title</th>
            <th scope="col">Author's name</th>
            <th scope="col">Year of publishing</th>
            <th scope="col">Number of pages</th>
        </tr>
        </thead>
        <tbody class="table-striped">
        <c:if test="${not empty book}">
            <tr>
                <td>${book.bookISBN}</td>
                <td>${book.bookName}</td>
                <td>${book.authorName}</td>
                <td>${book.yearOfPublishing}</td>
                <td>${book.numberOfPages}</td>
            </tr>
        </c:if>
        <c:forEach var="bookElem" items="${booksList}">
            <tr>
                <td>${bookElem.bookISBN}</td>
                <td>${bookElem.bookName}</td>
                <td>${bookElem.authorName}</td>
                <td>${bookElem.yearOfPublishing}</td>
                <td>${bookElem.numberOfPages}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
