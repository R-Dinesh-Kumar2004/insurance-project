package com.project.entities;

import com.project.enums.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private String name;

    private Role role;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(length = 80)
    private String password;

}
