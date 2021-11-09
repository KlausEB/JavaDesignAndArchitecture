package com.epam.architecture.userinterface;

import com.epam.architecture.App;
import com.epam.architecture.IUserInterface;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUserInterface implements IUserInterface {
    public static final String WELCOME_MESSAGE = "Hello, user. LogIn or SignUp?\n(Input 'LOGIN' or 'SIGNUP')";
    public static final String LOGIN_REQUEST_MESSAGE = "Input login:";
    public static final String PASSWORD_REQUEST_MESSAGE = "Input password:";
    public static final String INVALID_ACCOUNT_DATA = "Invalid username or password";
    public static final String COMMAND_REQUEST_MESSAGE = "Input commands (Input 'COMMANDS_INFO' to display more information about the commands):";
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

    public static final Logger logger = App.logger;
    public static final String HISTORY_REGISTRATION_ATTEMPT = ":  registration request";
    public static final String HISTORY_APPEND = "Try to append: ";
    public static final String HISTORY_DELETE = "Try to delete: ";
    public static final String HISTORY_SEARCH_BOOKS = "Searching books by: ";
    public static final String HISTORY_REQUEST = "History request";

    public static final String EXCEPTION_USER_DATA_MESSAGE = "User data could not be retrieved";
    public static final String EXCEPTION_BOOK_DATA_MESSAGE = "Book data could not be retrieved";
    public static final String EXCEPTION_AUTHOR_DATA_MESSAGE = "Author data could not be retrieved";

    public static final String SEARCH_OUTPUT_PATTERN = "{}'{}' {}";

    private final LibraryService libraryService = new LibraryService();
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void startWorking() throws IOException {
        loadData();
        System.out.println(WELCOME_MESSAGE);
        String userInput = reader.readLine();
        CommandsEnum currentCommand = CommandsEnum.valueOf(userInput.toUpperCase());
        if (currentCommand.equals(CommandsEnum.LOGIN)) {
            loginAccount();
        } else if (currentCommand.equals(CommandsEnum.SIGNUP)) {
            signUpAccount();
        } else {
            System.out.println(UNDEFINED_COMMAND_MESSAGE);
        }
        userCommandExecutor();
    }

    @Override
    public void loginAccount() throws IOException {
        while (true) {
            String login;
            String password;
            try {
                System.out.println(LOGIN_REQUEST_MESSAGE);
                login = reader.readLine();
                System.out.println(PASSWORD_REQUEST_MESSAGE);
                password = reader.readLine();
            } catch (IOException e) {
                logger.error(EXCEPTION_USER_DATA_MESSAGE);
                throw e;
            }
            logger.info("{}{}",login, HISTORY_REGISTRATION_ATTEMPT);
            if (libraryService.logInAccount(login, password)) {
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
            String login;
            String password;
            try {
                System.out.println(LOGIN_REQUEST_MESSAGE);
                login = reader.readLine();
                System.out.println(PASSWORD_REQUEST_MESSAGE);
                password = reader.readLine();
            } catch (IOException e) {
                logger.error(EXCEPTION_USER_DATA_MESSAGE, e);
                throw e;
            }
            logger.info("{}{}",login, HISTORY_REGISTRATION_ATTEMPT);
            if (libraryService.signUpAccount(login, password)) {
                logger.info("DONE");
                break;
            }
            logger.info(INVALID_ACCOUNT_DATA);
            System.out.println(INVALID_ACCOUNT_DATA);
        }
    }

    @Override
    public void takeCommands() {
        for (CommandsEnum currentCommand : CommandsEnum.values()) {
            if (!currentCommand.equals(CommandsEnum.LOGIN) && !currentCommand.equals(CommandsEnum.SIGNUP) && !currentCommand.equals(CommandsEnum.COMMANDS_INFO)) {
                System.out.println(currentCommand);
            }
        }
    }

    @Override
    public void appendBook() throws IOException {
        String authorName;
        String bookName;
        int yearOfPublishing;
        int numberOfPages;
        String bookISBN;
        try {
            System.out.println(AUTHOR_MESSAGE);
            authorName = reader.readLine();
            System.out.println(BOOK_MESSAGE);
            bookName = reader.readLine();
            System.out.println(YEAR_MESSAGE);
            yearOfPublishing = Integer.parseInt(reader.readLine());
            System.out.println(NUMBER_PAGE_MESSAGE);
            numberOfPages = Integer.parseInt(reader.readLine());
            System.out.println(ISBN_MESSAGE);
            bookISBN = reader.readLine();
        } catch (IOException e) {
            logger.error(EXCEPTION_BOOK_DATA_MESSAGE, e);
            throw e;
        }

        logger.info("{}{} book",HISTORY_APPEND, bookName);
        appendTry(libraryService.appendBook(authorName,
                bookName, yearOfPublishing, numberOfPages, bookISBN));

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void deleteBook() throws IOException {
        System.out.println(ISBN_MESSAGE);
        String bookISBN;
        try {
            bookISBN = reader.readLine();
        } catch (IOException e) {
            logger.error(EXCEPTION_USER_DATA_MESSAGE, e);
            throw e;
        }

        logger.info("{}{} book",HISTORY_DELETE, bookISBN);
        deleteTry(libraryService.deleteBook(bookISBN));

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void appendAuthor() throws IOException {
        System.out.println(AUTHOR_MESSAGE);
        String authorName;
        try {
            authorName = reader.readLine();
        } catch (IOException e) {
            logger.error(EXCEPTION_AUTHOR_DATA_MESSAGE, e);
            throw e;
        }


        logger.info("{}{} author", HISTORY_APPEND,authorName);
        appendTry(libraryService.appendAuthor(authorName));

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void deleteAuthor() throws IOException {
        System.out.println(AUTHOR_MESSAGE);
        String authorName;
        try {
            authorName = reader.readLine();
        } catch (IOException e) {
            logger.error(EXCEPTION_AUTHOR_DATA_MESSAGE, e);
            throw e;
        }

        logger.info("{}{} author", HISTORY_DELETE, authorName);
        deleteTry(libraryService.deleteAuthor(authorName));

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void searchBooksByPartAuthorName() throws IOException {
        System.out.println(PART_NAME_MESSAGE);
        String partName;
        try {
            partName = reader.readLine();
        } catch (IOException e) {
            logger.error(EXCEPTION_AUTHOR_DATA_MESSAGE, e);
            throw e;
        }

        logger.info(SEARCH_OUTPUT_PATTERN,HISTORY_SEARCH_BOOKS, partName, "part author name");
        String booksByPartAuthorName = libraryService.booksByPartAuthorName(partName);

        System.out.println(booksByPartAuthorName);

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void searchBooksByPartName() throws IOException {
        System.out.println(PART_NAME_MESSAGE);
        String partName;
        try {
            partName = reader.readLine();
        } catch (IOException e) {
            logger.error(EXCEPTION_BOOK_DATA_MESSAGE, e);
            throw e;
        }

        logger.info(SEARCH_OUTPUT_PATTERN,HISTORY_SEARCH_BOOKS, partName, "part book name");
        String booksByPartName = libraryService.booksByPartName(partName);
        System.out.println(booksByPartName);

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void searchBookByISBN() throws IOException {
        System.out.println(ISBN_MESSAGE);
        String isbn;
        try {
            isbn = reader.readLine();
        } catch (IOException e) {
            logger.error(EXCEPTION_BOOK_DATA_MESSAGE, e);
            throw e;
        }

        logger.info(SEARCH_OUTPUT_PATTERN,HISTORY_SEARCH_BOOKS, isbn, "ISBN");
        String bookByISBN = libraryService.bookByISBN(isbn);
        System.out.println(bookByISBN);

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void searchBooksByYearRange() throws IOException {
        System.out.println(NUMBER_RANGE_MESSAGE);
        int minYear;
        int maxYear;
        try {
            minYear = Integer.parseInt(reader.readLine());
            maxYear = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            logger.error(EXCEPTION_BOOK_DATA_MESSAGE, e);
            throw e;
        }

        logger.info("{} from {} to {} + years",HISTORY_SEARCH_BOOKS, minYear, maxYear);
        String booksByYearRange = libraryService.booksByYearRange(minYear, maxYear);
        System.out.println(booksByYearRange);

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void searchBooksByYearPagesPartName() throws IOException {
        int yearOfPublishing;
        int numberOfPages;
        String partBookName;
        try {
            System.out.println(YEAR_MESSAGE);
            yearOfPublishing = Integer.parseInt(reader.readLine());
            System.out.println(NUMBER_PAGE_MESSAGE);
            numberOfPages = Integer.parseInt(reader.readLine());
            System.out.println(PART_NAME_MESSAGE);
            partBookName = reader.readLine();
        } catch (IOException e) {
            logger.error(EXCEPTION_BOOK_DATA_MESSAGE, e);
            throw e;
        }

        logger.info("{}{} year, {} number of pages, {} part book name",HISTORY_SEARCH_BOOKS, yearOfPublishing, numberOfPages, partBookName);
        String booksByYearPagesPartName = libraryService.booksByYearPagesPartName(yearOfPublishing, numberOfPages, partBookName);
        System.out.println(booksByYearPagesPartName);

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void appendBookmark() throws IOException {
        String isbn;
        int pageNumber;
        try {
            System.out.println(ISBN_MESSAGE);
            isbn = reader.readLine();
            System.out.println(NUMBER_PAGE_MESSAGE);
            pageNumber = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            logger.error(EXCEPTION_BOOK_DATA_MESSAGE, e);
            throw e;
        }

        logger.info(HISTORY_APPEND + "bookmark");
        appendTry(libraryService.appendBookmark(isbn, pageNumber));

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void deleteBookmark() throws IOException {
        String isbn;
        int pageNumber;
        try {
            System.out.println(ISBN_MESSAGE);
            isbn = reader.readLine();
            System.out.println(NUMBER_PAGE_MESSAGE);
            pageNumber = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            logger.error(EXCEPTION_BOOK_DATA_MESSAGE, e);
            throw e;
        }

        logger.info("{} bookmark by {} ISBN {} page number",HISTORY_DELETE, isbn, pageNumber);

        deleteTry(libraryService.deleteBookmark(isbn, pageNumber));

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void takeBooksWithMyBookmarks() {
        logger.info(HISTORY_SEARCH_BOOKS + "bookmarks");
        String booksWithMyBookmarks;
        booksWithMyBookmarks = libraryService.booksWithUserBookmarks();
        System.out.println(booksWithMyBookmarks);

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void createNewUser() throws IOException {
        String login;
        String password;
        try {
            System.out.println(LOGIN_REQUEST_MESSAGE);
            login = reader.readLine();
            System.out.println(PASSWORD_REQUEST_MESSAGE);
            password = reader.readLine();
        } catch (IOException e) {
            logger.error(EXCEPTION_USER_DATA_MESSAGE);
            throw e;
        }

        logger.info("{}{}",login, HISTORY_REGISTRATION_ATTEMPT);
        appendTry(libraryService.appendNewUser(login, password));

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void banUser() throws IOException {
        System.out.println(LOGIN_REQUEST_MESSAGE);
        String login;
        try {
            login = reader.readLine();
        } catch (IOException e) {
            logger.error(EXCEPTION_USER_DATA_MESSAGE, e);
            throw e;
        }

        logger.info("{} new user {}",HISTORY_APPEND, login);
        deleteTry(libraryService.banUser(login));

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void takeHistoryOperations() {
        logger.info(HISTORY_REQUEST);
        System.out.println(libraryService.takeHistory());

        System.out.println(DONE_COMMAND_WORKING_MESSAGE);
    }

    @Override
    public void saveData() {
        libraryService.requestSerializeData();
    }

    @Override
    public void loadData() {
        libraryService.requestDeserializeData();
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

    private void userCommandExecutor() throws IOException {
        while (true) {
            System.out.println(COMMAND_REQUEST_MESSAGE);
            String userInput = reader.readLine();
            CommandsEnum currentCommand = CommandsEnum.valueOf(userInput.toUpperCase());
            switch (currentCommand) {
                case COMMANDS_INFO:
                    takeCommands();
                    break;
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
                case SAVE_DATA:
                    saveData();
                    break;
                case LOG_OUT:
                    saveData();
                    return;
                default:
                    System.out.println(UNDEFINED_COMMAND_MESSAGE);
                    logger.info(UNDEFINED_COMMAND_MESSAGE);
            }
        }
    }
}
