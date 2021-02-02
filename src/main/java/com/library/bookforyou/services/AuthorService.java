package com.library.bookforyou.services;

import com.library.bookforyou.models.Author;
import com.library.bookforyou.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public void addNewAuthor(String newAuthor) {
        authorRepository.save(new Author(newAuthor));
    }
}
