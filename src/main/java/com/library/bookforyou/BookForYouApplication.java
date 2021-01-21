package com.library.bookforyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BookForYouApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookForYouApplication.class, args);
    }

}
