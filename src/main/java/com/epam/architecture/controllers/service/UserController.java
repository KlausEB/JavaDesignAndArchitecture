package com.epam.architecture.controllers.service;

import com.epam.architecture.controllers.dto.BookDTO;
import com.epam.architecture.controllers.dto.BookmarkDTO;
import com.epam.architecture.service.BookmarkService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class UserController {

    private final BookmarkService bookmarkService;
    private final ModelMapper modelMapper;

    public UserController(BookmarkService bookmarkService, ModelMapper modelMapper) {
        this.bookmarkService = bookmarkService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public void addBookmark(@RequestBody BookmarkDTO bookmark) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        bookmarkService.addBookmark(authentication.getName(), bookmark.getIsbn(), bookmark.getPageNumber());
    }

    @DeleteMapping("/deleteBookmark")
    public void deleteBookmark(@RequestParam String isbn, @RequestParam int pageNumber) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        bookmarkService.deleteBookmark(authentication.getName(), isbn, pageNumber);
    }

    @GetMapping("/bookmarks")
    public List<BookDTO> booksWithUserBookmarks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return bookmarkService.takeBooksWithUserBookmarks(authentication.getName())
                .stream().map(x -> modelMapper.map(x, BookDTO.class)).collect(Collectors.toList());
    }
}
