package com.pedrao.ExpenseTracker.service;

import com.pedrao.ExpenseTracker.dto.NewIncomeRequest;
import com.pedrao.ExpenseTracker.model.AppUser;
import com.pedrao.ExpenseTracker.model.Income;
import com.pedrao.ExpenseTracker.repository.AuthRepository;
import com.pedrao.ExpenseTracker.repository.IncomeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    // Buscar todos os Incomes de um usuário
    public List<Income> findAllIncomes(String username) {
        AppUser user = authRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        return incomeRepository.findByUser(user);
    }

    // Buscar um Income pelo ID
    public Income findIncomeById(Long id) {
        return incomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Income não encontrado!"));
    }

    // Criar um novo Income
    public Income createIncome(NewIncomeRequest incomeRequest) {
        AppUser user = authRepository.findByUsername(appUserService.getName())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        appUserService.addBalance((float) incomeRequest.getAmount());

        Income income = new Income();
        income.setAmount(incomeRequest.getAmount());
        income.setName(incomeRequest.getName());
        income.setSource(incomeRequest.getSource());
        income.setDescription(incomeRequest.getDescription());

        LocalDateTime time = LocalDateTime.now();
        income.setDate(time);

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

        return incomeRepository.save(income); // Salva as alterações
    }

    // Editar Descricao de um income
    public Income editIncomeDescription(Long id, String description) {
        Income income = incomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("income não encontrado!"));

        // Atualiza a descrição do income
        income.setDescription(description);

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

        return incomeRepository.save(income); // Salva as alterações
    }
}

