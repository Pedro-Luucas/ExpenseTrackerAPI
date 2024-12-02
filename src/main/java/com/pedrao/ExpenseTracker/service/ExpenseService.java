package com.pedrao.ExpenseTracker.service;

import com.pedrao.ExpenseTracker.dto.NewExpenseRequest;
import com.pedrao.ExpenseTracker.model.AppUser;
import com.pedrao.ExpenseTracker.model.Expense;
import com.pedrao.ExpenseTracker.repository.AuthRepository;
import com.pedrao.ExpenseTracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public List<Expense> findAllExpenses() {
        AppUser user = authRepository.findByUsername(appUserService.getName())
                .orElseThrow(() -> new RuntimeException("usuário não encontrado!"));
        return expenseRepository.findByUser(user);
    }

    // Buscar uma expense pelo ID
    public Expense findExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("expense não encontrada!"));
    }

    // Criar uma nova expense
    public Expense createExpense(NewExpenseRequest expenseRequest) {
        AppUser user = authRepository.findByUsername(appUserService.getName())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        Expense expense = new Expense();

        appUserService.subtractBalance((float) expenseRequest.getAmount());

        expense.setName(expenseRequest.getName());
        expense.setDescription(expenseRequest.getDescription());
        expense.setAmount(expenseRequest.getAmount());
        LocalDateTime time = LocalDateTime.now();
        expense.setDate(time);

        expense.setUser(user);
        return expenseRepository.save(expense);
    }

    // Deletar uma expense pelo ID
    public void deleteExpense(Long id) {
        Expense expense = findExpenseById(id);
        appUserService.addBalance((float) expense.getAmount());
        expenseRepository.delete(expense);
    }

    // Editar o nome de uma expense
    public Expense editExpenseName(Long id, String name) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("expense não encontrada!"));

        // Atualiza o nome da expense
        expense.setName(name);

        return expenseRepository.save(expense); // Salva as alterações
    }

    // Editar Descricao de uma expense
    public Expense editExpenseDescription(Long id, String description) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("expense não encontrada!"));

        // Atualiza a descrição da expense
        expense.setDescription(description);

        return expenseRepository.save(expense); // Salva as alterações
    }

    public Expense editExpenseAmount(Long id, double amount) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("expense não encontrada!"));

        // Subtrai o valor antigo dessa Expense do balance do AppUser
        appUserService.subtractBalance((float) expense.getAmount());

        // Atualiza o valor da Expense
        expense.setAmount(amount);

        // Adiciona o novo valor da Expense no balance do AppUser
        appUserService.addBalance((float) expense.getAmount());

        return expenseRepository.save(expense); // Salva as alterações
    }
}

