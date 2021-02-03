package com.library.bookforyou.web.dto;

import com.library.bookforyou.models.Author;
import com.library.bookforyou.models.BookCategory;
import com.library.bookforyou.models.Publisher;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class BookDto {
    private Logger logger = LoggerFactory.getLogger(BookDto.class);

    @NotBlank(message = "Field should not be empty")
    private String title;
    private String[] authors;
    private String publisher;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "The date should be before today")
    private LocalDate publishedDate;
    private String[] categories;

    @Min(value = 0, message = "Amount should be greater or equal to zero")
    private int quantity;
    private String description;

//    public BookDto(String title, String[]authors, String[] categories, String publisher,
//                   LocalDate publishedDate, String quantity, String description) {
//        this.title = title;
//        this.authors = authors;//.stream(authors).map(Author::new).collect(Collectors.toSet());
//        this.publisher = new Publisher(publisher);
//        this.publishedDate = publishedDate;
//        this.categories = categories;//Arrays.stream(categories).map(BookCategory::new).collect(Collectors.toSet());
//        this.quantity = Integer.parseInt(quantity);
//        this.description = description;
//
//    }

    @Override
    public String toString() {
        return "BookDto{" +
                "title='" + title + '\'' +
                ", authors=" + Arrays.toString(authors) +
                ", publisher=" + publisher +
                ", publishedDate=" + publishedDate +
                ", categories=" + Arrays.toString(categories) +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                '}';
    }


    //    @OneToOne(mappedBy = "book",    cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
//            CascadeType.REFRESH}, fetch = FetchType.LAZY)
//    private Reserved reserved;

}

