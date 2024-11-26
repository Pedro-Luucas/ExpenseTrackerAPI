package com.pedrao.ExpenseTracker.repository;

import com.pedrao.ExpenseTracker.model.Expense;
import com.pedrao.ExpenseTracker.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // Buscar todas as Expenses associadas a um usuário
    List<Expense> findByUser(AppUser user);
}
