package com.epam.architecture.repository;

import com.epam.architecture.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaBookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findByUserLogin_Login(String login);
}
