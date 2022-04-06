package com.epam.architecture.repository;

import com.epam.architecture.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBookmarkRepository extends JpaRepository<Bookmark, Long> {
}
