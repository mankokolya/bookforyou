package com.library.bookforyou.services;

import com.library.bookforyou.models.Book;
import com.library.bookforyou.repositories.BookRepository;
import com.library.bookforyou.web.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Page<Book> listAll(int pageNumber, String sortField, String sortDir) {
        int pageSize = 2;
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending()
        );
        return bookRepository.findAll(pageable);
    }

    public void save(BookDto bookDto) {
        bookRepository.save(new Book(bookDto.getTitle(), bookDto.getAuthors(), bookDto.getQuantity(),
                bookDto.getPublisher(), bookDto.getPublishedDate(),
                bookDto.getCategories(), bookDto.getDescription()));
    }
}
