package com.epam.architecture.service.impl;

import com.epam.architecture.exceptions.RestoreFromDataSourceException;
import com.epam.architecture.model.Book;
import com.epam.architecture.model.Bookmark;
import com.epam.architecture.repository.JpaBookRepository;
import com.epam.architecture.repository.JpaBookmarkRepository;
import com.epam.architecture.repository.JpaUserRepository;
import com.epam.architecture.service.BookmarkService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookmarkServiceImpl implements BookmarkService {
    private final JpaBookmarkRepository bookmarkRepository;
    private final JpaUserRepository userRepository;
    private final JpaBookRepository bookRepository;

    public BookmarkServiceImpl(JpaBookmarkRepository bookmarkRepository, JpaUserRepository userRepository, JpaBookRepository bookRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void addBookmark(String userLogin, String isbn, int pageName) {
        Bookmark newBookmark = createBookmark(userLogin, isbn, pageName);
        bookmarkRepository.save(newBookmark);
    }

    @Override
    public Bookmark createBookmark(String userLogin, String isbn, int numberOfPage) {
        return new Bookmark(
                userRepository.findById(userLogin).orElseThrow(() ->
                        new RestoreFromDataSourceException("User doesn't exist")),
                bookRepository.findById(isbn).orElseThrow(() ->
                        new RestoreFromDataSourceException("Book doesn't exist")),
                numberOfPage);
    }

    @Override
    public void deleteBookmark(String userLogin, String isbn, int pageNumber) {
        Bookmark deleteBookmark = createBookmark(userLogin, isbn, pageNumber);
        bookmarkRepository.delete(deleteBookmark);
    }

    @Override
    public List<Book> takeBooksWithUserBookmarks(String login) {
        List<Bookmark> bookmarksList = bookmarkRepository.findByUserLogin_Login(login);
        return bookmarksList.stream()
                .map(Bookmark::getBook)
                .collect(Collectors.toList());
    }
}
