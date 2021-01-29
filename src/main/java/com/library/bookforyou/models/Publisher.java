package com.library.bookforyou.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "publisher", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "publisher", fetch=FetchType.EAGER)
    private Set<Book> books;
}
