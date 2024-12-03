package com.pedrao.ExpenseTracker.service;

import com.pedrao.ExpenseTracker.model.AppUser;
import com.pedrao.ExpenseTracker.repository.AuthRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
    private final AuthRepository authRepository;

    public AppUserService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    // Adicionar ao balance do usuário
    public AppUser addBalance(float amount) {
        String username = getName();
        AppUser user = authRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        user.setBalance(user.getBalance() + amount); // Adiciona o valor ao balance
        return authRepository.save(user); // Salva as alterações
    }

    // Subtrair do balance do usuário
    public AppUser subtractBalance(float amount) {
        String username = getName();
        AppUser user = authRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        user.setBalance(user.getBalance() - amount); // Subtrai o valor do balance
        return authRepository.save(user); // Salva as alterações
    }

    // Obter o balance atual do usuário
    public Float getBalance() {
        String username = getName();
        AppUser user = authRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        return user.getBalance(); // Retorna o balance do usuário
    }


    public String getName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}

