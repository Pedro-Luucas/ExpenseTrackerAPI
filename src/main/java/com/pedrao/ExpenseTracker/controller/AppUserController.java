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
    @GetMapping("/{username}/balance")
    public Float getBalance(@PathVariable String username) {
        return appUserService.getBalance(username);
    }

    // Verificar se o usuário está autenticado
    @GetMapping("/is-auth")
    public boolean isAuth() {
        return appUserService.isAuthenticated();
    }
}
