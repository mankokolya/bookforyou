package com.library.bookforyou.repositories;

import com.library.bookforyou.models.Account;
import com.library.bookforyou.models.Order;
import com.library.bookforyou.models.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByAccount(Account account, Pageable pageable);
    Page<Order> findAllByStatus(Status status, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Order ord SET ord.status = :status WHERE ord.id = :id")
    int updateStatus(@Param("status") Status status, @Param("id") long id);

}
