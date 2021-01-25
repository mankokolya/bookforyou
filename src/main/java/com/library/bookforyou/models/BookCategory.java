package com.library.bookforyou.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="category")
@ToString(onlyExplicitlyIncluded = true)
public class BookCategory {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(unique=true)
    @ToString.Include
    private String name;
}
