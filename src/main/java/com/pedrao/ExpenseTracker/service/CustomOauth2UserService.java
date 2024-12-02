package com.pedrao.ExpenseTracker.service;

import com.pedrao.ExpenseTracker.model.AppUser;
import com.pedrao.ExpenseTracker.repository.AuthRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    private final AuthRepository authRepository;

    public CustomOauth2UserService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oauthUser = super.loadUser(userRequest);

        String githubUsername = oauthUser.getAttribute("login");
        authRepository.findByUsername(githubUsername).orElseGet(() -> {
            AppUser newUser = new AppUser();
            newUser.setUsername(githubUsername);
            newUser.setPassword(""); // Não precisa de senha para OAuth2
            newUser.setBalance(0f);
            return authRepository.save(newUser);
        });

        return oauthUser;
    }
}
