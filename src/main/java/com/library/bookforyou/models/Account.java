package com.library.bookforyou.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(mappedBy = "account", fetch=FetchType.EAGER)
    private User user;

    @OneToMany(mappedBy = "account", fetch=FetchType.LAZY)
    private Set<Order> orders;

}
