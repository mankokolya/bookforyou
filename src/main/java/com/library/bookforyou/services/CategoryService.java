package com.library.bookforyou.services;

import com.library.bookforyou.controllers.BookController;
import com.library.bookforyou.models.BookCategory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CategoryService {
    List<BookCategory> findAll();
}
