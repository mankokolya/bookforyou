package com.library.bookforyou.controllers;

import com.library.bookforyou.models.Author;
import com.library.bookforyou.models.Book;
import com.library.bookforyou.services.BookService;
import com.library.bookforyou.util.PagingModel;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        createModel(model, currentPage, sortField, sortDir, page);

        return "index";
    }

    private void createModel(Model model, int currentPage, String sortField, String sortDir, Page<Book> page) {
        PagingModel.createPagingModel(model, currentPage, sortField, sortDir, page);
        model.addAttribute("books", page.getContent());
    }
}
