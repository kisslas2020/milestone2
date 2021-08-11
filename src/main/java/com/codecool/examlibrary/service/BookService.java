package com.codecool.examlibrary.service;

import com.codecool.examlibrary.model.Author;
import com.codecool.examlibrary.model.Book;
import com.codecool.examlibrary.repository.AuthorRepository;
import com.codecool.examlibrary.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Book createBook(Book book) {
        if (book.getId() != null) {
            Book saved = bookRepository.findById(book.getId()).orElseThrow(IllegalArgumentException::new);
            saved.setAuthor(book.getAuthor());
            saved.setYear(book.getYear());
            saved.setTitle(book.getTitle());
            return bookRepository.save(saved);
        }
        if (book.getAuthor() == null) {
            return bookRepository.save(book);
        } else if (book.getAuthor().getId() == null) {
            Author author = authorRepository.save(book.getAuthor());
            return bookRepository.save(book);
        } else {
            return bookRepository.save(book);
        }
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> findByYear(int year) {
        return bookRepository.findByYear(year);
    }
}
