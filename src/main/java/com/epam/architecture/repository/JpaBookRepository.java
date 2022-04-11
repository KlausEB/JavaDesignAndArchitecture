package com.epam.architecture.repository;

import com.epam.architecture.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaBookRepository extends JpaRepository<Book, String> {

    List<Book> findByBookNameLike(String s);

    List<Book> findByYearOfPublishingBetween(int minYear, int maxYear);

    List<Book> findByYearOfPublishingAndNumberOfPagesAndBookNameLike(int yearOfPublishing, int numberOfPages, String s);

    List<Book> findByAuthorName_AuthorNameLike(String authorName);
}
