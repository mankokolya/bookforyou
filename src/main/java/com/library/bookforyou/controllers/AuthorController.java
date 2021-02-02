package com.library.bookforyou.controllers;

import com.library.bookforyou.services.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.ConstraintViolationException;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    AuthorService authorService;

    @PostMapping("/addAuthor")
    public String addNewAuthor(@RequestParam("newAuthor") String newAuthor) {
        logger.debug("Adding new Author with name {}", newAuthor);
        //todo constraint violation exception
        authorService.addNewAuthor(newAuthor);
        return "redirect:/books/new?success";
    }
}
