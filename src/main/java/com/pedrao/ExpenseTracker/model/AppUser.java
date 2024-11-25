package com.pedrao.ExpenseTracker.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "AppUsers")
@Data
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

}
