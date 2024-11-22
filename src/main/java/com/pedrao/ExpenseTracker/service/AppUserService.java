package com.pedrao.ExpenseTracker.service;

import com.pedrao.ExpenseTracker.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    public UserDetails loadUserByUsername(String email)
        throws UsernameNotFoundException {
            return appUserRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException(
                            String.format("USER WITH %s EMAIL WAS NOT FOUND...", email)));
        }

}
