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
    public AppUser addBalance(String username, float amount) {
        AppUser user = authRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        user.setBalance(user.getBalance() + amount); // Adiciona o valor ao balance
        return authRepository.save(user); // Salva as alterações
    }

    // Subtrair do balance do usuário
    public AppUser subtractBalance(String username, float amount) {
        AppUser user = authRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        user.setBalance(user.getBalance() - amount); // Subtrai o valor do balance
        return authRepository.save(user); // Salva as alterações
    }

    // Obter o balance atual do usuário
    public Float getBalance(String username) {
        AppUser user = authRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        return user.getBalance(); // Retorna o balance do usuário
    }

    // Verificar se o usuário está autenticado
    public boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }
}

