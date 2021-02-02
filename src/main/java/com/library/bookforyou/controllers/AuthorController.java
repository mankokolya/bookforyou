package com.library.bookforyou.controllers;

import com.library.bookforyou.services.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    AuthorService authorService;


    @PostMapping("/addBook")
    public String addNewAuthor(@RequestBody String newAuthor) {
        logger.info("newAuthor" + newAuthor);

//        authorService.addNewAuthor(newAuthor);
        return "redirect:/books/new";
    }
}
