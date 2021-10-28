package com.epam.designAndArchitecture;

import java.io.IOException;

public interface IUserInterface {
    void startWorking() throws IOException;

    void loginAccount() throws IOException;

    void signUpAccount() throws IOException;

    void appendBook() throws IOException;

    void deleteBook() throws IOException;

    void appendAuthor() throws IOException;

    void deleteAuthor() throws IOException;

    void searchBooksByPartAuthorName() throws IOException;

    void searchBooksByPartName() throws IOException;

    void searchBookByISBN() throws IOException;

    void searchBooksByYearRange() throws IOException;

    void searchBooksByYearPagesPartName() throws IOException;
}
