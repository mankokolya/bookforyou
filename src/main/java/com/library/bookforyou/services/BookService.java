package com.library.bookforyou.services;

import com.library.bookforyou.models.Book;
import com.library.bookforyou.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

//    @Autowired
//    BookRepository bookRepository;
//
//    public List<Book> listAll() {
//        Pageable pageable = PageRequest.of(0, 10);
//        return bookRepository.findAll(pageable);
//    }

}
