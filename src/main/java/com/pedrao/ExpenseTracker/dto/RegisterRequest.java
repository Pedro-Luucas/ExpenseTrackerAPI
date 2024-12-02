package com.pedrao.ExpenseTracker.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private final String username;
    private final String password;
    private final Float balance;

}