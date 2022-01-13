package com.epam.architecture.webapp.commands;

public enum CommandsEnum {
    LOGIN(new LoginCommand()),
    SIGNUP(new SignUpCommand()),
    COMMANDS_INFO(null),
    APPEND_BOOK(new AppendBookCommand()),
    DELETE_BOOK(new DeleteBookCommand()),
    APPEND_AUTHOR(new AppendAuthorCommand()),
    DELETE_AUTHOR(new DeleteAuthorCommand()),
    SEARCH_BOOKS_BY_PART_AUTHOR_NAME(new SearchBooksByPartAuthorCommand()),
    SEARCH_BOOKS_BY_PART_NAME(new SearchBooksByPartNameCommand()),
    SEARCH_BOOK_BY_ISBN(new SearchBookByISBNCommand()),
    SEARCH_BOOKS_BY_YEAR_RANGE(new SearchBooksByYearRangeCommand()),
    SEARCH_BOOKS_BY_YEAR_PAGES_PART_NAME(new SearchBooksByYearPagesPartNameCommand()),
    APPEND_BOOKMARK(new AppendBookmarkCommand()),
    DELETE_BOOKMARK(new DeleteBookmarkCommand()),
    TAKE_BOOKS_WITH_MY_BOOKMARKS(new UserBookmarkBooksCommand()),
    CREATE_NEW_USER(new CreateUserCommand()),
    BAN_USER(new BanUserCommand()),
    TAKE_HISTORY_OPERATIONS(new HistoryOperationsCommand()),
    SAVE_DATA(new SaveDataCommand()),
    LOG_OUT(new LogoutCommand()),
    COOKIE(new CookieCommand());

    private final WebCommand currentCommand;

    CommandsEnum(WebCommand currentCommand) {
        this.currentCommand = currentCommand;
    }

    public WebCommand getCurrentCommand() {
        return currentCommand;
    }
}
