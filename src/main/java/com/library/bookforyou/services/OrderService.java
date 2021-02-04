package com.library.bookforyou.services;

import com.library.bookforyou.models.*;
import com.library.bookforyou.repositories.BookRepository;
import com.library.bookforyou.repositories.OrderRepository;
import com.library.bookforyou.repositories.UserRepository;
import com.library.bookforyou.util.Constants;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
public class OrderService {
    private Logger logger = LoggerFactory.getLogger(OrderService.class);


    @Autowired
    OrderRepository orderRepository;

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    public Order createTakeHomeOrder(long bookId, String username) {
        User user = userService.findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with username %s not registered in the system", username)));

        Book book = bookService.findBook(bookId).
                orElseThrow(() -> new NoSuchElementException(
                        String.format("Book with id: %d, not present in the library please choose another one", bookId)));

        Order order = new Order(user.getAccount(), book, LocalDate.now(), null,
                LocalDate.now().plusDays(Constants.DAYS_FOR_HOME_READING), 0.0, Place.HOME, Status.NEW);

        return saveOrder(book, order);
    }

    @Transactional
    public Order saveOrder(Book book, Order order) {
        logger.info("Trying to decrease quantity by one is decreased by one.");

        bookService.updateBookQuantity(book.getQuantity() - 1, book.getId());

        logger.info("Quantity is decreased by one. Saving Order to Account");

        return orderRepository.save(order);
    }



    public Page<Order> findAllWithUsername(int currentPage, String sortField, String sortDir, String username) {
//        if (sortField.equals("categories") || sortField.equals("authors")) {
//            Pageable pageable = PageRequest.of(pageNumber - 1, PAGE_SIZE,
//                    sortDir.equals("asc") ? Sort.by(sortField + ".name").ascending() : Sort.by(sortField + ".name").descending()
//            );
//            return bookRepository.findAll(pageable);
//        }

        User user = userService.findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with username %s not registered in the system", username)));
        Pageable pageable = PageRequest.of(currentPage - 1, Constants.PAGE_SIZE,
                sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());

        return orderRepository.findAllByAccount(user.getAccount(), pageable);
    }
}
