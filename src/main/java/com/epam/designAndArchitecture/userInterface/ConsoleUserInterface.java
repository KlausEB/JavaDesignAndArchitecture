package com.epam.designAndArchitecture.userInterface;

import com.epam.designAndArchitecture.IUserInterface;
import com.epam.designAndArchitecture.exceptions.LoginStateException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ConsoleUserInterface implements IUserInterface {
    public static final String WELCOME_MESSAGE = "Hello, user. LogIn or SignUp?";
    public static final String ACCOUNT_REQUEST_MESSAGE = "Input login and password:";
    public static final String INVALID_ACCOUNT_DATA = "Invalid username or password";
    public static final String COMMAND_REQUEST_MESSAGE = "Input commands:";
    public static final String AUTHOR_MESSAGE = "Input author name:";
    public static final String BOOK_MESSAGE = "Input author name, " +
            "book name, " +
            "year of publishing, " +
            "number of pages, " +
            "ISBN:";
    public static final String ISBN_MESSAGE = "Input ISBN message:";
    public static final String PART_NAME_MESSAGE = "Input part of the name:";
    public static final String NUMBER_RANGE_MESSAGE = "Input min and max numbers:";
    public static final String YEAR_PAGES_NAME_PART_MESSAGE = "Input year of publishing, " +
            "number of pages, "+
            "part of the name of the book";
    public static final String UNDEFINED_COMMAND_MESSAGE = "An undefined command was entered";

    public static final String SUCCESSFULLY_APPENDED_MESSAGE = "The addition was successful";
    public static final String UNSUCCESSFULLY_APPENDED_MESSAGE = "The addition was unsuccessful";
    public static final String SUCCESSFULLY_DELETED_MESSAGE = "The deletion was successful";
    public static final String UNSUCCESSFULLY_DELETED_MESSAGE = "The deletion was unsuccessful";
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
            }
        }
    }

    @Override
    public void loginAccount() throws IOException {
        while (true) {
            System.out.println(ACCOUNT_REQUEST_MESSAGE);
            String login = reader.readLine();
            String password = reader.readLine();
            if (libraryService.requestLogInAccount(login, password)) {
                break;
            } else {
                System.out.println(INVALID_ACCOUNT_DATA);
            }
        }
    }

    @Override
    public void signUpAccount() throws IOException {
        while (true) {
            System.out.println(ACCOUNT_REQUEST_MESSAGE);
            String login = reader.readLine();
            String password = reader.readLine();
            if (libraryService.requestSignUpAccount(login, password)) {
                break;
            } else {
                System.out.println(INVALID_ACCOUNT_DATA);
            }
        }
    }

    @Override
    public void appendBook() throws IOException {
        System.out.println(BOOK_MESSAGE);
        String authorName = reader.readLine();
        String bookName = reader.readLine();
        int yearOfPublishing = Integer.parseInt(reader.readLine());
        int numberOfPages = Integer.parseInt(reader.readLine());
        String bookISBN = reader.readLine();
        if (libraryService.requestAppendBook(authorName,
                bookName, yearOfPublishing, numberOfPages, bookISBN)) {
            System.out.println(SUCCESSFULLY_APPENDED_MESSAGE);
        } else {
            System.out.println(UNSUCCESSFULLY_APPENDED_MESSAGE);
        }
    }

    @Override
    public void deleteBook() throws IOException {
        System.out.println(ISBN_MESSAGE);
        String bookISBN = reader.readLine();
        if (libraryService.requestDeleteBook(bookISBN)) {
            System.out.println(SUCCESSFULLY_DELETED_MESSAGE);
        } else {
            System.out.println(UNSUCCESSFULLY_DELETED_MESSAGE);
        }
    }

    @Override
    public void appendAuthor() throws IOException {
        System.out.println(AUTHOR_MESSAGE);
        String authorName = reader.readLine();
        if (libraryService.requestAppendAuthor(authorName)) {
            System.out.println(SUCCESSFULLY_APPENDED_MESSAGE);
        } else {
            System.out.println(UNSUCCESSFULLY_APPENDED_MESSAGE);
        }
    }

    @Override
    public void deleteAuthor() throws IOException {
        System.out.println(AUTHOR_MESSAGE);
        String authorName = reader.readLine();
        if (libraryService.requestDeleteAuthor(authorName)) {
            System.out.println(SUCCESSFULLY_DELETED_MESSAGE);
        } else {
            System.out.println(UNSUCCESSFULLY_DELETED_MESSAGE);
        }
    }

    @Override
    public void searchBooksByPartAuthorName() throws IOException {
        System.out.println(PART_NAME_MESSAGE);
        String partName = reader.readLine();
        List<String> booksByPartAuthorName = libraryService.requestBooksByPartAuthorName(partName);
        for (String currentBookString : booksByPartAuthorName) {
            System.out.println(currentBookString);
        }
    }

    @Override
    public void searchBooksByPartName() throws IOException {
        System.out.println(PART_NAME_MESSAGE);
        String partName = reader.readLine();
        List<String> booksByPartName = libraryService.requestBooksByPartName(partName);
        for (String currentBookString : booksByPartName) {
            System.out.println(currentBookString);
        }
    }

    @Override
    public void searchBookByISBN() throws IOException {
        System.out.println(ISBN_MESSAGE);
        String ISBN = reader.readLine();
        String bookByISBN = libraryService.requestBookByISBN(ISBN);
        System.out.println(bookByISBN);
    }

    @Override
    public void searchBooksByYearRange() throws IOException {
        System.out.println(NUMBER_RANGE_MESSAGE);
        int minYear = Integer.parseInt(reader.readLine());
        int maxYear = Integer.parseInt(reader.readLine());
        List<String> booksByYearRange = libraryService.requestBooksByYearRange(minYear, maxYear);
        for (String currentBookString : booksByYearRange) {
            System.out.println(currentBookString);
        }
    }

    @Override
    public void searchBooksByYearPagesPartName() throws IOException {
        System.out.println(YEAR_PAGES_NAME_PART_MESSAGE);
        int yearOfPublishing = Integer.parseInt(reader.readLine());
        int numberOfPages = Integer.parseInt(reader.readLine());
        String partName = reader.readLine();
        List<String> booksByYearPagesPartName = libraryService.requestByYearPagesPartName(yearOfPublishing, numberOfPages, partName);
        for (String currentBookString : booksByYearPagesPartName) {
            System.out.println(currentBookString);
        }
    }
}
