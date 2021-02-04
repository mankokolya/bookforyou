package com.library.bookforyou.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private Book book;

    private LocalDate takenDate;
    private LocalDate returnedDate;
    private LocalDate dueDate;
    private double penalty;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Place place;

    public Order(Account account, Book book, LocalDate takenDate,
                 LocalDate returnedDate, LocalDate dueDate, double penalty, Place place, Status status) {
        this.account = account;
        this.book = book;
        this.takenDate = takenDate;
        this.returnedDate = returnedDate;
        this.dueDate = dueDate;
        this.penalty = penalty;
        this.place = place;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(book, order.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book);
    }
}
