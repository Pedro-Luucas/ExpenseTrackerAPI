package com.pedrao.ExpenseTracker.repository;

import com.pedrao.ExpenseTracker.model.Income;
import com.pedrao.ExpenseTracker.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    // Buscar todos os Incomes associados a um usuário
    List<Income> findByUser(AppUser user);
}
