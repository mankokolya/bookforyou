package com.library.bookforyou.controllers;

import com.library.bookforyou.models.Order;
import com.library.bookforyou.models.Status;
import com.library.bookforyou.services.OrderService;
import com.library.bookforyou.services.UserDetailsServiceImpl;
import com.library.bookforyou.util.PagingModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;


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
        logger.info("Getting my orders");

        return listMyByPage(1, "takenDate", "desc", principal, model);
    }

    @GetMapping("/newOrders")
    public String newOrders(Model model) {
        logger.info("Getting new orders");

        return listNewByPage(1, "takenDate", "desc", model);
    }

    @GetMapping("/newOrders/page/{pageNumber}")
    private String listNewByPage(@PathVariable("pageNumber") int currentPage,
                                 @Param("sortField") String sortField,
                                 @Param("sortDir") String sortDir,
                                 Model model) {

        Page<Order> newOrdersPage = orderService.findAllOrdersByStatus(currentPage, sortField, sortDir, Status.NEW);
        createModel(currentPage, sortField, sortDir, model, newOrdersPage);
        return "orders/newOrders";
    }


    @GetMapping("/page/{pageNumber}")
    public String listMyByPage(@PathVariable("pageNumber") int currentPage,
                               @Param("sortField") String sortField,
                               @Param("sortDir") String sortDir,
                               Principal principal,
                               Model model) {

        Page<Order> page = orderService.findAllWithUsername(currentPage, sortField, sortDir, principal.getName());
        createModel(currentPage, sortField, sortDir, model, page);
        return "orders/myOrders";
    }


    @GetMapping("/delete")
    public String deleteOrder(@RequestParam(name = "id") long id,
                              @RequestParam(name = "bookId") long bookID,
                              @RequestParam(name = "bookQuantity") int booQuantity) {
        logger.info(String.format("Deleting order with id %d", id));
        orderService.cancelOrder(id, bookID, booQuantity);
        return "redirect:/order/myOrders?deletedSuccess";
    }

    @GetMapping("/decline")
    public String declineOrder(@RequestParam(name = "id") long id,
                               @RequestParam(name = "bookId") long bookID,
                               @RequestParam(name = "bookQuantity") int bookQuantity) {
        logger.info(String.format("Librarian declines order with id %d", id));
        orderService.declineOrder(id, bookID, bookQuantity);
        return "redirect:/order/newOrders";
    }


    @GetMapping("/approve")
    public String approveOrder(@RequestParam(name = "id") long id) {
        logger.info(String.format("Librarian approves order with id %d", id));
        orderService.approveOrder(id);
        return "redirect:/order/newOrders";
    }


    private void createModel(@PathVariable("pageNumber") int currentPage, @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir, Model model, Page<Order> page) {
        PagingModel.createPagingModel(model, currentPage, sortField, sortDir, page);
        model.addAttribute("pagedOrders", page.getContent());
    }
}
