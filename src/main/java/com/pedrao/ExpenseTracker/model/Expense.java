package com.pedrao.ExpenseTracker.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double amount;
    private LocalDate date;

    // Getters and setters
}
