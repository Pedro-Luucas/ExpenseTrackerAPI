package com.pedrao.ExpenseTracker.service;

import com.pedrao.ExpenseTracker.dto.NewIncomeRequest;
import com.pedrao.ExpenseTracker.exception.ResourceNotFoundException;
import com.pedrao.ExpenseTracker.model.AppUser;
import com.pedrao.ExpenseTracker.model.Income;
import com.pedrao.ExpenseTracker.repository.AuthRepository;
import com.pedrao.ExpenseTracker.repository.IncomeRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class IncomeService {
    private final IncomeRepository incomeRepository;
    private final AuthRepository authRepository;
    private final AppUserService appUserService;

    public IncomeService(IncomeRepository incomeRepository, AuthRepository authRepository, AppUserService appUserService) {
        this.incomeRepository = incomeRepository;
        this.authRepository = authRepository;
        this.appUserService = appUserService;
    }

    // Buscar todos os incomes do usuário autenticado
    public List<Income> findAllIncomes() {
        String username = appUserService.getName(); // Nome do usuário autenticado
        AppUser user = authRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));
        return incomeRepository.findByUser(user);
    }

    // Buscar um income pelo ID
    public Income findIncomeById(Long id) {
        Income income = incomeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Income não encontrado!"));

        // Verifica se o usuário autenticado é o dono do income
        String username = appUserService.getName();
        if (!income.getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("Você não tem permissão para acessar este recurso.");
        }

        return income;
    }

    // Criar um novo Income
    public Income createIncome(NewIncomeRequest incomeRequest) {
        String username = appUserService.getName();
        AppUser user = authRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado!"));

        appUserService.addBalance((float) incomeRequest.getAmount());

        Income income = new Income();
        income.setAmount(incomeRequest.getAmount());
        income.setName(incomeRequest.getName());
        income.setSource(incomeRequest.getSource());
        income.setDescription(incomeRequest.getDescription());

        LocalDateTime time = LocalDateTime.now();
        income.setDate(time);
        income.setLastEdited(time);

        income.setUser(user);
        return incomeRepository.save(income);
    }

    // Deletar um Income pelo ID
    public void deleteIncome(Long id) {

        Income income = findIncomeById(id);
        incomeRepository.delete(income);
    }

    // Editar o nome de um income
    public Income editIncomeName(Long id, String name) {
        Income income = incomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("income não encontrado!"));

        // Atualiza o nome do income
        income.setName(name);

        LocalDateTime time = LocalDateTime.now();
        income.setLastEdited(time);

        return incomeRepository.save(income); // Salva as alterações
    }

    // Editar Descricao de um income
    public Income editIncomeDescription(Long id, String description) {
        Income income = incomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("income não encontrado!"));

        // Atualiza a descrição do income
        income.setDescription(description);

        LocalDateTime time = LocalDateTime.now();
        income.setLastEdited(time);

        return incomeRepository.save(income); // Salva as alterações
    }

    // Editar valor de um income
    public Income editIncomeAmount(Long id, double amount) {
        Income income = incomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("income não encontrado!"));

        // Subtrai o valor antigo desse Income do balance do AppUser
        appUserService.subtractBalance((float) income.getAmount());

        // Atualiza o valor do Income
        income.setAmount(amount);

        // Adiciona o novo valor do Income no balance do AppUser
        appUserService.addBalance((float) income.getAmount());

        LocalDateTime time = LocalDateTime.now();
        income.setLastEdited(time);

        return incomeRepository.save(income); // Salva as alterações
    }
}

