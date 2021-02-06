package com.library.bookforyou.services;

import com.library.bookforyou.models.Account;
import com.library.bookforyou.models.Role;
import com.library.bookforyou.models.Roles;
import com.library.bookforyou.models.User;
import com.library.bookforyou.repositories.RoleRepository;
import com.library.bookforyou.repositories.UserRepository;
import com.library.bookforyou.util.PagingModel;
import com.library.bookforyou.web.dto.userDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of {@link UserService} interface
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void saveUser(userDTO userDTO) {
        User user = new User(userDTO.getFirstName(), userDTO.getLastName(),
                userDTO.getEmail(), passwordEncoder.encode(userDTO.getPassword()));
        user.addRole(roleRepository.findByName(Roles.USER.name()));
        userRepository.save(user);
    }

    public boolean userExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public Page<User> findAll(int pageNumber, String sortField, String sortDir) {
        return userRepository.findAll(PagingModel.getPageable(pageNumber - 1, sortDir, sortField));
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByEmail(username);
    }
}
