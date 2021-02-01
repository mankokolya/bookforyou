package com.library.bookforyou.controllers;

import com.library.bookforyou.models.Book;
import com.library.bookforyou.services.AuthorService;
import com.library.bookforyou.services.BookService;
import com.library.bookforyou.services.CategoryService;
import com.library.bookforyou.web.dto.BookDto;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
private Logger logger = Logger.getLogger(BookController.class);
    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/new")
    public String addBook(Model model) {
        model.addAttribute("bookDTO", new BookDto());
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "books/newBook";
    }

    @PostMapping("/new")
    public String saveBook(BookDto bookDto) {
        logger.info(bookDto.toString());
        bookService.save(bookDto);
        return "redirect:/home";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam(name = "id") long id) {
        bookService.deleteBook(id);
        return "redirect:/home";
    }

    @GetMapping("/findOne")
    @ResponseBody
    public Book findOne(long id) {
        return bookService.findBook(id).get();
    }
}
