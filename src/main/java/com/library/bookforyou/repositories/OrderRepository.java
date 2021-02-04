package com.library.bookforyou.repositories;

import com.library.bookforyou.models.Account;
import com.library.bookforyou.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByAccount(Account account, Pageable pageable);

}
