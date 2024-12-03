package com.pedrao.ExpenseTracker.service;

import com.pedrao.ExpenseTracker.dto.RegisterRequest;
import com.pedrao.ExpenseTracker.model.AppUser;
import com.pedrao.ExpenseTracker.model.Role;
import com.pedrao.ExpenseTracker.repository.AuthRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthRepository authRepository, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterRequest registerUser(String username, String password, List<Role> roles) {
        if (authRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Usuário já existe!");
        }

        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setBalance(0f);
        user.setRoles(roles != null ? roles : Collections.singletonList(Role.USER)); // Define papéis padrão como USER
        authRepository.save(user);

        return new RegisterRequest(username, passwordEncoder.encode(password), 0f, roles);
    }
}
