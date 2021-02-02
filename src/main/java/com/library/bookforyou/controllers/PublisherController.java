package com.library.bookforyou.controllers;

import com.library.bookforyou.services.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/publishers")
public class PublisherController {
    private Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    PublisherService publisherService;

    @PostMapping ("/addPublisher")
    public String addNewPublisher(@RequestParam("newPublisher") String addPublisher) {
        logger.debug("Adding new Publisher with name {}", addPublisher);
        //todo constraint violation exception
        publisherService.addNewPublisher(addPublisher);
        return "redirect:/books/new?success";
    }



}
