package com.pedrao.ExpenseTracker.controller;

import com.pedrao.ExpenseTracker.service.AppUserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expense_tracker/users")
public class AppUserController {
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    // Obter o balance atual do usuário
    @GetMapping("/balance")
    public Float getBalance() {
        return appUserService.getBalance();
    }

}
