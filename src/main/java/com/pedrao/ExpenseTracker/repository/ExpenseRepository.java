package com.pedrao.ExpenseTracker.repository;

import com.pedrao.ExpenseTracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
