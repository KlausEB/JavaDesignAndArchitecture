package com.epam.architecture.controllers.service;

import com.epam.architecture.controllers.dto.BookmarkDTO;
import com.epam.architecture.model.Book;
import com.epam.architecture.security.JwtTokenProvider;
import com.epam.architecture.service.BookmarkService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class UserController {

    private final BookmarkService bookmarkService;

    public UserController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @PostMapping("/add")
    public void addBookmark(@RequestBody BookmarkDTO bookmark, @RequestHeader String authorization) {
        bookmarkService.addBookmark(JwtTokenProvider.getLoginFromJwtToken(authorization), bookmark.getIsbn(), bookmark.getPageNumber());
    }

    @DeleteMapping("/deleteBookmark")
    public void deleteBookmark(@RequestParam String isbn, @RequestParam int pageNumber, @RequestHeader String authorization) {
        bookmarkService.deleteBookmark(JwtTokenProvider.getLoginFromJwtToken(authorization), isbn, pageNumber);
    }

    @GetMapping("/bookmarks")
    public List<Book> booksWithUserBookmarks(@RequestHeader String authorization) {
        return bookmarkService.takeBooksWithUserBookmarks(JwtTokenProvider.getLoginFromJwtToken(authorization));
    }
}
