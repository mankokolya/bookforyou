package com.library.bookforyou.services;

import com.library.bookforyou.models.Role;
import com.library.bookforyou.models.Roles;
import com.library.bookforyou.models.User;
import com.library.bookforyou.repositories.RoleRepository;
import com.library.bookforyou.repositories.UserRepository;
import com.library.bookforyou.web.dto.userDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public boolean saveUser(userDTO userDTO) {
        Optional<User> userFromDB = userRepository.findByEmail(userDTO.getEmail());
        if (userFromDB.isPresent()) {
            return false;
        }
        User user = new User(userDTO.getFirstName(), userDTO.getLastName(),
                userDTO.getEmail(), passwordEncoder.encode(userDTO.getPassword()));
        user.addRole(new Role(Roles.USER.name()));
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean userExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(userRole -> new SimpleGrantedAuthority(userRole.getRole()))
                .collect(Collectors.toList());
    }
}
