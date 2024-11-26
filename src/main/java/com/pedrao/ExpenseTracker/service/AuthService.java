package com.pedrao.ExpenseTracker.service;

import com.pedrao.ExpenseTracker.dto.RegisterRequest;
import com.pedrao.ExpenseTracker.model.AppUser;
import com.pedrao.ExpenseTracker.repository.AuthRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthRepository authRepository, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterRequest registerUser(String username, String password) {
        if (authRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Usuario já existe!");
        }

        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setBalance((float) 0);
        authRepository.save(user);

        return new RegisterRequest(username, passwordEncoder.encode(password), 0f);
    }

    public AppUser findByUsername(String username) {
        return authRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));
    }
}
