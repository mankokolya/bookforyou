package com.library.bookforyou.services;

import com.library.bookforyou.models.User;
import com.library.bookforyou.web.dto.userDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    boolean saveUser(userDTO userRegistrationDto);
    boolean userExists(String email);
}
