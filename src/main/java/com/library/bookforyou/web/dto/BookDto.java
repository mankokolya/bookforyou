package com.library.bookforyou.web.dto;

import com.library.bookforyou.models.Author;
import com.library.bookforyou.models.BookCategory;

import java.util.Set;

public class BookDto {

    private String title;
    private Set<Author> authors;
    private String publishedDate;
    private Set<BookCategory> categories;
    private String description;

    public BookDto(String title, Set<Author> authors, String publishedDate, Set<BookCategory> categories, String description) {
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.categories = categories;
        this.description = description;
    }

    public BookDto() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Set<BookCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<BookCategory> categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //    @OneToOne(mappedBy = "book",    cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
//            CascadeType.REFRESH}, fetch = FetchType.LAZY)
//    private Reserved reserved;

}

