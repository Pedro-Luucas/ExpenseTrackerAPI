package com.pedrao.ExpenseTracker.controller;

import com.pedrao.ExpenseTracker.dto.NewExpenseRequest;
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

    // Buscar todas as Expenses do usuário
    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.findAllExpenses();
    }

    // Buscar uma Expense pelo ID
    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseService.findExpenseById(id);
    }

    // Criar uma nova Expense
    @PostMapping
    public Expense createExpense(@RequestBody NewExpenseRequest expense) {
        return expenseService.createExpense(expense);
    }

    // Deletar uma Expense pelo ID
    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }

    // Editar o nome de uma Expense
    @PutMapping("/{id}/edit-name")
    public Expense editExpenseName(@PathVariable Long id, @RequestParam String newName) {
        return expenseService.editExpenseName(id, newName);
    }

    // Editar a descrição de uma Expense
    @PutMapping("/{id}/edit-description")
    public Expense editExpenseDescription(@PathVariable Long id, @RequestParam String newDescription) {
        return expenseService.editExpenseDescription(id, newDescription);
    }

    // Editar o valor de uma Expense
    @PutMapping("/{id}/edit-amount")
    public Expense editExpenseAmount(@PathVariable Long id, @RequestParam double newAmount) {
        return expenseService.editExpenseAmount(id, newAmount);
    }
}

