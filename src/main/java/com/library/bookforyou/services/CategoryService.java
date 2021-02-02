package com.library.bookforyou.services;

import com.library.bookforyou.models.BookCategory;
import com.library.bookforyou.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<BookCategory> findAll() {
        return categoryRepository.findAll();
    }

    public void addNewCategory(String newCategory) {
        categoryRepository.save(new BookCategory(newCategory));
    }
}
