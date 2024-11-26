package com.pedrao.ExpenseTracker.service;

import com.pedrao.ExpenseTracker.model.AppUser;
import com.pedrao.ExpenseTracker.model.Expense;
import com.pedrao.ExpenseTracker.repository.AuthRepository;
import com.pedrao.ExpenseTracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final AuthRepository authRepository;
    private final AppUserService appUserService;

    public ExpenseService(ExpenseRepository expenseRepository, AuthRepository authRepository, AppUserService appUserService) {
        this.expenseRepository = expenseRepository;
        this.authRepository = authRepository;
        this.appUserService = appUserService;
    }

    // Buscar todas as expenses de um usuário
    public List<Expense> findAllExpenses(String username) {
        AppUser user = authRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        return expenseRepository.findByUser(user);
    }

    // Buscar uma expense pelo ID
    public Expense findExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("expense não encontrada!"));
    }

    // Criar uma nova expense
    public Expense createExpense(Expense expense, String username) {
        AppUser user = authRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        expense.setUser(user);
        return expenseRepository.save(expense);
    }

    // Deletar uma expense pelo ID
    public void deleteExpense(Long id) {
        Expense expense = findExpenseById(id);
        expenseRepository.delete(expense);
    }

    // Editar o nome de uma expense
    public Expense editExpenseName(Long id, String name, String username) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("expense não encontrada!"));

        // Atualiza o nome da despesa
        expense.setName(name);

        return expenseRepository.save(expense); // Salva as alterações
    }

    // Editar Descricao de uma expense
    public Expense editExpenseDescription(Long id, String description, String username) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("expense não encontrada!"));

        // Atualiza a descrição da expense
        expense.setDescription(description);

        return expenseRepository.save(expense); // Salva as alterações
    }

    public Expense editExpenseAmount(Long id, double amount, String username) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("expense não encontrada!"));

        // Subtrai o valor antigo dessa Expense do balance do AppUser
        appUserService.subtractBalance(username, (float) expense.getAmount());

        // Atualiza o valor da Expense
        expense.setAmount(amount);

        // Adiciona o novo valor da Expense no balance do AppUser
        appUserService.addBalance(username, (float) expense.getAmount());

        return expenseRepository.save(expense); // Salva as alterações
    }
}

