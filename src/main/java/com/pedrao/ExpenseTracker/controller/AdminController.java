package com.pedrao.ExpenseTracker.controller;

import com.pedrao.ExpenseTracker.model.AppUser;
import com.pedrao.ExpenseTracker.model.Expense;
import com.pedrao.ExpenseTracker.model.Income;
import com.pedrao.ExpenseTracker.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("expense_tracker/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Endpoint para listar todos os usuários
    @Operation(summary = "Listar todos os usuários", description = "Obtém uma lista de todos os usuários cadastrados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    // -------------------------EXPENSES-------------------------
    @Operation(summary = "Listar todas as expenses", description = "Obtém uma lista de todas as expenses registradas.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @GetMapping("/expenses")
    public ResponseEntity<List<Expense>> getAllExpenses() {
        return ResponseEntity.ok(adminService.getAllExpenses());
    }

    @Operation(summary = "Buscar uma expense por ID", description = "Obtém os detalhes de uma expense pelo seu ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Expense retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Expense não encontrada")
    })
    @GetMapping("/expenses/{id}")
    public ResponseEntity<?> getExpenseById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(adminService.getExpenseById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // -------------------------INCOMES-------------------------
    @Operation(summary = "Listar todos os incomes", description = "Obtém uma lista de todos os incomes registrados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @GetMapping("/incomes")
    public ResponseEntity<List<Income>> getAllIncomes() {
        return ResponseEntity.ok(adminService.getAllIncomes());
    }

    @Operation(summary = "Buscar um income por ID", description = "Obtém os detalhes de um income pelo seu ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Income retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Income não encontrado")
    })
    @GetMapping("/incomes/{id}")
    public ResponseEntity<?> getIncomeById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(adminService.getIncomeById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // -------------------------METODOS-ADMIN-------------------------
    @Operation(summary = "Deletar um usuário por ID", description = "Remove um usuário pelo seu ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        try {
            adminService.deleteUserById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping("/users/{id}/balance")
    @Operation(summary = "Atualizar saldo de um usuário", description = "Atualiza o saldo de um usuário pelo seu ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Saldo atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<?> updateUserBalance(@PathVariable Long id, @RequestBody Float newBalance) {
        try {
            return ResponseEntity.ok(adminService.updateUserBalance(id, newBalance));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
