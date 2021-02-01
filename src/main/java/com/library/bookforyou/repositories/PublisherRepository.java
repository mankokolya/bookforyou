package com.library.bookforyou.repositories;

import com.library.bookforyou.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    Publisher findByName(String name);
}
