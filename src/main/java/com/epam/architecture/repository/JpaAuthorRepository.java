package com.epam.architecture.repository;

import com.epam.architecture.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaAuthorRepository extends JpaRepository<Author, String> {
    List<Author> findByAuthorNameLike(String s);
}
