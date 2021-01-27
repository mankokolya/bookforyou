package com.library.bookforyou.controllers;

import com.library.bookforyou.services.BookService;
import com.library.bookforyou.web.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {

@Autowired
    BookService bookService;

    @GetMapping("/new")
    public String addBook(Model model) {
        model.addAttribute("book", new BookDto());
        return "books/newBook";
    }
    
    @PostMapping("/save")
        public String saveBook(BookDto bookDto){
        bookService.save(bookDto);
        return "redirect:@{/home}";
    }
}
