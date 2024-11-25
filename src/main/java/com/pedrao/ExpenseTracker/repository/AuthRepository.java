package com.pedrao.ExpenseTracker.repository;

import com.pedrao.ExpenseTracker.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}