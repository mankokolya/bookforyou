package com.library.bookforyou.services;

import com.library.bookforyou.models.*;
import com.library.bookforyou.repositories.BookRepository;
import com.library.bookforyou.repositories.OrderRepository;
import com.library.bookforyou.repositories.UserRepository;
import com.library.bookforyou.util.Constants;
import com.library.bookforyou.util.PagingModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
public class OrderService {
    private Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;


    //todo refactor get book from
    public Order createTakeHomeOrder(long bookId, String username) {
        User user = getUser(username);

        Book book = bookRepository.findById(bookId).
                orElseThrow(() -> new NoSuchElementException(
                        String.format("Book with id: %d, not present in the library please choose another one", bookId)));

        Order order = new Order(user.getAccount(), book, LocalDate.now(), null,
                LocalDate.now().plusDays(Constants.DAYS_FOR_HOME_READING), 0.0, Place.HOME, Status.NEW);

        return saveOrder(book, order);
    }

    @Transactional
    public Order saveOrder(Book book, Order order) {
        bookRepository.updateQuantity(book.getQuantity() - 1, book.getId());
        return orderRepository.save(order);
    }

    public Page<Order> findAllWithUsername(int currentPage, String sortField, String sortDir, String username) {

        //TODO FILTERING
        //        if (sortField.equals("categories") || sortField.equals("authors")) {
//            Pageable pageable = PageRequest.of(pageNumber - 1, PAGE_SIZE,
//                    sortDir.equals("asc") ? Sort.by(sortField + ".name").ascending() : Sort.by(sortField + ".name").descending()
//            );
//            return bookRepository.findAll(pageable);
//        }

        return orderRepository.findAllByAccount(
                getUser(username).getAccount(),
                PagingModel.getPageable(currentPage, sortField, sortDir));
    }


    @Transactional
    public void cancelOrder(long id, long bookId, int bookQuantity) {
        bookRepository.updateQuantity(bookQuantity + 1, bookId);
        orderRepository.deleteById(id);
    }

    public Page<Order> findAllOrdersByStatus(int currentPage, String sortField, String sortDir, Status status) {
        return orderRepository.findAllByStatus(status, PagingModel.getPageable(currentPage, sortField, sortDir));
    }


    private User getUser(String username) {
        return userRepository.findByEmail(username).
                orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with username %s not registered in the system", username)));
    }

    @Transactional
    public void declineOrder(long id, long bookId, int bookQuantity) {
        bookRepository.updateQuantity(bookQuantity + 1, bookId);
        orderRepository.updateStatus(Status.DECLINED, id);
    }


    @Transactional
    public void approveOrder(long id) {
        orderRepository.updateStatus(Status.APPROVED, id);
    }
}
