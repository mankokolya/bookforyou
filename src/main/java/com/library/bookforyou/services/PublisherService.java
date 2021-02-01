package com.library.bookforyou.services;

import com.library.bookforyou.models.Publisher;
import com.library.bookforyou.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    public List<Publisher> findAll() {
       return publisherRepository.findAll();
    }
}
