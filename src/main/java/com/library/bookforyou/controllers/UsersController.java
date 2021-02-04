package com.library.bookforyou.controllers;

import com.library.bookforyou.models.User;
import com.library.bookforyou.services.UserService;
import com.library.bookforyou.util.PagingModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UserService userService;

    private Logger logger = LoggerFactory.getLogger(UsersController.class);

    @GetMapping
    public String getAllUsers(Model model) {
        logger.info("Getting all users");
        return listByPage(model, 1, "lastName", "asc");
    }

    @GetMapping("/page/{pageNumber}")
    private String listByPage(Model model,
                              @PathVariable("pageNumber") int currentPage,
                              @Param("sortField") String sortField,
                              @Param("sortDir") String sortDir) {

        Page<User> page = userService.findAll(currentPage, sortField, sortDir);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("users", page.getContent());

        return "users/users";
    }

    @GetMapping("/userprofile")
    public String getProfile(Model model, Principal principal) {
        model.addAttribute(userService.findByUsername(principal.getName()));
        return "profile/profile";
    }
}
