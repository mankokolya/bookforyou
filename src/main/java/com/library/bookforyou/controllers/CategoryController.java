package com.library.bookforyou.controllers;

import com.library.bookforyou.services.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    CategoryService categoryService;

    @PostMapping("/addCategory")
    public String addNewCategory(@RequestParam("newCategory") String newCategory) {
        logger.debug("Adding new Category with name {}", newCategory);
        //todo constraint violation exception
        categoryService.addNewCategory(newCategory);
        return "redirect:/books/new?success";
    }
}
