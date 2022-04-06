package com.epam.architecture.controllers.service;

import com.epam.architecture.model.Book;
import com.epam.architecture.service.LiteratureService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final LiteratureService literatureService;

    public SearchController(LiteratureService literatureService) {
        this.literatureService = literatureService;
    }


    @GetMapping("/booksByAuthorName/{partName}")
    public List<Book> booksByPartAuthorName(@PathVariable String partName) {
        return literatureService.searchBooksByPartAuthorName(partName);
    }

    @GetMapping("/booksByName/{partName}")
    public List<Book> booksByPartName(@PathVariable String partName) {
        return literatureService.searchBooksByPartName(partName);
    }

    @GetMapping("/booksByIsbn/{isbn}")
    public Book bookByISBN(@PathVariable String isbn) {
        return literatureService.searchBookByISBN(isbn);
    }

    @GetMapping("/booksByYearRange")
    public List<Book> booksByYearRange(@RequestParam int minYear, @RequestParam int maxYear) {
        return literatureService.searchBooksByYearRange(minYear, maxYear);
    }

    @GetMapping("/booksByYearPagesName")
    public List<Book> booksByYearPagesPartName(@RequestParam int yearOfPublishing, @RequestParam int numberOfPages, @RequestParam String partName) {
        return literatureService.searchBooksByYearPagesPartName(yearOfPublishing, numberOfPages, partName);
    }
}
