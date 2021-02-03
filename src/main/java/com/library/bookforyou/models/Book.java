package com.library.bookforyou.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "book", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
@Getter
@Setter
@NoArgsConstructor

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL})
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    private Set<Author> authors;

    @Min(value = 0)
    private int quantity;


    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(referencedColumnName = "id")
    private Publisher publisher;

    @Column(name = "published_date", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate publishedDate;


    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id" , referencedColumnName = "id")
    )
    private Set<BookCategory> categories;

    @Type(type = "text")
    private String description;


    @OneToMany(mappedBy = "book", fetch=FetchType.EAGER)
    private Set<Order> orders;




    public Book(String title, Set<Author> authors, int quantity, Publisher publisher,
                LocalDate publishedDate, Set<BookCategory> categories, String description) {
        this.title = title;
        this.authors = authors;
        this.quantity = quantity;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.categories = categories;
        this.description = description;
    }
}
