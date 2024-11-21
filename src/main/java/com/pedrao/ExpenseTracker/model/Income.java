package com.pedrao.ExpenseTracker.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String source;
    private double amount;
    private LocalDate date;

    // Getters and setters
}
