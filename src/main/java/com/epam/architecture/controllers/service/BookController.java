package com.epam.architecture.controllers.service;

import com.epam.architecture.controllers.dto.BookDTO;
import com.epam.architecture.service.LiteratureService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class BookController {

    private final LiteratureService literatureService;

    public BookController(LiteratureService literatureService) {
        this.literatureService = literatureService;
    }

    @PostMapping("/add")
    public boolean addBook(@RequestBody BookDTO book) {
        return literatureService.appendBook(
                book.getAuthorName(),
                book.getBookName(),
                book.getYearOfPublishing(),
                book.getNumberOfPages(),
                book.getBookISBN());
    }

    @DeleteMapping("/delete/{bookISBN}")
    public void deleteBook(@PathVariable String bookISBN) {
        literatureService.deleteBook(bookISBN);
    }

    @PostMapping("/addAuthor/{authorName}")
    public void addAuthor(@PathVariable String authorName) {
        literatureService.appendAuthor(authorName);
    }

    @DeleteMapping("/deleteAuthor/{authorName}")
    public void deleteAuthor(@PathVariable String authorName) {
        literatureService.deleteAuthor(authorName);
    }
}
