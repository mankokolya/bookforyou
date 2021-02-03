package com.library.bookforyou.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple java Bean Object that represents a User
 *
 * @author Mykola Manko
 */
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Email
    @NotNull
    private String email;

    @NotBlank
    @Size(min = 4, max = 100)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    private Account account;


    public User(String firstName, String lastName, @Email @NotNull String email, @NotBlank String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = new HashSet<>();
        this.account = new Account();
    }

    public void addRole(Role userRole) {
        roles.add(userRole);
    }
}
