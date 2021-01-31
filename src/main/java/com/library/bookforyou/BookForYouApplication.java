package com.library.bookforyou;

import com.library.bookforyou.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses = UserRepository.class)

public class BookForYouApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookForYouApplication.class, args);
    }

}
