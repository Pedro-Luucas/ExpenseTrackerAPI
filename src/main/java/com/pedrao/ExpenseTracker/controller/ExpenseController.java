package com.pedrao.ExpenseTracker.controller;

import com.pedrao.ExpenseTracker.model.Expense;
import com.pedrao.ExpenseTracker.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.findAll();
    }

    @PostMapping
    public Expense createExpense(@RequestBody Expense expense) {
        return expenseService.save(expense);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteById(id);
    }
}
