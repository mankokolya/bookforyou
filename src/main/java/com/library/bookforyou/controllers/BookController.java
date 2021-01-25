package com.library.bookforyou.controllers;

import com.library.bookforyou.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
//@RequestMapping()
//public class BookController {
//
//
//    @GetMapping()
//    public String findAll(Model model) {
//        model.addAttribute("books", bookRepository.findAll());
//        return "index";
//    }
//}
