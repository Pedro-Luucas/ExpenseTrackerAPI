package com.pedrao.ExpenseTracker.service;

import com.pedrao.ExpenseTracker.model.AppUser;
import com.pedrao.ExpenseTracker.model.Expense;
import com.pedrao.ExpenseTracker.model.Income;
import com.pedrao.ExpenseTracker.repository.AuthRepository;
import com.pedrao.ExpenseTracker.repository.ExpenseRepository;
import com.pedrao.ExpenseTracker.repository.IncomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AuthRepository authRepository;
    private final ExpenseRepository expenseRepository;
    private final IncomeRepository incomeRepository;

    public AdminService(AuthRepository authRepository, ExpenseRepository expenseRepository, IncomeRepository incomeRepository) {
        this.authRepository = authRepository;
        this.expenseRepository = expenseRepository;
        this.incomeRepository = incomeRepository;
    }

    // Retorna todos os usuários
    public List<AppUser> getAllUsers() {
        return authRepository.findAll();
    }

    // -------------------------EXPENSES-------------------------
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(Long expenseId) {
        return expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense com ID " + expenseId + " não encontrada."));
    }

    // -------------------------INCOMES-------------------------
    public List<Income> getAllIncomes() {
        return incomeRepository.findAll();
    }

    public Income getIncomeById(Long incomeId) {
        return incomeRepository.findById(incomeId)
                .orElseThrow(() -> new RuntimeException("Income com ID " + incomeId + " não encontrada."));
    }

    // -------------------------METODOS-ADMIN-------------------------
    public AppUser updateUserBalance(Long userId, Float newBalance) {
        AppUser user = authRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + userId + " não encontrado."));
        user.setBalance(newBalance);
        return authRepository.save(user);
    }

    public void deleteUserById(Long userId) {
        if (!authRepository.existsById(userId)) {
            throw new RuntimeException("Usuário com ID " + userId + " não encontrado.");
        }
        authRepository.deleteById(userId);
    }
}
