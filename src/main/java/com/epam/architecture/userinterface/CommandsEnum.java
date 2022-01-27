package com.epam.architecture.userinterface;

public enum CommandsEnum {
    LOGIN("ns2:logInAccount"),
    SIGNUP("ns2:signUpAccount"),
    COMMANDS_INFO(null),
    ADD_BOOK("ns2:addBook"),
    DELETE_BOOK("ns2:deleteBook"),
    ADD_AUTHOR("ns2:addAuthor"),
    DELETE_AUTHOR("ns2:deleteAuthor"),
    SEARCH_BOOKS_BY_PART_AUTHOR_NAME("ns2:booksByPartAuthorName"),
    SEARCH_BOOKS_BY_PART_NAME("ns2:booksByPartName"),
    SEARCH_BOOK_BY_ISBN("ns2:bookByISBN"),
    SEARCH_BOOKS_BY_YEAR_RANGE("ns2:booksByYearRange"),
    SEARCH_BOOKS_BY_YEAR_PAGES_PART_NAME("ns2:booksByYearPagesPartName"),
    ADD_BOOKMARK("ns2:addBookmark"),
    DELETE_BOOKMARK("ns2:deleteBookmark"),
    TAKE_BOOKS_WITH_MY_BOOKMARKS("ns2:booksWithUserBookmarks"),
    CREATE_NEW_USER("ns2:addNewUser"),
    BAN_USER("ns2:banUser"),
    TAKE_HISTORY_OPERATIONS("ns2:takeHistory"),
    SAVE_DATA("ns2:save"),
    LOG_OUT("ns2:logout");

    private final String requestType;

    CommandsEnum(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestType() {
        return requestType;
    }
}
