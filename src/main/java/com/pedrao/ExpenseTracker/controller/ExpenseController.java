package com.pedrao.ExpenseTracker.controller;

import com.pedrao.ExpenseTracker.dto.NewExpenseRequest;
import com.pedrao.ExpenseTracker.exception.ResourceNotFoundException;
import com.pedrao.ExpenseTracker.exception.UnauthorizedAccessException;
import com.pedrao.ExpenseTracker.model.Expense;
import com.pedrao.ExpenseTracker.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense_tracker/user/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @Operation(summary = "Listar todas as expenses do usuário autenticado", description = "Obtém todas as expenses do usuário atual.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = expenseService.findAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    @Operation(summary = "Buscar expense por id", description = "busca expense pelo id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Expense retornado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
            @ApiResponse(responseCode = "404", description = "Expense não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getExpenseById(@PathVariable Long id) {
        try {
            Expense expense = expenseService.findExpenseById(id);
            return ResponseEntity.ok(expense);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (UnauthorizedAccessException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @Operation(summary = "Criar uma nova expense", description = "Adiciona uma nova expense para o usuário autenticado.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Expense criada com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @PostMapping
    public ResponseEntity<Object> createExpense(@Valid @RequestBody NewExpenseRequest expenseRequest) {
        try {
            Expense expense = expenseService.createExpense(expenseRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(expense);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar expense.");
        }
    }

    @Operation(summary = "Deletar uma expense por ID", description = "Remove uma expense existente pelo seu ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Expense removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Expense não encontrada"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteExpense(@PathVariable Long id) {
        try {
            expenseService.deleteExpense(id);
            return ResponseEntity.ok("Expense deletada com sucesso.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (UnauthorizedAccessException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @Operation(summary = "Editar nome da expense pelo id", description = "Edita o NOME uma expense existente pelo seu ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Expense alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Expense não encontrado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @PutMapping("/{id}/edit-name")
    public ResponseEntity<Object> editExpenseName(@PathVariable Long id, @RequestParam String newName) {
        try {
            Expense expense = expenseService.editExpenseName(id, newName);
            return ResponseEntity.ok(expense);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Editar descricao da expense pelo id", description = "Edita a DESCRICAO uma expense existente pelo seu ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Expense alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Expense não encontrado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @PutMapping("/{id}/edit-description")
    public ResponseEntity<Object> editExpenseDescription(@PathVariable Long id, @RequestParam String newDescription) {
        try {
            Expense expense = expenseService.editExpenseDescription(id, newDescription);
            return ResponseEntity.ok(expense);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Editar valor da expense pelo id", description = "Edita o VALOR uma expense existente pelo seu ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Expense alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Expense não encontrado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @PutMapping("/{id}/edit-amount")
    public ResponseEntity<Object> editExpenseAmount(@PathVariable Long id, @RequestParam double newAmount) {
        try {
            Expense expense = expenseService.editExpenseAmount(id, newAmount);
            return ResponseEntity.ok(expense);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}