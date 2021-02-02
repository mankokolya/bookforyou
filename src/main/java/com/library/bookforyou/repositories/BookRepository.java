package com.library.bookforyou.repositories;

import com.library.bookforyou.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

//    @Query(value = "select * from book where lower(title) like lower(?1)",
////            countQuery = "select count(*) from book where lower(title) like lower(?1)",
//            nativeQuery = true)
//
    @Query(value = "select * from book b " +
            "join book_author ba on b.id = ba.book_id " +
            "join author a on a.id = ba.author_id " +
            "where lower(b.title) like lower(?1) " +
            "or lower(a.name) like lower(?1)",
//
//
            nativeQuery = true)

    Page<Book> findAllByParam(String param, Pageable pageable);
}
