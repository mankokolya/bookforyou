package com.library.bookforyou.services;

import com.library.bookforyou.models.Author;
import com.library.bookforyou.models.Book;
import com.library.bookforyou.models.BookCategory;
import com.library.bookforyou.models.Publisher;
import com.library.bookforyou.repositories.AuthorRepository;
import com.library.bookforyou.repositories.BookRepository;
import com.library.bookforyou.repositories.CategoryRepository;
import com.library.bookforyou.repositories.PublisherRepository;
import com.library.bookforyou.util.Constants;
import com.library.bookforyou.util.PagingModel;
import com.library.bookforyou.web.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class BookService {

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
            return bookRepository.findBooksByQuantityGreaterThanEqual(Constants.BOOK_QUANTITY_MIN_FOR_USER,
                    PagingModel.getPageable(pageNumber, sortField + ".name", sortDir));
        }
        return bookRepository.findBooksByQuantityGreaterThanEqual(Constants.BOOK_QUANTITY_MIN_FOR_USER,
                PagingModel.getPageable(pageNumber, sortField, sortDir));
    }

    @Transactional
    public void save(BookDto bookDto) {
        Set<Author> authors = Arrays.stream(bookDto.getAuthors())
                .map(author -> authorRepository.findByName(author))
                .collect(Collectors.toSet());

        Set<BookCategory> categories = Arrays.stream(bookDto.getCategories())
                .map(category -> (categoryRepository.findByName(category)))
                .collect(Collectors.toSet());

        Publisher publisher = publisherRepository.findByName(bookDto.getPublisher());

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
            return bookRepository.findBooksByTitleLikeIgnoreCaseAndQuantityIsGreaterThanEqualOrAuthorsNameLikeIgnoreCaseAndQuantityIsGreaterThanEqual(
                    "%" + param + "%",
                    Constants.BOOK_QUANTITY_MIN_FOR_USER,
                    "%" + param + "%",
                    Constants.BOOK_QUANTITY_MIN_FOR_USER,
                    PagingModel.getPageable(pageNumber, sortField + ".name", sortDir));
        }

        return bookRepository.findBooksByTitleLikeIgnoreCaseAndQuantityIsGreaterThanEqualOrAuthorsNameLikeIgnoreCaseAndQuantityIsGreaterThanEqual(
                "%" + param + "%",
                Constants.BOOK_QUANTITY_MIN_FOR_USER,
                "%" + param + "%",
                Constants.BOOK_QUANTITY_MIN_FOR_USER,
                PagingModel.getPageable(pageNumber, sortField, sortDir));
    }
}
