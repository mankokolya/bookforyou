package com.library.bookforyou.repositories;

import com.library.bookforyou.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByTitleLikeIgnoreCaseOrAuthorsNameLikeIgnoreCase(String param, String author, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Book b SET b.quantity = :quantity WHERE b.id = :id")
    int updateQuantity(@Param("quantity") int quantity, @Param("id") long id);
}
