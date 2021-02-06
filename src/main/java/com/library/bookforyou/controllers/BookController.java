package com.library.bookforyou.controllers;

import com.library.bookforyou.models.Book;
import com.library.bookforyou.services.AuthorService;
import com.library.bookforyou.services.BookService;
import com.library.bookforyou.services.CategoryService;
import com.library.bookforyou.services.PublisherService;
import com.library.bookforyou.util.PagingModel;
import com.library.bookforyou.web.dto.BookDto;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    private Logger logger = Logger.getLogger(BookController.class);

    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final PublisherService publisherService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService,
                          CategoryService categoryService, PublisherService publisherService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.publisherService = publisherService;
    }

    @GetMapping("/new")
    public String addBook(Model model) {
        model.addAttribute("bookDTO", new BookDto());
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("publishers", publisherService.findAll());
        return "books/newBook";
    }

    @PostMapping("/new")
    public String saveBook(@Valid BookDto bookDto, BindingResult bindingResult) {
        logger.info("Saving new book to library");
        if (bindingResult.hasErrors()){
            return "books/newBook";
        }

        try {
            bookService.save(bookDto);
        } catch (ConstraintViolationException exception) {
            bindingResult.addError(new FieldError("title", "title",
                    "The book is already in library. Please add new one."));
        }
        return "redirect:/home";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam(name = "id") long id) {
        bookService.deleteBook(id);
        return "redirect:/home";
    }

    @GetMapping("/find")
    public String findBySearchParam(@RequestParam("searchParam") String searchParam, Model model) {
        return listByPage(model, searchParam, 1, "title", "asc");
    }

    @GetMapping("/page/{pageNumber}")
    public String listByPage(Model model,
                             @RequestParam("searchParam") String searchParam,
                             @PathVariable("pageNumber") int currentPage,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir) {

        Page<Book> page = bookService.findAllByParam(currentPage, sortField, sortDir, searchParam);

        createModel(model, searchParam, currentPage, sortField, sortDir, page);

        return "searchBook";
    }

    private void createModel(Model model, String searchParam, int currentPage, String sortField, String sortDir, Page<Book> page) {
        PagingModel.createPagingModel(model, currentPage, sortField, sortDir, page);
        model.addAttribute("searchParam", searchParam);
        model.addAttribute("books", page.getContent());
    }
}
