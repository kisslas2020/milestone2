package com.codecool.examlibrary.controller;

import com.codecool.examlibrary.model.Book;
import com.codecool.examlibrary.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        if (book.getId() != null) {
            Book saved = findById(book.getId());
            saved.setAuthor(book.getAuthor());
            saved.setTitle(book.getTitle());
            saved.setYear(book.getYear());
            return bookService.createBook(saved);
        }
        return bookService.createBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @GetMapping("/search")
    public List<Book> findByYear(@RequestParam int year) {
        return bookService.findByYear(year);
    }
}
