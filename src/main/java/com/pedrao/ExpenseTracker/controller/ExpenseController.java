package com.pedrao.ExpenseTracker.controller;

import com.pedrao.ExpenseTracker.model.Expense;
import com.pedrao.ExpenseTracker.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense_tracker/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // Buscar todas as Expenses de um usuário
    @GetMapping
    public List<Expense> getAllExpenses(@RequestParam String username) {
        return expenseService.findAllExpenses(username);
    }

    // Buscar uma Expense pelo ID
    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseService.findExpenseById(id);
    }

    // Criar uma nova Expense
    @PostMapping
    public Expense createExpense(@RequestBody Expense expense, @RequestParam String username) {
        return expenseService.createExpense(expense, username);
    }

    // Deletar uma Expense pelo ID
    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }

    // Editar o nome de uma Expense
    @PutMapping("/{id}/edit-name")
    public Expense editExpenseName(@PathVariable Long id, @RequestParam String newName, @RequestParam String username) {
        return expenseService.editExpenseName(id, newName, username);
    }

    // Editar a descrição de uma Expense
    @PutMapping("/{id}/edit-description")
    public Expense editExpenseDescription(@PathVariable Long id, @RequestParam String newDescription, @RequestParam String username) {
        return expenseService.editExpenseDescription(id, newDescription, username);
    }

    // Editar o valor de uma Expense
    @PutMapping("/{id}/edit-amount")
    public Expense editExpenseAmount(@PathVariable Long id, @RequestParam double newAmount, @RequestParam String username) {
        return expenseService.editExpenseAmount(id, newAmount, username);
    }
}

