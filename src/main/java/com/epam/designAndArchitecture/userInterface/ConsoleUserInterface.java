package com.epam.designAndArchitecture.userInterface;

import com.epam.designAndArchitecture.IUserInterface;
import com.epam.designAndArchitecture.exceptions.LoginStateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUserInterface implements IUserInterface {
    public static final String WELCOME_MESSAGE = "Hello, user. LogIn or SignUp?";
    public static final String LOGIN_REQUEST_MESSAGE = "Input login:";
    public static final String PASSWORD_REQUEST_MESSAGE = "Input password:";
    public static final String INVALID_ACCOUNT_DATA = "Invalid username or password";
    public static final String COMMAND_REQUEST_MESSAGE = "Input commands:";
    public static final String AUTHOR_MESSAGE = "Input author name:";
    public static final String BOOK_MESSAGE = "Input book name:";
    public static final String ISBN_MESSAGE = "Input ISBN:";
    public static final String YEAR_MESSAGE = "Input year of publishing:";
    public static final String NUMBER_PAGE_MESSAGE = "Input number of page:";
    public static final String PART_NAME_MESSAGE = "Input part of the name:";
    public static final String NUMBER_RANGE_MESSAGE = "Input min and max numbers:";
    public static final String UNDEFINED_COMMAND_MESSAGE = "An undefined command was entered";
    public static final String DONE_COMMAND_WORKING_MESSAGE = "!!!The command worked out!!!";

    public static final String SUCCESSFULLY_APPENDED_MESSAGE = "The addition was successful";
    public static final String UNSUCCESSFULLY_APPENDED_MESSAGE = "The addition was unsuccessful";
    public static final String SUCCESSFULLY_DELETED_MESSAGE = "The deletion was successful";
    public static final String UNSUCCESSFULLY_DELETED_MESSAGE = "The deletion was unsuccessful";
    public static final Logger logger = LogManager.getLogger();
    public static final String HISTORY_REGISTRATION_ATTEMPT = ":  registration request";
    public static final String HISTORY_APPEND = "Try to append: ";
    public static final String HISTORY_DELETE = "Try to delete: ";
    public static final String HISTORY_SEARCH_BOOKS = "Searching books by: ";
    public static final String HISTORY_REQUEST = "History request";
    private final LibraryService libraryService = new LibraryService();
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void startWorking() throws IOException {
        System.out.println(WELCOME_MESSAGE);
        String userInput = reader.readLine();
        CommandsEnum currentCommand = CommandsEnum.valueOf(userInput.toUpperCase());
        if (currentCommand.equals(CommandsEnum.LOGIN)) {
            loginAccount();
        } else if (currentCommand.equals(CommandsEnum.SIGNUP)) {
            signUpAccount();
        } else {
            throw new LoginStateException(UNDEFINED_COMMAND_MESSAGE);
        }
        while (true) {
            System.out.println(COMMAND_REQUEST_MESSAGE);
            userInput = reader.readLine();
            currentCommand = CommandsEnum.valueOf(userInput.toUpperCase());
            switch (currentCommand) {
                case ADD_AUTHOR:
                    appendAuthor();
                    break;
                case DELETE_AUTHOR:
                    deleteAuthor();
                    break;
                case ADD_BOOK:
                    appendBook();
                    break;
                case DELETE_BOOK:
                    deleteBook();
                    break;
                case SEARCH_BOOKS_BY_PART_AUTHOR_NAME:
                    searchBooksByPartAuthorName();
                    break;
                case SEARCH_BOOKS_BY_PART_NAME:
                    searchBooksByPartName();
                    break;
                case SEARCH_BOOK_BY_ISBN:
                    searchBookByISBN();
                    break;
                case SEARCH_BOOKS_BY_YEAR_RANGE:
                    searchBooksByYearRange();
                    break;
                case SEARCH_BOOKS_BY_YEAR_PAGES_PART_NAME:
                    searchBooksByYearPagesPartName();
                    break;
                case APPEND_BOOKMARK:
                    appendBookmark();
                    break;
                case DELETE_BOOKMARK:
                    deleteBookmark();
                    break;
                case TAKE_BOOKS_WITH_MY_BOOKMARKS:
                    takeBooksWithMyBookmarks();
                    break;
                case CREATE_NEW_USER:
                    createNewUser();
                    break;
                case BAN_USER:
                    banUser();
                    break;
                case TAKE_HISTORY_OPERATIONS:
                    takeHistoryOperations();
                    break;
            }
        }
    }

    @Override
    public void loginAccount() throws IOException {
        while (true) {
            System.out.println(LOGIN_REQUEST_MESSAGE);
            String login = reader.readLine();
            System.out.println(PASSWORD_REQUEST_MESSAGE);
            String password = reader.readLine();
            logger.info(login + HISTORY_REGISTRATION_ATTEMPT);
            if (libraryService.requestLogInAccount(login, password)) {
                logger.info("DONE");
                break;
            }
            logger.info(INVALID_ACCOUNT_DATA);
            System.out.println(INVALID_ACCOUNT_DATA);
        }
    }

    @Override
    public void signUpAccount() throws IOException {
        while (true) {
            System.out.println(LOGIN_REQUEST_MESSAGE);
            String login = reader.readLine();
            System.out.println(PASSWORD_REQUEST_MESSAGE);
            String password = reader.readLine();
            logger.info(login + HISTORY_REGISTRATION_ATTEMPT);
            if (libraryService.requestSignUpAccount(login, password)) {
                logger.info("DONE");
                break;
            }
            logger.info(INVALID_ACCOUNT_DATA);
            System.out.println(INVALID_ACCOUNT_DATA);
        }
    }

    @Override
    public void appendBook() throws IOException {
        System.out.println(AUTHOR_MESSAGE);
        String authorName = reader.readLine();
        System.out.println(BOOK_MESSAGE);
        String bookName = reader.readLine();
        System.out.println(YEAR_MESSAGE);
        int yearOfPublishing = Integer.parseInt(reader.readLine());
        System.out.println(NUMBER_PAGE_MESSAGE);
        int numberOfPages = Integer.parseInt(reader.readLine());
        System.out.println(ISBN_MESSAGE);
        String bookISBN = reader.readLine();

        logger.info(HISTORY_APPEND + bookName + " book");
        appendTry(libraryService.requestAppendBook(authorName,
                bookName, yearOfPublishing, numberOfPages, bookISBN));

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void deleteBook() throws IOException {
        System.out.println(ISBN_MESSAGE);
        String bookISBN = reader.readLine();

        logger.info(HISTORY_DELETE + bookISBN + " book");
        deleteTry(libraryService.requestDeleteBook(bookISBN));

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void appendAuthor() throws IOException {
        System.out.println(AUTHOR_MESSAGE);
        String authorName = reader.readLine();

        logger.info(HISTORY_APPEND + authorName + " author");
        appendTry(libraryService.requestAppendAuthor(authorName));

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void deleteAuthor() throws IOException {
        System.out.println(AUTHOR_MESSAGE);
        String authorName = reader.readLine();

        logger.info(HISTORY_DELETE + authorName + " author");
        deleteTry(libraryService.requestDeleteAuthor(authorName));

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void searchBooksByPartAuthorName() throws IOException {
        System.out.println(PART_NAME_MESSAGE);
        String partName = reader.readLine();

        logger.info(HISTORY_SEARCH_BOOKS + '\'' + partName + '\'' + " part author name");
        String booksByPartAuthorName = libraryService.requestBooksByPartAuthorName(partName);

        System.out.println(booksByPartAuthorName);

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void searchBooksByPartName() throws IOException {
        System.out.println(PART_NAME_MESSAGE);
        String partName = reader.readLine();

        logger.info(HISTORY_SEARCH_BOOKS + '\'' + partName + '\'' + " part book name");
        String booksByPartName = libraryService.requestBooksByPartName(partName);
        System.out.println(booksByPartName);

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void searchBookByISBN() throws IOException {
        System.out.println(ISBN_MESSAGE);
        String ISBN = reader.readLine();

        logger.info(HISTORY_SEARCH_BOOKS + '\'' + ISBN + '\'' + " ISBN");
        String bookByISBN = libraryService.requestBookByISBN(ISBN);
        System.out.println(bookByISBN);

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void searchBooksByYearRange() throws IOException {
        System.out.println(NUMBER_RANGE_MESSAGE);
        int minYear = Integer.parseInt(reader.readLine());
        int maxYear = Integer.parseInt(reader.readLine());

        logger.info(HISTORY_SEARCH_BOOKS + "from " + minYear + " to " + maxYear + " years");
        String booksByYearRange = libraryService.requestBooksByYearRange(minYear, maxYear);
        System.out.println(booksByYearRange);

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void searchBooksByYearPagesPartName() throws IOException {
        System.out.println(YEAR_MESSAGE);
        int yearOfPublishing = Integer.parseInt(reader.readLine());
        System.out.println(NUMBER_PAGE_MESSAGE);
        int numberOfPages = Integer.parseInt(reader.readLine());
        System.out.println(PART_NAME_MESSAGE);
        String partBookName = reader.readLine();

        logger.info(HISTORY_SEARCH_BOOKS + yearOfPublishing + " year, " + numberOfPages + " number of pages " + partBookName + " part book name");
        String booksByYearPagesPartName = libraryService.requestByYearPagesPartName(yearOfPublishing, numberOfPages, partBookName);
        System.out.println(booksByYearPagesPartName);

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void appendBookmark() throws IOException {
        System.out.println(ISBN_MESSAGE);
        String isbn = reader.readLine();
        System.out.println(NUMBER_PAGE_MESSAGE);
        int pageNumber = Integer.parseInt(reader.readLine());

        logger.info(HISTORY_APPEND + "bookmark");
        appendTry(libraryService.requestAppendBookmark(isbn, pageNumber));

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void deleteBookmark() throws IOException {
        System.out.println(ISBN_MESSAGE);
        String isbn = reader.readLine();
        System.out.println(NUMBER_PAGE_MESSAGE);
        int pageNumber = Integer.parseInt(reader.readLine());

        logger.info(HISTORY_DELETE + "bookmark by " + isbn + " ISBN " + pageNumber + " page number");

        deleteTry(libraryService.requestDeleteBookmark(isbn, pageNumber));

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void takeBooksWithMyBookmarks() throws IOException {
        logger.info(HISTORY_SEARCH_BOOKS + "bookmarks");
        String booksWithMyBookmarks = libraryService.requestBooksWithUserBookmarks();
        System.out.println(booksWithMyBookmarks);

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void createNewUser() throws IOException {
        System.out.println(LOGIN_REQUEST_MESSAGE);
        String login = reader.readLine();
        System.out.println(PASSWORD_REQUEST_MESSAGE);
        String password = reader.readLine();

        logger.info(HISTORY_APPEND + login + "user");
        appendTry(libraryService.requestAppendNewUser(login, password));

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void banUser() throws IOException {
        System.out.println(LOGIN_REQUEST_MESSAGE);
        String login = reader.readLine();

        logger.info(HISTORY_APPEND + "new user " + login);
        deleteTry(libraryService.requestBanUser(login));

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void takeHistoryOperations() throws IOException {
        logger.info(HISTORY_REQUEST);
        System.out.println(libraryService.requestTakeHistory());

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    private void appendTry(boolean task) {
        if (task) {
            logger.info(SUCCESSFULLY_APPENDED_MESSAGE);
            System.out.println(SUCCESSFULLY_APPENDED_MESSAGE);
        } else {
            logger.info(UNSUCCESSFULLY_APPENDED_MESSAGE);
            System.out.println(UNSUCCESSFULLY_APPENDED_MESSAGE);
        }
    }

    private void deleteTry(boolean task) {
        if (task) {
            logger.info(SUCCESSFULLY_DELETED_MESSAGE);
            System.out.println(SUCCESSFULLY_DELETED_MESSAGE);
        } else {
            logger.info(UNSUCCESSFULLY_DELETED_MESSAGE);
            System.out.println(UNSUCCESSFULLY_DELETED_MESSAGE);
        }
    }
}
