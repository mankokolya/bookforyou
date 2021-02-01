package com.library.bookforyou.services;

import com.library.bookforyou.models.Author;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface AuthorService {
    List<Author> findAll();
}
