package com.library.bookforyou.services;

import com.library.bookforyou.models.Book;
import com.library.bookforyou.models.Order;
import com.library.bookforyou.repositories.BookRepository;
import com.library.bookforyou.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    BookRepository bookRepository;

    public Order createOrder(long id){
        Book book = bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Book with id: %d, " +
                "not present in the library please choose another one"));
        Order order = new Order();
        return orderRepository.save(order);
    }
}
