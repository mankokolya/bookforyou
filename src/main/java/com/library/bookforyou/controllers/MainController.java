package com.library.bookforyou.controllers;

import com.library.bookforyou.models.Author;
import com.library.bookforyou.models.Book;
import com.library.bookforyou.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private BookService bookService;


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String loginSuccess(Model model) {
        return goHome(model);
    }


    @GetMapping("/home")
    public String goHome(Model model) {
        return listByPage(model, 1, "title", "asc");
    }

    @GetMapping("/page/{pageNumber}")
    public String listByPage(Model model,
                             @PathVariable("pageNumber") int currentPage,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir) {

        Page<Book> page = bookService.listAll(currentPage, sortField, sortDir);
        List<Book> books = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("books", books);

        return "index";
    }
}
