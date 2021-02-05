package com.library.bookforyou.services;

import com.library.bookforyou.controllers.BookController;
import com.library.bookforyou.models.Author;
import com.library.bookforyou.models.Book;
import com.library.bookforyou.models.BookCategory;
import com.library.bookforyou.models.Publisher;
import com.library.bookforyou.repositories.AuthorRepository;
import com.library.bookforyou.repositories.BookRepository;
import com.library.bookforyou.repositories.CategoryRepository;
import com.library.bookforyou.repositories.PublisherRepository;
import com.library.bookforyou.util.Constants;
import com.library.bookforyou.web.dto.BookDto;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.*;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class BookService {
    private Logger logger = Logger.getLogger(BookController.class);

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PublisherRepository publisherRepository;

    public Page<Book> listAll(int pageNumber, String sortField, String sortDir) {
        if (sortField.equals("categories") || sortField.equals("authors")) {
            Pageable pageable = PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE,
                    sortDir.equals("asc") ? Sort.by(sortField + ".name").ascending() : Sort.by(sortField + ".name").descending()
            );
            return bookRepository.findBooksByQuantityGreaterThanEqual(Constants.BOOK_QUANTITY_MIN_FOR_USER, pageable);
        }

        Pageable pageable = PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE,
                sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending()
        );
        return bookRepository.findBooksByQuantityGreaterThanEqual(Constants.BOOK_QUANTITY_MIN_FOR_USER, pageable);
    }

    public void save(BookDto bookDto) {
        //todo refactor
        Set<Author> authors = Arrays.stream(bookDto.getAuthors())
                .map(author -> authorRepository.findByName(author))
                .collect(Collectors.toSet());

        Set<BookCategory> categories = Arrays.stream(bookDto.getCategories())
                .map(category -> (categoryRepository.findByName(category)))
                .collect(Collectors.toSet());

        Publisher publisher = publisherRepository.findByName(bookDto.getPublisher());


        //transaction
        bookRepository.save(new Book(
                bookDto.getTitle(),
                authors,
                bookDto.getQuantity(),
                publisher,
                bookDto.getPublishedDate(),
                categories,
                bookDto.getDescription()));
    }

    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    public Optional<Book> findBook(Long id) {
        return bookRepository.findById(id);
    }

    public Page<Book> findAllByParam(int pageNumber, String sortField, String sortDir, String param) {

        if (sortField.equals("categories") || sortField.equals("authors")) {
            Pageable pageable = PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE,
                    sortDir.equals("asc") ? Sort.by(sortField + ".name").ascending() : Sort.by(sortField + ".name").descending()
            );
            return bookRepository.findBooksByTitleLikeIgnoreCaseAndQuantityIsGreaterThanEqualOrAuthorsNameLikeIgnoreCaseAndQuantityIsGreaterThanEqual(
                    "%" + param + "%", Constants.BOOK_QUANTITY_MIN_FOR_USER, "%" + param + "%",
                    Constants.BOOK_QUANTITY_MIN_FOR_USER, pageable);
        }

        Pageable pageable = PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE,
                sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending()
        );
        return bookRepository.findBooksByTitleLikeIgnoreCaseAndQuantityIsGreaterThanEqualOrAuthorsNameLikeIgnoreCaseAndQuantityIsGreaterThanEqual(
                "%" + param + "%", Constants.BOOK_QUANTITY_MIN_FOR_USER, "%" + param + "%",
                Constants.BOOK_QUANTITY_MIN_FOR_USER, pageable);
    }

    @Transactional
    public int updateBookQuantity(int bookQuantity, Long id) {
        return bookRepository.updateQuantity(bookQuantity, id);
    }
}
