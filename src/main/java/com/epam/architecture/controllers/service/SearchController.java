package com.epam.architecture.controllers.service;

import com.epam.architecture.controllers.dto.BookDTO;
import com.epam.architecture.service.LiteratureService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final LiteratureService literatureService;
    private final ModelMapper modelMapper;

    public SearchController(LiteratureService literatureService, ModelMapper modelMapper) {
        this.literatureService = literatureService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/booksByAuthorName/{partName}")
    public List<BookDTO> booksByPartAuthorName(@PathVariable String partName) {
        return literatureService.searchBooksByPartAuthorName(partName)
                .stream().map(x -> modelMapper.map(x, BookDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/booksByName/{partName}")
    public List<BookDTO> booksByPartName(@PathVariable String partName) {
        return literatureService.searchBooksByPartName(partName)
                .stream().map(x -> modelMapper.map(x, BookDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/booksByIsbn/{isbn}")
    public BookDTO bookByISBN(@PathVariable String isbn) {
        return modelMapper.map(literatureService.searchBookByISBN(isbn), BookDTO.class);
    }

    @GetMapping("/booksByYearRange")
    public List<BookDTO> booksByYearRange(@RequestParam int minYear, @RequestParam int maxYear) {
        return literatureService.searchBooksByYearRange(minYear, maxYear)
                .stream().map(x -> modelMapper.map(x, BookDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/booksByYearPagesName")
    public List<BookDTO> booksByYearPagesPartName(@RequestParam int yearOfPublishing, @RequestParam int numberOfPages, @RequestParam String partName) {
        return literatureService.searchBooksByYearPagesPartName(yearOfPublishing, numberOfPages, partName)
                .stream().map(x -> modelMapper.map(x, BookDTO.class)).collect(Collectors.toList());
    }
}
