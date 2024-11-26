package com.pedrao.ExpenseTracker.dto;

import lombok.Data;

@Data
public class NewExpenseRequest {

    private String name;

    private String description;

    private double amount;

}
