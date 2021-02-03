package com.library.bookforyou.controllers;

import com.library.bookforyou.models.Book;
import com.library.bookforyou.services.OrderService;
import com.library.bookforyou.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/order")
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    @PostMapping("/take")
    public String takeHome(@RequestParam("id") long id, Authentication authentication) {

        logger.info("User with id {} taking home book with id {}", authentication.getName(), id);


        orderService.createOrder(id);


        return "redirect:/home";
    }


}
