package com.pedrao.ExpenseTracker.repository;

import com.pedrao.ExpenseTracker.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {
}
