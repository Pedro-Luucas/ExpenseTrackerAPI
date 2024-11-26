package com.pedrao.ExpenseTracker.controller;

import com.pedrao.ExpenseTracker.dto.NewIncomeRequest;
import com.pedrao.ExpenseTracker.model.Income;
import com.pedrao.ExpenseTracker.service.IncomeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense_tracker/incomes")
public class IncomeController {
    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    // Buscar todos os Incomes de um usuário
    @GetMapping
    public List<Income> getAllIncomes(@RequestParam String username) {
        return incomeService.findAllIncomes(username);
    }

    // Buscar um Income pelo ID
    @GetMapping("/{id}")
    public Income getIncomeById(@PathVariable Long id) {
        return incomeService.findIncomeById(id);
    }

    // Criar um novo Income
    @PostMapping
    public Income createIncome(@RequestBody NewIncomeRequest income) {
        return incomeService.createIncome(income);
    }

    // Deletar um Income pelo ID
    @DeleteMapping("/{id}")
    public void deleteIncome(@PathVariable Long id) {
        incomeService.deleteIncome(id);
    }

    // Editar o nome de um Income
    @PutMapping("/{id}/edit-name")
    public Income editIncomeName(@PathVariable Long id, @RequestParam String newName) {
        return incomeService.editIncomeName(id, newName);
    }

    // Editar a descrição de um Income
    @PutMapping("/{id}/edit-description")
    public Income editIncomeDescription(@PathVariable Long id, @RequestParam String newDescription) {
        return incomeService.editIncomeDescription(id, newDescription);
    }

    // Editar o valor de um Income
    @PutMapping("/{id}/edit-amount")
    public Income editIncomeAmount(@PathVariable Long id, @RequestParam double newAmount) {
        return incomeService.editIncomeAmount(id, newAmount);
    }
}

