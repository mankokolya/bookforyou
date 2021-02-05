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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @Autowired
    PublisherService publisherService;

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
        if (bindingResult.hasErrors()) {
            return "books/newBook";
        }
        bookService.save(bookDto);
        return "redirect:/home";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam(name = "id") long id) {
        bookService.deleteBook(id);
        return "redirect:/home";
    }

//    @GetMapping("/findOne")
//    @ResponseBody
//    public Book findOne(long id) {
//
//        return bookService.findBook(id).get(); //service get
//    }


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
        model.addAttribute("books", page.getContent());
    }
}
