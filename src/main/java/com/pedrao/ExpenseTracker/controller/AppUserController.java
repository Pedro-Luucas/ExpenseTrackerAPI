package com.pedrao.ExpenseTracker.controller;

import com.pedrao.ExpenseTracker.service.AppUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expense_tracker/user")
public class AppUserController {
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Operation(summary = "Obter balance do usuário", description = "Retorna o balance atual do usuário autenticado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "balance retornado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @GetMapping("/balance")
    public Float getBalance() {
        return appUserService.getBalance();
    }

    @Operation(summary = "Obter nome do usuário", description = "Retorna o nome do usuário autenticado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Nome retornado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @GetMapping("/name")
    public String getName() {
        return appUserService.getName();
    }

}
