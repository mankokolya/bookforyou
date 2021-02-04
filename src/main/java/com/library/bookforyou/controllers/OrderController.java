package com.library.bookforyou.controllers;

import com.library.bookforyou.models.Book;
import com.library.bookforyou.models.Order;
import com.library.bookforyou.services.OrderService;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/order")
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    @GetMapping("/take")
    public String takeHome(@RequestParam("id") long bookId, Principal principal) {
        if (principal == null) {
            return "redirect:/login?pleaseLogin";
        }
        orderService.createTakeHomeOrder(bookId, principal.getName());
        return "redirect:/order/myOrders";
    }


    @GetMapping("/myOrders")
    public String myOrders(Model model, Principal principal) {
        logger.info("MY ORDERS");

        return listByPage( 1, "takenDate", "asc", principal, model);
    }

    @GetMapping("/page/{pageNumber}")
    public String listByPage(@PathVariable("pageNumber") int currentPage,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir,
                             Principal principal,
                             Model model) {

logger.info("LIST BY PAGE");
        Page<Order> page = orderService.findAllWithUsername(currentPage, sortField, sortDir, principal.getName());
        logger.info("PAging done");
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("pagedOrders", page.getContent());
        logger.info("Total elements " + page.getTotalElements());

        return "orders/orders";
    }

}
