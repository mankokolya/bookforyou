package com.library.bookforyou.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class BookForYouController {

    @GetMapping("/home")
    public String goHome() {
        return "login";
    }
}
