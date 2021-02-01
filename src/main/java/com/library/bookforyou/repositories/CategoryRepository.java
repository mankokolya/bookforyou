package com.library.bookforyou.repositories;

import com.library.bookforyou.models.BookCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<BookCategory, Integer> {
    BookCategory findByName(String category);

}
