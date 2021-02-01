package com.library.bookforyou.services;

import com.library.bookforyou.models.BookCategory;
import com.library.bookforyou.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<BookCategory> findAll() {

        return categoryRepository.findAll();
    }
}
