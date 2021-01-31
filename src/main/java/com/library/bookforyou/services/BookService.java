package com.library.bookforyou.services;

import com.library.bookforyou.models.Book;
import com.library.bookforyou.repositories.BookRepository;
import com.library.bookforyou.web.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.*;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Page<Book> listAll(int pageNumber, String sortField, String sortDir) {
        int pageSize = 2;
        if (sortField.equals("categories") || sortField.equals("authors")) {
            Pageable pageable = PageRequest.of(pageNumber - 1, pageSize,
                    sortDir.equals("asc") ? Sort.by(sortField + ".name").ascending() : Sort.by(sortField + ".name").descending()
            );
            return bookRepository.findAll(pageable);
        }

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

    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    public Optional<Book> findBook(Long id) {
        return bookRepository.findById(id);
    }
}
