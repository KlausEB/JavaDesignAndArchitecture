package com.epam.architecture;

import java.io.IOException;

public interface IUserInterface {
    void startWorking() throws IOException;

    void loginAccount() throws IOException;

    void signUpAccount() throws IOException;

    void takeCommands();

    void appendBook() throws IOException;

    void deleteBook() throws IOException;

    void appendAuthor() throws IOException;

    void deleteAuthor() throws IOException;

    void searchBooksByPartAuthorName() throws IOException;

    void searchBooksByPartName() throws IOException;

    void searchBookByISBN() throws IOException;

    void searchBooksByYearRange() throws IOException;

    void searchBooksByYearPagesPartName() throws IOException;

    void appendBookmark() throws IOException;

    void deleteBookmark() throws IOException;

    void takeBooksWithMyBookmarks() throws IOException;

    void createNewUser() throws IOException;

    void banUser() throws IOException;

    void takeHistoryOperations() throws IOException;

    void saveData() throws IOException;

    void loadData() throws IOException;
}
