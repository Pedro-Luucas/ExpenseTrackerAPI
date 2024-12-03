package com.pedrao.ExpenseTracker.dto;

import com.pedrao.ExpenseTracker.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class RegisterRequest {

    private final String username;
    private final String password;
    private final Float balance;
    private final List<Role> roles;
}
