package com.library.bookforyou.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="category")
public class BookCategory {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private int id;

    @Column(unique=true)
    private String name;

    public BookCategory(String name) {
        this.name = name;
    }
}
