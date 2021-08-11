package com.codecool.examlibrary.service;

import com.codecool.examlibrary.model.Author;
import com.codecool.examlibrary.model.Book;
import com.codecool.examlibrary.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow();
    }

    public Author createOrUpdate(Author author) {
        return authorRepository.save(author);
    }

    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

    public List<Book> findBooksOfAuthor(Long id) {
        return findById(id).getBooks();
    }

    public List<Author> findAuthorsWithBookCount(int c) {
        return findAll().stream()
                .filter(a -> a.getBooks().size() >= c)
                .collect(Collectors.toList());
    }
}
