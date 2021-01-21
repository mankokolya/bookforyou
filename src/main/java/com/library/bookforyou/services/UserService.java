package com.library.bookforyou.services;

import com.library.bookforyou.models.User;
import com.library.bookforyou.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto userRegistrationDto);
}
