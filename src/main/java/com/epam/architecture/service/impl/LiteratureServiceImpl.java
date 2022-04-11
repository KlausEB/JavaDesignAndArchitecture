package com.epam.architecture.service.impl;

import com.epam.architecture.exceptions.RestoreFromDataSourceException;
import com.epam.architecture.model.Author;
import com.epam.architecture.model.Book;
import com.epam.architecture.repository.JpaAuthorRepository;
import com.epam.architecture.repository.JpaBookRepository;
import com.epam.architecture.service.LiteratureService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiteratureServiceImpl implements LiteratureService {
    private final JpaBookRepository bookRepository;
    private final JpaAuthorRepository authorRepository;

    public LiteratureServiceImpl(JpaBookRepository bookRepository, JpaAuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Book createBook(String authorName,
                           String bookName, int yearOfPublishing, int numberOfPages, String bookISBN) {
        return new Book(
                authorRepository.findById(authorName).orElseThrow(() ->
                        new RestoreFromDataSourceException("Author doesn't exist")),
                bookName,
                yearOfPublishing,
                numberOfPages,
                bookISBN);
    }

    @Override
    public void appendAuthor(String authorName) {
        Author newAuthor = createAuthor(authorName);
        authorRepository.save(newAuthor);
    }

    @Override
    public void deleteAuthor(String authorName) {
        authorRepository.deleteById(authorName);
    }

    @Override
    public boolean appendBook(String authorName,
                              String bookName, int yearOfPublishing, int numberOfPages, String bookISBN) {
        if (!authorRepository.existsById(authorName)) {
            return false;
        }
        Book newBook = createBook(authorName, bookName, yearOfPublishing, numberOfPages, bookISBN);
        bookRepository.save(newBook);
        return true;
    }

    @Override
    public void deleteBook(String bookISBN) {
        bookRepository.deleteById(bookISBN);
    }

    @Override
    public List<Book> searchBooksByPartAuthorName(String partName) {
        return bookRepository.findByAuthorName_AuthorNameLike('%' + partName + '%');
    }

    @Override
    public List<Book> searchBooksByPartName(String partName) {
        return bookRepository.findByBookNameLike('%' + partName + '%');
    }

    @Override
    public Book searchBookByISBN(String isbn) {
        return bookRepository.findById(isbn).orElseThrow(() ->
                new RestoreFromDataSourceException("Book doesn't exist"));
    }

    @Override
    public List<Book> searchBooksByYearRange(int minYear, int maxYear) {
        return bookRepository.findByYearOfPublishingBetween(minYear, maxYear);
    }

    @Override
    public List<Book> searchBooksByYearPagesPartName(int yearOfPublishing, int numberOfPages, String partName) {
        return bookRepository.findByYearOfPublishingAndNumberOfPagesAndBookNameLike(yearOfPublishing, numberOfPages, '%' + partName + '%');
    }
}
