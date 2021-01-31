package com.library.bookforyou.services;

import com.library.bookforyou.models.User;
import com.library.bookforyou.web.dto.userDTO;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Service class for {@link User}
 */
public interface UserService{
    void saveUser(userDTO userRegistrationDto);
    boolean userExists(String email);
}
