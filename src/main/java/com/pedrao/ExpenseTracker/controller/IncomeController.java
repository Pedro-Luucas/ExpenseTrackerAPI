package com.pedrao.ExpenseTracker.controller;

import com.pedrao.ExpenseTracker.dto.NewIncomeRequest;
import com.pedrao.ExpenseTracker.exception.ResourceNotFoundException;
import com.pedrao.ExpenseTracker.exception.UnauthorizedAccessException;
import com.pedrao.ExpenseTracker.model.Income;
import com.pedrao.ExpenseTracker.service.IncomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense_tracker/user/incomes")
@Validated
public class IncomeController {
    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }


    @Operation(summary = "Listar todos os incomes do usuário autenticado", description = "Obtém todos os incomes do usuário atual.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @GetMapping
    public ResponseEntity<List<Income>> getAllIncomes() {
        List<Income> incomes = incomeService.findAllIncomes();
        return ResponseEntity.ok(incomes);
    }

    @Operation(summary = "Buscar income por id", description = "busca income pelo id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Income retornado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
            @ApiResponse(responseCode = "404", description = "Income não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getIncomeById(@PathVariable Long id) {
        try {
            Income income = incomeService.findIncomeById(id);
            return ResponseEntity.ok(income);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (UnauthorizedAccessException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @Operation(summary = "Criar um novo income", description = "Adiciona um novo income para o usuário autenticado.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Income criado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @PostMapping
    public ResponseEntity<Object> createIncome(@Valid @RequestBody NewIncomeRequest incomeRequest) {
        try {
            Income income = incomeService.createIncome(incomeRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(income);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar income.");
        }
    }

    @Operation(summary = "Deletar um income por ID", description = "Remove um income existente pelo seu ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Income removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Income não encontrado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteIncome(@PathVariable Long id) {
        try {
            incomeService.deleteIncome(id);
            return ResponseEntity.ok("Income deletado com sucesso.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (UnauthorizedAccessException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @Operation(summary = "Editar nome do income pelo id", description = "Edita o NOME um income existente pelo seu ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Income alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Income não encontrado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @PutMapping("/{id}/edit-name")
    public ResponseEntity<Object> editIncomeName(@PathVariable Long id, @RequestParam String newName) {
        try {
            Income income = incomeService.editIncomeName(id, newName);
            return ResponseEntity.ok(income);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Editar descrição do income pelo id", description = "Edita a DESCRICAO um income existente pelo seu ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Income alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Income não encontrado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @PutMapping("/{id}/edit-description")
    public ResponseEntity<Object> editIncomeDescription(@PathVariable Long id, @RequestParam String newDescription) {
        try {
            Income income = incomeService.editIncomeDescription(id, newDescription);
            return ResponseEntity.ok(income);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Editar valor do income pelo id", description = "Edita o VALOR um income existente pelo seu ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Income alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Income não encontrado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @PutMapping("/{id}/edit-amount")
    public ResponseEntity<Object> editIncomeAmount(@PathVariable Long id, @RequestParam double newAmount) {
        try {
            Income income = incomeService.editIncomeAmount(id, newAmount);
            return ResponseEntity.ok(income);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
