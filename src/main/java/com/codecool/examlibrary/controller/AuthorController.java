package com.codecool.examlibrary.controller;

import com.codecool.examlibrary.model.Author;
import com.codecool.examlibrary.model.Book;
import com.codecool.examlibrary.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private AuthorService authorService;


    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> findAll() {
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public Author findById(@PathVariable Long id) {
        return authorService.findById(id);
    }

    @PostMapping
    public Author createOrUpdate(@RequestBody Author author) {
        if (author.getId() != null) {
            Author saved = findById(author.getId());
            saved.setName(author.getName());
            saved.setBooks(author.getBooks());
            return authorService.createOrUpdate(saved);
        }
        return authorService.createOrUpdate(author);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        authorService.deleteById(id);
    }

    @GetMapping("/{id}/books")
    public List<Book> findBooksOfAuthor(@PathVariable Long id) {
        return authorService.findBooksOfAuthor(id);
    }

    @GetMapping("/search")
    public List<Author> findAuthorsWithBookCount(@RequestParam int c) {
        return authorService.findAuthorsWithBookCount(c);
    }
}
