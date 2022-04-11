package com.epam.architecture.controllers.service;

import com.epam.architecture.controllers.dto.BookmarkDTO;
import com.epam.architecture.model.Book;
import com.epam.architecture.service.BookmarkService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class UserController {

    private final BookmarkService bookmarkService;
    private final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    public UserController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @PostMapping("/add")
    public void addBookmark(@RequestBody BookmarkDTO bookmark) {
        bookmarkService.addBookmark(authentication.getName(), bookmark.getIsbn(), bookmark.getPageNumber());
    }

    @DeleteMapping("/deleteBookmark")
    public void deleteBookmark(@RequestParam String isbn, @RequestParam int pageNumber) {
        bookmarkService.deleteBookmark(authentication.getName(), isbn, pageNumber);
    }

    @GetMapping("/bookmarks")
    public List<Book> booksWithUserBookmarks() {
        return bookmarkService.takeBooksWithUserBookmarks(authentication.getName());
    }
}
