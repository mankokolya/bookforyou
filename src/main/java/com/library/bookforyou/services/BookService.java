package com.library.bookforyou.services;

import com.library.bookforyou.models.Book;
import com.library.bookforyou.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

@Service
public interface BookService extends PagingAndSortingRepository<Book, Long> {

//    @Autowired
//    BookRepository bookRepository;
}
