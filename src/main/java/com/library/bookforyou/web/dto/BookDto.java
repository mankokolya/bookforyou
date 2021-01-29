package com.library.bookforyou.web.dto;

import com.library.bookforyou.models.Author;
import com.library.bookforyou.models.BookCategory;
import com.library.bookforyou.models.Publisher;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BookDto {

    private String title;
    private Set<Author> authors;
    private Publisher publisher;
    private LocalDate publishedDate;
    private Set<BookCategory> categories;
    private int quantity;
    private String description;

    public BookDto(String title, Set<Author> authors, Publisher publisher, LocalDate publishedDate,
                   Set<BookCategory> categories, int quantity, String description) {
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.categories = categories;
        this.quantity = quantity;
        this.description = description;

    }


    //    @OneToOne(mappedBy = "book",    cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
//            CascadeType.REFRESH}, fetch = FetchType.LAZY)
//    private Reserved reserved;

}

