package com.library.bookforyou.controllers;

import com.library.bookforyou.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    BookRepository bookRepository;


    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String loginSuccess(Model model){
        return goHome(model);
    }


    @GetMapping("/home")
    public String goHome(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "index";
    }
}
