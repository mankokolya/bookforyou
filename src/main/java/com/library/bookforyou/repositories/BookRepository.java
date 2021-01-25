package com.library.bookforyou.repositories;

import com.library.bookforyou.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(nativeQuery = true, value = "select b.title, a.name author, c.name category, b.description\n" +
            "from book b\n" +
            "         join book_author ba on b.id = ba.book_id\n" +
            "         join author a on a.id = ba.author_id\n" +
            "         join book_category bc on b.id = bc.book_id\n" +
            "         join category c on c.id = bc.category_id")
    List<Book> findAllBooks();
}
