package com.pedrao.ExpenseTracker.dto;

import lombok.Data;

@Data
public class NewIncomeRequest {

    private String name;

    private String description;

    private String source;

    private double amount;

}
