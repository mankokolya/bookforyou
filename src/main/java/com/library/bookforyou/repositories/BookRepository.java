package com.library.bookforyou.repositories;

import com.library.bookforyou.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByTitleLikeIgnoreCaseOrAuthorsNameLikeIgnoreCase(String param, String author, Pageable pageable);
}
